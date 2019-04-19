package leetcode.amazon;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class PalindromeLinkedList {
  public boolean isPalindrome(ListNode head) {
    // Check for null
    if (head == null) {
      return true;
    }
    // Check for single node
    if (head.next == null) {
      return true;
    }

    int[] valueArray = new int[Short.MAX_VALUE * 2];

    // Build the stack first
    ListNode iteratingNode = head;
    int counter = 0;
    while (iteratingNode != null) {
      //System.out.println("Adding " + iteratingNode.val + " into the stack");
      valueArray[counter++] = iteratingNode.val;
      iteratingNode = iteratingNode.next;
    }

    // Iterate the linked list the 2nd time so that we can compare the node
    // Trick: Iterate half the list is enough
    iteratingNode = head;
    int total = counter;
    for (int i = counter - 1; i >= counter / 2; i--) {
      //System.out.println("i=" + i);
      //System.out.println("Comparing " + iteratingNode.val + " against " + valueArray[i]);
      if (iteratingNode.val != valueArray[i]) {
        return false;
      }
      iteratingNode = iteratingNode.next;
    }
    return true;
  }
}
