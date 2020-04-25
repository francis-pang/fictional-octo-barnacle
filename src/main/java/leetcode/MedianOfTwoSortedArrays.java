package leetcode;

public class MedianOfTwoSortedArrays {
  private static final int DEFAULT_VALUE = Integer.MIN_VALUE;

  public double findMedianSortedArrays(int[] array1, int[] array2) {
    if (array1.length == 0 && array2.length == 0) {
      return Double.NaN;
    } else if (array1.length == 0) {
      return findMedianOfSortedArray(array2);
    } else if (array2.length == 0) {
      return findMedianOfSortedArray(array1);
    }
//    return findMedianByMergeSort(array1, array2);
    if (array2.length > array1.length) {
      return findMedianByBinarySearch(array2, array1);
    } else {
      return findMedianByBinarySearch(array1, array2);
    }
  }

  private double findMedianByBinarySearch(int[] arrayA, int[] arrayB) {
    final int START_LEFT_VALUE = 0;
    final int START_RIGHT_VALUE = arrayA.length - 1;
    int sizeOfEachSize = (arrayA.length + arrayB.length + 1) / 2;

    int left = START_LEFT_VALUE;
    int right = START_RIGHT_VALUE;
    int arrayACutOff = DEFAULT_VALUE;
    int arrayBCutOff = DEFAULT_VALUE;
    int arrayAMinRhs = DEFAULT_VALUE;
    int arrayAMaxLhs = DEFAULT_VALUE;
    int arrayBMinRhs = DEFAULT_VALUE;
    int arrayBMaxLhs = DEFAULT_VALUE;
    boolean foundCutOffPoint = false;
    while (!foundCutOffPoint && isWithinBoundary(left, arrayA) && isWithinBoundary(right, arrayA) && left <= right) {
      int mid = calculateMid(left, right);
      arrayACutOff = mid;
      arrayBCutOff = sizeOfEachSize - mid;
      // This means that the there is too little elements in the first array to form left region, we need to move
      // right to take more elements from first array
      if (arrayBCutOff > arrayB.length) {
        left = mid + 1;// move right
        continue;
      }
      // This means that the there is too many elements in the first array to form left region, we need to move
      // left to take more elements from second array
      if (arrayBCutOff < 0) {
        right = mid - 1;// move left
        continue;
      }

      // Reset value
      arrayAMinRhs = DEFAULT_VALUE;
      arrayAMaxLhs = DEFAULT_VALUE;
      arrayBMinRhs = DEFAULT_VALUE;
      arrayBMaxLhs = DEFAULT_VALUE;

      // Formula for Min(RHS) = array[cutOff]
      // Formula for Max(LHS) = array[cutOff - 1]
      if (isWithinBoundary(arrayACutOff, arrayA)) {
        arrayAMinRhs = arrayA[arrayACutOff];
      }
      if (isWithinBoundary(arrayACutOff - 1, arrayA)) {
        arrayAMaxLhs = arrayA[arrayACutOff - 1];
      }
      if (isWithinBoundary(arrayBCutOff, arrayB)) {
        arrayBMinRhs = arrayB[arrayBCutOff];
      }
      if (isWithinBoundary(arrayBCutOff - 1, arrayB)) {
        arrayBMaxLhs = arrayB[arrayBCutOff - 1];
      }

      if (arrayAMaxLhs != DEFAULT_VALUE && arrayBMinRhs != DEFAULT_VALUE) {
        if (arrayBMinRhs < arrayAMaxLhs) {
          right = mid - 1;// move left
          continue;
        }
      }

      if (arrayBMaxLhs != DEFAULT_VALUE && arrayAMinRhs != DEFAULT_VALUE) {
        if (arrayAMinRhs < arrayBMaxLhs) {
          left = mid + 1;// move right
          continue;
        }
      }
      // By this execution path, the program has found the right cutting point
      foundCutOffPoint = true;
    }

    // Normal circumstance
    if (foundCutOffPoint) {
      int smallerMedian;
      if (arrayAMaxLhs != DEFAULT_VALUE && arrayBMaxLhs != DEFAULT_VALUE) {
        smallerMedian = Math.max(arrayAMaxLhs, arrayBMaxLhs);
      } else if (arrayAMaxLhs != DEFAULT_VALUE) {
        smallerMedian = arrayAMaxLhs;
      } else if (arrayBMaxLhs != DEFAULT_VALUE) {
        smallerMedian = arrayBMaxLhs;
      } else {
        throw new IllegalStateException("Cannot find any cutting point!");
      }
      if (isOdd(arrayA.length + arrayB.length)) {
        return smallerMedian;
      } else if (arrayA.length + arrayB.length > 2) {
        // NOTE: This section of the code doesn't cater for combined array size of 2!
        int biggerMedian;
        if (arrayAMinRhs != DEFAULT_VALUE && arrayBMinRhs != DEFAULT_VALUE) {
          biggerMedian = Math.min(arrayAMinRhs, arrayBMinRhs);
        } else if (arrayAMinRhs != DEFAULT_VALUE) {
          biggerMedian = arrayAMinRhs;
        } else if (arrayBMinRhs != DEFAULT_VALUE) {
          biggerMedian = arrayBMaxLhs;
        } else {
          throw new IllegalStateException("Cannot find bigger median!");
        }
        return average(smallerMedian, biggerMedian);
      }
    }

    int medianPosition = (int) average(arrayA.length, arrayB.length);
    if (arrayACutOff >= arrayA.length || left >= arrayA.length) { // Array 1 is LHS of array 2
      int offSet = medianPosition - arrayA.length;
      if (isOdd(arrayA.length + arrayB.length)) {
        if (offSet < 0) {
          return arrayA[medianPosition];
        } else if (offSet == 0) {
          return arrayB[0];
        } else { // offSet > 0
          return arrayB[offSet];
        }
      } else {
        int smallerMedian;
        int biggerMedian;
        if (offSet > 0) {
          smallerMedian = arrayA[medianPosition];
          biggerMedian = arrayA[medianPosition + 1];
        } else if (offSet == 0) {
          smallerMedian = arrayA[arrayA.length - 1]; // last element
          biggerMedian = arrayB[0];
        } else { // offSet < 0
          smallerMedian = arrayB[offSet];
          biggerMedian = arrayB[offSet + 1];
        }
        return average(smallerMedian, biggerMedian);
      }
    }

    if (arrayACutOff == 0 || right < 0) { // Array 1 is RHS of array 2
      int offSet = medianPosition - arrayB.length;
      if (isOdd(arrayA.length + arrayB.length)) {
        return (offSet < 0) ? arrayB[medianPosition] : arrayA[offSet];
      } else {
        int smallerMedian;
        int biggerMedian;
        if (offSet > 0) {
          smallerMedian = arrayA[offSet - 1];
          biggerMedian = arrayA[offSet];
        } else if (offSet == 0) {
          smallerMedian = arrayB[arrayB.length - 1]; // last element
          biggerMedian = arrayA[0];
        } else { // offSet < 0
          smallerMedian = arrayB[medianPosition - 1];
          biggerMedian = arrayB[medianPosition];
        }
        return average(smallerMedian, biggerMedian);
      }
    }
    throw new IllegalStateException("Code cannot reach here.");
  }

