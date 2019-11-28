package other;

public class StringRotationCheck {
  public boolean isStringRotatedRepresentation(String checkString, String toBeChecked) {
    // There are 2 solutions I have come up with
    return checkRotationByIteratingTwiceString(checkString, toBeChecked);
  }

  public boolean checkRotationByIteratingTwiceString(String checkString, String toBeChecked) {
    // This is a solution which is longer.
    // The time complexity is O(n). In exact word, it is O(n + 0.5n), where the 0.5n comes from iterating half the
    // string, which is the worst case in this processing.
    // The space complexity is O(1). All the variables used are constant.
    int matchedCharacterIndex = 0;
    for (int i = 0; i < checkString.length(); i++) {
      char toBeCheckedChar = toBeChecked.charAt(matchedCharacterIndex);
      char checkingChar = checkString.charAt(i);//checkString[i];
      if (toBeCheckedChar == checkingChar) {
        matchedCharacterIndex++;
      } else if (matchedCharacterIndex > 0) { // No match
        matchedCharacterIndex = 0;
      }
    }
    if (matchedCharacterIndex == 0) {
      return false;
    }
    int checkingIndex = 0;
    for (int i = matchedCharacterIndex; i < toBeChecked.length(); i++) {
      char toBeCheckedChar = toBeChecked.charAt(i);
      char checkingChar = checkString.charAt(checkingIndex);
      if (toBeCheckedChar != checkingChar) {
        return false;
      }
      checkingIndex++;
    }
    return true;
  }

  public boolean checkRotationByAppend(String checkString, String toBeChecked) {
    // This is another solution to this problem. The solution is shorter and look more elegant. I have made used of
    // the Java String internal API to execute the job I have done previously.
    // The time complexity is O(n). In exact word, it is O(2n + 2n), where we take O(2n) time to copy the string
    // twice into a new character array of double its length. The other 2n comes from checking for the contains
    // functionality, which run through the newly created doubly length string.
    // The space complexity is O(n), because we need a new string which contains twice the length of the checking
    // string for it to happen.
    // We will need to present both of this solution to the interviewer. The benefit of this solution is that it is
    // shorter, more elegant to be understood, while it suffers a bigger space complexity.

    // Append string to the back of original string
    String doubleLengthCheckString = (checkString + checkString);
    return (doubleLengthCheckString.contains(toBeChecked));
  }
}
