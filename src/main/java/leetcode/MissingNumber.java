package leetcode;

public class MissingNumber {
  class Solution {
    private static final int NUMBER_NOT_FOUND = 0;

    public int missingNumber(int[] nums) {
      if (nums == null) {
        return NUMBER_NOT_FOUND;
      }
      if (nums.length == 0) {
        return NUMBER_NOT_FOUND;
      }

      int sum = 0;
      int supposedSum = 0;
      for (int index = 0; index < nums.length; index++) {
        sum += nums[index];
        supposedSum += index;
      }
      supposedSum += nums.length;
      return supposedSum - sum;
    }
  }
}
