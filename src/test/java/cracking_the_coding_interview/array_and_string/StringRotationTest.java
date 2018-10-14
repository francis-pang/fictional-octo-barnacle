package cracking_the_coding_interview.array_and_string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringRotationTest {
	private static StringRotation stringRotation;

	@BeforeAll
	static void setUpOnce() {
		stringRotation = new StringRotation();
	}

	@Test
	void sameString() {
		assertTrue(stringRotation.isStringRotationOfAnother("abcdefg", "abcdefg"));
	}

	@Test
	void differentLength() {
		assertFalse(stringRotation.isStringRotationOfAnother("abc", "abcdefg"));
	}

	@Test
	void sameAfterRotate() {
		assertTrue(stringRotation.isStringRotationOfAnother("waterbottle", "erbottlewat"));
	}

	@Test
	void differentAfterRotate() {
		assertFalse(stringRotation.isStringRotationOfAnother("waterbotwle", "erbottlewat"));
	}
}