package elementsofprogramminginterviews.stacksandqueues;

import java.util.Stack;

public class ImplementAStackWithMaxApi<T extends Comparable<T>> {
  private Stack<T> stack;
  private Stack<T> maxStack;

  public ImplementAStackWithMaxApi() {
    stack = new Stack<>();
    maxStack = new Stack<>();
  }

  public void push(T element) {
    T maxElement;
    if (!stack.isEmpty()) {
      maxElement = maxStack.peek();
      if (maxElement.compareTo(element) > 0) {
        maxStack.push(maxElement);
      } else {
        maxStack.push(element);
      }
    } else {
      maxStack.push(element);
    }
    stack.push(element);
  }

  public T pop() {
    maxStack.pop();
    return stack.pop();
  }

  public T max() {
    return maxStack.peek();
  }
}
