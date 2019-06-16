package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
 * i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
 * array A.
 * FOLLOW UP
 * What if the values are not distinct?
 */
public class MagicIndex {
  public int locateMagicIndex(int[] array) {
    // Retrieve Integer.MIN_VALUE if there is no magic index
    return locateMagicIndex(array, new HashSet<>(), 0, array.length - 1);
  }

  private int locateMagicIndex(int[] array, Set<Integer> processedIndexes, int leftBound, int rightBound) {
    // Since we know that the array is sorted and distinct. By comparing the index with the value at the index, we
    // know to move upward or downwards.
    int midPoint = (leftBound + (rightBound + 1)) / 2;

    if (processedIndexes.contains(midPoint)) {
      return Integer.MIN_VALUE;
    }

    if (midPoint == array[midPoint]) {
      return midPoint;
    } else if (midPoint < array[midPoint]) { // smaller
      processedIndexes.add(midPoint);
      return locateMagicIndex(array, processedIndexes, leftBound, midPoint);
    } else { // bigger
      processedIndexes.add(midPoint);
      return locateMagicIndex(array, processedIndexes, midPoint, rightBound);
    }
  }

  public int locateMagicIndexAllowDuplicate(int[] array) {
    return locateMagicIndex(array, new HashSet<>(), 0, array.length - 1);
  }
}
