package facebook.abcs.arraysandstrings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * We define the distance between two array values as the number of indices between the two values. Given <i>a</i>,
 * find the minimum distance between any pair of equal elements in the array. If no such value exists, print <i>-1</i>.
 * <p>
 * For example, if <i>a = [3,2,1,2,3]</i>, there are two matching pairs of values: <i>3 abd 2</i>. The indices of the
 * <i>3</i>'s are <i>i = 0</i> and <i>j =4</i>, so their distance is <i>d[i,j] = |j -1| = 4</i>. The indices of the
 * <i>2</i>'s are <i>i = 1</i> and <i>j =3</i>, so their distance is <i>d[i,j] = |j -1| = 2</i>.
 *
 * <b>Function Description</b>
 * Complete the minimumDistances function in the editor below. It should return the minimum distance between any two
 * matching elements.
 * minimumDistances has the following parameter(s):
 * <list>
 * <u>a: an array of integers</u>
 * </list>
 *
 * <b>Input Format</b>
 * The first line contains an integer , the size of array <i>a</i>.
 * The second line contains <i>n</i> space-separated integers <i>a[i]</i>.
 *
 * <b>Constraints</b>
 * <list>
 * <u><i>1 <= n <= 10^3</i></u>
 * <u><i>1 <= a[i] <= 10^5</i></u>
 * </list>
 * <b>Output Format</b>
 * Print a single integer denoting the minimum <i>d[i,j]</i> in <i>a</i>. If no such value exists, print <-1></-1>.
 *
 * <b>Sample Input</b>
 * 6
 * 7 1 3 4 1 7
 * <b>Sample Output</b>
 * 3
 * <b>>Explanation</b
 * Here, we have two options:
 * <list>
 * <u><i>a[1]</i> and <i>a[4]</i> are both <i>1</i>, so <i>d[1,4] = |1 - 4| = 3</i></u>
 * <u><i>a[0]</i> and <i>a[5]</i> are both <i>7</i>, so <i>d[0,5] = |0 - 5| = 5</i></u>
 * </list>
 * The answer is <i>min(3,5) = 3</i>.
 */
public class MinimumDistances {
  public static class Solution {

    // Complete the minimumDistances function below.
    static int minimumDistances(int[] a) {
      final int NO_PAIR_OF_EQUAL_ELEMENT = -1;
      final int MINIMUM_POSSIBLE_DISTANCE = 1;
      int minimumDistance = Integer.MAX_VALUE;

      Map<Integer, Integer> lastSeenPositionOfElement = new HashMap<>();
      for (int index = 0; index < a.length; index++) {
        if (lastSeenPositionOfElement.containsKey(a[index])) {
          int distance = index - lastSeenPositionOfElement.get(a[index]);
          if (distance == MINIMUM_POSSIBLE_DISTANCE) {
            return MINIMUM_POSSIBLE_DISTANCE;
          }
          minimumDistance = (distance < minimumDistance) ? distance : minimumDistance;
        }
        lastSeenPositionOfElement.put(a[index], index);
      }
      return (minimumDistance == Integer.MAX_VALUE) ? NO_PAIR_OF_EQUAL_ELEMENT : minimumDistance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] a = new int[n];

      String[] aItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
        int aItem = Integer.parseInt(aItems[i]);
        a[i] = aItem;
      }

      int result = minimumDistances(a);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
    }
  }

}
