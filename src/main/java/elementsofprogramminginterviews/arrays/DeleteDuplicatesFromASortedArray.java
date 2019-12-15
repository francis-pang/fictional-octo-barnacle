package elementsofprogramminginterviews.arrays;

public class DeleteDuplicatesFromASortedArray {
  public int numberOfUniqueElement(int[] array) {
    if (array == null) {
      return 0;
    }
    if (array.length <= 1) {
      return array.length;
    }
    int uniqueIndex = 0;
    for (int i = 1; i < array.length; i++) {
      if (array[uniqueIndex] != array[i]) {
        uniqueIndex++;
        array[uniqueIndex] = array[i];
      }
    }
    return uniqueIndex + 1;
  }
}
