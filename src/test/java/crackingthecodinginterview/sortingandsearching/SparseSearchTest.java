package crackingthecodinginterview.sortingandsearching;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SparseSearchTest {
  private static SparseSearch sparseSearch;

  @BeforeAll
  static void setUpOnce() {
    sparseSearch = new SparseSearch();
  }

  @Test
  void findLocationOfStringIn_nullTarget() {
    assertEquals(-1, sparseSearch.findLocationOfStringIn(new String[]{"a", "b", "ddd"}, null));
  }

  @Test
  void findLocationOfStringIn_nullArray() {
    assertEquals(-1, sparseSearch.findLocationOfStringIn(null, "target"));
  }

  @Test
  void findLocationOfStringIn_SingleItemArrayFound() {
    assertEquals(0, sparseSearch.findLocationOfStringIn(new String[]{"aa"}, "aa"));
  }

  @Test
  void findLocationOfStringIn_SingleItemArrayNotFound() {
    assertEquals(-1, sparseSearch.findLocationOfStringIn(new String[]{"bb"}, "target"));
  }

  @Test
  void findLocationOfStringIn_EmptyStringNotFound() {
    assertEquals(-1, sparseSearch.findLocationOfStringIn(new String[]{"bb"}, ""));
  }

  @Test
  void findLocationOfStringIn_EmptyStringFound() {
    assertEquals(1, sparseSearch.findLocationOfStringIn(new String[]{"bb", "", "bb"}, ""));
  }

  @Test
  void findLocationOfStringIn_DuplicateStringWithoutSpaceInBetweenFound() {
    assertEquals(0, sparseSearch.findLocationOfStringIn(new String[]{"bb", "", "bb"}, "bb"));
  }

  @Test
  void findLocationOfStringIn_DuplicateStringWithoutSpaceInBetweenNotFound() {
    assertEquals(-1, sparseSearch.findLocationOfStringIn(new String[]{"bb", "", "bb"}, "ab"));
  }

  @Test
  void findLocationOfStringIn_RegularLongStringFound() {
    assertEquals(4, sparseSearch.findLocationOfStringIn(new String[]{"at", "", "", "", "ball", "", "", "car",
            "", "", "dad", "", ""},
        "ball"));
  }
}