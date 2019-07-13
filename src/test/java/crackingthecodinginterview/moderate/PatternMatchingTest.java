package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternMatchingTest {
  private static PatternMatching patternMatching;

  @BeforeAll
  static void setUpOnce() {
    patternMatching = new PatternMatching();
  }

  @Test
  void doesValueMatchesPattern_sample1() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("catcatgocatgo", "aabab"));
  }

  @Test
  void doesValueMatchesPattern_singleLetterA() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("catcatgocatgo", "a"));
  }

  @Test
  void doesValueMatchesPattern_singleLetterB() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("catcatgocatgo", "b"));
  }

  @Test
  void doesValueMatchesPattern_oneLetterEach() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("catcatgocatgo", "ab"));
  }

  @Test
  void doesValueMatchesPattern_bThenA() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("catcatgocatgo", "ba"));
  }

  @Test
  void doesValueMatchesPattern_longStringTrue() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("eddeddfeedfeedfeedfeedfeedfeed", "aabbbbbb"));
  }

  @Test
  void doesValueMatchesPattern_longStringFalse() {
    assertEquals(false, patternMatching.doesValueMatchesPattern("eddedfedfeedffefdfefdfffeeefde", "aabbbbbb"));
  }

  @Test
  void doesValueMatchesPattern_otherStringOnlyAppearOnce() {
    assertEquals(true, patternMatching.doesValueMatchesPattern("feedfeedfeededdfeedfeedfeed", "bbbabbb"));
  }

  @Test
  void doesValueMatchesPattern_otherStringOnlyAppearOnceFalse() {
    assertEquals(false, patternMatching.doesValueMatchesPattern("feedfeedfeedfeedfeedfeedfeed", "bbbabbb"));
  }

  @Test
  void doesValueMatchesPattern_oneTypeOfStringOnlyFalse() {
    assertEquals(false, patternMatching.doesValueMatchesPattern("feedfeedfeededdfeedfeedfeed", "bbbbbbb"));
  }
}