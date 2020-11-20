package exercise.COTA.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exercise.COTA.domain.Analysis;
import exercise.COTA.domain.Text;

public class TextAnalyzerTest {

	TextAnalyzer analyzer = new TextAnalyzer();
	
	@Test
	public void willIdentifyFemalePronouns() {
		String expected = "female";
		String str = "She sells sea shells by the sea shore.";
	
		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getFemaleIdentified());
		
	}
	
	@Test
	public void willIgnoreMalePronouns() {
		String expected = "other";
		String str = "Jack and Jill went up the hill. Jack fell down and broke his crown.";
	
		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getFemaleIdentified());
		
	}
	
	@Test
	public void willIgnoreSingleDate() {
		Long expected = new Long(0);
		String str = "John downloaded the Pokemon Go app on 07/15/2016.";

		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getTimeDuration());

	}
	
	@Test
	public void willCalculateDurationForTwoDates() {
		Long expected = new Long(8);
		String str = "John downloaded the Pokemon Go app on 07/15/2016. By 07/22/2016, he was on level 24.";

		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getTimeDuration());
		
	}

	@Test
	public void willCalculateDurationForMultipleDates() {
		Long expected = new Long(6857);
		String str = "In the text, we see three dates - 07/07/1996, 08/12/2010 and 04/15/2015.";

		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getTimeDuration());
		
	}
	
	@Test
	public void willProperlyOrderMultipleDates() {
		Long expected = new Long(6857);
		String str = "In the text, we see three dates - 08/12/2010 and 04/15/2015 as well as 07/07/1996.";

		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getTimeDuration());
		
	}

	@Test
	public void willDetectMixedSentiment() {
		String expected = "mixed";
		String str = "John downloaded the Pokemon Go app on 07/15/2016. " +
		             "By 07/22/2016, he was on level 24. Initially, he was very happy " + 
				     "with the app. However, he soon became very disappointed with the app " +
		             "because it was crashing very often. As soon as he reached level 24, " +
				     "he uninstalled";

		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getSentiment());

	}
	
	@Test
	public void willDetectPositiveSentiment() {
		String expected = "positive";
		String str = "Hua Min liked playing tennis. She first started playing on her 8th " +
		             "birthday - 07/07/1996. Playing tennis always made her happy. She won " +
				     "her first tournament on 08/12/2010. However, on 04/15/2015 when she " +
		             "was playing at the Flushing Meadows, she had a serious injury and had " +
				     "to retire from her tennis career.";
				
		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getSentiment());
		
	}

	@Test
	public void willDetectNegativeSentiment() {
		String expected = "negative";
		String str = "One of the most overused memes on the internet is the scene " +
		             "from StarWars Episode 2 where Anakin Skywalker says - I don't " +
				     "like sand,it's coarse and rough and irritating and it " + 
		             "gets everywhere.";
		
		Text text = new Text();
		text.setParagraph(str);
		
		Analysis analysis = analyzer.process(text);
		
		assertEquals(expected, analysis.getSentiment());
				
	}
}
