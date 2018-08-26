package cracking_the_coding_interview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class UrlifyTest {
	
	private static Urlify urlify;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		urlify = new Urlify();
	}

	@Test
	void testReplaceEmptyString() {
		char[] emptyCharArray = new char[0];
		char[] actualReplacedCharArray = urlify.replaceEmptySpacesInString(emptyCharArray, 0);
		assertTrue(Arrays.equals(emptyCharArray, actualReplacedCharArray));
	}
	
	@Test
	void testReplaceSingleCharacterStringWithoutSpace() {
		char[] singleCharacterArray = "c".toCharArray();
		char[] actualReplacedCharArray = urlify.replaceEmptySpacesInString(singleCharacterArray, 1);
		assertTrue(Arrays.equals(singleCharacterArray, actualReplacedCharArray));
	}

	@Test
	void testReplaceSingleCharacterStringWithSpace() {
		char[] singleCharacterArray = "       b".toCharArray();
		char[] actualReplacedCharArray = urlify.replaceEmptySpacesInString(singleCharacterArray, 1);
		assertTrue(Arrays.equals("b".toCharArray(), actualReplacedCharArray));
	}
	
	@Test
	void testReplaceMultipleCharacterStringWithSpace() {
		char[] multipleCharactersArray = new char[18];
		char[] questionCharactersArray = "Mr John Smith   ".toCharArray();
		// Copy question to an bigger enough array
		for (int i = 0; i < questionCharactersArray.length; i++) {
			multipleCharactersArray[i] = questionCharactersArray[i];
		}
		char[] actualReplacedCharArray = urlify.replaceEmptySpacesInString(multipleCharactersArray, 13);
		// Because Arrays.equals doesn't work, I have to loop manually
		char[] expectedCharacterArray = "Mr%20John%20Smith".toCharArray();
		for (int i = 0; i < expectedCharacterArray.length; i++) {
			assertEquals(expectedCharacterArray[i], actualReplacedCharArray[i]);
		}
	}
	
	//Test no space case also
	@Test
	void testReplaceMultipleCharacterStringWithoutSpace() {
		char[] multipleCharactersArray = "NoSpace".toCharArray();
		char[] actualReplacedCharArray = urlify.replaceEmptySpacesInString(multipleCharactersArray, 7);
		assertTrue(Arrays.equals(multipleCharactersArray, actualReplacedCharArray));
	}	
}
