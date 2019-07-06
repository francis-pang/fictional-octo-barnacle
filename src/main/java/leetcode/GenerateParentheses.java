package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.generateParenthesis(3));
  }

  static class Solution {
    public List<String> generateParenthesis(int n) {
      List<String> parenthesesCombinations = new ArrayList<>();
      // Use n * 2 because we know that the maximum length of the string generated is "()" [length 2] * number of
      // parentheses
      char[] string = new char[n * 2];
      populateParenthesisCombination(parenthesesCombinations, n, n, string, 0);
      return parenthesesCombinations;
    }

    // Initially the idea is to use StringBuilder, but because StringBuilder is passed by reference, the string will
    // be added in the deeper call stack, and when returned, the stringBuilder passed into the next recursive call is
    // already inaccurate, so it is best to stick to character array and int as index.
    private void populateParenthesisCombination(List<String> combinations, int remainingLeftParenthesis,
                                                int remainingRightParenthesis, char[] parenthese, int index) {
      if (remainingLeftParenthesis < 0 || remainingRightParenthesis < 0 || remainingRightParenthesis < remainingLeftParenthesis) {
        return;
      }
      if (remainingLeftParenthesis == 0 && remainingRightParenthesis == 0) {
        combinations.add(new String(parenthese));
      } else {
        final char OPEN_PARENTHESIS = '(';
        parenthese[index] = OPEN_PARENTHESIS;
        populateParenthesisCombination(combinations, remainingLeftParenthesis - 1, remainingRightParenthesis,
            parenthese, index + 1);

        final char CLOSE_PARENTHESIS = ')';
        parenthese[index] = CLOSE_PARENTHESIS;
        populateParenthesisCombination(combinations, remainingLeftParenthesis, remainingRightParenthesis - 1,
            parenthese, index + 1);
      }
    }
  }
}
