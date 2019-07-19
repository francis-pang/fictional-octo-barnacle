package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountOf2sTest {
  private static CountOf2s countOf2s;

  @BeforeAll
  static void setUpOnce() {
    countOf2s = new CountOf2s();
  }

  @Test
  void countTwosFromZeroTo_Zero() {
    assertEquals(0, countOf2s.countTwosFromZeroTo(0));
  }

  @Test
  void countTwosFromZeroTo_Negative() {
    assertEquals(0, countOf2s.countTwosFromZeroTo(-5));
  }

  @Test
  void countTwosFromZeroTo_One() {
    assertEquals(countOf2s.count2sInRange(1), countOf2s.countTwosFromZeroTo(1));
  }

  @Test
  void countTwosFromZeroTo_Two() {
    assertEquals(countOf2s.count2sInRange(2), countOf2s.countTwosFromZeroTo(2));
  }

  @Test
  void countTwosFromZeroTo_Three() {
    assertEquals(countOf2s.count2sInRange(3), countOf2s.countTwosFromZeroTo(3));
  }

  @Test
  void countTwosFromZeroTo_32() {
    assertEquals(countOf2s.count2sInRange(32), countOf2s.countTwosFromZeroTo(32));
  }

  @Test
  void countTwosFromZeroTo_132() {
    assertEquals(countOf2s.count2sInRange(132), countOf2s.countTwosFromZeroTo(132));
  }

  @Test
  void countTwosFromZeroTo_22() {
    assertEquals(countOf2s.count2sInRange(22), countOf2s.countTwosFromZeroTo(22));
  }

  @Test
  void countTwosFromZeroTo_222() {
    assertEquals(countOf2s.count2sInRange(222), countOf2s.countTwosFromZeroTo(222));
  }
}