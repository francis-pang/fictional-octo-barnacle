package leetcode;

// https://leetcode.com/problems/min-stack/

import java.util.Stack;

class MinStack {
  private Stack<Integer> itemStack;
  private Stack<Integer> minStack;
  /** initialize your data structure here. */
  public MinStack() {
    itemStack = new Stack<>();
    minStack = new Stack<>();
  }

  /**
   * Push element x onto stack.
   */
  public void push(int x) {
    if (minStack.isEmpty()) {
      minStack.push(x);
      itemStack.push(x);
      return;
    }
    int smallestItem = minStack.peek();
    if (smallestItem >= x) {
      minStack.push(x);
    }
    itemStack.push(x);
  }

  /**
   *  Removes the element on top of the stack.
   */
  public void pop() {
    if (minStack.isEmpty()) {
      return;
    }
    int item = minStack.peek();
    if (itemStack.peek().equals(item)) {
      minStack.pop();
    }
    itemStack.pop();
  }

  /**
   *  Get the top element.
   */
  public int top() {
    if (itemStack.isEmpty()) {
      return Integer.MIN_VALUE;
    }
    return itemStack.peek();
  }

  /**
   * Retrieve the minimum element in the stack.
   */
  public int getMin() {
    if (minStack.isEmpty()) {
      return Integer.MIN_VALUE;
    }
    return minStack.peek();
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
