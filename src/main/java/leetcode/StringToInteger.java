package leetcode;

public class StringToInteger {
  private final static int INVALID = 0;
  private final static char EMPTY_SPACE = ' ';
  private final static char POSITIVE_SIGN = '+';
  private final static char NEGATIVE_SIGN = '-';

  public int myAtoi(String string) {
    if (string.length() == 0) {
      return INVALID;
    }
    int characterPosition = 0;
    char c = string.charAt(characterPosition);
    while (c == EMPTY_SPACE) {
      characterPosition++;
      if (characterPosition == string.length()) {
        break;
      }
      c = string.charAt(characterPosition);
    }
    if (characterPosition == string.length()) {
      return INVALID;
    }
    long result = 0;
    boolean negative = false;
    if (c == POSITIVE_SIGN) {
      negative = false;
    } else if (c == NEGATIVE_SIGN) {
      negative = true;
    } else if (Character.isDigit(c)) { // digit
      result += Long.parseLong(Character.toString(c));
    } else { // Other non-allowed char
      return INVALID;
    }
    characterPosition++;
    if (characterPosition == string.length()) {
      return parseResult(result, negative);
    }
    c = string.charAt(characterPosition);
    while (Character.isDigit(c)) {
      result *= 10;
      result += Long.parseLong(Character.toString(c));
      characterPosition++;
      if (result > Integer.MAX_VALUE || characterPosition == string.length()) {
        break;
      }
      c = string.charAt(characterPosition);
    }
    return parseResult(result, negative);
  }

  private int parseResult(long number, boolean negative) {
    if (number <= Integer.MAX_VALUE && number >= Integer.MIN_VALUE) {
      int resultInt = (int) number;
      return (negative) ? -1 * resultInt : resultInt;
    } else {
      return (negative) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
  }

  public static void main(String[] args) {
    StringToInteger stringToInteger = new StringToInteger();
    System.out.println(stringToInteger.myAtoi("18446744073709551617"));
  }
}
