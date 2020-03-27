package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ShortestSuperSequenceTest {
  private static ShortestSuperSequence shortestSupersequence;
  private final int[] invalidRange = new int[]{};

  @BeforeAll
  static void setUp() {
    shortestSupersequence = new ShortestSuperSequence();
  }

  @Test
  void getShortestSuperSequence_arrayANull() {
    assertArrayEquals(invalidRange, shortestSupersequence.findShortestSuperSequence(null, new int[]{1}));
  }

  @Test
  void getShortestSuperSequence_arrayBNull() {
    assertArrayEquals(invalidRange, shortestSupersequence.findShortestSuperSequence(new int[]{1}, null));
  }

  @Test
  void getShortestSuperSequence_arrayAEmpty() {
    assertArrayEquals(invalidRange, shortestSupersequence.findShortestSuperSequence(new int[]{}, new int[]{1, 2}));
  }

  @Test
  void getShortestSuperSequence_arrayBEmpty() {
    assertArrayEquals(invalidRange, shortestSupersequence.findShortestSuperSequence(new int[]{1}, new int[]{}));
  }

  @Test
  void getShortestSuperSequence_arrayBShorterThanArrayA() {
    assertArrayEquals(invalidRange, shortestSupersequence.findShortestSuperSequence(new int[]{1, 2}, new int[]{1}));
  }

  @Test
  void getShortestSuperSequence_arrayBSingleElementFoundAtFirstElement() {
    assertArrayEquals(new int[]{0, 0},
        shortestSupersequence.findShortestSuperSequence(new int[]{1}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSuperSequence_arrayBSingleElementFoundAtMiddleElement() {
    assertArrayEquals(new int[]{2, 2},
        shortestSupersequence.findShortestSuperSequence(new int[]{3}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSuperSequence_arrayBSingleElementFoundAtLastElement() {
    assertArrayEquals(new int[]{4, 4},
        shortestSupersequence.findShortestSuperSequence(new int[]{5}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSuperSequences_arrayBFoundInAllRepeat() {
    assertArrayEquals(new int[]{0, 2},
        shortestSupersequence.findShortestSuperSequence(new int[]{1, 2, 3},
            new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3}));
  }

  @Test
  void getShortestSuperSequence_arrayBFoundOnlyLastOnce() {
    assertArrayEquals(new int[]{6, 8},
        shortestSupersequence.findShortestSuperSequence(new int[]{1, 2, 3},
            new int[]{9, 10, 11, 12, 13, 14, 1, 2, 3}));
  }

  @Test
  void getShortestSuperSequences_arrayBFoundInFirstPartRepeatDifferentOrder() {
    assertArrayEquals(new int[]{0, 2},
        shortestSupersequence.findShortestSuperSequence(new int[]{3, 1, 2},
            new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3}));
  }

  @Test
  void getShortestSuperSequences_arrayBFoundInFirstPartRepeat() {
    assertArrayEquals(new int[]{7, 10},
        shortestSupersequence.findShortestSuperSequence(new int[]{1, 5, 9},
            new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}));
  }
}