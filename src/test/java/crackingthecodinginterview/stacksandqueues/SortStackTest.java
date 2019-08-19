package crackingthecodinginterview.stacksandqueues;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortStackTest {
  private static SortStack sortStack;

  @BeforeAll
  static void setUpOnce() {
    sortStack = new SortStack();
  }

  @Test
  void sortStack_null() {
    Stack<Integer> testStack = null;
    sortStack.sortStack(testStack);
  }

  @Test
  void sortStack_emptyStack() {
    Stack<Integer> testStack = new Stack<>();
    sortStack.sortStack(testStack);
    assertEquals(0, testStack.size());
  }

  @Test
  void sortStack_singleItem() {
    int[] array = new int[]{1};
    Stack<Integer> testStack = insertIntoStack(array);
    sortStack.sortStack(testStack);
    assertEquals(1, testStack.pop());
  }

  @Test
  void sortStack_alreadySorted() {
    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
    Stack<Integer> testStack = insertIntoStack(array);
    sortStack.sortStack(testStack);
    for (int number : array) {
      assertEquals(number, testStack.pop());
    }
  }

  @Test
  void sortStack_reverseSort() {
    int[] array = new int[]{7, 6, 5, 4, 3, 2, 1};
    Stack<Integer> testStack = insertIntoStack(array);
    sortStack.sortStack(testStack);
    int[] expectedArray = new int[]{1, 2, 3, 4, 5, 6, 7};
    for (int number : expectedArray) {
      assertEquals(number, testStack.pop());
    }
  }

  @Test
  void sortStack_random() {
    int[] array = new int[]{-45, 29, 46, -3, 43, -37, 38, 24};
    Stack<Integer> testStack = insertIntoStack(array);
    sortStack.sortStack(testStack);
    int[] expectedArray = new int[]{-45, -37, -3, 24, 29, 38, 43, 46};
    for (int number : expectedArray) {
      assertEquals(number, testStack.pop());
    }
  }

  private Stack insertIntoStack(int[] array) {
    Stack stack = new Stack();
    for (int number : array) {
      stack.push(number);
    }
    return stack;
  }
}