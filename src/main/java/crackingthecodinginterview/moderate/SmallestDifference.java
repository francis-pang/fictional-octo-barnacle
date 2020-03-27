package crackingthecodinginterview.moderate;

import java.util.Arrays;

/**
 * Given two arrays of integers, compute the pair of values (one value in each array) with the smallest
 * (non-negative) difference. Return the difference.
 */
public class SmallestDifference {
  private static final int UNDEFINED = -1;

  public int smallDifferencePair(int[] arrayA, int[] arrayB) {
    if (arrayA == null || arrayA.length == 0 ||
        arrayB == null || arrayB.length == 0) {
      return UNDEFINED;
    }

    sort(arrayA);
    sort(arrayB);

    int smallestDifference = Integer.MAX_VALUE;
    int arrayAIterator = 0;
    int arrayBIterator = 0;
    while (arrayAIterator < arrayA.length && arrayBIterator < arrayB.length) {
      int difference;
      if (arrayA[arrayAIterator] > arrayB[arrayBIterator]) {
        difference = arrayA[arrayAIterator] - arrayB[arrayBIterator];
        arrayBIterator++;
      } else {
        difference = arrayB[arrayBIterator] - arrayA[arrayAIterator];
        arrayAIterator++;
      }
      if (difference < smallestDifference) {
        smallestDifference = difference;
      }
    }
    return smallestDifference;
  }

  private void sort(int[] array) {
    Arrays.sort(array);
  }
}
