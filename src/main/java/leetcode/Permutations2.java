package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations2 {
  class Solution {
    private Set<List<Integer>> permutationSet = new HashSet<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
      if (nums.length == 0) {
        return new ArrayList<>();
      }
      List<List<Integer>> permutations = new ArrayList<>();
      if (nums.length == 1) {
        List<Integer> singleElement = new ArrayList<>();
        singleElement.add(nums[0]);
        permutations.add(singleElement);
        return permutations;
      }

      // Create nums with 1 less element
      int[] subNums = new int[nums.length - 1];
      for (int i = 0; i < nums.length - 1; i++) {
        subNums[i] = nums[i];
      }
      int insertingNum = nums[nums.length - 1];
      List<List<Integer>> subPermutations = permuteUnique(subNums);

      for (List<Integer> permutation : subPermutations) {
        for (int i = 0; i <= permutation.size(); i++) {
          List<Integer> newList = cloneList(permutation);
          newList.add(i, insertingNum);
          if (permutationSet.add(newList)) {
            permutations.add(newList);
          }
        }
      }
      return permutations;
    }

    private List<Integer> cloneList(List<Integer> clonee) {
      List<Integer> clonedList = new ArrayList<>();
      for (Integer integer : clonee) {
        clonedList.add(Integer.valueOf(integer));
      }
      return clonedList;
    }
  }
}
