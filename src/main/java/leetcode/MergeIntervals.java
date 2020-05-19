package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    final int START_TIME = 0;
    final int END_TIME = 1;
    if (intervals.length == 0) {
      return intervals;
    }
    sortArray(intervals);
    List<int[]> mergedIntervals = new ArrayList<>();
    int[] previousInterval = intervals[0];
    for (int i = 1; i < intervals.length; i++) {
      int[] interval = intervals[i];
      if (interval[START_TIME] <= previousInterval[END_TIME]) {
        int longerEndTime = (interval[END_TIME] < previousInterval[END_TIME]) ? previousInterval[END_TIME] :
            interval[END_TIME];
        previousInterval[END_TIME] = longerEndTime;
      } else {
        mergedIntervals.add(previousInterval);
        previousInterval = interval;
      }
    }
    mergedIntervals.add(previousInterval);
    return mergedIntervals.toArray(new int[0][]);
  }

  private void sortArray(int[][] intervals) {
    quickSortArray(intervals, 0, intervals.length - 1);
  }

  private void quickSortArray(int[][] array, int left, int right) {
    if (left >= right) {
      return;
    }
    int[] pivotValue = array[right];
    int partitionPoint = left;
    for (int currentPointer = left; currentPointer < right; currentPointer++) {
      int[] currentValue = array[currentPointer];
      if (currentValue[0] < pivotValue[0] ||
          (currentValue[0] == pivotValue[0] && currentValue[1] < pivotValue[1])) {
        swap(array, partitionPoint, currentPointer);
        partitionPoint++;
      }
    }
    swap(array, partitionPoint, right);
    quickSortArray(array, left, partitionPoint - 1);
    quickSortArray(array, partitionPoint + 1, right);
  }

  private void swap(int[][] array, int position1, int position2) {
    int[] temp = array[position1];
    array[position1] = array[position2];
    array[position2] = temp;
  }

  public static void main(String[] args) {
    MergeIntervals mergeIntervals = new MergeIntervals();
    int[][] result = mergeIntervals.merge(new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}});
    for (int[] interval : result) {
      System.out.printf("%s,", Arrays.toString(interval));
    }
  }
}
