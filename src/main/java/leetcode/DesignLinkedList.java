package leetcode;

import java.util.StringJoiner;

public class DesignLinkedList {
  static class MyLinkedList {
    Node head;
    Node tail;
    int size;

    private final int INVALID_INDEX = -1;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
      size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
      if (index < 0 || index >= size) {
        return INVALID_INDEX;
      }
      Node node = head;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
      return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will
     * be the first node of the linked list.
     */
    public void addAtHead(int val) {
      Node node = new Node(val);
      if (size == 0) {
        head = node;
        tail = node;
      } else {
        Node oldHead = head;
        oldHead.prev = node;
        node.next = oldHead;
        head = node;
      }
      size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
      Node node = new Node(val);
      if (size == 0) {
        head = node;
        tail = node;
      } else {
        Node oldTail = tail;
        oldTail.next = node;
        node.prev = oldTail;
        tail = node;
      }
      size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked
     * list, the node will be appended to the end of linked list. If index is greater than the length, the node
     * will not be inserted.
     */
    public void addAtIndex(int index, int val) {
      if (index < 0 || index > size) {
        return;
      }

      if (index == 0) {
        addAtHead(val);
      } else if (index == size) {
        addAtTail(val);
      } else {
        Node node = new Node(val);
        Node previousNode = head;
        Node insertBeforeMe = head.next;
        for (int i = 1; i < index; i++) {
          previousNode = insertBeforeMe;
          insertBeforeMe = insertBeforeMe.next;
        }
        previousNode.next = node;
        node.prev = previousNode;
        insertBeforeMe.prev = node;
        node.next = insertBeforeMe;
        size++;
      }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
      if (index < 0 || index >= size) {
        return;
      }

      if (size == 1 && index == 0) { // if size == 1
        head = null;
        tail = null;
      } else if (index == 0) { // if delete head
        Node newHead = head.next;
        newHead.prev = null;
        head = newHead;
      } else if (index == size - 1) { // if delete tail
        Node newTail = tail.prev;
        newTail.next = null;
        tail = newTail;
      } else {
        Node previousNode = head;
        Node nodeToBeDeleted = head.next;
        for (int i = 1; i < index; i++) {
          previousNode = nodeToBeDeleted;
          nodeToBeDeleted = nodeToBeDeleted.next;
        }
        previousNode.next = nodeToBeDeleted.next;
        if (nodeToBeDeleted.next != null) {
          nodeToBeDeleted.next.prev = previousNode;
        }
      }
      size--;
    }

    @Override
    public String toString() {
      Node node = head;
      StringJoiner stringJoiner = new StringJoiner(", ", MyLinkedList.class.getSimpleName() + "[", "]");
      while(node != null) {
        stringJoiner.add(Integer.toString(node.val));
        node = node.next;
      }
      return stringJoiner.toString();
    }
  }

  static class Node {
    public int val;
    public Node next;
    public Node prev;

    public Node(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    //["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
    //[[],[1],[3],[1,2],[1],[1],[1]]
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(3);
    myLinkedList.addAtIndex(1, 2);
    System.out.println(myLinkedList.get(1));
    myLinkedList.deleteAtIndex(1);
    System.out.println(myLinkedList.get(1));

    myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.deleteAtIndex(0);

    //["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
    //[[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
    myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(7);
    myLinkedList.addAtHead(2);
    myLinkedList.addAtHead(1);
    myLinkedList.addAtIndex(3, 0);
    myLinkedList.deleteAtIndex(2);
    myLinkedList.addAtHead(6);
    myLinkedList.addAtTail(4);
    System.out.println(myLinkedList.get(4)); // Should be 4
    myLinkedList.addAtHead(4);
    myLinkedList.addAtIndex(5, 0);
    myLinkedList.addAtHead(6);

    //["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
    //[[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]
    myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(2);
    myLinkedList.deleteAtIndex(1);
    myLinkedList.addAtHead(2);
    myLinkedList.addAtHead(7);
    myLinkedList.addAtHead(3);
    myLinkedList.addAtHead(2);
    myLinkedList.addAtHead(5);
    myLinkedList.addAtTail(5);
    System.out.println(myLinkedList.get(5));
    myLinkedList.deleteAtIndex(6);
    myLinkedList.deleteAtIndex(4);

    //["MyLinkedList","addAtHead","get","addAtHead","addAtHead","deleteAtIndex","addAtHead","get","get","get","addAtHead","deleteAtIndex"]
    //[[],[4],[1],[1],[5],[3],[7],[3],[3],[3],[1],[4]]
    myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(4);
    System.out.println(myLinkedList.get(1));
    myLinkedList.addAtHead(1);
    myLinkedList.addAtHead(5);
    myLinkedList.deleteAtIndex(3);
    myLinkedList.addAtHead(7);
    System.out.println(myLinkedList.get(3));
    System.out.println(myLinkedList.get(3));
    System.out.println(myLinkedList.get(3));
    myLinkedList.addAtHead(1);
    myLinkedList.deleteAtIndex(4);
  }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
