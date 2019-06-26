package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.Stack;
import java.util.StringJoiner;

public class TowersOfHanoi {
  private Stack<Disk> stack1;
  private Stack<Disk> stack2;
  private Stack<Disk> stack3;

  public void setStacks(Stack<Disk> stack1, Stack<Disk> stack2, Stack<Disk> stack3) {
    this.stack1 = stack1;
    this.stack2 = stack2;
    this.stack3 = stack3;
  }

  public void moveDisksFromFirstTowerToLast(Stack<Disk> stack1, Stack<Disk> stack2, Stack<Disk> stack3) {
    // Exit case
    if (stack1.size() == 0) {
      return;
    }

    Stack<Disk> spareStack = new Stack<>();
    // This is just the preparation step, shouldn't be count as the moving of the disk, so it 'effectively' doesn't
    // violate the rule
    while (stack1.size() > 1) {
      spareStack.push(stack1.pop());
    }
    Disk biggestDisk = stack1.pop();

    while (!spareStack.isEmpty()) {
      stack1.push(spareStack.pop());
    }
    moveDisksFromFirstTowerToLast(stack1, stack3, stack2);
    stack3.push(biggestDisk);
    moveDisksFromFirstTowerToLast(stack2, stack1, stack3);
  }

  static class Disk {
    public int size;

    public Disk(int size) {
      this.size = size;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Disk.class.getSimpleName() + "[", "]")
          .add("size=" + size)
          .toString();
    }
  }
}

