package leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class LicenseKeyFormatting {
  public String licenseKeyFormatting(String string, int k) {
    List<Character> answer = new ArrayList<>();
    int stringLength = 0;
    for (int i = string.length() - 1; i >= 0; i--) {
      if (string.charAt(i) != '-') {
        if (stringLength > 0 && stringLength % k == 0) {
          answer.add('-');
        }
        answer.add(string.charAt(i));
        stringLength++;
      }
    }
    return reverseString(answer);
  }

  private String reverseString(List<Character> answerReverse) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = answerReverse.size() - 1; i >= 0; i--) {
      char character = answerReverse.get(i);
      character = Character.toUpperCase(character);
      stringBuilder.append(character);
    }
    return stringBuilder.toString();
  }
}