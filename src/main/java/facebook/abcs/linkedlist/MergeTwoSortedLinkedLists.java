package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * You’re given the pointer to the head nodes of two sorted linked lists. The data in both lists will be sorted in
 * ascending order. Change the next pointers to obtain a single, merged linked list which also has data in ascending
 * order. Either head pointer given may be null meaning that the corresponding list is empty.
 * <b>Input Format</b>
 * You have to complete the {@code Node* MergeLists(Node* headA, Node* headB)} method which takes two arguments -
 * the
 * heads of the two sorted linked lists to merge. You should NOT read any input from stdin/console.
 *
 * <b>Output Format</b>
 * Change the {@code next} pointer of individual nodes so that nodes from both lists are merged into a single list.
 * Then {@code return} the head of this merged list. Do NOT print anything to stdout/console.
 *
 * <b>Sample Input</b>
 * 1 -> 3 -> 5 -> 6 -> NULL
 * 2 -> 4 -> 7 -> NULL
 * <p>
 * 15 -> NULL
 * 12 -> NULL
 * <p>
 * NULL
 * 1 -> 2 -> NULL
 *
 * <b>Sample Output</b>
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> NULL
 * 12 -> 15 -> NULL
 * 1 -> 2 -> NULL
 *
 * <b>Explanation</b>
 * 1. We merge elements in both list in sorted order and output.
 */
public class MergeTwoSortedLinkedLists {
  public static class Solution {
    static class SinglyLinkedListNode {
      public int data;
      public SinglyLinkedListNode next;

      public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
      }
    }

    static class SinglyLinkedList {
      public SinglyLinkedListNode head;
      public SinglyLinkedListNode tail;

      public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
      }

      public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
          this.head = node;
        } else {
          this.tail.next = node;
        }

        this.tail = node;
      }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
      while (node != null) {
        bufferedWriter.write(String.valueOf(node.data));

        node = node.next;

        if (node != null) {
          bufferedWriter.write(sep);
        }
      }
    }

    // Complete the mergeLists function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
      SinglyLinkedListNode headOfNewList = null;
      if (head1 == null && head2 == null) {
        return null;
      } else if (head1 == null) {
        return head2;
      } else if (head2 == null) {
        return head1;
      }

      SinglyLinkedListNode node1 = head1;
      SinglyLinkedListNode node2 = head2;

      SinglyLinkedListNode comparisonNode = null;
      if (node1.data > node2.data) {
        headOfNewList = node2;
        node2 = node2.next;
      } else {
        headOfNewList = node1;
        node1 = node1.next;
      }
      SinglyLinkedListNode currentNode = headOfNewList;
      while (node1 != null && node2 != null) {
        comparisonNode = (node1.data > node2.data) ? node1 : node2;
        if (node1.data < comparisonNode.data) {
          currentNode.next = node1;
          node1 = node1.next;
        } else {
          currentNode.next = node2;
          node2 = node2.next;
        }
        currentNode = currentNode.next;
      }

      while (node1 != null) {
        currentNode.next = node1;
        currentNode = currentNode.next;
        node1 = node1.next;
      }

      while (node2 != null) {
        currentNode.next = node2;
        currentNode = currentNode.next;
        node2 = node2.next;
      }
      return headOfNewList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

      int tests = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int testsItr = 0; testsItr < tests; testsItr++) {
        SinglyLinkedList llist1 = new SinglyLinkedList();

        int llist1Count = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llist1Count; i++) {
          int llist1Item = scanner.nextInt();
          scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

          llist1.insertNode(llist1Item);
        }

        SinglyLinkedList llist2 = new SinglyLinkedList();

        int llist2Count = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llist2Count; i++) {
          int llist2Item = scanner.nextInt();
          scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

          llist2.insertNode(llist2Item);
        }

        SinglyLinkedListNode llist3 = mergeLists(llist1.head, llist2.head);

        printSinglyLinkedList(llist3, " ", bufferedWriter);
        bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
    }
  }

}
