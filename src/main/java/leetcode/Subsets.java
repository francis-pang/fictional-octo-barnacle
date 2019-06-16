package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 */
public class Subsets {
  static class Solution {
    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> subSetsList = new ArrayList<>();
      subSetsList.add(new ArrayList<>());
      // Bottom up approach
      for (int numsArrayIndex = 0; numsArrayIndex < nums.length; numsArrayIndex++) {
        int currentSubSetsListSize = subSetsList.size();
        for (int subsetsListIndex = 0; subsetsListIndex < currentSubSetsListSize; subsetsListIndex++) {
          List<Integer> subsetsWithArrayIndex = new ArrayList<>(subSetsList.get(subsetsListIndex));
          subsetsWithArrayIndex.add(nums[numsArrayIndex]);
          subSetsList.add(subsetsWithArrayIndex);
        }
      }
      return subSetsList;
    }
  }
}
