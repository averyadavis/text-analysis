package exercise.COTA;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import exercise.COTA.domain.Analysis;
import exercise.COTA.domain.Text;
import exercise.COTA.model.TextAnalyzer;

public class App 
{
    public static void main( String[] args )
    {
    	if(args.length != 1) {
            System.out.println( usage() );
            System.exit(1);
     	}

    	TextAnalyzer analyzer = new TextAnalyzer();
    	Analysis analysis = null;
    	    	
    	try {
    		
    		analysis = analyzer.process(textFromFile(args[0]));
    		
    	} catch (DataProcessingException e) {
			System.err.println("Invalid json in file " + args[0] + ", must be in form {\"paragraph\":\"Some text to be analyzed.\"}");
			System.exit(2);   		
    	} catch (IOException e) {
			System.err.println("Error accessing file " + args[0] + ": " + e.getMessage());
			System.exit(3);
    	}
    	
		try {
			System.out.println(analysis.toJSON());
		} catch (DataProcessingException e) {
			System.err.println("Error rendering analysis : " + e.getMessage());
			System.exit(4);
		}
    	
    }

    private static Text textFromFile(String file) throws IOException, DataProcessingException {
    	StringBuffer buf = new StringBuffer();

        Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(l -> buf.append(l));
                
		return Text.fromJSON(buf.toString());
	}

	private static String usage() {
    	return "usage: java -jar textanalysis.jar file";
    }


}
