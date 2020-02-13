package crackingthecodinginterview.sortingandsearching;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method
 * to merge B into A in sorted order.
 * Answer is in {@link leetcode.amazon.MergeTwoSortedLists}
 */
public class SortedMerge {
  public void mergeSortedArray(int[] arrayA, int[] arrayB) {
    if (arrayB.length == 0) {
      return;
    }
    if (arrayA.length == 0) {
      for (int i = 0; i < arrayB.length; i++) {
        arrayA[i] = arrayB[i];
      }
    }
    // Find out the length of arrayA
    // Assume that empty entries are filled with Integer.MIN_VALUE
    int aSize = 0;
    while (arrayA[aSize] != Integer.MIN_VALUE) {
      aSize++;
    }
  }

  private void mergeSortWhenArrayAIsInfiniteBig(int[] arrayA, int[] arrayB, int aSize) {
    int aIterator = 0;
    int bIterator = 0;
    int aReverseStart = arrayA.length - 1;
    int aReverseEnd = arrayA.length - 1;
    while (bIterator < arrayB.length) {
      if (aReverseEnd == arrayA.length - 1) {
        if (arrayA[aIterator] <= arrayB[bIterator]) {
        } else { // arrayA[aIterator] > arrayB[bIterator]
          arrayA[aReverseEnd] = arrayA[aIterator];
          aReverseEnd--;
        }
      } else {
        arrayA[aReverseEnd] = arrayA[aIterator];
        aReverseEnd++;
        if (arrayA[aReverseStart] <= arrayB[bIterator]) {
          arrayA[aIterator] = arrayA[aReverseStart];
          aReverseStart++;
        } else { // arrayA[aReverseStart] > arrayB[bIterator]
          arrayA[aIterator] = arrayB[bIterator];
          bIterator++;
        }
      }
      aIterator++;
    }
    while (aIterator < aSize) {
      arrayA[aReverseEnd++] = arrayA[aIterator];
      arrayA[aIterator++] = arrayA[aReverseStart++];
    }
    while (aReverseStart != aReverseEnd) {
      arrayA[aIterator++] = arrayA[aReverseStart++];
    }
  }

}
