package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ValidWordSquare {
  public boolean validWordSquare(List<String> words) {
    // Determine max length
    int maxLength = words.size();
    for (String word : words) {
      if (maxLength < word.length()) {
        maxLength = word.length();
      }
    }
    for (int line = 0; line < maxLength; line++) {
      for (int col = 0; col < maxLength; col++) {
        // left word
        char leftChar = Character.MIN_VALUE;
        if (line < words.size()) {
          String leftCompare = words.get(line);
          if (col < leftCompare.length()) {
            leftChar = leftCompare.charAt(col);
          }
        }

        char rightChar = Character.MIN_VALUE;
        if (col < words.size()) {
          String rightCompare = words.get(col);
          if (line < rightCompare.length()) {
            rightChar = rightCompare.charAt(line);
          }
        }
        if (leftChar != rightChar) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<>();
    words.add("ball");
    words.add("area");
    words.add("read");
    words.add("lady");

    ValidWordSquare validWordSquare = new ValidWordSquare();
    System.out.println(validWordSquare.validWordSquare(words));
  }
}