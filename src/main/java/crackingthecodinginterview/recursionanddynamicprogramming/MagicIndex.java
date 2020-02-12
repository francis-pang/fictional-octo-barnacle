package crackingthecodinginterview.recursionanddynamicprogramming;

/**
 * A magic index in an array A [e ... n -1] is defined to be an index such that A[ i] =
 * i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
 * array A.
 * FOLLOW UP
 * What if the values are not distinct?
 */
public class MagicIndex {
  public int findAMagicIndex(int[] array) {
    int left = 0;
    int right = array.length - 1;
    return findAMagicIndex(array, left, right);
  }

  private int findAMagicIndex(int[] array, int left, int right) {
    if (right < left) {
      return -1;
    }
    int mid = left + ((right - left) / 2);
    if (array[mid] == mid) {
      return mid;
    }
    int index = findAMagicIndex(array, left, mid - 1);
    if (index == -1) {
      index = findAMagicIndex(array, mid + 1, right);
    }
    return index;
  }

  public static void main(String[] args) {
    MagicIndex magicIndex = new MagicIndex();
    System.out.println(magicIndex.findAMagicIndex(new int[]{-10, -5, 1, 2, 2, 3, 4, 8, 9, 12, 13}));
  }
}
