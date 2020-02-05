package crackingthecodinginterview.linkedlists;

import java.util.StringJoiner;

public class SumLists {
  public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
    ListNode ansHead = new ListNode(0);
    boolean carry = false;
    ListNode ansListNode = ansHead;
    ListNode listNode1 = head1;
    ListNode listNode2 = head2;
    while (listNode1 != null || listNode2 != null) {
      int sum = 0;
      if (carry) {
        sum += 1;
        carry = false;
      }
      if (listNode1 != null && listNode2 != null) {
        sum += listNode1.val + listNode2.val;
      } else if (listNode1 != null) {
        sum += listNode1.val;
      } else if (listNode2 != null) {
        sum += listNode2.val;
      }
      if (sum >= 10) {
        sum = sum % 10;
        carry = true;
      }
      ListNode ans = new ListNode(sum);
      ansListNode.next = ans;
      ansListNode = ans;
      if (listNode1 != null) {
        listNode1 = listNode1.next;
      }
      if (listNode2 != null) {
        listNode2 = listNode2.next;
      }
    }
    if (carry) {
      ansListNode.next = new ListNode(1);
    }
    return ansHead.next;
  }

  public ListNode addForwardReadingNumber(ListNode head1, ListNode head2) {
    ListNode reverseHead1 = reverseLinkedList(head1);
    ListNode reverseHead2 = reverseLinkedList(head2);
    ListNode reverseAnswer = addTwoNumbers(reverseHead1, reverseHead2);
    return reverseLinkedList(reverseAnswer);
  }

  private ListNode reverseLinkedList(ListNode head) {
    ListNode prev = null;
    ListNode next;
    ListNode node = head;
    while (node != null) {
      next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }
    return prev;
  }

  public static class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", ListNode.class.getSimpleName() + "[", "]")
          .add("val=" + val)
          .toString();
    }
  }

  public void printLinkedList(ListNode listNode) {
    while (listNode != null) {
      System.out.printf("%s, ", listNode.val);
      listNode = listNode.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    ListNode listNode11 = new ListNode(7);
    ListNode listNode12 = new ListNode(1);
    ListNode listNode13 = new ListNode(9);
    listNode11.next = listNode12;
    listNode12.next = listNode13;

    ListNode listNode21 = new ListNode(5);
    ListNode listNode22 = new ListNode(9);
    listNode21.next = listNode22;

    SumLists sumLists = new SumLists();
    ListNode ans = sumLists.addTwoNumbers(listNode11, listNode21);
    sumLists.printLinkedList(ans);

    ListNode reversedLinkedList = sumLists.addForwardReadingNumber(listNode11, listNode21);
    sumLists.printLinkedList(reversedLinkedList);
  }
}
