package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthLargestElementInAnArrayTest {

  private KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();

  @Test
  void findKthLargest_null() {
    assertEquals(Integer.MIN_VALUE, kthLargestElementInAnArray.findKthLargest(null, 5));
  }

  @Test
  void findKthLargest_emptyArray() {
    int[] questionArray = new int[0];
    assertEquals(Integer.MIN_VALUE, kthLargestElementInAnArray.findKthLargest(questionArray, 5));
  }

  @Test
  void findKthLargest_kBiggerThanSizeOfArray() {
    int[] questionArray = new int[]{1};
    assertEquals(Integer.MIN_VALUE, kthLargestElementInAnArray.findKthLargest(questionArray, 2));
  }

  @Test
  void findKthLargest_kIsNegativeNumber() {
    int[] questionArray = new int[]{1};
    assertEquals(Integer.MIN_VALUE, kthLargestElementInAnArray.findKthLargest(questionArray, -1));
  }

  @Test
  void findKthLargest_kIsOne() {
    int[] questionArray = new int[]{1, 3, 2};
    assertEquals(3, kthLargestElementInAnArray.findKthLargest(questionArray, 1));
  }

  @Test
  void findKthLargest_nonDecreasingOrder() {
    int[] questionArray = new int[]{1, 2, 3, 4, 4, 5, 5, 5, 6, 7};
    assertEquals(5, kthLargestElementInAnArray.findKthLargest(questionArray, 3));
  }

  @Test
  void findKthLargest_decreasingOrder() {
    int[] questionArray = new int[]{7, 6, 6, 6, 5, 4, 4, 4, 4, 3, 1};
    assertEquals(6, kthLargestElementInAnArray.findKthLargest(questionArray, 3));
  }

  @Test
  void findKthLargest_mixture() {
    int[] questionArray = new int[]{1, 4, 3, 12, 10, 7, 11, 18, 15, 9, 5, 13, 20, 6, 2, 8, 17, 19, 16, 14};
    assertEquals(18, kthLargestElementInAnArray.findKthLargest(questionArray, 3));
  }

  @Test
  void findKthLargest_mixture2() {
    int[] questionArray = new int[]{3, 2, 1, 5, 6, 4};
    assertEquals(5, kthLargestElementInAnArray.findKthLargest(questionArray, 2));
  }
}