package crackingthecodinginterview.sortingandsearching;

public class SparseSearch {
  private static final int NUMBER_NOT_FOUND = -1;

  public int findLocationOfStringIn(String[] sortedArray, String target) {
    if (sortedArray == null || target == null) {
      return NUMBER_NOT_FOUND;
    }
    return binarySearch(sortedArray, target, 0, sortedArray.length - 1);
  }

  private int binarySearch(String[] sortedArray, String target, int lower, int upper) {
    if (upper < lower) {
      return NUMBER_NOT_FOUND;
    }
    int midPoint = (int) Math.ceil((lower + upper) / 2);
    if (sortedArray[midPoint].equals(target)) {
      return midPoint;
    }

    if (sortedArray[midPoint].isEmpty()) { // Non deterministic binary search
      int leftSearchResult = binarySearch(sortedArray, target, lower, midPoint - 1);
      if (leftSearchResult == NUMBER_NOT_FOUND) {
        return binarySearch(sortedArray, target, midPoint + 1, upper);
      } else {
        return leftSearchResult;
      }
    } else { // Normal binary search
      if (compareTo(sortedArray[midPoint], target) > 0) {
        return binarySearch(sortedArray, target, lower, midPoint - 1);
      } else {
        return binarySearch(sortedArray, target, midPoint + 1, upper);
      }
    }
  }

  private int compareTo(String a, String b) {
    if (a.equals(b)) {
      return 0;
    }
    if (a.isEmpty()) {
      return 1;
    } else if (b.isEmpty()) {
      return -1;
    }
    for (int index = 0; index < a.length(); index++) {
      char charA = a.charAt(index);
      if (b.length() < index) {
        return 1;
      } else {
        char charB = b.charAt(index);
        if (charA > charB) {
          return 1;
        } else if (charA < charB) {
          return -1;
        }
      }
    }
    if (b.length() > a.length()) {
      return -1;
    }
    return 0;
  }
}
