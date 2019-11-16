package leetcode;

import java.util.Stack;

public class MyQueue {
  private Stack<Integer> inOrderedStack;
  private Stack<Integer> prePopStack;

  /** Initialize your data structure here. */
  public MyQueue() {
    inOrderedStack = new Stack<Integer>();
    prePopStack = new Stack<>();
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    prePopStack.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    transferStack();
    return inOrderedStack.pop();
  }

  /** Get the front element. */
  public int peek() {
    transferStack();
    return inOrderedStack.peek();
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return inOrderedStack.isEmpty() && prePopStack.isEmpty();
  }

  private void transferStack() {
    if (inOrderedStack.isEmpty()) {
      while (!prePopStack.isEmpty()) {
        inOrderedStack.push(prePopStack.pop());
      }
    }
  }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */