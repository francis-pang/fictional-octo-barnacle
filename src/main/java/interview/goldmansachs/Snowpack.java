package interview.goldmansachs;

/* Problem Name is &&& Snowpack &&& PLEASE DO NOT REMOVE THIS LINE. */

/*
 ** Instructions to candidate.
 **  1) Given an array of non-negative integers representing the elevations
 **     from the vertical cross section of a range of hills, determine how
 **     many units of snow could be captured between the hills.
 **
 **     See the example array and elevation map below.
 **                                 ___
 **             ___                |   |        ___
 **            |   |        ___    |   |___    |   |
 **         ___|   |    ___|   |   |   |   |   |   |
 **     ___|___|___|___|___|___|___|___|___|___|___|___
 **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
 **                                 ___
 **             ___                |   |        ___
 **            |   | *   *  _*_  * |   |_*_  * |   |
 **         ___|   | *  _*_|   | * |   |   | * |   |
 **     ___|___|___|_*_|___|___|_*_|___|___|_*_|___|___
 **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
 **
 **     Solution: In this example 13 units of snow (*) could be captured.
 **
 **  2) Consider adding some additional tests in doTestsPass().
 **  3) Implement computeSnowpack() correctly.
 */
public class Snowpack {
  /*
   **  Find the amount of snow that could be captured.
   */
  public static Integer computeSnowpack(Integer[] array) {
    // Todo: Implement computeSnowpack

    int[] left = new int[array.length];
    left[0] = 0;
    for (int i = 1; i < array.length; i++) {
      int previousBiggestValue = array[left[i - 1]];
      int value = array[i];
      if (value >= previousBiggestValue) {
        left[i] = i;
      } else {
        left[i] = left[i - 1];
      }
    }

    int sum = 0;
    int peakPos = left[array.length - 1];
    int nextPeakPos = (peakPos == 0) ? -1 : left[peakPos - 1];
    while (nextPeakPos >= 0) {
      int peakValue = array[peakPos];
      int nextPeakValue = array[nextPeakPos];

      int minPeakValue = Math.min(peakValue, nextPeakValue);
      for (int pos = nextPeakPos + 1; pos < peakPos; pos++) {
        int difference = minPeakValue - array[pos];
        if (difference > 0) {
          sum += difference;
        }
      }
      peakPos = nextPeakPos;
      nextPeakPos = (peakPos == 0) ? -1 : left[peakPos - 1];
    }

    System.out.println("Sum=" + sum);

    int[] right = new int[array.length];
    right[array.length - 1] = array.length - 1;
    for (int i = array.length - 2; i >= 0; i--) {

      int previousBiggestValue = array[right[i + 1]];
      int value = array[i];
      if (value > previousBiggestValue) {
        right[i] = i;
      } else {
        right[i] = right[i + 1];
      }
    }

    peakPos = right[0];
    nextPeakPos = (peakPos == array.length - 1) ? array.length : right[peakPos + 1];
    while (nextPeakPos < array.length) {
      int peakValue = array[peakPos];
      int nextPeakValue = array[nextPeakPos];

      int minPeakValue = Math.min(peakValue, nextPeakValue);
      for (int pos = peakPos + 1; pos < nextPeakPos; pos++) {
        int difference = minPeakValue - array[pos];
        if (difference > 0) {
          sum += difference;
        }
      }
      peakPos = nextPeakPos;
      nextPeakPos = (peakPos == array.length - 1) ? array.length : right[peakPos + 1];
    }
    System.out.println("Sum=" + sum);
    return sum;
  }

  /*
   **  Returns true if the tests pass. Otherwise, returns false;
   */
  public static boolean doTestsPass() {
    boolean result = true;
    result &= computeSnowpack(new Integer[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;
    result &= computeSnowpack(new Integer[]{7, 7, 7, 7, 7, 7}) == 0;
    result &= computeSnowpack(new Integer[]{7, 6, 6, 6, 6, 7}) == 4;
    result &= computeSnowpack(new Integer[]{0, 0, 1, 0, 0}) == 0;
    result &= computeSnowpack(new Integer[]{1, 0, 0, 0, 0}) == 0;
    return result;
  }

  /*
   **  Execution entry point.
   */
  public static void main(String[] args) {
    if (doTestsPass()) {
      System.out.println("All tests pass");
    } else {
      System.out.println("Tests fail.");
    }
  }
}