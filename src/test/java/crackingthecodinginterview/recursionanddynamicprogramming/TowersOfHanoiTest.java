package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Stack;

class TowersOfHanoiTest {
  private static TowersOfHanoi towersOfHanoi;

  @BeforeAll
  static void setUpOnce() {
    towersOfHanoi = new TowersOfHanoi();
  }

  @Test
  void moveDisksFromFirstTowerToLast_1Disk() {
    TowersOfHanoi.Disk disk1 = new TowersOfHanoi.Disk(1);
    Stack<TowersOfHanoi.Disk> stack1 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack2 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack3 = new Stack<>();
    stack1.push(disk1);

    towersOfHanoi.moveDisksFromFirstTowerToLast(stack1, stack2, stack3);
    System.out.println(stack1);
    System.out.println(stack2);
    System.out.println(stack3);
  }

  @Test
  void moveDisksFromFirstTowerToLast_2Disk() {
    TowersOfHanoi.Disk disk1 = new TowersOfHanoi.Disk(1);
    TowersOfHanoi.Disk disk2 = new TowersOfHanoi.Disk(2);
    Stack<TowersOfHanoi.Disk> stack1 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack2 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack3 = new Stack<>();
    stack1.push(disk2);
    stack1.push(disk1);

    towersOfHanoi.moveDisksFromFirstTowerToLast(stack1, stack2, stack3);
    System.out.println(stack1);
    System.out.println(stack2);
    System.out.println(stack3);
  }

  @Test
  void moveDisksFromFirstTowerToLast_3Disk() {
    TowersOfHanoi.Disk disk1 = new TowersOfHanoi.Disk(1);
    TowersOfHanoi.Disk disk2 = new TowersOfHanoi.Disk(2);
    TowersOfHanoi.Disk disk3 = new TowersOfHanoi.Disk(3);
    Stack<TowersOfHanoi.Disk> stack1 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack2 = new Stack<>();
    Stack<TowersOfHanoi.Disk> stack3 = new Stack<>();
    stack1.push(disk3);
    stack1.push(disk2);
    stack1.push(disk1);

    towersOfHanoi.setStacks(stack1, stack2, stack3);
    towersOfHanoi.moveDisksFromFirstTowerToLast(stack1, stack2, stack3);
    System.out.println(stack1);
    System.out.println(stack2);
    System.out.println(stack3);
  }
}