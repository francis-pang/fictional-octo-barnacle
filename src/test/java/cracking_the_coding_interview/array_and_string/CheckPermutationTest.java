package cracking_the_coding_interview.array_and_string;

import static org.junit.jupiter.api.Assertions.*;

import cracking_the_coding_interview.array_and_string.CheckPermutation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckPermutationTest {

	private static CheckPermutation checkPermutation;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		checkPermutation = new CheckPermutation();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSameLengthNotPermutation() {
		assertEquals(false, checkPermutation.isStringPermutable("ABCDE", "EFGHG"));
	}

	@Test
	void testSameLengthIsExactMatch() {
		assertEquals(true, checkPermutation.isStringPermutable("ABCDE", "ABCDE"));
	}
	
	@Test
	void testSameLengthIsPermutation() {
		assertEquals(true, checkPermutation.isStringPermutable("ABCDE", "CADEB"));
	}
	
	@Test
	void testDifferentLength() {
		assertEquals(false, checkPermutation.isStringPermutable("ABCDE", "ABC"));
	}
	
	@Test
	void testOneCharacterIsPermutation() {
		assertEquals(true, checkPermutation.isStringPermutable("A", "A"));
	}
	
	@Test
	void testOneCharacterNotPermutation() {
		assertEquals(false, checkPermutation.isStringPermutable("A", "B"));
	}
	
	@Test
	void testEmptyString() {
		assertEquals(true, checkPermutation.isStringPermutable("", ""));
	}
	
	@Test
	void testNullString() {
		assertEquals(false, checkPermutation.isStringPermutable("", null));
	}
}
