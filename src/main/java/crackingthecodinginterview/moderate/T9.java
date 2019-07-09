package crackingthecodinginterview.moderate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T9 {
  public List<String> getMatchingWordsFromDigits(String digits, Set<String> validWords) {
    // Get the list of possible words first
    Set<String> allPossibleWords = generatePossibleWordsFromDigits(digits);

    // Use the word on the list and do a interception joint
    allPossibleWords.retainAll(validWords);
    return new ArrayList<>(allPossibleWords);
  }

  private Set<String> generatePossibleWordsFromDigits(String digits) {
    if (digits.length() == 0) {
      return new HashSet<>();
    }
    char lastDigit = digits.charAt(digits.length() - 1);
    if (!Character.isDigit(lastDigit)) {
      throw new IllegalArgumentException(lastDigit + " inside " + digits + " is not a digit");
    }
    char[] charactersOfLastDigit = NumberPad.resolve(Integer.parseInt(Character.toString(lastDigit))).letters;
    Set<String> subWordsSet = generatePossibleWordsFromDigits(digits.substring(0, digits.length() - 1));
    if (charactersOfLastDigit.length == 0) {
      return subWordsSet;
    }
    Set<String> possibleWords = new HashSet<>();
    if (subWordsSet.isEmpty()) {
      for (char lastPossibleCharacter : charactersOfLastDigit) {
        possibleWords.add(Character.toString(lastPossibleCharacter));
      }
    } else {
      for (String subWords : subWordsSet) {
        for (char lastPossibleCharacter : charactersOfLastDigit) {
          possibleWords.add(subWords.concat(Character.toString(lastPossibleCharacter)));
        }
      }
    }
    return possibleWords;
  }

  public enum NumberPad {
    ONE(1, new char[]{}),
    TWO(2, new char[]{'a', 'b', 'c'}),
    THREE(3, new char[]{'d', 'e', 'f'}),
    FOUR(4, new char[]{'g', 'h', 'i'}),
    FIVE(5, new char[]{'j', 'k', 'l'}),
    SIX(6, new char[]{'m', 'n', 'o'}),
    SEVEN(7, new char[]{'p', 'q', 'r', 's'}),
    EIGHT(8, new char[]{'t', 'u', 'v'}),
    NINE(9, new char[]{'w', 'x', 'y', 'z'}),
    ZERO(0, new char[]{}),
    ;
    private final int number;
    private final char[] letters;

    NumberPad(int number, char[] letters) {
      this.number = number;
      this.letters = letters;
    }

    public static NumberPad resolve(int value) {
      for (NumberPad numberPad : values()) {
        if (numberPad.number == value) {
          return numberPad;
        }
      }
      return null;
    }
  }
}
