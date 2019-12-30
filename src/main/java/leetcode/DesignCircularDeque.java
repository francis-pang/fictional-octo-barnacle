package leetcode;

import java.util.Arrays;

public class DesignCircularDeque {
  static class MyCircularDeque {
    private static final int EMPTY = -1;
    private int[] array;
    private int start;
    private int end;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
      array = new int[k];
      Arrays.fill(array, EMPTY);
      start = 0;
      end = 0;
    }

    private int actualIndex(int index) {
      if (index < 0) {
        // Have to do this because you can't do modulus on a negative number
        int absIndex = Math.abs(index);
        int modIndex = absIndex % array.length;
        return array.length - modIndex;
      } else if (index >= array.length) {
        return index % array.length;
      } else {
        return index;
      }
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
      if (isFull()) {
        return false;
      }
      if (!isEmpty()) {
        start--;
      }
      int insertionActualIndex = actualIndex(start);
      array[insertionActualIndex] = value;
      return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
      if (isFull()) {
        return false;
      }
      if (!isEmpty()) {
        end++;
      }
      int insertionActualIndex = actualIndex(end);
      array[insertionActualIndex] = value;
      return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
      if (isEmpty()) {
        return false;
      }
      int actualStart = actualIndex(start);
      array[actualStart] = EMPTY;
      if (isEmpty()) {
        return true;
      }
      start++;
      return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
      if (isEmpty()) {
        return false;
      }
      int actualEnd = actualIndex(end);
      array[actualEnd] = EMPTY;
      if (isEmpty()) {
        return true;
      }
      end--;
      return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
      int actualStart = actualIndex(start);
      return array[actualStart];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
      int actualEnd = actualIndex(end);
      return array[actualEnd];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
      // When empty, both start and end will point to the same index, and that index will be empty
      int actualStart = actualIndex(start);
      int actualEnd = actualIndex(end);
      boolean sameIndex = (actualStart == actualEnd);
      if (!sameIndex) {
        return false;
      }
      start = actualStart;
      end = actualEnd;
      return array[start] == EMPTY;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
      int actualOffByOneEnd = actualIndex(end + 1);
      // If it isn't full, it mean that the next element is empty.
      // Empty is represented by -1.
      return array[actualOffByOneEnd] != EMPTY;
    }
  }

  /**
   * Your MyCircularDeque object will be instantiated and called as such:
   * MyCircularDeque obj = new MyCircularDeque(k);
   * boolean param_1 = obj.insertFront(value);
   * boolean param_2 = obj.insertLast(value);
   * boolean param_3 = obj.deleteFront();
   * boolean param_4 = obj.deleteLast();
   * int param_5 = obj.getFront();
   * int param_6 = obj.getRear();
   * boolean param_7 = obj.isEmpty();
   * boolean param_8 = obj.isFull();
   */
// Come up with a test case where you add and delete until start become at middle of array, then this continue until
// start and end is both out of the size of array
}

