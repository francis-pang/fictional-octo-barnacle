package crackingthecodinginterview.arrayandstring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCompressionTest {
	private static StringCompression stringCompression;

	@BeforeAll
	static void setUp() {
		stringCompression = new StringCompression();
	}

	@Test
	void compressSingleLength() {
		assertEquals("D", stringCompression.compressString("D"));
	}

	@Test
	void compressEmptyString() {
		assertEquals("", stringCompression.compressString(""));
	}

	@Test
	void compressMixedCase() {
		assertEquals("a3b4B6",stringCompression.compressString("aaabbbbBBBBBB"));
	}

	@Test
	void compressNoCompressionNeeded() {
		assertEquals("abbcdefff", stringCompression.compressString("abbcdefff"));
	}

	@Test
	void compressLongString() {
		assertEquals("a3b4B6a1t1c5G3",stringCompression.compressString("aaabbbbBBBBBBatcccccGGG"));
	}
}