package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.Stack;

public class TowersOfHanoi {
  public void moveTowerOfHanoi(int n) {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    Stack<Integer> stack3 = new Stack<>();
    // Initialise tower 1
    for (int i = n; i > 0; i--) {
      stack1.push(i);
    }
    moveDisc(stack1, stack2, stack3, n);
    printAllStacks(stack1, stack2, stack3);
  }

  private void moveDisc(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3, int n) {
    if (n == 1) {
      stack3.push(stack1.pop());
      return;
    }
    if (n == 2) {
      stack2.push(stack1.pop());
      stack3.push(stack1.pop());
      stack3.push(stack2.pop());
      return;
    }
    moveDisc(stack1, stack3, stack2, n - 1);
    stack3.push(stack1.pop());
    moveDisc(stack2, stack1, stack3, n - 1);
  }

  private void printAllStacks(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3) {
    System.out.printf("s1=");
    printStack(stack1);
    System.out.println();

    System.out.printf("s2=");
    printStack(stack2);
    System.out.println();

    System.out.printf("s3=");
    printStack(stack3);
    System.out.println();
  }

  private void printStack(Stack<Integer> s) {
    while (!s.isEmpty()) {
      System.out.printf("%s, ", s.pop());
    }
  }

  public static void main(String[] args) {
    TowersOfHanoi towersOfHanoi = new TowersOfHanoi();
    towersOfHanoi.moveTowerOfHanoi(7);
  }
}

