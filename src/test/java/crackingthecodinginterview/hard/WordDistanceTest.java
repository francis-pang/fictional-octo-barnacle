package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordDistanceTest {
  private static WordDistance wordDistance;
  private static final String FIRST_WORD = "wordA";
  private static final String SECOND_WORD = "wordB";

  @BeforeEach
  void setUp() {
    wordDistance = new WordDistance();
  }


  @Test
  void findShortestDistanceBetweenWords_1() {
    String[] stringArray = new String[30];
    for (int i = 0; i < 30; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    assertEquals(10, wordDistance.findShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void findShortestDistanceBetweenWords_LastIterationIsShortest() {
    final int LENGTH_OF_STRING = 35;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    assertEquals(5, wordDistance.findShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void findShortestDistanceBetweenWords_LastIterationIsShortest2() {
    final int LENGTH_OF_STRING = 45;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    assertEquals(4, wordDistance.findShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void findShortestDistanceBetweenWords_BreakOfWordAtTheEnd() {
    final int LENGTH_OF_STRING = 45;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    stringArray[41] = SECOND_WORD;
    assertEquals(4, wordDistance.findShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void findShortestDistanceBetweenWords_ShortestAtNewLastIteration() {
    final int LENGTH_OF_STRING = 60;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    stringArray[41] = SECOND_WORD;
    stringArray[44] = FIRST_WORD;
    assertEquals(3, wordDistance.findShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }


  @Test
  void repeatFindShortestDistanceBetweenWords_1() {
    String[] stringArray = new String[30];
    for (int i = 0; i < 30; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    assertEquals(10, wordDistance.repeatFindShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void repeatFindShortestDistanceBetweenWords_LastIterationIsShortest() {
    final int LENGTH_OF_STRING = 35;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    assertEquals(5, wordDistance.repeatFindShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void repeatFindShortestDistanceBetweenWords_LastIterationIsShortest2() {
    final int LENGTH_OF_STRING = 45;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    assertEquals(4, wordDistance.repeatFindShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void repeatFindShortestDistanceBetweenWords_BreakOfWordAtTheEnd() {
    final int LENGTH_OF_STRING = 45;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    stringArray[41] = SECOND_WORD;
    assertEquals(4, wordDistance.repeatFindShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }

  @Test
  void repeatFindShortestDistanceBetweenWords_ShortestAtNewLastIteration() {
    final int LENGTH_OF_STRING = 60;
    String[] stringArray = new String[LENGTH_OF_STRING];
    for (int i = 0; i < LENGTH_OF_STRING; i++) {
      stringArray[i] = Integer.toString(i);
    }
    stringArray[9] = FIRST_WORD;
    stringArray[19] = FIRST_WORD;
    stringArray[29] = SECOND_WORD;
    stringArray[34] = FIRST_WORD;
    stringArray[38] = SECOND_WORD;
    stringArray[41] = SECOND_WORD;
    stringArray[44] = FIRST_WORD;
    assertEquals(3, wordDistance.repeatFindShortestDistanceBetweenWords(stringArray, FIRST_WORD, SECOND_WORD));
  }
}
