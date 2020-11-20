package exercise.COTA.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import exercise.COTA.DataProcessingException;

public class AnalysisTest {
	
	@Test
	public void willRenderJSON() {
		String expected = "{\"timeDuration\":8,\"femaleIdentified\":\"other\",\"sentiment\":\"mixed\"}";
		String json = null;
		
		Analysis analysis = new Analysis();
		
		analysis.setTimeDuration(8);
		analysis.setFemaleIdentified("other");
		analysis.setSentiment("mixed");
		
		try {
			json = analysis.toJSON();
		} catch (DataProcessingException e) {
			fail("Exception thrown - " + e.getMessage());		
		}
		
		assertEquals(expected, json);
		
	}

}
