package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _3Sum {
  public List<List<Integer>> threeSum(int[] nums) {
    Map<Integer, Integer> numberCounts = new HashMap<>();
    for (int num : nums) {
      numberCounts.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
    }
    Set<Integer> keySet = numberCounts.keySet();
    ArrayList<Integer> numbers = new ArrayList<>(keySet);
    Set<List<Integer>> resultSet = new HashSet<>();
    for (int i = 0; i < numbers.size(); i++) {
      int num = numbers.get(i);
      // Single occurrences
      for (int j = i + 1; j < numbers.size(); j++) {
        addIfSumToZero(resultSet, num, numbers.get(j), numberCounts);
      }
      // Handle number with multiple occurrences
      if (numberCounts.get(num) == 1) {
        continue;
      }
      addIfSumToZero(resultSet, num, num, numberCounts);
    }
    return new ArrayList<>(resultSet);
  }

  private void addIfSumToZero(Set<List<Integer>> resultSet, int a, int b, Map<Integer, Integer> numberCounts) {
    int sum = a + b;
    int numberToLookFor = 0 - sum;
    // Last edge case, triple zero
    if (a == 0 && b == 0) {
      if (numberCounts.get(0) >= 3) {
        resultSet.add(createdSortedList(0, 0, 0));
      }
      return;
    }
    if (numberToLookFor == a && numberCounts.get(a) == 1) {
      return;
    }
    if (numberToLookFor == b && numberCounts.get(b) == 1) {
      return;
    }
    if (numberCounts.containsKey(numberToLookFor)) {
      ArrayList<Integer> foundCombination = createdSortedList(a, b, numberToLookFor);
      resultSet.add(foundCombination);
    }
  }

  private ArrayList<Integer> createdSortedList(int a, int b, int c) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(a);
    list.add(b);
    list.add(c);
    Collections.sort(list);
    return list;
  }

  public static void main(String[] args) {
    _3Sum sum = new _3Sum();
    System.out.println(sum.threeSum(new int[]{0, 0}));
  }
}

