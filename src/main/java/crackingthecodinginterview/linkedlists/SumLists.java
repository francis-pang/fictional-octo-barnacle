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

  public ListNode convertNumberToLinkedList(int number) {
    ListNode tail = null;
    ListNode node = null;
    while (number > 0) {
      node = new ListNode(number % 10);
      node.next = tail;
      tail = node;
      number = number / 10;
    }
    return node;
  }

  public ListNode convertNumberToReversedLinkedList(int number) {
    ListNode head = new ListNode(number % 10);
    number = number / 10;
    ListNode node = head;
    while (number > 0) {
      ListNode next = new ListNode(number % 10);
      number = number / 10;
      node.next = next;
      node = next;
    }
    return head;
  }

  public static void main(String[] args) {
    SumLists sumLists = new SumLists();
    ListNode reversedListNodeHead1 = sumLists.convertNumberToReversedLinkedList(719);
    ListNode reversedListNodeHead2 = sumLists.convertNumberToReversedLinkedList(59);

    ListNode ans = sumLists.addTwoNumbers(reversedListNodeHead1, reversedListNodeHead2);
    sumLists.printLinkedList(ans);

    ListNode listNodeHead1 = sumLists.convertNumberToLinkedList(719);
    ListNode listNodeHead2 = sumLists.convertNumberToLinkedList(59);
    ListNode reversedLinkedList = sumLists.addForwardReadingNumber(listNodeHead1, listNodeHead2);
    sumLists.printLinkedList(reversedLinkedList);
  }
}
