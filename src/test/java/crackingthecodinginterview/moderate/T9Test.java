package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class T9Test {
  private static T9 t9;

  @BeforeAll
  static void setUpOnce() {
    t9 = new T9();
  }

  @Test
  void getMatchingWordsFromDigits_sample1() {
    Set<String> wordList = new HashSet<>();
    wordList.add("tree");
    wordList.add("rubb");
    wordList.add("used");
    wordList.add("empty");

    assertThat(t9.getMatchingWordsFromDigits("8733", wordList))
        .containsExactlyInAnyOrder("tree", "used");
  }
}