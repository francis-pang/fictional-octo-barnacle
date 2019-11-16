package crackingthecodinginterview.hard;

/**
 * Write a method to count the number of 2s that appear in all the numbers between 0 and n (inclusive).
 */
public class CountOf2s {
  public int countTwosFromZeroTo(int number) {
    int totalCount = 0;
    int digitPlace = 1;

    String numberString = Integer.toString(number);

    int multiplier = 10;
    int additionValue = 0;
    while (number > multiplier) {
      int additionMultiplier = multiplier / 10;
      additionValue = (number / multiplier) * additionMultiplier;
      totalCount += additionValue;
      if (valueAtDigitPlaceIsMoreThan2(numberString, digitPlace)) {
        totalCount += additionMultiplier;
      }
      digitPlace++;
      multiplier = (int) Math.pow(10, digitPlace);
    }

    multiplier = multiplier / 10;
    int base2Number = 2 * multiplier;
    int difference = number - (base2Number - 1);
    if (difference > 0) {
      additionValue = Math.min(multiplier, difference);
      totalCount += additionValue;
    }

    return totalCount;
  }

  private boolean valueAtDigitPlaceIsMoreThan2(String numberString, int digitPlace) {
    int examinePosition = numberString.length() - digitPlace;
    char digitPlaceValueChar = numberString.charAt(examinePosition);
    int digitPlaceValue = Integer.parseInt(Character.toString(digitPlaceValueChar));
    return digitPlaceValue >= 2;
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
