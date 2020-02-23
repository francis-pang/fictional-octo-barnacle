package other.algorithm;

public class Heap {
  private static final int BOUNDARY_VALUE = Integer.MIN_VALUE;

  public void heapSortAscending(int[] array) {
    if (array == null) {
      return;
    }
    // Transform the array into a min heap
    for (int i = 1; i < array.length; i++) {
      heapify(array, i);
    }

    // 'Pop' all the values from the array to sort it
    for (int i = array.length - 1; i > 0; i--) {
      swap(array, 0, i);
      balanceHeap(array, i - 1);
    }
  }

  private void balanceHeap(int[] array, int endIndex) {
    int parentIndex = 0;
    while (parentIndex <= endIndex) {
      int leftChildIndex = parentIndex * 2 + 1;
      int leftChildValue = (leftChildIndex <= endIndex) ? array[leftChildIndex] : BOUNDARY_VALUE;
      int rightChildIndex = parentIndex * 2 + 2;
      int rightChildValue = (rightChildIndex <= endIndex) ? array[rightChildIndex] : BOUNDARY_VALUE;
      boolean swapOccurs = false;
      int max = Math.max(array[parentIndex], Math.max(leftChildValue, rightChildValue));
      if (max == leftChildValue) {
        swap(array, parentIndex, leftChildIndex);
        swapOccurs = true;
        parentIndex = leftChildIndex;
      } else if (max == rightChildValue) {
        swap(array, parentIndex, rightChildIndex);
        swapOccurs = true;
        parentIndex = rightChildIndex;
      }
      if (!swapOccurs) {
        break;
      }
    }
  }


  public void heapify(int[] array, int startIndex) {
    int childIndex = startIndex;
    while (childIndex > 0) {
      int parentIndex = (int) Math.ceil(childIndex / 2.0) - 1;
      if (array[childIndex] > array[parentIndex]) {
        swap(array, childIndex, parentIndex);
        childIndex = parentIndex;
      } else {
        break;
      }
    }
  }

  private void swap(int[] array, int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  public static void main(String[] args) {
    int[] array = new int[]{4132, 2543, 4633, 1814, 3644, 2999, 218, 188, 456, 4039, 3343, 4310, 2362, 3678, 352, 3395, 1658, 64, 2325, 4751, 2427, 1683, 2912, 420, 1499, 2076, 2712, 1605, 520, 830, 4785, 2756, 1836, 1557, 4568, 610, 3387, 1565, 4083, 1659, 2590, 4879, 822, 490, 2491, 4706, 2158, 4657, 1563, 60, 4054, 4968, 3767, 1489, 1972, 3995, 4092, 3336, 2830, 311, 2353, 4436, 3895, 1703, 3713, 1319, 575, 3908, 581, 2429, 2878, 1041, 4807, 1811, 1727, 2247, 1240, 3732, 1875, 1486, 1134, 198, 611, 3340, 4374, 2388, 501, 73, 1135, 745, 423, 2313, 1064, 4886, 4221, 2381, 2067, 1400, 3307, 4082};
    Heap heap = new Heap();
    heap.heapSortAscending(array);
    for (int i = 0; i < array.length; i++) {
      System.out.printf("%d, ", array[i]);
    }
    System.out.println();
  }
}
