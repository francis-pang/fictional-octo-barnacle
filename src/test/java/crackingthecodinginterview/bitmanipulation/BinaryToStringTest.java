package crackingthecodinginterview.bitmanipulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryToStringTest {
	private static BinaryToString binaryToString;
	@BeforeAll
	static void init() {
		binaryToString = new BinaryToString();
	}

	@Test
	// Test the most basic case 0.5 (1/2 or half), because only require loop once
	void testBaseCase5() {
		assertEquals("0.1",binaryToString.binaryToString(0.5));
	}

	@Test
	// This is the base case for infinite binary
	void testBaseRecurringCase() {
		assertEquals("ERROR", binaryToString.binaryToString(0.1));
	}

	@Test
	void testNegativeNumber() {
		assertEquals("ERROR", binaryToString.binaryToString(-0.3));
	}

	@Test
	void testOutOfBoundNumber() {
		assertEquals("ERROR", binaryToString.binaryToString(1.1));
	}


}
