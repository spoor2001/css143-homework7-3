/**
 * Spoorthi Gowda
 * 
 * Homeowork 7 part 3
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MyAutoCompleteSearchTest {

	@Test
	public void searchTest() {
		List<String> wordList = Arrays.asList("hello", "high", "seattle", "seatac", "see", "hollow", "how","spoorthi");
		
		AutoCompleteSearch mas = new MyAutoCompleteSearch(wordList);
		
		//assertEquals(Arrays.asList("hello", "high", "hollow", "how"), mas.search("h"));
		assertEquals(Arrays.asList("seattle", "seatac", "see","spoorthi"), mas.search("s"));
		assertEquals(Arrays.asList("seattle", "seatac", "see"), mas.search("se"));
		assertEquals(Arrays.asList("seattle", "seatac"), mas.search("sea"));
		assertEquals(Arrays.asList("hollow", "how"), mas.search("ho"));
		assertEquals(Arrays.asList("hello", "high","hollow", "how"), mas.search("h"));
		assertEquals(Arrays.asList("spoorthi"), mas.search("sp"));
		assertEquals(Arrays.asList(), mas.search("xyz"));
	}
}