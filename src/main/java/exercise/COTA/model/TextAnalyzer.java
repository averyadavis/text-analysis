package exercise.COTA.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import exercise.COTA.domain.Analysis;
import exercise.COTA.domain.Text;
import exercise.COTA.model.nlp.Pipeline;

public class TextAnalyzer {
	
    private static String dateregex = "\\d{2}/\\d{2}/\\d{4}";
    private static Pattern datepattern = Pattern.compile(dateregex);
    private static DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
    private static String[] femalePronouns = new String[]{"she", "her", "hers"};

    private static StanfordCoreNLP pipeline = Pipeline.getPipeline();
    private CoreDocument doc;
	
    private SortedSet<LocalDate> dates = new TreeSet<>();
    private Set<String> pronouns = new HashSet<>();
    private Set<String> sentiments = new HashSet<>();

	public Analysis process(Text t) {

		doc = new CoreDocument(t.getParagraph());
		pipeline.annotate(doc);
		
		for(CoreSentence sentence : doc.sentences()){
			collectDates(sentence);
			collectPronouns(sentence);
			collectSentiments(sentence);
		}
		
		Analysis analysis = new Analysis();
		
		analysis.setTimeDuration(analyzeDates());
		analysis.setFemaleIdentified(analyzePronouns());
		analysis.setSentiment(analyzeSentiments());

		return analysis;
	}

	private String analyzeSentiments() {
		int positive = 0, neutral = 0, negative = 0;

		for(String sentiment : sentiments) {
			switch(sentiment)
			{
			case "Very positive":
				positive += 2;
				break;
			case "Positive":
				positive += 1;
				break;
			case "Neutral":
				neutral += 1;
				break;
			case "Negative":
				negative += 1;
				break;
			case "Very negative":
				negative += 2;
				break;
			}
						
		}
		
		sentiments.clear();
		
		if((positive >= neutral) && (positive > negative))
			return "positive";
		
		if((negative >= neutral) && (negative > positive))
			return "negative";
		
		if((positive >= neutral) && (negative >= neutral))
			return "mixed";
		
		return "unknown";
		
	}

	private String analyzePronouns() {
		String femaleIdentified = 
			Collections.disjoint(pronouns, Arrays.asList(femalePronouns)) 
					? "other"
					: "female";		
		
		pronouns.clear();
		
		return femaleIdentified;
	}

	private long analyzeDates() {
		if(dates.size() < 2) {
			dates.clear();
			return 0;			
		}
		
		LocalDate first = dates.first();
		LocalDate last = dates.last();
		
		dates.clear();
		
		return ChronoUnit.DAYS.between(first,last) + 1L;
	}

	private void collectSentiments(CoreSentence sentence) {
		sentiments.add(sentence.sentiment());		
	}

	private void collectPronouns(CoreSentence sentence) {
		for(CoreLabel label : sentence.tokens()) {
			if(label.get(CoreAnnotations.PartOfSpeechAnnotation.class).startsWith("PRP"))
				pronouns.add(label.originalText().toLowerCase());
		}
	}

	private void collectDates(CoreSentence sentence) {
	    Matcher matcher = datepattern.matcher(sentence.text());
	    
	    while (matcher.find()) {
	        try {
	        	LocalDate date = LocalDate.parse(matcher.group(), dateformat);
				dates.add(date);
			} catch (DateTimeParseException e) {
				System.err.println("Failed to parse token " + matcher.group() + " as valid date, will be ignored in duration calculation" );
			}
	    }
		
	}
	
}
