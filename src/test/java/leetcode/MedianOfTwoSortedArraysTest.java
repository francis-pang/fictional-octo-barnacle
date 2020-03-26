package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianOfTwoSortedArraysTest {
  private MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();

  @Test
  void findMedianSortedArrays_interweavingOddNumberOfElements() {
    int[] array1 = new int[]{1, 3, 5, 7, 9, 9};
    int[] array2 = new int[]{0, 0, 2, 2, 4};
    assertEquals(3, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_interweavingEvenNumberOfElements() {
    int[] array1 = new int[]{1, 3, 5, 7, 9, 9};
    int[] array2 = new int[]{0, 0, 2, 2, 4, 5};
    assertEquals(3.5, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_bothArraysAreEmpty() {
    int[] array1 = new int[]{};
    int[] array2 = new int[]{};
    assertEquals(Double.NaN, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_oneArrayIsEmptyOddNumberedElement() {
    int[] array1 = new int[]{1, 2, 3};
    int[] array2 = new int[]{};
    assertEquals(2, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_oneArrayIsEmptyEvenNumberedElement() {
    int[] array1 = new int[]{1, 2};
    int[] array2 = new int[]{};
    assertEquals(1.5, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_oneArrayIsEmptySingleElement() {
    int[] array1 = new int[]{};
    int[] array2 = new int[]{1};
    assertEquals(1, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @Test
  void findMedianSortedArrays_oneArrayCompletelySmallerAndMedianInBiggerArray() {
    int[] array1 = new int[]{1, 2, 3};
    int[] array2 = new int[]{4, 5, 6, 7, 7};
    assertEquals(4.5, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }
}