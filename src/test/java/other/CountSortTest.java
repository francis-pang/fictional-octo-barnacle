package other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CountSortTest {
  @Test
  void sort_null() {
    int[] nullArray = null;
    CountSort.sort(nullArray);
    assertNull(nullArray);
  }

  @Test
  void sort_empty() {
    int[] emptyArray = {};
    CountSort.sort(emptyArray);
    assertArrayEquals(new int[]{}, emptyArray);
  }

  @Test
  void sort_singleElement() {
    int[] singleElementArray = {77};
    CountSort.sort(singleElementArray);
    assertArrayEquals(new int[]{77}, singleElementArray);
  }

  @Test
  void sort_multipleElements() {
    int[] multipleElementsArray = {247, 46, 197, 75, 127, 42, 238, 10, 127, 176};
    CountSort.sort(multipleElementsArray);
    assertArrayEquals(new int[]{10, 42, 46, 75, 127, 127, 176, 197, 238, 247}, multipleElementsArray);
  }

  @Test
  void sort_alreadySorted() {
    int[] alreadySortedArray = {49, 105, 112, 141, 152, 163, 192, 207, 243, 246};
    CountSort.sort(alreadySortedArray);
    assertArrayEquals(new int[]{49, 105, 112, 141, 152, 163, 192, 207, 243, 246}, alreadySortedArray);
  }

  @Test
  void sort_reverseSorted() {
    int[] reverseSortedArray = {186, 176, 169, 140, 114, 85, 72, 71, 53, 20};
    CountSort.sort(reverseSortedArray);
    assertArrayEquals(new int[]{20, 53, 71, 72, 85, 114, 140, 169, 176, 186}, reverseSortedArray);
  }

  @Test
  void sort_boundaryLow() {
    int[] arrayWithLowBoundary = {66, 1};
    CountSort.sort(arrayWithLowBoundary);
    assertArrayEquals(new int[]{1, 66}, arrayWithLowBoundary);
  }

  @Test
  void sort_boundaryHigh() {
    int[] arrayWithLowBoundary = {255, 55};
    CountSort.sort(arrayWithLowBoundary);
    assertArrayEquals(new int[]{55, 255}, arrayWithLowBoundary);
  }
}