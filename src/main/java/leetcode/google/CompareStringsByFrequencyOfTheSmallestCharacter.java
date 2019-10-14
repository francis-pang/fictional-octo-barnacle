package leetcode.google;

public class CompareStringsByFrequencyOfTheSmallestCharacter {
  class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
      // Since every item in the query need to be compare against each word in the words list, we can pre-compute the
      // word list first to know the frequency of each word first
      int[] cumulativeFrequenciesArray = populateCumulativeFrequenciesArray(words);
      int[] answers = populateAnswer(queries, cumulativeFrequenciesArray);
      return answers;
    }

    private int[] populateAnswer(String[] queries, int[] cumulativeFrequenciesArray) {
      int[] answers = new int[queries.length];
      for (int index = 0; index < queries.length; index++) {
        String query = queries[index];
        int frequencyOfSmallestCharacter = calculateFrequencyOfSmallestCharacter(query);
        answers[index] = (frequencyOfSmallestCharacter == 10)
            ? 0
            : cumulativeFrequenciesArray[frequencyOfSmallestCharacter + 1];
      }
      return answers;
    }

    private int[] populateCumulativeFrequenciesArray(String[] words) {
      // Since we know the maximum length of each word is 10 character, we will just use a 10 length array
      int[] occurrencesOfFrequencyOfSmallestChar = new int[11];
      for (String word : words) {
        int frequencyOfSmallestCharacter = calculateFrequencyOfSmallestCharacter(word);
        occurrencesOfFrequencyOfSmallestChar[frequencyOfSmallestCharacter]++;
      }
      for (int index = 9; index > 0; index--) {
        occurrencesOfFrequencyOfSmallestChar[index] += occurrencesOfFrequencyOfSmallestChar[index + 1];
      }
      return occurrencesOfFrequencyOfSmallestChar;
    }

    private int calculateFrequencyOfSmallestCharacter(String word) {
      char[] wordCharacter = word.toCharArray();
      int count = 0;
      char smallestChar = wordCharacter[0];
      for (int index = 0; index < word.length(); index++) {
        char character = word.charAt(index);
        if (character < smallestChar) {
          smallestChar = character;
          count = 1;
        } else if (character == smallestChar) {
          count++;
        }
      }
      return count;
    }
  }
}
