package crackingthecodinginterview.bitmanipulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlipBitToWinTest {
  static FlipBitToWin flipBitToWin;

  @BeforeAll
  static void setUp() {
    flipBitToWin = new FlipBitToWin();
  }

  @Test
  void testSingleOne() {
    assertEquals(1, flipBitToWin.longestSequenceOfOnes(1));
  }

  @Test
  void testSingleZero() {
    assertEquals(1, flipBitToWin.longestSequenceOfOnes(0));
  }

  @Test
  void testAllOnes() {
    assertEquals(5, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("11111", 2)));
  }

  @Test
  void testEndsWithOne() {
    assertEquals(4, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("1011", 2)));
  }

  @Test
  void testEndsWithZero() {
    assertEquals(4, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("10110", 2)));
  }

  @Test
  void testDoubleZero() {
    assertEquals(3, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("10011", 2)));
  }

  @Test
  void testDoubleZeroEndZero() {
    assertEquals(3, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("100110", 2)));
  }

  @Test
  void testComplexCase() {
    assertEquals(4, flipBitToWin.longestSequenceOfOnes(Integer.parseInt("10011010100110", 2)));
  }
}