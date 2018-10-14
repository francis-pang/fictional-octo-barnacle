package cracking_the_coding_interview.array_and_string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneAwayTest {
	private OneAway oneAway;

	@BeforeEach
	void setUp() {
		oneAway = new OneAway();
	}

	@Test
	final void exactString() {
		assertEquals(true, oneAway.isOneAway("pale", "pale"));
	}

	@Test
	final void sameLengthFirstEdit() {
		assertEquals(true, oneAway.isOneAway("pale", "bale"));
	}

	@Test
	final void sameLengthLastEdit() {
		assertEquals(true, oneAway.isOneAway("pale", "pala"));
	}

	@Test
	final void sameLengthMiddleEdit() {
		assertEquals(true, oneAway.isOneAway("pale", "pare"));
	}

	@Test
	final void sameLengthDifferent() {
		assertEquals(false, oneAway.isOneAway("pale", "bear"));
	}

	@Test
	final void differenceByTwoLength() {
		assertEquals(false, oneAway.isOneAway("pale", "pa"));
	}

	@Test
	final void differenceBySixLength() {
		assertEquals(false, oneAway.isOneAway("pale", "strawberry"));
	}

	@Test
	final void sameInsertFirstLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "ale"));
	}

	@Test
	final void sameInsertMiddleLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "ple"));
	}

	@Test
	final void sameInsertLastLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "pal"));
	}

	@Test
	final void differentInsertion() {
		assertEquals(false, oneAway.isOneAway("pale", "pla"));
	}

	@Test
	final void sameRemoveFirstLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "apale"));
	}

	@Test
	final void sameRemoveMiddleLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "palte"));
	}

	@Test
	final void sameRemoveLastLetter() {
		assertEquals(true, oneAway.isOneAway("pale", "palef"));
	}

	@Test
	final void differentRemove() {
		assertEquals(false, oneAway.isOneAway("pale", "parel"));
	}

}