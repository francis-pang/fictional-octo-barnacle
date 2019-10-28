package leetcode;

public class RomanToInteger {
  private static final char ONE = 'I';
  private static final char FIVE = 'V';
  private static final char TEN = 'X';
  private static final char FIFTY = 'L';
  private static final char ONE_HUNDRED = 'C';
  private static final char FIVE_HUNDRED = 'D';
  private static final char ONE_THOUSAND = 'M';
  private static final int INVALID_INPUT = Integer.MIN_VALUE;

  public int romanToInt(String s) {
    if (s == null ||
        s.length() == 0) {
      return INVALID_INPUT;
    }
    char previousRomanRepresentation = Character.MIN_VALUE;
    int number = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      char romanRepresentation = s.charAt(i);
      switch (romanRepresentation) {
        case ONE:
          if (previousRomanRepresentation == FIVE ||
              previousRomanRepresentation == TEN) {
            number -= 1;
          } else {
            number += 1;
          }
          break;
        case FIVE:
          number += 5;
          break;
        case TEN:
          if (previousRomanRepresentation == FIFTY ||
              previousRomanRepresentation == ONE_HUNDRED) {
            number -= 10;
          } else {
            number += 10;
          }
          break;
        case FIFTY:
          number += 50;
          break;
        case ONE_HUNDRED:
          if (previousRomanRepresentation == FIVE_HUNDRED ||
              previousRomanRepresentation == ONE_THOUSAND) {
            number -= 100;
          } else {
            number += 100;
          }
          break;
        case FIVE_HUNDRED:
          number += 500;
          break;
        case ONE_THOUSAND:
          number += 1000;
          break;
        default:
          return INVALID_INPUT;
      }
      previousRomanRepresentation = romanRepresentation;
    }
    return number;
  }
}

