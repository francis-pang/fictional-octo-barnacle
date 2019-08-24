package leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInRotatedSortedArrayTest {
  private static SearchInRotatedSortedArray.Solution solution;

  @BeforeAll
  static void setUpOnce() {
    solution = new SearchInRotatedSortedArray.Solution();
  }

  @Test
  void search_leetcode_sample() {
    assertEquals(0, solution.search(new int[]{3, 1}, 3));
  }
}