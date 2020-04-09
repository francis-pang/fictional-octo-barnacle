package crackingthecodinginterview.hard;

import java.util.HashMap;
import java.util.Map;

public class ShortestSuperSequence {
  public int[] findShortestSuperSequence(int[] shortArray, int[] longArray) {
    if (shortArray == null || shortArray.length == 0 ||
        longArray == null || longArray.length == 0 ||
        longArray.length < shortArray.length // The longer array is assumed to be at least longer
    ) {
      return new int[]{};
    }

    Map<Integer, Integer> shortArrayTable = new HashMap<>();
    int missingElementCount = shortArray.length;
    for (int e : shortArray) {
      shortArrayTable.put(e, -1);
    }

    int position;
    for (position = 0; position < longArray.length && missingElementCount > 0; position++) {
      int element = longArray[position];
      if (shortArrayTable.containsKey(element)) {
        int lastFoundPosition = shortArrayTable.get(element);
        shortArrayTable.put(element, position);
        if (lastFoundPosition == -1) {
          missingElementCount--;
        }
      }
    }
    if (missingElementCount > 0) { // Means there ins't such a super sequence
      return null;
    }
    NumberDistance start = new NumberDistance(0, 0);
    NumberDistance end = new NumberDistance(0, 0);
    updateStartEnd(shortArrayTable, start, end);
    int shortestDistanceEndPosition = end.position;
    int shortestDistanceStartPosition = start.position;
    int shortestDistance = calculateDistance(start.position, end.position);

    for (; position < longArray.length; position++) {
      if (!shortArrayTable.containsKey(longArray[position])) {
        continue;
      }
      int number = longArray[position];
      shortArrayTable.put(number, position);
      if (number == start.number || number == end.number) {
        updateStartEnd(shortArrayTable, start, end);
        int distance = calculateDistance(start.position, end.position);
        if (distance < shortestDistance) {
          shortestDistanceEndPosition = end.position;
          shortestDistanceStartPosition = start.position;
          shortestDistance = distance;
        }
      }
    }
    return new int[]{shortestDistanceStartPosition, shortestDistanceEndPosition};
  }

  private int calculateDistance(int start, int end) {
    return end - start + 1;
  }

  private void updateStartEnd(Map<Integer, Integer> shortArrayTable, NumberDistance start, NumberDistance end) {
    NumberDistance smallest = new NumberDistance(Integer.MIN_VALUE, Integer.MAX_VALUE);
    NumberDistance biggest = new NumberDistance(Integer.MIN_VALUE, Integer.MIN_VALUE);
    for (Map.Entry<Integer, Integer> entry : shortArrayTable.entrySet()) {
      int entryNumber = entry.getKey();
      int entryPosition = entry.getValue();
      if (smallest.position > entryPosition) {
        smallest.number = entryNumber;
        smallest.position = entryPosition;
      }
      if (biggest.position < entryPosition) {
        biggest.number = entryNumber;
        biggest.position = entryPosition;
      }
    }
    start.clone(smallest);
    end.clone(biggest);
  }

  class NumberDistance implements Comparable<NumberDistance> {
    public int number;
    public int position;

    public NumberDistance(int number, int position) {
      this.number = number;
      this.position = position;
    }

    @Override
    public int compareTo(NumberDistance o) {
      return this.position - o.position;
    }

    public void clone(NumberDistance numberDistance) {
      this.position = numberDistance.position;
      this.number = numberDistance.number;
    }
  }

  public static void main(String[] args) {
    int[] shortArray = new int[]{1, 5, 9, 7};
    int[] longArray = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
    ShortestSuperSequence shortestSupersequence = new ShortestSuperSequence();
    int[] result = shortestSupersequence.findShortestSuperSequence(shortArray, longArray);
    System.out.println(result[0] + "," + result[1]);
  }
}
