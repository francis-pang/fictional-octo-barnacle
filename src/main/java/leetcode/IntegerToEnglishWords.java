package leetcode;

import java.util.StringJoiner;

public class IntegerToEnglishWords {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.numberToWords(1000000000));
  }

  static class Solution {
    public String numberToWords(int num) {
      final String WORD_IN_ZERO = "Zero";
      if (num == 0) {
        return WORD_IN_ZERO;
      }
      // Constuct integer array
      char[] splitNumbers = Integer.toString(num).toCharArray();
      int[] numbers = new int[splitNumbers.length];
      for (int index = 0; index < splitNumbers.length; index++) {
        numbers[index] = Character.digit(splitNumbers[index], 10);
      }

      StringJoiner wordsStringJoiner = new StringJoiner(" ");
      boolean previousAllZero = true;
      boolean previousNumberIsZero = true;
      for (int index = 0; index < numbers.length; index++) {
        int numberPosition = numbers.length - index;
        switch (numberPosition % 3) {
          case 0:
            if (numbers[index] > 0) {
              wordsStringJoiner = wordsStringJoiner.add(CardinalNumberBelowTen.resolve(numbers[index]));
              wordsStringJoiner = wordsStringJoiner.add("Hundred");
            }
            break;
          case 1:
            if (numbers[index] > 0) {
              wordsStringJoiner = wordsStringJoiner.add(CardinalNumberBelowTen.resolve(numbers[index]));
              wordsStringJoiner = (AugmentativeSuffix.resolve(numberPosition) != null) ?
                  wordsStringJoiner.add(AugmentativeSuffix.resolve(numberPosition)) : wordsStringJoiner;
            } else {
              wordsStringJoiner = addIfNotNull(wordsStringJoiner, numberPosition);
            }
            break;
          case 2:
            String presentWord = CardinalNumber.resolve(numbers[index] * 10 + numbers[index + 1]);
            if (presentWord == null) {
              wordsStringJoiner = (CardinalNumber.resolve(numbers[index] * 10) != null) ?
                  wordsStringJoiner.add(CardinalNumber.resolve(numbers[index] * 10)) : wordsStringJoiner;
            } else {
              wordsStringJoiner = wordsStringJoiner.add(presentWord);
              index++;
              numberPosition--;
              wordsStringJoiner = addIfNotNull(wordsStringJoiner, numberPosition);
            }
            break;
        }
      }
      return wordsStringJoiner.toString().trim();
    }

    private StringJoiner addIfNotNull(StringJoiner stringJoiner, int numberPosition) {
      String suffix = AugmentativeSuffix.resolve(numberPosition);
      if (suffix == null) {
        return stringJoiner;
      }
      String[] strings = stringJoiner.toString().split(" ");
      if (AugmentativeSuffix.resolve(strings[strings.length - 1]) < numberPosition) {
        return stringJoiner.add(suffix);
      }
      return stringJoiner;
    }
  }

  public enum AugmentativeSuffix {
    THOUSAND("Thousand", 4),
    MILLION("Million", 7),
    BILLION("Billion", 10),
    TRILLION("Trillion", 13),
    QUINTILLION("Quintillion", 16),
    SEXTILLION("Sextillion", 19);

    private String name;
    private int numberOfDigit;

    AugmentativeSuffix(String name, int numberOfDigit) {
      this.name = name;
      this.numberOfDigit = numberOfDigit;
    }

    public static String resolve(int numberOfDigit) {
      for (AugmentativeSuffix augmentativeSuffix : values()) {
        if (augmentativeSuffix.numberOfDigit == numberOfDigit) {
          return augmentativeSuffix.name;
        }
      }
      return null;
    }

    public static int resolve(String name) {
      for (AugmentativeSuffix augmentativeSuffix : values()) {
        if (augmentativeSuffix.name.equals(name)) {
          return augmentativeSuffix.numberOfDigit;
        }
      }
      return -1;
    }
  }

  public enum CardinalNumber {
    TEN("Ten", 10),
    ELEVEN("Eleven", 11),
    TWELVE("Twelve", 12),
    THIRTEEN("Thirteen", 13),
    FOURTEEN("Fourteen", 14),
    FIFTEEN("Fifteen", 15),
    SIXTEEN("Sixteen", 16),
    SEVENTEEN("Seventeen", 17),
    EIGHTEEN("Eighteen", 18),
    NINETEEN("Nineteen", 19),
    TWENTY("Twenty", 20),
    THIRTY("Thirty", 30),
    FORTY("Forty", 40),
    FIFTY("Fifty", 50),
    SIXTY("Sixty", 60),
    SEVENTY("Seventy", 70),
    EIGHTY("Eighty", 80),
    NINTY("Ninety", 90);

    private String name;
    private int value;

    CardinalNumber(String name, int value) {
      this.name = name;
      this.value = value;
    }

    public static String resolve(int value) {
      for (CardinalNumber cardinalNumber : values()) {
        if (value == cardinalNumber.value) {
          return cardinalNumber.name;
        }
      }
      return null;
    }
  }

  public enum CardinalNumberBelowTen {
    ONE("One", 1),
    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four", 4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9);

    private String name;
    private int value;

    CardinalNumberBelowTen(String name, int value) {
      this.name = name;
      this.value = value;
    }

    public static String resolve(int value) {
      for (CardinalNumberBelowTen cardinalNumberBelowTen : values()) {
        if (value == cardinalNumberBelowTen.value) {
          return cardinalNumberBelowTen.name;
        }
      }
      return null;
    }
  }
}
