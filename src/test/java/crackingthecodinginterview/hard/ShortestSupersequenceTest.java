package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestSupersequenceTest {
  private static ShortestSupersequence shortestSupersequence;
  private final ShortestSupersequence.SuperSequenceRange invalidRange =
      new ShortestSupersequence.SuperSequenceRange(-1, -1);

  @BeforeAll
  static void setUp() {
    shortestSupersequence = new ShortestSupersequence();
  }

  @Test
  void getShortestSupersequence_arrayANull() {
    assertEquals(invalidRange, shortestSupersequence.getShortestSupersequence(null, new int[]{1}));
  }

  @Test
  void getShortestSupersequence_arrayBNull() {
    assertEquals(invalidRange, shortestSupersequence.getShortestSupersequence(new int[]{1}, null));
  }

  @Test
  void getShortestSupersequence_arrayAEmpty() {
    assertEquals(invalidRange, shortestSupersequence.getShortestSupersequence(new int[]{}, new int[]{1, 2}));
  }

  @Test
  void getShortestSupersequence_arrayBEmpty() {
    assertEquals(invalidRange, shortestSupersequence.getShortestSupersequence(new int[]{1}, new int[]{}));
  }

  @Test
  void getShortestSupersequence_arrayBShorterThanArrayA() {
    assertEquals(invalidRange, shortestSupersequence.getShortestSupersequence(new int[]{1, 2}, new int[]{1}));
  }

  @Test
  void getShortestSupersequence_arrayBSingleElementFoundAtFirstElement() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(0, 0),
        shortestSupersequence.getShortestSupersequence(new int[]{1}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSupersequence_arrayBSingleElementFoundAtMiddleElement() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(2, 2),
        shortestSupersequence.getShortestSupersequence(new int[]{3}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSupersequence_arrayBSingleElementFoundAtLastElement() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(4, 4),
        shortestSupersequence.getShortestSupersequence(new int[]{5}, new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void getShortestSupersequence_arrayBFoundInAllRepeast() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(0, 2),
        shortestSupersequence.getShortestSupersequence(new int[]{1, 2, 3}, new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3}));
  }

  @Test
  void getShortestSupersequence_arrayBFoundOnlyLastOnce() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(6, 8),
        shortestSupersequence.getShortestSupersequence(new int[]{1, 2, 3}, new int[]{9, 10, 11, 12, 13, 14, 1, 2, 3}));
  }

  @Test
  void getShortestSupersequence_arrayBFoundInFirstPartRepeastDifferentOrder() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(0, 2),
        shortestSupersequence.getShortestSupersequence(new int[]{3, 1, 2}, new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3}));
  }

  @Test
  void getShortestSupersequence_arrayBFoundInFirstPartRepeast() {
    assertEquals(new ShortestSupersequence.SuperSequenceRange(7, 10),
        shortestSupersequence.getShortestSupersequence(new int[]{1, 5, 9}, new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7
        }));
  }
}