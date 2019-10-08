package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

/**
 * You're given the pointer to the head node of a sorted linked list, where the data in the nodes is in ascending
 * order. Delete as few nodes as possible so that the list does not contain any value more than once. The given head
 * pointer may be null indicating that the list is empty.
 * For now do not be concerned with the memory deallocation. In common abstract data structure scenarios,
 * deleting an element might also require deallocating the memory occupied by it. For an initial intro to the
 * topic of dynamic memory please consult: http://www.cplusplus.com/doc/tutorial/dynamic/
 *
 * <b>Input Format</b>
 * You have to complete the <i>Node* RemoveDuplicates(Node* head)</i> method which takes one argument - the head of
 * the sorted linked list. You should NOT read any input from stdin/console.
 *
 * <b>Output Format</b>
 * Delete as few nodes as possible to ensure that no two nodes have the same data. Adjust the <i>next</i>
 * pointers to ensure that the remaining nodes form a single sorted linked list. Then <i>return</i> the head of the
 * sorted updated linked list. Do NOT print anything to stdout/console.
 *
 * <b>Sample Input</b>
 * 1 -> 1 -> 3 -> 3 -> 5 -> 6 -> NULL
 * NULL
 *
 * <b>Sample Output</b>
 * 1 -> 3 -> 5 -> 6 -> NULL
 * NULL
 *
 * <b>Explanation</b>
 * 1. 1 and 3 are repeated, and are deleted.
 * 2. Empty list remains empty.
 */
public class DeleteDuplicateValueNodesFromASortedLinkedList {
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

    // Complete the removeDuplicates function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {
      if (head == null) {
        return null;
      }
      SinglyLinkedListNode previousNode = head;
      SinglyLinkedListNode currentNode = head.next;
      while (currentNode != null) {
        if (currentNode.data == previousNode.data) {
          removeNextNode(previousNode);
        } else {
          previousNode = currentNode;
        }
        currentNode = currentNode.next;
      }
      return head;
    }

    private static void removeNextNode(SinglyLinkedListNode node) {
      node.next = node.next.next;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new StringWriter());

      int t = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int tItr = 0; tItr < t; tItr++) {
        SinglyLinkedList llist = new SinglyLinkedList();

        int llistCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llistCount; i++) {
          int llistItem = scanner.nextInt();
          scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

          llist.insertNode(llistItem);
        }

        SinglyLinkedListNode llist1 = removeDuplicates(llist.head);

        printSinglyLinkedList(llist1, " ", bufferedWriter);
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      scanner.close();
    }
  }

}
