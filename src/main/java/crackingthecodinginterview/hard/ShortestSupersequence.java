package crackingthecodinginterview.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class ShortestSupersequence {
  private static final int RANGE_NOT_FOUND = -1;

  public SuperSequenceRange getShortestSupersequence(int[] shortArray, int[] longArray) {
    if (shortArray == null || longArray == null) {
      return new SuperSequenceRange(RANGE_NOT_FOUND, RANGE_NOT_FOUND);
    }
    Set<Integer> shortSet = convertArrayToSet(shortArray);
    HashMap<Integer, Integer> locationsOfShortArray = new HashMap<>();
    SuperSequenceRange shortestSupersequenceRange = new SuperSequenceRange(RANGE_NOT_FOUND, RANGE_NOT_FOUND);
    int shortestDistance = Integer.MAX_VALUE;

    for (int index = 0; index < longArray.length; index++) {
      if (!shortSet.contains(longArray[index])) {
        continue;
      }
      locationsOfShortArray.put(longArray[index], index);
      if (locationsOfShortArray.size() < shortSet.size()) {
        continue;
      }
      int maximum = Collections.max(locationsOfShortArray.values());
      int minimum = Collections.min(locationsOfShortArray.values());
      int distance = maximum - minimum;
      if (distance == 0) { // This can only happen when the short array only has 1 element
        return new SuperSequenceRange(index, index);
      }
      if (distance < shortestDistance) {
        shortestDistance = distance;
        shortestSupersequenceRange = new SuperSequenceRange(minimum, maximum);
      }
    }
    return shortestSupersequenceRange;
  }

  private Set convertArrayToSet(final int[] array) {
    Set<Integer> convertedSet = new HashSet<>();
    for (int element : array) {
      convertedSet.add(element);
    }
    return convertedSet;
  }

  public static class SuperSequenceRange {
    public int left;
    public int right;

    public SuperSequenceRange(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SuperSequenceRange)) return false;
      SuperSequenceRange that = (SuperSequenceRange) o;
      return left == that.left &&
          right == that.right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", SuperSequenceRange.class.getSimpleName() + "[", "]")
          .add("left=" + left)
          .add("right=" + right)
          .toString();
    }
  }
}
