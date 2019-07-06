package leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsetsTest {
  private static Subsets.Solution solution;

  @BeforeAll
  static void setUpOnce() {
    solution = new Subsets.Solution();
  }

  @Test
  void subsets_empty() {
    List<List<Integer>> expectedLists = new ArrayList<>();
    expectedLists.add(new ArrayList<>(Arrays.asList(new Integer[]{})));
    List<List<Integer>> actualLists = solution.subsets(new int[]{});
    assertTrue(expectedLists.size() == actualLists.size() &&
        actualLists.containsAll(expectedLists) && expectedLists.containsAll(actualLists));
  }

  @Test
  void subsets_123() {
    List<List<Integer>> expectedLists = new ArrayList<>();
    expectedLists.add(new ArrayList<>(Arrays.asList(new Integer[]{})));
    expectedLists.add(new ArrayList<>(Arrays.asList(1)));
    expectedLists.add(new ArrayList<>(Arrays.asList(2)));
    expectedLists.add(new ArrayList<>(Arrays.asList(3)));
    expectedLists.add(new ArrayList<>(Arrays.asList(1, 2)));
    expectedLists.add(new ArrayList<>(Arrays.asList(1, 3)));
    expectedLists.add(new ArrayList<>(Arrays.asList(2, 3)));
    expectedLists.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
    List<List<Integer>> actualLists = solution.subsets(new int[]{1, 2, 3});
    System.out.println(actualLists);
    assertTrue(expectedLists.size() == actualLists.size() &&
        actualLists.containsAll(expectedLists) && expectedLists.containsAll(actualLists));
  }

}