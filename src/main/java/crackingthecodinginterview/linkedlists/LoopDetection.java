package crackingthecodinginterview.linkedlists;

public class LoopDetection {
  public static ListNode locateCycleSource(ListNode head) {
    ListNode fastRunner = head;
    ListNode slowRunner = head;
    do {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next.next;
    } while (!slowRunner.equals(fastRunner));
    slowRunner = head;
    while (!fastRunner.equals(slowRunner)) {
      slowRunner = slowRunner.next;
      fastRunner = fastRunner.next;
    }
    return slowRunner;
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
//    node5.next = node2;
    ListNode node6 = new ListNode(6);
    node5.next = node6;
    node6.next = node1;

    System.out.println(locateCycleSource(node1).value);
  }
}
