package leetcode;

public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] array1, int[] array2) {
    if (array1.length == 0 && array2.length == 0) {
      return Double.NaN;
    } else if (array1.length == 0) {
      return findMedianOfSortedArray(array2);
    } else if (array2.length == 0) {
      return findMedianOfSortedArray(array1);
    }
    return findMedianByMergeSort(array1, array2);
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

    if (isOdd(medianPosition)) {
      int median = 0;
      while (counter <= medianPosition) {
        if (array1Iterator < array1.length && array1[array1Iterator] < array2[array2Iterator]) {
          median = array1[array1Iterator];
          array1Iterator++;
        } else {
          median = array2[array2Iterator];
          array2Iterator++;
        }
        counter++;
      }
      return median;
    } else {
      int smallerMedian = 0;
      int biggerMedian = 0;
      while (counter <= medianPosition) {
        smallerMedian = biggerMedian;
        if (array1Iterator < array1.length && array1[array1Iterator] < array2[array2Iterator]) {
          biggerMedian = array1[array1Iterator];
          array1Iterator++;
        } else {
          biggerMedian = array2[array2Iterator];
          array2Iterator++;
        }
        counter++;
      }
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
