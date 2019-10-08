package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Given pointers to the head nodes of <b>2</b> linked lists that merge together at some point, find the Node where
 * the two
 * lists merge. It is guaranteed that the two head Nodes will be different, and neither will be NULL. In the diagram
 * below, the two lists converge at Node <i>x</i>:
 * <pre>
 * [List #1] a--->b--->c
 *                \
 *                 x--->y--->z--->NULL
 *                /
 *     [List #2] p--->q
 * </pre>
 * Complete the <i>int FindMergeNode(Node* headA, Node* headB)</i> method so that it finds and returns the
 * data value of the Node where the two lists merge.
 *
 * <b>Input Format</b>
 * Do not read any input from stdin/console.
 * The <i>FindMergeNode(Node*,Node*)</i> method has two parameters, <b>headA</b> and <b>headB</b>, which are the
 * non-null head Nodes of two separate linked lists that are guaranteed to converge.
 *
 * <b>Constraints</b>
 * The lists will merge.
 * <b>headA,headB != null</b>
 * <b>headA != headB</b>
 *
 * <b>Output Format</b>
 * Do not write any output to stdout/console.
 * Each Node has a data field containing an integer. Return the integer data for the Node where the two lists
 * merge.
 *
 * <b>Sample Input</b>
 * The diagrams below are graphical representations of the lists that input Nodes <b>headA</b> and <b>headB</b>are
 * connected to. Recall that this is a method-only challenge; the method only has initial visibility to those <b>2</b>
 * Nodes and must explore the rest of the Nodes using some algorithm of your own design.
 * <b>Test Case 0</b>
 * <pre>
 * 1
 *  \
 *   2--->3--->NULL
 *  /
 * 1
 * </pre>
 * <b>Test Case 1</b>
 * <pre>
 * 1--->2
 *  \
 *   3--->Null
 *  /
 * 1
 * </pre>
 * <b>Sample Output</b>
 * 2
 * 3
 * <b>Explanation</b>
 * Test Case 0: As demonstrated in the diagram above, the merge Node's data field contains the integer <b>2</b>.
 * Test Case 1: As demonstrated in the diagram above, the merge Node's data field contains the integer <b>3</b>.
 */
public class FindMergePointOfTwoLists {
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
    // Complete the findMergeNode function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
      Set<SinglyLinkedListNode> head1Nodes = new HashSet<>();
      // Collect all head1 node
      while (head1 != null) {
        head1Nodes.add(head1);
        head1 = head1.next;
      }

      SinglyLinkedListNode mergeNode = null;
      while (head2 != null) {
        if (head1Nodes.contains(head2)) {
          mergeNode = head2;
          break;
        } else {
          head2 = head2.next;
        }
      }
      if (mergeNode == null) {
        return -1;
      } else {
        return mergeNode.data;
      }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new StringWriter());

      int tests = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int testsItr = 0; testsItr < tests; testsItr++) {
        int index = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

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

        SinglyLinkedListNode ptr1 = llist1.head;
        SinglyLinkedListNode ptr2 = llist2.head;

        for (int i = 0; i < llist1Count; i++) {
          if (i < index) {
            ptr1 = ptr1.next;
          }
        }

        for (int i = 0; i < llist2Count; i++) {
          if (i != llist2Count - 1) {
            ptr2 = ptr2.next;
          }
        }

        ptr2.next = ptr1;

        int result = findMergeNode(llist1.head, llist2.head);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      scanner.close();
    }
  }

}
