package leetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinStackTest {
  @Test
  void emptyStack() {
    MinStack minStack = new MinStack();
    assertEquals(Integer.MIN_VALUE, minStack.getMin());
    assertEquals(Integer.MIN_VALUE, minStack.top());
  }

  @Test
  void oneItem() {
    MinStack minStack = new MinStack();
    minStack.push(7);
    assertEquals(7, minStack.getMin());
    assertEquals(7, minStack.top());
    minStack.pop();
    assertEquals(Integer.MIN_VALUE, minStack.getMin());
    assertEquals(Integer.MIN_VALUE, minStack.top());
  }

  @Test
  @DisplayName(value = "Have 4 items to test multiple items")
  void multipleItems() {
    MinStack minStack = new MinStack();
    minStack.push(7);
    // Element is {7}
    assertEquals(7, minStack.getMin());
    assertEquals(7, minStack.top());
    minStack.push(6);
    // Element is {7, 6}
    assertEquals(6, minStack.getMin());
    assertEquals(6, minStack.top());
    minStack.push(9);
    // Element is {7, 6, 9}
    assertEquals(6, minStack.getMin());
    assertEquals(9, minStack.top());
    minStack.push(6);
    // Element is {7, 6, 9, 6}
    assertEquals(6, minStack.getMin());
    assertEquals(6, minStack.top());
    minStack.pop();
    // Element is {7, 6, 9}
    assertEquals(6, minStack.getMin());
    assertEquals(9, minStack.top());
    minStack.pop();
    // Element is {7, 6}
    assertEquals(6, minStack.getMin());
    assertEquals(6, minStack.top());
    minStack.pop();
    // Element is {7}
    assertEquals(7, minStack.getMin());
    assertEquals(7, minStack.top());
  }
}