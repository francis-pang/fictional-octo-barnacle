package leetcode;

public class SearchInRotatedSortedArray {
  static class Solution {
    private final int NUMBER_NOT_IN_ARRAY = -1;

    public int search(int[] nums, int target) {
      return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] array, int number, int lowPosition, int highPosition) {
      if (lowPosition > highPosition) {
        return NUMBER_NOT_IN_ARRAY;
      } else if (array[lowPosition] > array[highPosition]) {
        return searchInRotatedArray(array, number, lowPosition, highPosition);
      } else {
        return searchInNormalArray(array, number, lowPosition, highPosition);
      }
    }

    private int searchInRotatedArray(int[] array, int number, int lowPosition, int highPosition) {
      int midPoint = calculateMidPoint(array, lowPosition, highPosition);
      if (array[midPoint] == number) {
        return midPoint;
      } else if (number < array[midPoint] && number >= array[lowPosition]) {
        return search(array, number, lowPosition, midPoint - 1);
      } else {
        return search(array, number, midPoint + 1, highPosition);
      }
    }

    private int searchInNormalArray(int[] array, int number, int lowPosition, int highPosition) {
      int midPoint = calculateMidPoint(array, lowPosition, highPosition);
      if (array[midPoint] == number) {
        return midPoint;
      } else if (number >= array[midPoint]) {
        return search(array, number, midPoint + 1, highPosition);
      } else {
        return search(array, number, lowPosition, midPoint - 1);
      }
    }

    private int calculateMidPoint(int[] array, int lowPosition, int highPosition) {
      return (int) Math.ceil((lowPosition + highPosition) / 2.0);
    }
  }
}
