package leetcode.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // Use to store a set of entry using the difference to target as key, and the index as value
        Map<Integer, Integer> differenceMap = new HashMap<Integer, Integer>();
        for (int counter = 0; counter < nums.length; counter++) {
            int difference = target - nums[counter];
            if (differenceMap.get(difference) == null) {
                differenceMap.put(nums[counter], counter);
            } else {
                int answerIndex = differenceMap.get(difference);
                return new int[]{answerIndex, counter};
            }
        }
        return new int[]{};
    }
}
