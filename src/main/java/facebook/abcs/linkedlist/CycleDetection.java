package facebook.abcs.linkedlist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A linked list is said to contain a cycle if any node is visited more than once while traversing the list. Complete
 * the function provided for you in your editor. It has one parameter: a pointer to a Node object named <b>head</b> that
 * points
 * to the head of a linked list. Your function must return a boolean denoting
 * whether or not there is a cycle in the list. If there is a cycle, return true; otherwise, return false.
 * <b>Note</b>: If the list is empty, <b>head</b> will be null.
 *
 * <b>Input Format</b>
 * Our hidden code checker passes the appropriate argument to your function. You are not responsible for
 * reading any input from stdin.
 *
 * <b>Constraints</b>
 * 0 <= list size <= 1000
 *
 * <b>Output Format</b>
 * If the list contains a cycle, your function must return true. If the list does not contain a cycle, it must
 * return false. The binary integer corresponding to the boolean value returned by your function is printed to
 * stdout by our hidden code checker.
 *
 * <b>Sample Input</b>
 * The following linked lists are passed as arguments to your function:
 *
 * <b>Sample Output</b>
 * 0
 * 1
 * <b>Explanation</b>
 * 1. The first list has no cycle, so we return false and the hidden code checker prints to stdout.
 * 2. The second list has a cycle, so we return true and the hidden code checker prints to stdout.
 */
public class CycleDetection {
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

    // Complete the hasCycle function below.
    static boolean hasCycle(SinglyLinkedListNode head) {
      SinglyLinkedListNode slowRunner = head;
      SinglyLinkedListNode fastRunner = head;

      while (slowRunner != null) {
        slowRunner = slowRunner.next;
        try {
          fastRunner = fastRunner.next.next;
        } catch (NullPointerException e) {
          return false;
        }
        if (slowRunner.equals(fastRunner)) {
          return true;
        }
      }
      return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int tests = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int testsItr = 0; testsItr < tests; testsItr++) {
        int index = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        SinglyLinkedList llist = new SinglyLinkedList();

        int llistCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < llistCount; i++) {
          int llistItem = scanner.nextInt();
          scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

          llist.insertNode(llistItem);
        }

        SinglyLinkedListNode extra = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp = llist.head;

        for (int i = 0; i < llistCount; i++) {
          if (i == index) {
            extra = temp;
          }

          if (i != llistCount - 1) {
            temp = temp.next;
          }
        }

        temp.next = extra;

        boolean result = hasCycle(llist.head);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
    }
  }
}
