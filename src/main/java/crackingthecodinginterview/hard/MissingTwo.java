package crackingthecodinginterview.hard;

/**
 * You are given an array with all the numbers from 1 to N appearing exactly once, except for one number that is
 * missing. How can you find the missing number in 0 (N) time and 0(1) space? What if there were two numbers missing?
 * Answer is in {@link leetcode.MissingNumber}
 */
public class MissingTwo {
  private static final int NOT_FOUND = 0;

  public MissingPair findTwoMissingNumberIn(int[] array) {
    int actualSum = 0;
    int totalSum = 0;
    int actualMultiple = 1;
    int totalMultiple = 1;

    for (int index = 0; index < array.length; index++) {
      actualSum += array[index];
      totalSum += index + 1;
      actualMultiple *= array[index];
      totalMultiple *= index + 1;
    }
    totalSum += (array.length * 2) + 1 + 2;
    totalMultiple *= (array.length + 1) * (array.length + 2);

    int sumOfPair = totalSum - actualSum;
    int multiplyOfPair = totalMultiple / actualMultiple;
    for (int index = 0; index < sumOfPair; index++) {
      int firstNumber = index;
      int secondNumber = sumOfPair - firstNumber;
      if (multiplyOfPair % firstNumber == 0 &&
          multiplyOfPair % secondNumber == 0) {
        return new MissingPair(firstNumber, secondNumber);
      }
    }
    return new MissingPair(NOT_FOUND, NOT_FOUND);
  }

  class MissingPair {
    public int firstNumber;
    public int secondNumber;

    public MissingPair(int firstNumber, int secondNumber) {
      this.firstNumber = firstNumber;
      this.secondNumber = secondNumber;
    }
  }
}
