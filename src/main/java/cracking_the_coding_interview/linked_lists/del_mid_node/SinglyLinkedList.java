package cracking_the_coding_interview.linked_lists.del_mid_node;

import java.util.*;

public class SinglyLinkedList {
    Node head;
    Node last;

    public void add(Integer element) {
        if(head == null) {
            head = new Node(element, last);
        } else if(last == null){
            last = new Node(element, null);
            head.setNext(last);
        } else {
            Node newLast = new Node(element, null);
            last.setNext(newLast);
            last = newLast;
        }
    }

    public Node getFirst() {
        return head;
    }
}