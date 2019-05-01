package leetcode;

import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class IntersectionOfTwoLinkedLists {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    //Pre-computation
    HashSet<ListNode> listASet = new HashSet<>();
    ListNode iteratingNode = headA;
    do {
      listASet.add(iteratingNode);
      iteratingNode = iteratingNode.next;
    } while (iteratingNode != null);
    //Computation
    iteratingNode = headB;
    do {
      if (listASet.contains(iteratingNode)) {
        return iteratingNode;
      }
      iteratingNode = iteratingNode.next;
    } while (iteratingNode != null);
    return null;
  }
}
