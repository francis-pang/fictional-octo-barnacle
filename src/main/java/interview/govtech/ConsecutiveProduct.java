package interview.govtech;

/**
 * Write a function <i>solution</i> that, given two integers A and B, returns the number of integers from the range
 * [A..B] (ends are included) which can be expressed as the product of two consecutive integers, that is X * (X + 1)
 * , for some integer X.
 * <p>
 * Examples:
 * 1. Given A = 6 and B = 20, the function should return 3. These integers are: 6 = 2 * 3, 12 = 3 * 4 and 20 = 4 * 5.
 * 2. Given A = 21 and B = 29, the function should return 0. There are no integers of the form X * (X + 1) in this
 * interval.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * - A and B are integers within the range [1..1,000,000,000];
 * - A ≤ B.
 */
public class ConsecutiveProduct {
  public int solutionSlow(int a, int b) {
    // write your code in Java SE 8
    int number = b;
    int count = 0;
    int result;
    do {
      result = number * (number - 1);
      if (result <= b && result >= a) {
        count++;
      }
      number--;
    } while (result >= a);
    return count;
  }

  public int solution(int a, int b) {
    int left = 0;
    int right = b;
    int largestNumberWithProductLessThanB = 0;
    while (right >= left) {
      int mid = calculateMid(left, right);
      int product = mid * (mid + 1);
      if (product > b) {
        right = mid - 1;
      } else {
        if (mid == left) {
          largestNumberWithProductLessThanB = mid;
          break;
        }
        left = mid;
      }
    }

    left = 0;
    right = a;
    int smallestNumberWithProductMoreThanA = 0;
    while (right >= left) {
      int mid = calculateMid(left, right);
      int product = mid * (mid + 1);
      if (product >= a) {
        right = mid - 1;
      } else {
        if (mid == left) {
          smallestNumberWithProductMoreThanA = mid;
          break;
        }
        left = mid;
      }
    }

    return Math.abs(smallestNumberWithProductMoreThanA - largestNumberWithProductLessThanB);
  }

  private int calculateMid(int left, int right) {
    return (int) Math.ceil(left + ((right - left) / 2.0));
  }

  public static void main(String[] args) {
    ConsecutiveProduct consecutiveProduct = new ConsecutiveProduct();
//    System.out.println(consecutiveProduct.solution(6, 20));
    System.out.println(consecutiveProduct.solution(2, 55));
//    System.out.println(consecutiveProduct.solution(21, 29));
  }
}
