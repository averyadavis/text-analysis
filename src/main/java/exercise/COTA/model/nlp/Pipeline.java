package exercise.COTA.model.nlp;

import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Pipeline {
	
	private static Properties properties;
	private static String annotators = "tokenize, ssplit, pos, parse, sentiment";
	private static StanfordCoreNLP stanfordCoreNLP;
	
	private Pipeline() {}

	public static StanfordCoreNLP getPipeline()
	{
		if(stanfordCoreNLP == null){
			properties = new Properties();
			properties.setProperty("annotators",annotators);
			
			stanfordCoreNLP = new StanfordCoreNLP(properties);
		}
		
		return stanfordCoreNLP;
		
	}
}
