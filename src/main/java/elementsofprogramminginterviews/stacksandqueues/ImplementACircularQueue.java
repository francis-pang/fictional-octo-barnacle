package elementsofprogramminginterviews.stacksandqueues;

import java.util.Queue;
import java.util.stream.IntStream;

public class ImplementACircularQueue {
  public abstract class CircularQueue implements Queue<Integer> {
    private int[] array;
    private int size;
    private int start;
    private int end;

    public CircularQueue(int capacity) {
      this.array = new int[capacity];
      size = capacity;
      start = 0;
      end = 0;
    }

    @Override
    public boolean offer(Integer integer) {
      if (end == size - 1) {
        if (start == 0) { // All elements are filled up, need to resize
          long exceedLimit = size * 2;
          if (exceedLimit > Integer.MAX_VALUE) {
            return false;
          }
          int newSize = (int) exceedLimit;
          int[] expandedArray = new int[newSize];
          IntStream.range(0, size).forEach(i -> expandedArray[i] = array[i]);
          size = newSize;
          array = expandedArray;
        } else { // Not all elements are filled up, can just do shifting
          IntStream.range(start, size).forEach(i -> array[i - start] = array[i]);
          end -= start;
          start = 0;
        }
        end++;
        array[end] = integer;
      }
      return true;
    }

    @Override
    public Integer poll() {
      if (start == end) { // Empty queue
        return null;
      }
      Integer element = array[start];
      start++;
      return element;
    }

    @Override
    public Integer peek() {
      if (start == end) { // Empty queue
        return null;
      }
      return array[start];
    }

    @Override
    public int size() {
      return end - start + 1;
    }
  }
}
