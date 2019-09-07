package leetcode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {

  public static void main(String[] args) {
    Solution solution = new Solution();
    final String question = "{a,b,c}d{e,f}";
    Instant start = Instant.now();
    solution.convertToSortedList((ArrayList<String>) solution.getAllExpansion(question));
    System.out.println("Offered solution takes " + Instant.from(start).getNano() + "ns.");
    start = Instant.now();
    solution.expand(question);
    System.out.println("    Own solution takes " + Instant.from(start).getNano() + "ns.");
  }
  static class Solution {
    private static final String[] EMPTY_LIST = new String[]{""};
    private static final char START_OF_LIST = '{';
    private static final char END_OF_LIST = '}';
    private static final char LIST_SEPARATOR = ',';

    /**
     * This answer is taken from the leetcode contributor(https://leetcode.com/discuss/interview-question/algorithms/175553/Google-or-Onsite-or-Brace-expansion/290999)
     */
    public List<String> getAllExpansion(String string) {
      List<String> result = new ArrayList<>();
      depthFirstSearch(string, 0, "", result);
      return result;
    }

    private void depthFirstSearch(String string, int start, String cursor, List<String> result) {
      if (start == string.length()) {
        result.add(cursor);
        return;
      }
      int left = string.indexOf('{', start);
      int right = string.indexOf('}', start);
      if (left == -1) { // No groups with more than 1 options of word found in string from 'start' position
        result.add(cursor + string.substring(start));
        return;
      }
      // At this point, we need know that there are
      String substring = string.substring(left + 1, right);
      String[] strings = substring.split(",");
      /**
       * The cursor is used to store the prefix to be passed into the backtracking algorithm.
       * In each pass, the parameter <i>cursor</i> = cursor + string.substring(start, left) + str, which means to take
       * all the prefix and all the single option letter + one options from the current iterating group of words.
       * This solution is slightly better because it skips all the unnecessary processing of single option groups by
       * grouping them together.
       **/
      for (String str : strings) {
        depthFirstSearch(string, right + 1, cursor + string.substring(start, left) + str, result);
      }
    }

    public String[] expand(String S) {
      if (S == null || S.length() == 0) {
        return EMPTY_LIST;
      }
      ArrayList<String[]> listOfGroupOfWords = constructListOfWords(S);
      return formAllCombinationOfWordsFromList(listOfGroupOfWords);
    }

    private String[] formAllCombinationOfWordsFromList(final ArrayList<String[]> listOfGroupOfWords) {
      ArrayList<String> listOfWords = new ArrayList<>();
      for (String[] groupOfWords : listOfGroupOfWords) {
        listOfWords = combineTwoGroupOfWords(listOfWords, groupOfWords);
      }
      return convertToSortedList(listOfWords);
    }

    public String[] convertToSortedList(ArrayList<String> stringList) {
      Collections.sort(stringList);
      String[] strings = new String[stringList.size()];
      for (int index = 0; index < strings.length; index++) {
        strings[index] = stringList.get(index);
      }
      return strings;
    }

    private ArrayList<String> combineTwoGroupOfWords(ArrayList<String> existingCombination,
                                                     String[] newGroupOfWords) {
      ArrayList<String> combinedList = new ArrayList<>();
      for (String character : newGroupOfWords) {
        if (!existingCombination.isEmpty()) {
          for (String word : existingCombination) {
            combinedList.add(word + character);
          }
        } else {
          combinedList.add(character);
        }
      }
      return combinedList;
    }

    private ArrayList<String[]> constructListOfWords(final String string) {
      ArrayList<String[]> listOfWords = new ArrayList<>();
      int cursorPosition = 0;
      while (cursorPosition < string.length()) {
        String[] words;
        if (string.charAt(cursorPosition) == START_OF_LIST) {
          int endOfListPosition = string.indexOf(END_OF_LIST, cursorPosition);
          words = string.substring(cursorPosition + 1, endOfListPosition).split(",");
          cursorPosition = endOfListPosition + 1;
        } else { // Single word
          words = new String[1];
          words[0] = Character.toString(string.charAt(cursorPosition));
          cursorPosition++;
        }
        listOfWords.add(words);
      }
      return listOfWords;
    }
  }
}
