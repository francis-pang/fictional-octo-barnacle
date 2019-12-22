package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordLadderTest {

  private WordLadder wordLadder = new WordLadder();

  @Test
  void ladderLength_5() {
    List<String> wordList = new ArrayList<>();
    wordList.addAll(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    assertEquals(5, wordLadder.ladderLength("hit", "cog", wordList));
  }

  @Test
  void ladderLength_unreachable() {
    List<String> wordList = new ArrayList<>();
    wordList.addAll(Arrays.asList("hot", "dot", "dog", "lot", "log"));
    assertEquals(0, wordLadder.ladderLength("hit", "cog", wordList));
  }

  @Test
  void ladderLength_4() {
    List<String> wordList = new ArrayList<>();
    wordList.addAll(Arrays.asList("dot", "dog", "lot", "log", "cog", "tog"));
    assertEquals(4, wordLadder.ladderLength("hot", "tog", wordList));
  }

  @Test
  void ladderLength_3() {
    List<String> wordList = new ArrayList<>();
    wordList.addAll(Arrays.asList("hot", "dog", "dot"));
    assertEquals(3, wordLadder.ladderLength("hot", "dog", wordList));
  }
}