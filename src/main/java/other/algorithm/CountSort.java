package other.algorithm;

public class CountSort {
  public static void sort(int[] array) {
    // Counting sort, pre-requite that you know the size of the input
    // Data type must be positive Integer, and you know the range
    final int MAXIMUM_VALUE_OF_INPUT = 255;

    // Edge case
    if (array == null) {
      return;
    }

    // Initialise the histogram for collection of data
    int[] histogram = new int[MAXIMUM_VALUE_OF_INPUT + 1];
    // Iterating through the array to be sorted, and put each element into the respective histogram
    for (int element : array) {
      histogram[element]++;
    }
    // Iterating through the history to re-create the sorted array
    int sortedArrayCounter = 0;
    for (int index = 0; index < histogram.length; index++) {
      for (int occurance = 0; occurance < histogram[index]; occurance++) {
        array[sortedArrayCounter] = index;
        sortedArrayCounter++;
        if (sortedArrayCounter == array.length) { // Enhancement for early termination
          return;
        }
      }
    }
  }
}
