package leetcode.amazon;


/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int startingNode = 0;
        ListNode sortedList = null;
        do {
            if (lists[startingNode] != null) {
                sortedList = lists[startingNode];
            }
            startingNode++;
        } while (startingNode < lists.length && sortedList == null);

        for(int counter = startingNode; counter < lists.length; counter++) {
            ListNode toBeInsertedNode = lists[counter];
            while(toBeInsertedNode != null) {
                boolean inserted = false;
                ListNode currentNode = sortedList;
                ListNode previousNode = null;
                do {
                    if(currentNode.val >= toBeInsertedNode.val) {
                        if (previousNode == null) { //to be inserted as first node
                            sortedList = new ListNode(toBeInsertedNode.val);
                            sortedList.next = currentNode;
                        } else {
                            previousNode.next = new ListNode(toBeInsertedNode.val);
                            previousNode.next.next = currentNode;
                        }
                        inserted = true;
                    } else {
                        previousNode = currentNode;
                        currentNode = currentNode.next;
                    }
                } while(currentNode != null && !inserted);
                if (!inserted) { // Edge case: The number is biggest, and meant to be put in the last position
                    previousNode.next = new ListNode(toBeInsertedNode.val);
                }
                toBeInsertedNode = toBeInsertedNode.next;
            }
        }
        return sortedList;
    }
}
