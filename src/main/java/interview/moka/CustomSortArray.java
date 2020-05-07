package interview.moka;

import java.util.List;

class CustomSortArray {

  /*
   * Complete the 'moves' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY a as parameter.
   */

  public static int moves(List<Integer> a) {
    // Write your code here

    // Reset the list to array so that won't suffer from O(n) as compared to doing list.get(index)
    int[] array = new int[a.size()];
    int counter = 0;
    int oddCount = 0;
    for (int item : a) {
      array[counter] = item;
      counter++;
      if (!isEven(item)) {
        oddCount++;
      }
    }

    int shiftCount = 0;
    for (int i = array.length - oddCount; i < array.length; i++) {
      if (isEven(array[i])) {
        shiftCount++;
      }
    }
    return shiftCount;
  }

  private static boolean isEven(int number) {
    return (number % 2) == 0;
  }
}
