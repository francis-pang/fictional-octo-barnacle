package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class SplitArrayLargestSum {
  public static void main(String[] args) {
    int[] nums = new int[]{9, 16, 19, 5, 17};
    Solution solution = new Solution();
    System.out.println(solution.splitArray(nums, 3));
  }

  static class Solution {
    public int splitArray(int[] nums, int m) {
      // Build the single partition maximum
      Map<RangePartition, Integer> minimumSumOfPartitionsByRange = new HashMap<>();
      buildSinglePartitionSumMap(minimumSumOfPartitionsByRange, nums);
      // Build the maximum partition minimum of maximum
      buildMultiPartitionSumMap(minimumSumOfPartitionsByRange, nums, m);
      return minimumSumOfPartitionsByRange.get(new RangePartition(new Range(0, nums.length - 1), m));
    }

    private void buildSinglePartitionSumMap(Map<RangePartition, Integer> minimumSumOfPartitionsByRange, int[] array) {
      for (int rangeStart = 0; rangeStart < array.length; rangeStart++) {
        int sum = 0;
        for (int rangeEnd = rangeStart; rangeEnd < array.length; rangeEnd++) {
          sum += array[rangeEnd];
          minimumSumOfPartitionsByRange.put(new RangePartition(new Range(rangeStart, rangeEnd), 1), sum);
        }
      }
    }

    private void buildMultiPartitionSumMap(Map<RangePartition, Integer> minimumSumOfPartitionsByRange, int[] array,
                                           int partitionSize) {
      for (int buildPartitionSize = 2; buildPartitionSize <= partitionSize; buildPartitionSize++) {
        int numberOfUnbuiltPartition = partitionSize - buildPartitionSize;
        for (int end = buildPartitionSize - 1; end < array.length - numberOfUnbuiltPartition; end++) {
          Range range = new Range(0, end);
          RangePartition rangePartition = new RangePartition(range, buildPartitionSize);
          int minLargestSumSubarrayForRange = findMinLargestSumSubarrayForRange(range, minimumSumOfPartitionsByRange,
              buildPartitionSize);
          minimumSumOfPartitionsByRange.put(rangePartition, minLargestSumSubarrayForRange);
        }
      }
    }

    private int findMinLargestSumSubarrayForRange(Range range,
                                                  Map<RangePartition, Integer> minimumSumOfPartitionsByRange,
                                                  int partitionSize) {
      int minLargestSum = Integer.MAX_VALUE;
      for (int lastPartitionStart = range.left + (partitionSize - 1); lastPartitionStart <= range.right; lastPartitionStart++) {
        Range lastPartitionRange = new Range(lastPartitionStart, range.right);
        int lastPartitionSum = minimumSumOfPartitionsByRange.get(new RangePartition(lastPartitionRange, 1));
        Range remainingPartitionsRange = new Range(range.left, lastPartitionStart - 1);
        int remainingPartitionsMinSum = minimumSumOfPartitionsByRange.getOrDefault(new RangePartition(remainingPartitionsRange
            , partitionSize - 1), -1);
        int maxSum = Math.max(lastPartitionSum, remainingPartitionsMinSum);
        if (maxSum < minLargestSum) {
          minLargestSum = maxSum;
        }
      }
      return minLargestSum;
    }

    class Range {
      public int left;
      public int right;

      public Range(int left, int right) {
        this.left = left;
        this.right = right;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null && !o.getClass().equals(getClass())) {
          return false;
        }
        Range range = (Range) o;
        return left == range.left && right == range.right;
      }

      @Override
      public int hashCode() {
        return Objects.hash(left, right);
      }

      @Override
      public String toString() {
        return new StringJoiner(", ", Range.class.getSimpleName() + "[", "]")
            .add("left=" + left)
            .add("right=" + right)
            .toString();
      }
    }

    class RangePartition {
      public Range range;
      public int numberOfPartition;

      public RangePartition(Range range, int numberOfPartition) {
        this.range = range;
        this.numberOfPartition = numberOfPartition;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null && !o.getClass().equals(getClass())) {
          return false;
        }
        RangePartition that = (RangePartition) o;
        return numberOfPartition == that.numberOfPartition &&
            Objects.equals(range, that.range);
      }

      @Override
      public int hashCode() {
        return Objects.hash(range, numberOfPartition);
      }

      @Override
      public String toString() {
        return new StringJoiner(", ", RangePartition.class.getSimpleName() + "[", "]")
            .add("range=" + range)
            .add("numberOfPartition=" + numberOfPartition)
            .toString();
      }
    }
  }
}
