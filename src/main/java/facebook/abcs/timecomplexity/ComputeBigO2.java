package facebook.abcs.timecomplexity;

import java.util.Scanner;

/**
 * Find the runtime of this function.
 * <pre>{@code
 * int bar(int n) {
 *   int count = 0;
 *   for (int i = n; i > 0; i /= 2) {
 *     for (int j = 0; j < i; j++) {
 *       count += 1;
 *     }
 *   }
 *   return count;
 * }
 * }</pre>
 *
 * <b>Input Format</b>
 * int n = 10;
 * <b>Constraints</b>
 * None
 * <b>Output Format</b>
 * One of the following
 * <ul>
 *   <li>O(log n)</li>
 *   <li>O(n)</li>
 *   <li>O(n log n)</li>
 *   <li>O(n^2)</li>
 *   <li>O(n^3)</li>
 *   <li>O(2^n)</li>
 *   <li>O(n^n)</li>
 * </ul>
 */
public class ComputeBigO2 {
  public static class Solution {
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      //  Your output should print one of the following strings exactly.
      //  Please note the spaces, parenthesis, upper-case/lower-case, etc.
      //
      //  O(log n)
      //  O(n)
      //  O(n log n)
      System.out.print("O(n)");
      //  O(n^2)
      //  O(n^3)
      //  O(2^n)
      //  O(n^n)
      in.close();
    }
  }
}
