package leetcode.google;

public class _3SumClosest {
  public int threeSumClosest(int[] nums, int target) {
    TreeMap<Integer, Integer> sortedMap = new TreeSet<>();
    for (int element : array) {
      sortedMap.compute(element, (k, v) -> (v == null) ? 1 : v + 1);
    }

    int closestDiff = Integer.MAX_VALUE;
    int[] closestArray = new int[3];
    for (int i = 0; i < array.length; i++) {
      // Take out first, later put back
      sortedMap.compute(array[i], (k, v) -> v--);
      for (int j = i + 1; j < array.length; j++) {
        int pairSum = array[i] + array[j];
        sortedMap.compute(array[j], (k, v) -> v--);
        int diff = target - pairSum;
        // find exact match
        if (sortedMap.getOrDefault(diff, 0) > 0) {
          closestArray = new int[]{array[i], array[j], diff};
          return closestArray;
        }
        // By this time, we know there isn’t exact match
        int ceiling = diff;
        Map.Entry<Integer, Integer> maxEntry;
        do {
          maxEntry; =sortedMap.ceilingEntry(ceiling);
          if (maxEntry != null) {
            ceiling = maxEntry.getKey() + 1;
          }
        } while (maxEntry != null && maxEntry.getValue() > 0);

        int floor = diff;
        Map.Entry<Integer, Integer> minEntry;
        do {
          maxEntry; =sortedMap.floorEntry(floor);
          if (minEntry != null) {
            floor = minEntry.getKey() - 1;
          }
        } while (maxEntry != null && maxEntry.getValue() == 0);

        int actualDiff;
        int[] close;
        if (maxEntry == null) {
          int sum = pairSum + minEntry.getKey();
          actualDiff = Math.abs(sum - target);
          close = new int[]{array[i], array[j], minEntry.getKey()};
        } else if (minEntry == null) {
          int sum = pairSum + maxEntry.getKey();
          actualDiff = Math.abs(sum - target);
          close = new int[]{array[i], array[j], maxEntry.getKey()};
        } else {
          int minSum = pairSum + minEntry.getKey();
          int maxSum = pairSum + maxEntry.getKey();
          int minDiff = Math.abs(minSum - target);
          int maxDiff = Math.abs(maxSum - target);
          if (minDiff >= maxDiff) {
            diff = minDiff;
            close = new int[]{array[i], array[j], minEntry.getKey()};
          } else {
            diff = maxDiff;
            close = new int[]{array[i], array[j], maxEntry.getKey()};
          }
        }
        if (diff < closestDiff) {
          closestDiff = diff;
          cloestArray = close;
        }
        sortedMap.compute(array[j], (k, v) -> v++);
      }
      sortedMap.compute(array[i], (k, v) -> v++);
    }
    return closestArray;

  }
}

