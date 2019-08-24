package leetcode;

/**
 * Given an integer array sorted in ascending order, write a function to search target in nums. If target exists,
 * then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the
 * array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k
 * (0-indexed).
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds,
 * ArrayReader.get will return 2147483647.
 */
public class SearchInASortedArrayOfUnknownSize {
  private static int NUMBER_NOT_FOUND = -1;

  public int searchInUnknownSizeArray(ArrayReader array, int target) {
    return searchForTarget(array, target, 1, -1);
  }

  private int searchForTarget(ArrayReader array, int target, int increment, int lowerBound) {
    int position = lowerBound + increment;
    int numberInArray = array.get(position);
    if (numberInArray == target) {
      return position;
    } else if (numberInArray > target) {
      increment *= 2;
      lowerBound += position;
      return searchForTarget(array, target, increment, lowerBound);
    } else {
      return binarySearch(array, target, lowerBound, position);
    }
  }

  private int binarySearch(ArrayReader array, int target, int lowerBound, int upperBound) {
    if (lowerBound > upperBound) {
      return NUMBER_NOT_FOUND;
    }
    int midPoint = (int) Math.ceil((lowerBound + upperBound) / 2);
    int numberInArray = array.get(midPoint);
    if (numberInArray == target) {
      return midPoint;
    } else if (numberInArray > target) {
      return binarySearch(array, target, lowerBound, midPoint - 1);
    } else {
      return binarySearch(array, target, midPoint + 1, upperBound);
    }
  }

  private interface ArrayReader {
    int get(int position);
  }
}
