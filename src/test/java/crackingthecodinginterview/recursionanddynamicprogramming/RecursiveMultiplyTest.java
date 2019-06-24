package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveMultiplyTest {
  private static RecursiveMultiply recursiveMultiply;

  @BeforeAll
  static void setUpOnce() {
    recursiveMultiply = new RecursiveMultiply();
  }

  /**
   * input:
   * Testing go on edge and extreme value
   * Question states positive integers
   * First  number - Integer - can test Integer.MAX_VALUE, 1, 6
   * Second number - Integer - can test Integer.MAX_VALUE, 1, 6
   * <p>
   * Combination testing
   * 1 * 1
   * 1 * 6
   * 6 * 1
   * 6 * 5
   * Integer.MAX_VALUE * 1
   * 1 * Integer.MAX_VALUE
   * Integer.MAX_VALUE * 6
   * 6 * Integer.MAX_VALUE
   */

  @Test
  void multiplyRecursively_doubleOne() {
    assertEquals(1, recursiveMultiply.multiplyRecursively(1, 1));
  }

  @Test
  void multiplyRecursively_firstNumberOne() {
    assertEquals(6, recursiveMultiply.multiplyRecursively(1, 6));
  }

  @Test
  void multiplyRecursively_secondNumberOne() {
    assertEquals(6, recursiveMultiply.multiplyRecursively(6, 1));
  }

  @Test
  void multiplyRecursively_arbitraryPositive() {
    assertEquals(30, recursiveMultiply.multiplyRecursively(5, 6));
  }

  @Test
  void multipleRecursively_firstNumberMaxSecondNumberOne() {
    assertEquals(Integer.MAX_VALUE, recursiveMultiply.multiplyRecursively(Integer.MAX_VALUE, 1));
  }

  @Test
  void multipleRecursively_secondNumberMaxFirstNumberOne() {
    assertThrows(StackOverflowError.class, () -> recursiveMultiply.multiplyRecursively(1, Integer.MAX_VALUE));
  }

  @Test
  void multipleRecursively_firstNumberMax() {
    // The answer is -6, because of the overflowing integer
    assertEquals(-6, recursiveMultiply.multiplyRecursively(Integer.MAX_VALUE, 6));
  }

  @Test
  void multipleRecursively_seconNumberMax() {
    assertThrows(StackOverflowError.class, () -> recursiveMultiply.multiplyRecursively(6, Integer.MAX_VALUE));
  }
}