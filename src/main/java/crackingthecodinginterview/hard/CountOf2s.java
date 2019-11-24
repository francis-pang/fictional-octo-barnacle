package crackingthecodinginterview.hard;

/**
 * Write a method to count the number of 2s that appear in all the numbers between 0 and n (inclusive).
 */
public class CountOf2s {
  public int countTwosFromZeroTo(int number) {
    if (number <= 0) {
      return 0;
    }
    int totalCount = 0;
    String numberString = Integer.toString(number);

    // Do the ones digit place first
    int onesDigitPlaceValue = valueAtDigitPlace(numberString, 0);
    if (onesDigitPlaceValue >= 2) {
      totalCount++;
    }

    // Initialise start values
    int digitPlace = 1;
    int baseMultiplier = 1;
    int digitPlaceMultiplier = baseMultiplier * 10;
    while (number > digitPlaceMultiplier) {
      // For example 22 has 3 '2'
      int numberOfTwosForPreviousDigitPlace = (number / digitPlaceMultiplier) * baseMultiplier;
      totalCount += numberOfTwosForPreviousDigitPlace;
      int moreThanTwoAddition = checkAndAddIfMoreThanTwo(numberString, digitPlace);
      totalCount += moreThanTwoAddition;
      digitPlace++;
      baseMultiplier *= 10;
      digitPlaceMultiplier = baseMultiplier * 10;
    }

    if (number > 10) {
      int base2Number = 2 * baseMultiplier;
      int difference = number - (base2Number - 1);
      if (difference > 0) {
        int moreThan2XXAddition = Math.min(baseMultiplier, difference);
        totalCount += moreThan2XXAddition;
      }
    }
    return totalCount;
  }

  private int checkAndAddIfMoreThanTwo(String numberString, int digitPlace) {
    if (digitPlace == numberString.length() - 1) {
      return 0;
    }
    int digitPlaceValue = valueAtDigitPlace(numberString, digitPlace);
    if (digitPlaceValue > 2) {
      return (int) Math.pow(10, digitPlace);
    } else if (digitPlaceValue == 2) {
      if (digitPlace == 0) {
        return 1;
      }
      int fullValue = valueFromDigitPlace(numberString, digitPlace);
      int diff = fullValue - (2 * (int) Math.pow(10, digitPlace) - 1);
      return diff;
    } else {
      return 0;
    }
  }

  private int valueFromDigitPlace(String numberString, int digitPlace) {
    int positionOfCharacter = numberString.length() - 1 - digitPlace;
    String stringValue = numberString.substring(positionOfCharacter);
    return Integer.parseInt(stringValue);
  }

  private int valueAtDigitPlace(String numberString, int digitPlace) {
    int positionOfCharacter = numberString.length() - 1 - digitPlace;
    char numberCharAtDigitPlace = numberString.charAt(positionOfCharacter);
    String digitString = Character.toString(numberCharAtDigitPlace);
    return Integer.parseInt(digitString);
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
