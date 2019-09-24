package facebook.abcs.timecomplexity;

/**
 * Find the runtime of this function.
 * int foo(int a[], int n) {
 * int j = 0;
 * for(int i = 0; i < n; ++i) {
 * while(j < n && a[i] < a[j]) {
 * j++;
 * }
 * }
 * return j;
 * }
 * <b>Input Format</b>
 * 1. Elements of the array each separated from the other by space, like so:
 * 5 4 3 2 7 8
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
 * Where n is the size of array a .
 */
public class ComputeBigO1 {
  public static class Solution {
    public static void main(String[] args) {
      // Your output should print one of the following strings exactly.
      // Please note the spaces, parenthesis, upper-case/lower-case, etc.
      // `n` is the size of array `a` in function `foo()`.
      //
      // O(log n)
      System.out.print("O(n)");
      //
      // O(n log n)
      // O(n^2)
      // O(n^3)
      // O(2^n)
      // O(n^n)
    }
  }

}
