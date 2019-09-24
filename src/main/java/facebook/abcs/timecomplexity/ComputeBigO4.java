package facebook.abcs.timecomplexity;

import java.util.Scanner;

/**
 * Find the runtime of this function, where n is an integer.
 * int qux(int n) {
 * int a = 0;
 * for (int i = n; i > 0; i--) {
 * for (int j = 0; j < i; j++) {
 * a = a + i + j;
 * }
 * }
 * return a;
 * }
 * <b>Input Format</b>
 * 1. N, the input to the function, like so
 * 10
 * <b>Constraints</b>
 * None
 * <b>Output Format</b>
 * One of the following
 * O(log n)
 * O(n)
 * O(n log n)
 * O(n^2)
 * O(n^3)
 * O(2^n)
 * O(n^n)
 */
public class ComputeBigO4 {
  public static class Solution {

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      //  Your output should print one of the following strings exactly.
      //  Please note the spaces, parenthesis, upper-case/lower-case, etc.
      //
      //  O(log n)
      //  O(n)
      //  O(n log n)
      System.out.print("O(n^2)");
      //  O(n^2)
      //  O(n^3)
      //  O(2^n)
      //  O(n^n)
      in.close();
    }
  }
}
