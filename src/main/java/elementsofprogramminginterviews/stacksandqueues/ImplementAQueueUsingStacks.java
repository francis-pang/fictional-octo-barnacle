package elementsofprogramminginterviews.stacksandqueues;

import java.util.Queue;
import java.util.Stack;

public class ImplementAQueueUsingStacks {
  public abstract class StackQueue implements Queue<Integer> {
    private Stack<Integer> stack;
    private Stack<Integer> sortedStack;

    public StackQueue() {
      stack = new Stack<>();
      sortedStack = new Stack<>();
    }

    @Override
    public boolean offer(Integer integer) {
      boolean offerResult = stack.add(integer);
      return offerResult;
    }

    @Override
    public Integer poll() {
      if (sortedStack.isEmpty()) {
        if (stack.isEmpty()) {
          return null;
        }
        while (!stack.isEmpty()) {
          sortedStack.add(stack.pop());
        }
      }
      return sortedStack.pop();
    }

    @Override
    public Integer peek() {
      if (sortedStack.isEmpty()) {
        if (stack.isEmpty()) {
          return null;
        }
        while (!stack.isEmpty()) {
          sortedStack.add(stack.pop());
        }
      }
      return sortedStack.peek();
    }

    @Override
    public int size() {
      return stack.size() + sortedStack.size();
    }
  }
}
