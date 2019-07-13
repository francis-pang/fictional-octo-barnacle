package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SubSort {
  public SortRange findMinimumSortRange(int[] integers) {
    SortRange sortRange = new SortRange();
    Map<Integer, Integer> valuesByIndexMap = new HashMap<>();

    int smallestUnsortedNumber = Integer.MAX_VALUE;
    for (int counter = 1; counter < integers.length; counter++) {
      valuesByIndexMap.putIfAbsent(integers[counter], counter);
      if (integers[counter] < integers[counter - 1] &&
          integers[counter] < smallestUnsortedNumber) {
        smallestUnsortedNumber = integers[counter];
      }
    }

    if (smallestUnsortedNumber != Integer.MAX_VALUE) {
      return new SortRange();
    } else {
      Set<Integer> values = valuesByIndexMap.keySet();
      int biggestSorted = Integer.MIN_VALUE;
      for (Integer value : values) {
        if (value < smallestUnsortedNumber && value > biggestSorted) {
          biggestSorted = value;
        }
      }
      sortRange.startPoint = valuesByIndexMap.get(biggestSorted);
    }

    //This can be combined with the previous step, but to maintain clarity, better to separate it
    valuesByIndexMap = new HashMap<>();
    int biggestUnsortedNumber = Integer.MIN_VALUE;
    for (int counter = integers.length - 2; counter > 0; counter--) {
      valuesByIndexMap.putIfAbsent(integers[counter], counter);
      if (integers[counter] > integers[counter + 1] &&
          integers[counter] > biggestUnsortedNumber) {
        biggestUnsortedNumber = integers[counter];
      }
    }

    Set<Integer> values = valuesByIndexMap.keySet();
    int smallestSorted = Integer.MAX_VALUE;
    for (Integer value : values) {
      if (value > biggestUnsortedNumber && value < smallestSorted) {
        smallestSorted = value;
      }
    }
    sortRange.endPoint = valuesByIndexMap.get(smallestSorted);
    return sortRange;
  }

  class SortRange {
    int startPoint;
    int endPoint;
  }
}
