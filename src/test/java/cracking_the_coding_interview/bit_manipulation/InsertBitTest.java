package cracking_the_coding_interview.bit_manipulation;

import static org.junit.jupiter.api.Assertions.*;

import cracking_the_coding_interview.bit_manipulation.InsertBit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InsertBitTest {
	static InsertBit insertBit;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		insertBit = new InsertBit();
	}

	@Test
	final void testInsertBit3BitNInsertTo1() {
		assertEquals(6, insertBit.insertBit(1, 4, 1, 1));
	}
	
	@Test
	final void testInsertBit4BitInsert12() {
		assertEquals(12, insertBit.insertBit(2, 8, 1, 2));
	}

}
