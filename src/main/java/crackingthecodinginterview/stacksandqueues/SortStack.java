package crackingthecodinginterview.stacksandqueues;

import java.util.Stack;

public class SortStack {
  public void sortStack(Stack<Integer> stack) {
    if (stack == null || stack.isEmpty()) {
      return;
    }
    Stack<Integer> tempStack = new Stack<>();

    // 1st pass find out size
    int sizeOfStack = 0;
    while (!stack.isEmpty()) {
      tempStack.push(stack.pop());
      sizeOfStack++;
    }

    do {
      // Even pass - transfer to original stack, keep the smallest
      int holding = tempStack.pop();
      for (int index = 0; index < sizeOfStack - 1; index++) {
        int item = tempStack.pop();
        if (item < holding) {
          stack.push(holding);
          holding = item;
        } else {
          stack.push(item);
        }
      }
      sizeOfStack--;
      tempStack.push(holding);
      if (sizeOfStack <= 1) {
        break;
      }

      // Odd pass - transfer to temp stack, keep the largest
      holding = stack.pop();
      for (int index = 0; index < sizeOfStack - 1; index++) {
        int item = stack.pop();
        if (item > holding) {
          tempStack.push(holding);
          holding = item;
        } else {
          tempStack.push(item);
        }
      }
      sizeOfStack--;
      stack.push(holding);
    } while (sizeOfStack > 0);

    // move all the item from temp stack to original stack
    while (!tempStack.isEmpty()) {
      stack.push(tempStack.pop());
    }
  }
}
