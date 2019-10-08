package facebook.abcs.linkedlist;

import java.util.Scanner;

/**
 * If you're new to linked lists, this is a great exercise for learning about them. Given a pointer to the head node
 * of a linked list, print its elements in order, one element per line. If the head pointer is null (indicating the
 * list is empty), don’t print anything.
 *
 * <b>Input Format</b>
 * The first line of input contains <i>n</i>, the number of elements in the linked list. The next <i>n</i> lines
 * contain one element each, which are the elements of the linked list.
 * <b>Note</b>: Do not read any input from stdin/console. Complete the printLinkedList function in the editor below.
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= n <= 1000</li>
 *   <li>1 <= list_i <= 1000</li>
 * </ul>
 * , where list_i is the ith element of the linked list.
 *
 * <b>Output Format</b>
 * Print the integer data for each element of the linked list to stdout/console (e.g.: using printf, cout, etc.).
 * There should be one element per line.
 */
public class PrintTheElementsOfALinkedList {
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

    // Complete the printLinkedList function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static void printLinkedList(SinglyLinkedListNode head) {
      SinglyLinkedListNode nextNode = head;
      while (nextNode != null) {
        System.out.println(nextNode.data);
        nextNode = nextNode.next;
      }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
      SinglyLinkedList llist = new SinglyLinkedList();
      int llistCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < llistCount; i++) {
        int llistItem = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        llist.insertNode(llistItem);
      }
      printLinkedList(llist.head);
      scanner.close();
    }
  }

}
