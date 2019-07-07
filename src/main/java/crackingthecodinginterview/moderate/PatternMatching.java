package crackingthecodinginterview.moderate;

/**
 * You are given two strings, pattern and value. The pattern string consists of just the letters a and b, describing
 * a pattern within a string. For example, the string catcatgocatgo matches the pattern aabab (where cat is a and go
 * is b). It also matches patterns like a, ab, and b. Write a method to determine if value matches pattern.
 */
public class PatternMatching {
  final char A_CHARACTER = 'a';
  final char B_CHARACTER = 'b';

  public boolean doesValueMatchesPattern(String pattern, String value) {
    OccurencePair occurencePair = extractFrequencyCountOfValue(value);
    int aOccurrence = occurencePair.a;
    int bOccurrence = occurencePair.b;

    if (pattern.length() < value.length()) {
      return false;
    }

    if (value.isEmpty()) {
      return false;
    }

    if (!value.isEmpty() &&
        (aOccurrence == 0 || bOccurrence == 0)) {
      int length = pattern.length() / (aOccurrence + bOccurrence);
      String remainingString = pattern.replaceAll(pattern.substring(0, length), "");
      return remainingString.isEmpty();
    }

    if (aOccurrence == 1 && bOccurrence == 1) {
      return true;
    }

    int minLengthA = calculateMinimum(pattern.length(), aOccurrence, bOccurrence);
    int minLengthB = calculateMinimum(pattern.length(), bOccurrence, aOccurrence);
    int maxLengthA = calculateMaximum(pattern.length(), aOccurrence, bOccurrence, minLengthB);
    int maxLengthB = calculateMaximum(pattern.length(), aOccurrence, bOccurrence, minLengthA);

    int possibleLengthOfA = maxLengthA - minLengthA;
    int possibleLengthOfB = maxLengthB - minLengthB;
    if (possibleLengthOfA > possibleLengthOfB) { // A has longer range
      return foundMatchingPattern(pattern, value.charAt(0), B_CHARACTER, minLengthB, maxLengthB, bOccurrence, aOccurrence);
    } else { // A has shorter range
      return foundMatchingPattern(pattern, value.charAt(0), A_CHARACTER, minLengthA, maxLengthA, aOccurrence, bOccurrence);
    }
  }

  private boolean foundMatchingPattern(String pattern, char firstCharacter, char shorterRangeChar, int selfMinLength,
                                       int selfMaxLength, int selfFrequency, int otherFrequency) {

    for (int counter = 0; counter <= (selfMaxLength - selfMinLength); counter++) {
      int selfLength = selfMinLength + counter;
      int otherLength = (pattern.length() - selfLength * selfFrequency) / otherFrequency;
      String remainingString = "cc";
      if (firstCharacter == shorterRangeChar) {
        remainingString = pattern.replaceAll(pattern.substring(0, selfLength), "");
        if (!remainingString.isEmpty()) {
          remainingString = remainingString.replaceAll(remainingString.substring(0, otherLength), "");
        } else {
          continue;
        }

      } else {
        remainingString = pattern.replaceAll(pattern.substring(0, otherLength), "");
        if (!remainingString.isEmpty()) {
          remainingString = remainingString.replaceAll(remainingString.substring(0, selfLength), "");
        } else {
          continue;
        }
      }
      if (remainingString.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private int calculateMinimum(int stringLength, int selfOccurrence, int otherOccurrence) {
    int counter = 1;
    while ((stringLength - (counter * selfOccurrence)) % otherOccurrence != 0) {
      counter++;
    }
    return counter;
  }

  private int calculateMaximum(int stringLength, int selfOccurrence, int otherOccurrence, int otherMin) {
    return (stringLength - (otherMin * selfOccurrence)) / otherOccurrence;
  }

  private OccurencePair extractFrequencyCountOfValue(String value) {
    int aOccurrence = 0;
    int bOccurrence = 0;

    for (char character : value.toCharArray()) {
      switch (character) {
        case A_CHARACTER:
          aOccurrence++;
          break;
        case B_CHARACTER:
          bOccurrence++;
          break;
      }
    }
    return new OccurencePair(aOccurrence, bOccurrence);
  }

  class OccurencePair {
    public int a;
    public int b;

    public OccurencePair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}
