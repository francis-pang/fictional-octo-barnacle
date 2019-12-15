package elementsofprogramminginterviews.arrays;

public class IncrementAnArbitraryPrecisionInteger {
  public int[] increment(int[] digits) {
    boolean carryOver = true;
    int index = digits.length - 1;
    while (index >= 0 && carryOver) {
      int number = digits[index];
      if (number == 9) {
        carryOver = true;
        digits[index] = 0;
      } else {
        carryOver = false;
        digits[index]++;
      }
    }
    if (carryOver) {
      int[] biggerArray = new int[digits.length + 1];
      biggerArray[0] = 1;
      for (int i = 0; i < digits.length; i++) {
        biggerArray[i + 1] = digits[i];
      }
      return biggerArray;
    } else {
      return digits;
    }
  }
}
