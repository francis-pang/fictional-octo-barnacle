package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
      List<List<Integer>> subPermutations = permute(subNums);

      for (List<Integer> permutation : subPermutations) {
        for (int i = 0; i <= permutation.size(); i++) {
          List<Integer> newList = cloneList(permutation);
          newList.add(i, insertingNum);
          permutations.add(newList);
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
