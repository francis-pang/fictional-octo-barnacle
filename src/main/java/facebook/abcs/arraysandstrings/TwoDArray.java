package facebook.abcs.arraysandstrings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * Given a <i>6 x 6</i> 2D Array, <i>arr</i>:
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * We define an hourglass in <i>A</i> to be a subset of values with indices falling in this pattern in 's graphical
 * representation:
 * <p>
 * a b c
 * d
 * e f g
 * <p>
 * There are hourglasses in <i>arr</i>, and an hourglass sum is the sum of an hourglass' values. Calculate the hourglass
 * sum for every hourglass in <i>arr</i>, then print the maximum hourglass sum.
 * <p>
 * For example, given the 2D array:
 * <p>
 * -9 -9 -9  1 1 1
 * 0 -9  0  4 3 2
 * -9 -9 -9  1 2 3
 * 0  0  8  6 6 0
 * 0  0  0 -2 0 0
 * 0  0  1  2 4 0
 * We calculate the following <i>16</i> hourglass values:
 * <p>
 * -63, -34, -9, 12,
 * -10, 0, 28, 23,
 * -27, -11, -2, 10,
 * 9, 17, 25, 18
 * Our highest hourglass value is <i>28</i> from the hourglass:
 * <p>
 * 0 4 3
 * 1
 * 8 6 6
 * <p>
 * Note: If you have already solved the Java domain's Java 2D Array challenge, you may wish to skip this challenge.
 *
 * <b>Function Description</b>
 * Complete the function hourglassSum in the editor below. It should return an integer, the maximum hourglass sum in the
 * array.
 * hourglassSum has the following parameter(s):
 * <list>
 * <u>arr: an array of integers</u>
 * </list>
 *
 * <b>Input Format</b>
 * Each of the <i>6</i> lines of inputs <i>arr[i]</i> contains 6 space-separated integers <i>arr[i][j]</i>.
 *
 * <b>Constraints</b>
 * <list>
 * <u><i> -9 <= arr[i][j] <= 9</i></u>
 * <u> <i> 0 <= i,j <= 5 </i></u>
 * </list>
 * <b>Output Format</b>
 * Print the largest (maximum) hourglass sum found in <i>arr</i>.
 *
 * <b>Sample Input</b>
 * <p>
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 2 4 4 0
 * 0 0 0 2 0 0
 * 0 0 1 2 4 0
 * <b>Sample Output</b>
 * 19
 * <b>Explanation</b>
 * <i>arr</i> contains the following hourglasses:
 * <img src="https://s3.amazonaws.com/hr-assets/0/1534256743-35b846ad4a-hourglasssum.png" alt="hourglasses">
 * The hourglass with the maximum sum (<i>19</i>) is:
 * 2 4 4
 * 2
 * 1 2 4
 */
public class TwoDArray {

  public static class Solution {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
      final int HOUR_GLASS_BOUNDARY_LENTH = 2;
      final int HOUR_GLASS_BOUNDARY_HEIGHT = 2;

      // This is brute force processing
      int maximumSum = Integer.MIN_VALUE;
      for (int row = 0; row < arr.length - HOUR_GLASS_BOUNDARY_LENTH; row++) {
        for (int column = 0; column < arr[0].length - HOUR_GLASS_BOUNDARY_HEIGHT; column++) {
          int hourGlassSum =
              arr[row][column] + arr[row][column + 1] + arr[row][column + 2] +
                  arr[row + 1][column + 1] +
                  arr[row + 2][column] + arr[row + 2][column + 1] + arr[row + 2][column + 2];
          maximumSum = (hourGlassSum > maximumSum) ? hourGlassSum : maximumSum;
        }
      }
      return maximumSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int[][] arr = new int[6][6];

      for (int i = 0; i < 6; i++) {
        String[] arrRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 6; j++) {
          int arrItem = Integer.parseInt(arrRowItems[j]);
          arr[i][j] = arrItem;
        }
      }
      Instant start = Instant.now();
      int result = hourglassSum(arr);
      Instant end = Instant.now();
      System.out.println("Time taken is " + Duration.between(start, end).getNano() + "ns");
      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
    }
  }

}
