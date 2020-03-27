package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmallestDifferenceTest {
  private static SmallestDifference smallestDifference;

  @BeforeAll
  static void setUpOnce() {
    smallestDifference = new SmallestDifference();
  }

  @Test
  void bothEmptyList() {
    assertEquals(-1, smallestDifference.smallDifferencePair(new int[]{}, new int[]{}));
  }

  @Test
  void arrayAIsNull() {
    assertEquals(-1, smallestDifference.smallDifferencePair(null, new int[]{8, 6, 3, 2, 4, -5, -5, 7}));
  }

  @Test
  void arrayBIsNull() {
    assertEquals(-1, smallestDifference.smallDifferencePair(new int[]{-6, -6, -10, -9}, null));
  }

  @Test
  void arrayAIsEmpty() {
    assertEquals(-1, smallestDifference.smallDifferencePair(new int[]{}, new int[]{8, 6, 3, 2, 4, -5, -5, 7}));
  }

  @Test
  void arrayBIsEmpty() {
    assertEquals(-1, smallestDifference.smallDifferencePair(new int[]{-6, -6, -10, -9}, new int[]{}));
  }

  @Test
  void arrayASingleElement() {
    assertEquals(1, smallestDifference.smallDifferencePair(new int[]{9}, new int[]{8, 6, 3, 2, 4, -5, -5, 7}));
  }

  @Test
  void arrayBSingleElement() {
    assertEquals(15, smallestDifference.smallDifferencePair(new int[]{-6, -6, -10, -9}, new int[]{9}));
  }

  @Test
  void bothSingleElement() {
    assertEquals(15, smallestDifference.smallDifferencePair(new int[]{-6}, new int[]{9}));
  }

  @Test
  void bothArraySameElement() {
    assertEquals(0, smallestDifference.smallDifferencePair(new int[]{9}, new int[]{9}));
  }

  @Test
  void arrayAHasMoreElementsThanArrayB() {
    assertEquals(1, smallestDifference.smallDifferencePair(new int[]{-6, -6, -10, -9},
        new int[]{8, 6, 3, 2, 4, -5, -5, 7}));
  }

  @Test
  void arrayBHasMoreElementsThanArrayA() {
    assertEquals(3, smallestDifference.smallDifferencePair(new int[]{7, 8, -5, -1},
        new int[]{-8, -10}));
  }

  @Test
  void allNumberOfArrayAIsBiggerThanArrayB() {
    assertEquals(3, smallestDifference.smallDifferencePair(new int[]{7, 8, -5, -1}, new int[]{-8, -10}));
  }
}