  private int calculateMid(int left, int right) {
    int difference = right - left;
    return left + difference / 2;
  }

  /**
   * Returns true if the position is a within the boundary of the array. Otherwise, returns false.
   *
   * @param position the position to be check
   * @param array    array to be check against
   * @return true if the position is a within the boundary of the array. Otherwise, returns false.
   */
  private boolean isWithinBoundary(int position, int[] array) {
    return position >= 0 && position < array.length;
  }

  private double findMedianOfSortedArray(int[] array) {
    int medianPosition = array.length / 2;
    if (array.length == 1) {
      return array[0];
    }
    if (isOdd(array.length)) {
      return array[medianPosition];
    } else {
      return average(array[medianPosition - 1], array[medianPosition]);
    }
  }

  private double findMedianByMergeSort(int[] array1, int[] array2) {
    int medianPosition = (int) average(array1.length, array2.length);
    int counter = 0;
    int array1Iterator = 0;
    int array2Iterator = 0;

    int smallerMedian = Integer.MIN_VALUE; // Meant to keep the smaller of the 2 median
    int biggerMedian = Integer.MIN_VALUE;
    while (counter <= medianPosition) {
      smallerMedian = biggerMedian;
      if (array2Iterator >= array2.length || (array1Iterator < array1.length && array1[array1Iterator] <= array2[array2Iterator])) {
        biggerMedian = array1[array1Iterator++];
      } else {
        biggerMedian = array2[array2Iterator++];
      }
      counter++;
    }
    int totalLength = array1.length + array2.length;
    if (isOdd(totalLength)) {
      return biggerMedian;
    } else { // Even, so we need to take 2 values and average them.
      return average(smallerMedian, biggerMedian);
    }
  }

  private double average(int number1, int number2) {
    return (number1 + number2) / 2.0;
  }

  private boolean isOdd(double number) {
    return (number % 2) != 0;
  }
}
