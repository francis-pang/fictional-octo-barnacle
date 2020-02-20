package leetcode;

public class FixedPoint {
  private static final int CANNOT_BE_FOUND = -1;

  public int fixedPoint(int[] array) {
    int left = 0;
    int right = array.length - 1;
    int smallest = Integer.MAX_VALUE;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (array[mid] < mid) {
        left = mid + 1;
      } else {
        if (array[mid] == mid) {
          int match = mid;
          if (match < smallest) {
            smallest = match;
          }
        }
        right = mid - 1;
      }
    }
    return (smallest == Integer.MAX_VALUE) ? CANNOT_BE_FOUND : smallest;
  }
}
