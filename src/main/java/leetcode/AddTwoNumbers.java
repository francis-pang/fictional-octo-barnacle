package leetcode;

public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode listA, ListNode listB) {
    SumCarrier sumCarrier = new SumCarrier();
    while (listA != null && listB != null) {
      int a = listA.val;
      int b = listB.val;
      addValue(a, b, sumCarrier);
      listA = listA.next;
      listB = listB.next;
    }
    while (listA != null) {
      int a = listA.val;
      addValue(a, 0, sumCarrier);
      listA = listA.next;
    }
    while (listB != null) {
      int b = listB.val;
      addValue(0, b, sumCarrier);
      listB = listB.next;
    }
    if (sumCarrier.carryOver == 1) {
      sumCarrier.previous.next = new ListNode(1);
    }
    return sumCarrier.root;
  }

  private void addValue(int a, int b, SumCarrier sumCarrier) {
    int sum = sumCarrier.carryOver + a + b;
    sumCarrier.carryOver = 0;
    if (sum >= 10) {
      sumCarrier.carryOver = 1;
      sum -= 10;
    }
    ListNode current = new ListNode(sum);
    if (sumCarrier.root == null) {
      sumCarrier.root = current;
    }
    if (sumCarrier.previous != null) {
      sumCarrier.previous.next = current;
    }
    sumCarrier.previous = current;
  }

  public class SumCarrier {
    public ListNode root;
    public ListNode previous;
    public int carryOver;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    ListNode node2 = new ListNode(2);
    ListNode node4 = new ListNode(4);
    node2.next = node4;
    ListNode node3 = new ListNode(3);
    node4.next = node3;

    ListNode node9 = new ListNode(9);

    AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
    ListNode answer = addTwoNumbers.addTwoNumbers(node2, node9);
  }
}

