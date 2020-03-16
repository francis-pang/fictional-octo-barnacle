package leetcode;

public class GreatestSumDivisibleByThree {
  public int maxBy3(int[] array) {
    int[] max = new int[3];
    int[] secondMax = new int[3];
    int[] thirdMax = new int[3];
    for (int i = 0; i < array.length; i++) {
      setMax(max, secondMax, thirdMax, array[i]);
    }
    int max0 = -1;
    if (max[0] > 0) {
      if (max[0] > 0) {
        if (secondMax[0] > 0 && thirdMax[0] > 0) {
          int sum = max[0] + secondMax[0] + thirdMax[0];
          if (sum > max0) {
            max0 = sum;
          }
        }
        if (max[1] > 0 && max[2] > 0) {
          int sum = max[0] + max[1] + max[2];
          if (sum > max0) {
            max0 = sum;
          }
        }
        if (max[1] > 0 && max[2] > 0) {
          int sum = max[0] + max[1] + max[2];
          if (sum > max0) {
            max0 = sum;
          }
        }
      }

      int max1 = -1;
      if (max[1] > 0) {
        if (secondMax[1] > 0 && thirdMax[1] > 0) {

        }
      }

      int max2 = -1;

      return Math.max(Math.max(max0, max1), max2);
    }
    return 0;
  }

  private void setMax(int[] max, int[] secondMax, int[] thirdMax, int number) {
    int q = number / 3;
    int r = number % 3;
    if (max[r] < number) {
      thirdMax[r] = secondMax[r];
      secondMax[r] = max[r];
      max[r] = number;
    } else if (secondMax[r] < number) {
      thirdMax[r] = secondMax[r];
      secondMax[r] = number;
    } else if (thirdMax[r] < number) {
      thirdMax[r] = number;
    }
  }
}
