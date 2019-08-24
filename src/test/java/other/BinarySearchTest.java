package other;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {
  private static BinarySearch binarySearch;

  @BeforeAll
  static void setUpOnce() {
    binarySearch = new BinarySearch();
  }

  @Test
  void binarySearch_1() {
    assertEquals(0, binarySearch.binarySearch(new int[]{1}, 1));
  }

  @Test
  void binarySearch_null() {
    assertEquals(-1, binarySearch.binarySearch(null, 7));
  }
}