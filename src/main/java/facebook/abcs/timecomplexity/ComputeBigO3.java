package facebook.abcs.timecomplexity;

import java.util.Scanner;

/**
 * Find the runtime of this function.
 * <pre>{@code
 * int f(int m, int n) {
 *   if (m==1 || n==1) {
 *     return 1;
 *   }
 *   return f(m, n-1) + f(m-1, n);
 * }
 * }</pre>
 *
 * <b>Input Format</b>
 * 1. M and N (in that order) separated by space, as inputs to the function:
 * {@code 3 4}
 * <b>Constraints</b>
 * None
 * <b>Output Format</b>
 * One of the following
 * <ul>
 *   <li>O(log n)</li>
 *   <li>O(log m)</li>
 *   <li>O(log(m+n))</li>
 *   <li>O(log(m*n))</li>
 *   <li>O(n)</li>
 *   <li>O(m)</li>
 *   <li>O(m+n)</li>
 *   <li>O(m*n)</li>
 *   <li>O(n log n)</li>
 *   <li>O(m log m)</li>
 *   <li>O(n log m)</li>
 *   <li>O(m log n)</li>
 *   <li>O((m+n) log(m+n))</li>
 *   <li>O((m*n) log(m*n))</li>
 *   <li>O(n^2)</li>
 *   <li>O(m^2)</li>
 *   <li>O((m+n)^2)</li>
 *   <li>O((m*n)^2)</li>
 *   <li>O(2^n)</li>
 *   <li>O(2^m)</li>
 *   <li>O(2^(m+n))</li>
 *   <li>O(2^(m*n))</li>
 * </ul>
 */
public class ComputeBigO3 {
  public static class Solution {
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      //  Your output should print one of the following strings exactly.
      //  Please note the spaces, parenthesis, upper-case/lower-case, etc.
      //
      //  O(log n)
      //  O(log m)
      //  O(log(m+n))
      //  O(log(m*n))
      //  O(n)
      //  O(m)
      //  O(m+n)
      //  O(m*n)
      //  O(n log n)
      //  O(m log m)
      //  O(n log m)
      //  O(m log n)
      //  O((m+n) log(m+n))
      //  O((m*n) log(m\*n))
      //  O(n^2)
      //  O(m^2)
      //  O((m+n)^2)
      //  O((m*n)^2)
      //  O(2^n)
      //  O(2^m)
      //  O(2^(m+n))
      System.out.print("O(2^(m+n))");
      //  O(2^(m*n))
      in.close();
    }
  }
}
