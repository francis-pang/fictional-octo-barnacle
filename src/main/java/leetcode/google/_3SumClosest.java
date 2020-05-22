package leetcode.google;

import java.util.Map;
import java.util.TreeMap;

public class _3SumClosest {
  public int threeSumClosest(int[] array, int target) {
    // Try using two pointers solution
    sortArray(array);

    // Once sorted, can use two pointer to find your best match
    int closestSum = Integer.MAX_VALUE;
    int smallestDifference = Integer.MAX_VALUE;
    for (int i = 0; i < array.length - 2; i++) {
      // Try to do this with linear search
      int baseValue = array[i];
      int leftPointer = i + 1;
      int rightPointer = array.length - 1;
      while (leftPointer + 1 <= rightPointer) {
        int threeSum = baseValue + array[leftPointer] + array[rightPointer];
        int difference = Math.abs(target - threeSum);
        if (difference < smallestDifference) {
          closestSum = threeSum;
          smallestDifference = difference;
        }
        if (threeSum == target) { // Found perfect sum
          return target;
        } else if (threeSum > target) {
          rightPointer--;
        } else { // threeSum < target
          leftPointer++;
        }
      }
    }
    return closestSum;
  }

  private void sortArray(int[] array) {
    quickSortArray(array, 0, array.length - 1);
  }

  private void quickSortArray(int[] array, int left, int right) {
    if (right <= left) {
      return;
    }
    int pivot = right;
    int partitionPoint = left;
    for (int i = left; i < right; i++) {
      if (array[i] < array[pivot]) {
        swap(array, i, partitionPoint);
        partitionPoint++;
      }
    }
    swap(array, partitionPoint, pivot);
    quickSortArray(array, left, partitionPoint - 1);
    quickSortArray(array, partitionPoint + 1, right);
  }

  private void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  private int binarySearchForClosestSum(int[] array, int target) {
    TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
    for (int element : array) {
      sortedMap.compute(element, (k, v) -> (v == null) ? 1 : v + 1);
    }

    int nearestNumber = Integer.MAX_VALUE;
    int smallestDiff = Integer.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
      // Take out first, later put back
      sortedMap.compute(array[i], (k, v) -> v - 1);
      for (int j = i + 1; j < array.length; j++) {
        int pairSum = array[i] + array[j];
        sortedMap.compute(array[j], (k, v) -> v - 1);
        int thirdNumberTarget = target - pairSum;
        // find exact match
        if (sortedMap.getOrDefault(thirdNumberTarget, 0) > 0) {
          return target;
        }
        // By this time, we know there isn’t exact match
        int ceiling = thirdNumberTarget + 1;
        Map.Entry<Integer, Integer> minEntry;
        do {
          minEntry = sortedMap.ceilingEntry(ceiling);
          if (minEntry != null) {
            ceiling = minEntry.getKey() + 1;
          }
        } while (minEntry != null && minEntry.getValue() == 0);

        int floor = thirdNumberTarget - 1;
        Map.Entry<Integer, Integer> maxEntry;
        do {
          maxEntry = sortedMap.floorEntry(floor);
          if (maxEntry != null) {
            floor = maxEntry.getKey() - 1;
          }
        } while (maxEntry != null && maxEntry.getValue() == 0);

        int actualDiff;
        int sum;
        if (minEntry == null) {
          sum = pairSum + maxEntry.getKey();
          actualDiff = Math.abs(sum - target);
        } else if (maxEntry == null) {
          sum = pairSum + minEntry.getKey();
          actualDiff = Math.abs(sum - target);
        } else { // both maxEntry and minEntry are not null
          int minSum = pairSum + maxEntry.getKey();
          int maxSum = pairSum + minEntry.getKey();
          int minDiff = Math.abs(minSum - target);
          int maxDiff = Math.abs(maxSum - target);
          if (minDiff <= maxDiff) {
            actualDiff = minDiff;
            sum = minSum;
          } else {
            actualDiff = maxDiff;
            sum = maxSum;
          }
        }
        if (actualDiff < smallestDiff) {
          nearestNumber = sum;
          smallestDiff = actualDiff;
        }
        sortedMap.compute(array[j], (k, v) -> v + 1);
      }
      sortedMap.compute(array[i], (k, v) -> v + 1);
    }
    return nearestNumber;
  }

  public static void main(String[] args) {
    _3SumClosest sumClosest = new _3SumClosest();
    System.out.println(sumClosest.threeSumClosest(new int[]{-5, 5, 1, -10, 7}, -5));
    System.out.println(sumClosest.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
  }
}

