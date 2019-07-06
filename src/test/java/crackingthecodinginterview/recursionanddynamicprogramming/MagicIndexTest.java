package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagicIndexTest {
  private static MagicIndex magicIndex;

  @BeforeAll
  static void setUpOnce() {
    magicIndex = new MagicIndex();
  }

  @Test
  void locateMagicIndex_foundIn2ndHalf() {
    int[] testArray = {-6, -2, 0, 1, 4, 9, 10, 13};
    assertEquals(4, magicIndex.locateMagicIndex(testArray));
  }

  @Test
  void locateMagicIndex_notFound() {
    int[] testArray = {-6, -2, 0, 1, 5, 9, 10, 13};
    assertEquals(Integer.MIN_VALUE, magicIndex.locateMagicIndex(testArray));
  }

  @Test
  void locateMagicIndex_foundIn1stHalf() {
    int[] testArray = {-6, -2, 2, 5, 9, 10, 11, 13};
    assertEquals(2, magicIndex.locateMagicIndex(testArray));
  }

  @Test
  void locateMagicIndexAllowDuplicate_foundIn2ndHalf() {
    int[] testArray = {-1, -1, -1, -1, -1, 6, 6};
    assertEquals(6, magicIndex.locateMagicIndexAllowDuplicate(testArray));
  }

  @Test
  void locateMagicIndexAllowDuplicate_foundInLast() {
    int[] testArray = {-1, -1, -1, -1, -1, 6, 6, 6};
    assertEquals(6, magicIndex.locateMagicIndexAllowDuplicate(testArray));
  }

  @Test
  void locateMagicIndexAllowDuplicate_foundIn1stHalf() {
    int[] testArray = {-1, -1, 2, 4, 4, 4, 4};
    assertEquals(2, magicIndex.locateMagicIndexAllowDuplicate(testArray));
  }
}