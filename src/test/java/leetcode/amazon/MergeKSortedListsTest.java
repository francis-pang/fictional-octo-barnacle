package leetcode.amazon;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeKSortedListsTest {

    private static MergeKSortedLists mergeKSortedLists;

    @BeforeAll
    static void setUp() {
        mergeKSortedLists = new MergeKSortedLists();
    }

    @Test
    void mergeKLists() {
        //1st ListNode
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        //2nd ListNode
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        //3rd ListNode
        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(6);
        listNode7.next = listNode8;

        // Expected ListNode
        ListNode expectedListNode1 = new ListNode(1);
        ListNode expectedListNode2 = new ListNode(1);
        ListNode expectedListNode3 = new ListNode(2);
        ListNode expectedListNode4 = new ListNode(3);
        ListNode expectedListNode5 = new ListNode(4);
        ListNode expectedListNode6 = new ListNode(4);
        ListNode expectedListNode7 = new ListNode(5);
        ListNode expectedListNode8 = new ListNode(6);
        expectedListNode1.next = expectedListNode2;
        expectedListNode2.next = expectedListNode3;
        expectedListNode3.next = expectedListNode4;
        expectedListNode4.next = expectedListNode5;
        expectedListNode5.next = expectedListNode6;
        expectedListNode6.next = expectedListNode7;
        expectedListNode7.next = expectedListNode8;

        assertEquals(expectedListNode1, mergeKSortedLists.mergeKLists(new ListNode[]{listNode1, listNode4, listNode7}));
    }

    @Test
    void mergeKSortedList2SingleList() {
        //1st ListNode
        ListNode listNode1 = new ListNode(0);

        //2nd ListNode
        ListNode listNode2 = new ListNode(1);

        // Expected ListNode
        ListNode expectedListNode1 = new ListNode(0);
        ListNode expectedListNode2 = new ListNode(1);
        expectedListNode1.next = expectedListNode2;

        assertEquals(expectedListNode1, mergeKSortedLists.mergeKLists(new ListNode[]{listNode1, listNode2}));
    }
}