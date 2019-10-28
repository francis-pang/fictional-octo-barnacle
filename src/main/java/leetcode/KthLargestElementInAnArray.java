package leetcode;

public class KthLargestElementInAnArray {
  private static final int INVALID_RETURN_VALUE = Integer.MIN_VALUE;

  public int findKthLargest(int[] nums, int k) {
    if (k <= 0 ||
        nums == null ||
        k > nums.length ||
        nums.length == 0) {
      return INVALID_RETURN_VALUE;
    }
    return partitionArray(nums, 0, nums.length, k);
  }

  private int partitionArray(int[] array, int leftBound, int rightBound, int k) {
    int pivotPosition = rightBound - 1;
    int movingPosition = leftBound;
    while (movingPosition < pivotPosition) {
      if (array[movingPosition] <= array[pivotPosition]) {
        movingPosition++;
      } else {
        swap(array, movingPosition, pivotPosition);
        swap(array, movingPosition, pivotPosition - 1);
        pivotPosition--;
      }
    }
    if (pivotPosition == array.length - k) {
      return array[pivotPosition];
    } else if (pivotPosition < array.length - k) { // the number is in the biggest stack
      return partitionArray(array, pivotPosition + 1, array.length, k);
    } else { // the number is in the smaller stack
      return partitionArray(array, 0, pivotPosition, k);
    }
  }

  private void swap(int[] array, int swapOne, int swapTwo) {
    int temp = array[swapOne];
    array[swapOne] = array[swapTwo];
    array[swapTwo] = temp;
  }
}
