package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {
  private RomanToInteger romanToInteger = new RomanToInteger();

  @Test
  void romanToInt_null() {
    assertEquals(Integer.MIN_VALUE, romanToInteger.romanToInt(null));
  }

  @Test
  void romanToInt_emptyString() {
    assertEquals(Integer.MIN_VALUE, romanToInteger.romanToInt(""));
  }

  @Test
  void romanToInt_1() {
    assertEquals(1, romanToInteger.romanToInt("I"));
  }

  @Test
  void romanToInt_previousCharacterMatter() {
    assertEquals(4, romanToInteger.romanToInt("IV"));
  }

  @Test
  void romanToInt_normalCharacterAfterMultiple() {
    assertEquals(6, romanToInteger.romanToInt("VI"));
  }

  @Test
  void romanToInt_minusAtTenLevel() {
    assertEquals(1042, romanToInteger.romanToInt("MXLII"));
  }

  @Test
  void romanToInt_addAtTens() {
    assertEquals(27, romanToInteger.romanToInt("XXVII"));
  }
}