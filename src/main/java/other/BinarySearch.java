package other;

public class BinarySearch {
  private static final int NUMBER_NOT_FOUND = -1;

  public int binarySearch(int[] array, int number) {
    if (array == null) {
      return NUMBER_NOT_FOUND;
    }
    return binarySearch(array, number, 0, array.length - 1);
  }

  private int binarySearch(int[] array, int number, int left, int right) {
    if (right < left) {
      return NUMBER_NOT_FOUND;
    }
    int midPoint = calculateMidPoint(left, right);
    int numberAtMidPoint = array[midPoint];
    if (numberAtMidPoint == number) {
      return midPoint;
    } else if (numberAtMidPoint > number) {
      return binarySearch(array, number, left, midPoint - 1);
    } else { //numberAtMidPoint < number
      return binarySearch(array, number, midPoint + 1, right);
    }
  }

  private int calculateMidPoint(int low, int high) {
    return (int) Math.ceil((low + high) / 2);
  }
}
