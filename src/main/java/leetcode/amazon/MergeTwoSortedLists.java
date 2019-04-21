package leetcode.amazon;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        } else if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        ListNode comparingList1Node = list1;
        ListNode comparingList2Node = list2;
        // Compare first node
        if (comparingList1Node.val > comparingList2Node.val) {
            ListNode precedeNode = new ListNode(comparingList2Node.val);
            precedeNode.next = comparingList1Node;
            comparingList1Node = precedeNode;
            list1 = precedeNode;
            comparingList2Node = comparingList2Node.next;
        }

        while (comparingList1Node.next != null && comparingList2Node != null) {
            if (comparingList1Node.next.val >= comparingList2Node.val) {
                ListNode nextNode = comparingList1Node.next;
                comparingList1Node.next = new ListNode(comparingList2Node.val);
                comparingList1Node.next.next = nextNode;
                comparingList2Node = comparingList2Node.next;
            }
            comparingList1Node = comparingList1Node.next;
        }
        if (comparingList2Node != null && comparingList2Node.val >= comparingList1Node.val) {
            comparingList1Node.next = comparingList2Node;
        }
        return list1;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
