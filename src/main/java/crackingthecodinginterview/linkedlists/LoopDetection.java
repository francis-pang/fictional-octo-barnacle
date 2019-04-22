package crackingthecodinginterview.linkedlists;

public class LoopDetection {
  public static ListNode locateCycleSource(ListNode head) throws InterruptedException {
    if (head == null) {
      return null;
    }
    ListNode fastRunnerNode = head.next.next;
    ListNode slowRunnerNode = head.next;
    int counter = 0;
    while (fastRunnerNode != slowRunnerNode) {
      System.out.println("Inside " + counter++ + " iteration. Fast Runner is at " + fastRunnerNode.value + "; Slow " +
          "runner is at " + slowRunnerNode.value);
      fastRunnerNode = fastRunnerNode.next.next;
      slowRunnerNode = slowRunnerNode.next;
    }
    System.out.println("Collision point is at " + fastRunnerNode.value);
    slowRunnerNode = head;
    while (fastRunnerNode != slowRunnerNode) {
      System.out.println("Inside. Fast Runner is at " + fastRunnerNode.value + "; Slow " +
          "runner is at " + slowRunnerNode.value);
      fastRunnerNode = fastRunnerNode.next;
      slowRunnerNode = slowRunnerNode.next;
    }
    return fastRunnerNode;
  }

  static class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);

    ListNode node2 = new ListNode(2);
    node1.next = node2;

    ListNode node3 = new ListNode(3);
    node2.next = node3;

    ListNode node4 = new ListNode(4);
    node3.next = node4;

    ListNode node5 = new ListNode(5);
    node4.next = node5;

    node5.next = node2;

    try {
      System.out.println(locateCycleSource(node1).value);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
