package crackingthecodinginterview.hard;

/**
 * Write a method to count the number of 2s that appear in all the numbers between 0 and n (inclusive).
 */
public class CountOf2s {
  public int countTwosFromZeroTo(int number) {
    int powerOfTen = -1;
    int totalNumberOfTwos = 0;
    while (number > 0) {
      int digit = number % 10;
      if (powerOfTen >= 0) {
        totalNumberOfTwos += Math.pow(10, powerOfTen) * digit;
      }
      if (digit >= 2) {
        if (powerOfTen >= 0) {
          totalNumberOfTwos += 10;
        } else {
          totalNumberOfTwos += 1;
        }
      }
      number /= 10;
      powerOfTen++;
    }
    return totalNumberOfTwos;
  }

  private int count2sInRangeAtDigit(int number, int d) {
    int powerOf10 = (int) Math.pow(10, d);
    int nextPowerOf10 = powerOf10 * 10;
    int right = number % powerOf10;
    int roundDown = number - number % nextPowerOf10;
    int roundUp = roundDown + nextPowerOf10;
    int digit = (number / powerOf10) % 10;
    if (digit < 2) {
      return roundDown / 10;
    } else if (digit == 2) {
      return roundDown / 10 + right + 1;
    } else {
      return roundUp / 10;
    }
  }

  public int count2sInRange(int number) {
    int count = 0;
    int len = String.valueOf(number).length();
    for (int digit = 0; digit < len; digit++) {
      count += count2sInRangeAtDigit(number, digit);
    }
    return count;
  }
}
