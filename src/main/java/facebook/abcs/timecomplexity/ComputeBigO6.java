package facebook.abcs.timecomplexity;

/**
 * Find the runtime of the c++ function, quuz().
 * <pre>{@code
 * // Swap two numbers. a and b are passed by reference
 * void swap(int& a, int& b) {
 *   int tmp = a;
 *   a = b;
 *   b = tmp;
 * }
 * // find a point within the array
 * int bleh(int A[], int p, int q) {
 *   int r = A[q];
 *   int i = p - 1;
 *   for (int j = p; j < q; j++) {
 *     if (A[j] < r) {
 *       i++;
 *       swap(A[i], A[j]);
 *     }
 *   }
 *   if (A[q] < A[i + 1]) {
 *     swap(A[i + 1], A[q]);
 *   }
 *   return i + 1;
 * }
 * int quuz(int A[], int x, int y) {
 *   if (x < y) {
 *     int z = bleh(A, x, y);
 *     quuz(A, x, z-1);
 *     if (z < y) {
 *       quuz(A, z + 1, y);
 *     }
 *   }
 * }
 * }</pre>
 *
 * <b>Input Format</b>
 * 1. Elements of the array each separated from the other by space, like so:
 * 5 4 3 2 7 8
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
 * Where n is the size of array a
 */
public class ComputeBigO6 {
  public static class Solution {
    public static void main(String[] args) {

      // Your output should print one of the following strings exactly.

      // Please note the spaces, parenthesis, upper-case/lower-case, etc.

      // `n` is the size of array `A` in function `corge()`.

      // O(log n)
      System.out.print("O(log n)"); // But I don't understand
      // O(n)

      // O(n log n)

      // O(n^2)

      // O(n^3)

      // O(2^n)

      // O(n^n)

    }
  }

}
