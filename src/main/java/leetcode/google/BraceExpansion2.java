package leetcode.google;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BraceExpansion2 {
  static class Solution {
    private static final char LIST_OPENING_CHAR = '{';
    private static final char LIST_CLOSING_CHAR = '}';
    private static final char SEPARATOR = ',';

    public List<String> braceExpansionII(String expression) {
      int[] startIndex = new int[]{0};
      char[] expressionCharArray = expression.toCharArray();
      Set<Set<Character>> wordBank = new HashSet<>();

      while (startIndex[0] < expressionCharArray.length) {
        Set<Character> word = extractWordFromExpression(expressionCharArray, startIndex);
        wordBank = concatenateStringWithWordBank(wordBank, word);
      }

      // Now construct a sorted list
      List<String> wordList = constructWordListFromWordBank(wordBank);
      return wordList;
    }

    private List<String> constructWordListFromWordBank(Set<Set<Character>> wordBank) {
      List<String> wordList = new ArrayList<>();
      for (Set<Character> possibleCharacter : wordBank) {
        StringBuilder stringBuilder = new StringBuilder();
        possibleCharacter.forEach(character -> stringBuilder.append(character));
        wordList.add(stringBuilder.toString());
      }

      wordList.sort(Comparator.naturalOrder());
      return wordList;
    }

    private Set<Set<Character>> concatenateStringWithWordBank(Set<Set<Character>> wordBank, Set<Character> word) {
      Set<Set<Character>> wordBankWithNewWord = new HashSet<>();
      if (wordBank.size() == 0) { // No word inside yet, need to build
        for (char character : word) {
          Set<Character> newPossibleSet = new HashSet<>();
          newPossibleSet.add(character);
          wordBankWithNewWord.add(newPossibleSet);
        }
        return wordBankWithNewWord;
      }
      for (Set<Character> possibleCharacters : wordBank) {
        for (char character : word) {
          Set<Character> possibleCharactersClone = new HashSet<>(possibleCharacters);
          possibleCharactersClone.add(character);
          wordBankWithNewWord.add(possibleCharactersClone);
        }
      }
      return wordBankWithNewWord;
    }

    private Set<Character> extractWordFromExpression(char[] expression, int[] startIndex) {
      // We won't keep the integrity of the string
      Set<Character> wordBuilder = new HashSet<>();
      int index = startIndex[0];
      if (expression[index] == LIST_OPENING_CHAR) {
        int numberOfOpeningChar = 0;
        int numberOfClosingChar = 0;
        do {
          switch (expression[index]) {
            case LIST_OPENING_CHAR:
              numberOfOpeningChar++;
              break;
            case LIST_CLOSING_CHAR:
              numberOfClosingChar++;
            case SEPARATOR:
              break;
            default:
              wordBuilder.add(expression[index]);
          }
          index++;
        } while (index < expression.length && numberOfOpeningChar != numberOfClosingChar);
      } else {
        // Extract consecutive character not inside list
        while (index < expression.length && expression[index] != LIST_OPENING_CHAR) {
          wordBuilder.add(expression[index]);
          index++;
        }
      }
      startIndex[0] = index;
      return wordBuilder;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    List<String> answer = solution.braceExpansionII("{{a,z},a{b,c},{ab,z}}");
  }
}
