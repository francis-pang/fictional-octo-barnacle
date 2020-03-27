package leetcode;

public class SearchInRotatedSortedArray {
  private final int NUMBER_NOT_IN_ARRAY = -1;

  public int search(int[] nums, int target) {
    return search(nums, target, 0, nums.length - 1);
  }

  private int search(int[] array, int target, int lowPosition, int highPosition) {
    if (lowPosition > highPosition) {
      return NUMBER_NOT_IN_ARRAY;
    } else if (array[lowPosition] > array[highPosition]) {
      return searchInRotatedArray(array, target, lowPosition, highPosition);
    } else {
      return searchInNormalArray(array, target, lowPosition, highPosition);
    }
  }

  private int searchInRotatedArray(int[] array, int target, int lowPosition, int highPosition) {
    int midPoint = calculateMidPoint(lowPosition, highPosition);
    if (array[midPoint] == target) {
      return midPoint;
    } else if (target < array[midPoint] && target >= array[lowPosition]) {
      return search(array, target, lowPosition, midPoint - 1);
    } else {
      return search(array, target, midPoint + 1, highPosition);
    }
  }

  private int searchInNormalArray(int[] array, int target, int lowPosition, int highPosition) {
    int midPoint = calculateMidPoint(lowPosition, highPosition);
    if (array[midPoint] == target) {
      return midPoint;
    } else if (target >= array[midPoint]) {
      return search(array, target, midPoint + 1, highPosition);
    } else {
      return search(array, target, lowPosition, midPoint - 1);
    }
  }

  private int calculateMidPoint(int lowPosition, int highPosition) {
    return lowPosition + (highPosition - lowPosition) / 2;
  }
}