package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * You’re given the pointer to the head node of a doubly linked list. Reverse the order of the nodes in the list. The
 * head node might be NULL to indicate that the list is empty. Change the next and prev pointers of all the nodes so
 * that the direction of the list is reversed. Return a reference to the head node of the reversed list.
 * <b>Function Description</b>
 * Complete the reverse function in the editor below. It should return a reference to the head of your reversed list.
 * reverse has the following parameter(s):
 * <ul>
 *  <li>head: a reference to the head of a DoublyLinkedList</li>
 * </ul>
 *
 * <b>Input Format</b>
 * The first line contains an integer <i>t</i>, the number of test cases.
 * Each test case is of the following format:
 * The first line contains an integer <i>n</i>, the number of elements in the linked list.
 * The next <i>n</i> lines contain an integer each denoting an element of the linked list.
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= t <= 10</li>
 *   <li>0 <= n <= 1000</li>
 *   <li>0 <= n.data <= 1000</li>
 * </ul>
 *
 * <b>Output Format</b>
 * Return a reference to the head of your reversed list. The provided code will print the reverse array as a
 * one line of space-separated integers for each test case.
 *
 * <b>Sample Input</b>
 * 1
 * 4
 * 1
 * 2
 * 3
 * 4
 * <b>Sample Output</b>
 * 4 3 2 1
 * <b>Explanation</b>
 * The initial doubly linked list is: <i>1 <-> 2 <-> 3 <-> 4 -> NULL </i>
 * The reversed doubly linked list is: <i>4 <-> 3 <-> 2 <-> 1 -> NULL </i>
 */
public class ReverseADoublyLinkedList {
  public static class Solution {

    static class DoublyLinkedListNode {
      public int data;
      public DoublyLinkedListNode next;
      public DoublyLinkedListNode prev;

      public DoublyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
        this.prev = null;
      }
    }

    static class DoublyLinkedList {
      public DoublyLinkedListNode head;
      public DoublyLinkedListNode tail;

      public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
      }

      public void insertNode(int nodeData) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

        if (this.head == null) {
          this.head = node;
        } else {
          this.tail.next = node;
          node.prev = this.tail;
        }

        this.tail = node;
      }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
      while (node != null) {
        System.out.print(node.data);
        node = node.next;

        if (node != null) {
          System.out.print(sep + " ");
        }
      }
    }

    // Complete the reverse function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
      DoublyLinkedListNode node = head;
      DoublyLinkedListNode lastNode = head;
      while (node != null) {
        DoublyLinkedListNode next = node.next;
        node.next = node.prev;
        node.prev = next;
        lastNode = node;
        node = next;
      }
      return lastNode;
    }

    private static final Scanner scanner = new Scanner("1\n" +
        "4\n" +
        "1\n" +
        "2\n" +
        "3\n" +
        "4");

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

      int t = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int tItr = 0; tItr < t; tItr++) {
        DoublyLinkedList llist = new DoublyLinkedList();

        int llistCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llistCount; i++) {
          int llistItem = scanner.nextInt();
          scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

          llist.insertNode(llistItem);
        }

        DoublyLinkedListNode llist1 = reverse(llist.head);

        printDoublyLinkedList(llist1, " ", bufferedWriter);
        bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
    }
  }
}
