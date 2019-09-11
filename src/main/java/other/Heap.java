package other;

public class Heap {
  public static void heapSort(int[] array) {
    if (array == null) {
      return;
    }
    buildHeap(array);
    for (int heapSize = array.length - 1; heapSize > 0; heapSize--) {
      swap(array, 0, heapSize);
      maxHeapify(array, 0, heapSize);
    }
  }

  private static void buildHeap(int[] array) {
    for (int nodeIndex = array.length / 2; nodeIndex > 0; nodeIndex--) {
      maxHeapify(array, nodeIndex - 1, array.length);
    }
  }

  private static void maxHeapify(int[] array, int index, int heapSize) {
    int leftChildIndex = (index + 1) * 2 - 1;
    if (leftChildIndex >= heapSize) { // Node is leaf
      return;
    }
    int rightChildIndex = leftChildIndex + 1;
    int maxChildIndex;
    if (rightChildIndex < heapSize) {
      maxChildIndex = (array[leftChildIndex] > array[rightChildIndex]) ? leftChildIndex : rightChildIndex;
    } else {
      maxChildIndex = leftChildIndex;
    }
    if (array[index] < array[maxChildIndex]) {
      swap(array, index, maxChildIndex);
      maxHeapify(array, maxChildIndex, heapSize);
    }
  }

  private static void swap(int[] array, int firstSwapIndex, int secondSwapIndex) {
    int temp = array[firstSwapIndex];
    array[firstSwapIndex] = array[secondSwapIndex];
    array[secondSwapIndex] = temp;
  }
}
