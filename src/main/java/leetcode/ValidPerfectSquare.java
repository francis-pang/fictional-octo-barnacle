package leetcode;

public class ValidPerfectSquare {
  public static void main(String[] args) {
    ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();
    System.out.println(validPerfectSquare.isPerfectSquare(104976));
  }

  public boolean isPerfectSquare(int num) {
    if (num == 1) {
      return true;
    }
    int left = 1;
    int right = num;
    while (left <= right) { // THIS IS IMPORTANT WHEN USING THE BELOW FORMULA
      int mid = left + ((right - left) / 2); // KEY LINE!
      double square = Math.pow(mid, 2);
      if (square == num) {
        return true;
      } else if (square < num) {
        left = mid + 1;
      } else { // square > num
        right = mid - 1;
      }
    }
    return false;
  }
}
