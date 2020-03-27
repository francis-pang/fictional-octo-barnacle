package leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInRotatedSortedArrayTest {
  private static SearchInRotatedSortedArray searchInRotatedSortedArray;

  @BeforeAll
  static void setUpOnce() {
    searchInRotatedSortedArray = new SearchInRotatedSortedArray();
  }

  @Test
  void search_leetcode_sample() {
    assertEquals(0, searchInRotatedSortedArray.search(new int[]{3, 1}, 3));
  }
}