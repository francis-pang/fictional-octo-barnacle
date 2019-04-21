package leetcode.amazon;

import java.util.Objects;

// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListNode)) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ListNode{");
        sb.append(val);
        ListNode nextNode = next;
        while (nextNode != null) {
            sb.append(", " + nextNode.val);
            nextNode = nextNode.next;
        }
        sb.append('}');
        return sb.toString();
    }
}