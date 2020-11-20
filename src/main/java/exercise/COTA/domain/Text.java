package exercise.COTA.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exercise.COTA.DataProcessingException;

public class Text {
	private String paragraph;
	
	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public static Text fromJSON(final String s) throws DataProcessingException 
	{
		Text text = null;
		ObjectMapper mapper = new ObjectMapper();

        try {
			text = mapper.readValue(s, Text.class);
		} catch (JsonProcessingException e) {
			throw new DataProcessingException(e.getMessage());
		}
		
        return text;
        
	}
}
