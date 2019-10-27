package facebook.abcs.hashtables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveDuplicatesTest {

  @Test
  void removeDuplicates_zeroLength() {
    assertEquals("", RemoveDuplicates.removeDuplicates(""));
  }

  @Test
  void removeDuplicates_null() {
    assertEquals("", RemoveDuplicates.removeDuplicates(null));
  }

  @Test
  void removeDuplicates_noDup() {
    assertEquals("qwerty", RemoveDuplicates.removeDuplicates("qwerty"));
  }

  @Test
  void removeDuplicates_allDup() {
    assertEquals("q", RemoveDuplicates.removeDuplicates("qqqqqqq"));
  }

  @Test
  void removeDuplciates_mixDup() {
    assertEquals("cba", RemoveDuplicates.removeDuplicates("ccbabacc"));
  }
}