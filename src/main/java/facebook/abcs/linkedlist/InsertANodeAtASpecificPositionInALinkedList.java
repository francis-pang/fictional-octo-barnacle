package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * You’re given the pointer to the head node of a linked list, an integer to add to the list and the position at
 * which the integer must be inserted. Create a new node with the given integer, insert this node at the desired
 * position and return the head node. A position of 0 indicates head, a position of 1 indicates one node away from
 * the head and so on. The head pointer given may be null meaning that the initial list is empty. As an example, if
 * your list starts as <i>1 -> 2 -> 3</i> and you want to insert a node at position <b>2</b> with <b>data = 4</b>, your
 * new list should be <b>1 -> 2 -> 4 -> 3</b>
 * <b>Function Description</b>
 * Complete the function SinglyLinkedListNode in the editor. It must return a reference to the head node of your
 * finished list.
 * <p>
 * SinglyLinkedListNode has the following parameters:
 * <ul>
 *  <li>head: a SinglyLinkedListNode pointer to the head of the list</li>
 *  <li>data: an integer value to insert as data in your new node</li>
 *  <li>position: an integer position to insert the new node, zero based indexing</li>
 * </ul>
 *
 * <b>Input Format</b>
 * The first line contains an integer <b>n</b>, the number of elements in the linked list.
 * Each of the next <b>n</b> lines contains an integer node[i].data.
 * The last line contains an integer <b>position</b>.
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= n <= 1000</li>
 *   <li>1 <= list_i <= 1000, where list_i is the ith element of the linked list.</li>
 * </ul>
 *
 * <b>Output Format</b>
 * Return a reference to the list head. Locked code prints the list for you.
 *
 * <b>Sample Input</b>
 * 3
 * 16
 * 13
 * 7
 * 1
 * 2
 *
 * <b>Sample Output</b>
 * 16 13 1 7
 *
 * <b>Explanation</b>
 * The initial linked list is <i>16 13 7</i>. We have to insert <b>1</b> at the position <b>2</b> which currently has
 * <b>7</b> in it. The updated linked list will be <i>16 13 1 7</i>
 */
public class InsertANodeAtASpecificPositionInALinkedList {
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

    // Complete the insertNodeAtPosition function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
      int counter = 1;
      SinglyLinkedListNode node = head;
      while (node.next != null && counter < position) {
        node = node.next;
        counter++;
      }
      SinglyLinkedListNode next = node.next;
      SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
      newNode.next = next;
      node.next = newNode;
      return head;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      SinglyLinkedList llist = new SinglyLinkedList();

      int llistCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < llistCount; i++) {
        int llistItem = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        llist.insertNode(llistItem);
      }

      int data = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int position = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      SinglyLinkedListNode llist_head = insertNodeAtPosition(llist.head, data, position);

      printSinglyLinkedList(llist_head, " ", bufferedWriter);
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
    }
  }

}
