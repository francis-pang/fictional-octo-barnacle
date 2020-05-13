package interview.shopee;

/*
 * Create a function to determine whether a given non-negative integer X is a power of two or not
 * X = 2^n where n is any non-negative integer, [0, )
 *
 * e.g.
 * 2 => True
 * 3 => False
 * 4 => True
 *
 * 2^1 = 2 => true
 * 3 => false
 *
 * 2^X ->
 * X = 0 .... k,
 * Time: O(log(n)), k
 * Space: O(1)
 */
public class Base2Verifier {
  public static void main(String[] args) {
    System.out.println("Hello, world!");
    int number = Integer.MAX_VALUE;
    System.out.println(isPowerOfTwo(number));
  }

  private static boolean isPowerOfTwo(int number) {
    int power = 0;
    int result = 0;
    while (result <= number) {
      result = (int) Math.pow(2, power);
      if (result == number) {
        return true;
      }
      power++;
    }
    return false;
  }
}
