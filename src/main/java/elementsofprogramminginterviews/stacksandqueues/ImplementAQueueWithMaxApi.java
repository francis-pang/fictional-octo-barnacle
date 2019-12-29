package elementsofprogramminginterviews.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Queue;

public class ImplementAQueueWithMaxApi {
  public abstract class MaxQueue implements Queue<Integer> {
    private Queue<ElementWithCount> maxQueue;
    private Queue<Integer> queue;

    public MaxQueue() {
      maxQueue = new ArrayDeque<>();
      queue = new ArrayDeque<>();
    }

    @Override
    public boolean offer(Integer integer) {
      boolean result = queue.add(integer);
      if (!result) {
        return result;
      }
      if (maxQueue.isEmpty()) {
        maxQueue.add(new ElementWithCount(integer, 1));
      }
      ElementWithCount maxElement = maxQueue.peek();
      if (maxElement.element == integer) {
        maxElement.count++;
      } else if (maxElement.element < integer) {
        maxQueue.add(new ElementWithCount(integer, 1));
      }
      return true;
    }

    @Override
    public Integer poll() {
      if (queue.isEmpty()) {
        return null;
      }
      int poppedElement = queue.poll();
      ElementWithCount maxElement = maxQueue.peek();
      if (poppedElement == maxElement.element) {
        if (maxElement.count == 1) {
          maxQueue.poll();
        } else {
          maxElement.count--;
        }
      }
      return poppedElement;
    }

    @Override
    public Integer peek() {
      return queue.peek();
    }

    public Integer max() {
      return maxQueue.peek().element;
    }

    public class ElementWithCount {
      public int element;
      public int count;

      public ElementWithCount(int element, int count) {
        this.element = element;
        this.count = count;
      }
    }
  }

}
