package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Design and build a "least recently used" cache, which evicts the least recently used item. The cache should map
 * from keys to values (allowing you to insert and retrieve a value associated with a particular key) and be
 * initialized with a max size. When it is full, it should evict the least recently used item.
 * Answer is in {@link leetcode.amazon.LRUCache}
 */
public class LruCache {
  private HashMap<Integer, Node> nodeTable;
  private Node head;
  private Node tail;
  private int sizeLimit;

  public LruCache(int size) {
    this.sizeLimit = size;
    nodeTable = new HashMap<>();
  }

  public void insert(int key, char value) {
    Node node = new Node(key, value);
    if (nodeTable.containsKey(key)) {
      node = nodeTable.get(key);
      node.value = value;
      get(key);
      return;
    }
    if (nodeTable.size() == sizeLimit) {
      removeFirstEntry();
    }
    nodeTable.put(key, node);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
  }

  private void removeFirstEntry() {
    nodeTable.remove(head.key);
    head = head.next;
    head.prev = null;
  }

  public void printAllEntries() {
    Node node = head;
    while (node != null) {
      System.out.printf("%s,", node.value);
      node = node.next;
    }
    System.out.println();
  }

  public char get(int key) {
    if (!nodeTable.containsKey(key)) {
      return Character.MIN_VALUE;
    }
    Node node = nodeTable.get(key);
    // Update the index
    Node oldPrev = node.prev;
    Node oldNext = node.next;
    tail.next = node;
    node.prev = tail;
    node.next = null;
    tail = node;

    if (oldPrev != null) {
      oldPrev.next = oldNext;
    } else { // mean this is a head node
      head = oldNext;
    }
    if (oldNext != null) {
      oldNext.prev = oldPrev;
    }
    return node.value;
  }

  private class Node {
    public int key;
    public char value;
    public Node next;
    public Node prev;

    public Node(int key, char value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
          .add("key=" + key)
          .add("value=" + value)
          .toString();
    }
  }

  public static void main(String[] args) {
    LruCache lruCache = new LruCache(3);
    lruCache.insert(3, 'C');
    lruCache.printAllEntries();
    lruCache.insert(2, 'B');
    lruCache.printAllEntries();
    lruCache.get(3);
    lruCache.printAllEntries();
    lruCache.insert(4, 'D');
    lruCache.printAllEntries();
    lruCache.insert(1, 'A');
    lruCache.printAllEntries();
  }
}
