package exercise.COTA.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import exercise.COTA.DataProcessingException;

public class TextTest {

	@Test
	public void willBuildFromJson() {
		String expected = "She sells sea shells by the sea shore.";
		String json = "{\"paragraph\":\"She sells sea shells by the sea shore.\"}";
		Text text = null;
		
		
		try {
			text = Text.fromJSON(json);
		} catch (DataProcessingException e) {
			fail("Exception thrown - " + e.getMessage());
		}
		
		assertNotNull(text);
		assertEquals(expected, text.getParagraph());
	}
	
	@Test
	@SuppressWarnings("unused")
	public void willThrowExceptionDataProcessing() {
		String json = "{\"paragraph\":She sells sea shells by the sea shore.\"}";
		Text text = null;
		
		try {
			text = Text.fromJSON(json);
			fail("Exception not thrown");
		} catch (DataProcessingException e) {
			assertNotNull(e.getMessage());		
		}
		
	}
}
