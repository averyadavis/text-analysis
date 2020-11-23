package exercise.COTA.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exercise.COTA.DataProcessingException;

public class Analysis {
	private Long timeDuration;
	private String femaleIdentified;
	private String sentiment;
	
	public Long getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(long timeDuration) {
		this.timeDuration = timeDuration;
	}
	public String getFemaleIdentified() {
		return femaleIdentified;
	}
	public void setFemaleIdentified(String femaleIdentified) {
		this.femaleIdentified = femaleIdentified;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public String toJSON() throws DataProcessingException  
	{
		String json = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			json = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new DataProcessingException(e.getMessage());
		}
		
		return json;
	}
	
}
