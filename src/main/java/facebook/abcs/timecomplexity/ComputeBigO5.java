package facebook.abcs.timecomplexity;

import java.util.Scanner;

/***
 * Find the runtime of this function, where n is an integer.
 * <pre>{@code
 * int quux(int n) {
 *   int a = 0;
 *   int b = n;
 *   int c;
 *   while (b - a > 0) {
 *     c = (a + b) / 2;
 *     if (c > rand()*n) {
 *       a = c;
 *     } else {
 *       b = c;
 *     }
 *   }
 *   return c;
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
public class ComputeBigO5 {
  public static class Solution {
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      //  Your output should print one of the following strings exactly.
      //  Please note the spaces, parenthesis, upper-case/lower-case, etc.
      //
      System.out.print("O(log n)");
      //  O(log n)
      //  O(n)
      //  O(n log n)
      //  O(n^2)
      //  O(n^3)
      //  O(2^n)
      //  O(n^n)
      in.close();
    }
  }
}
