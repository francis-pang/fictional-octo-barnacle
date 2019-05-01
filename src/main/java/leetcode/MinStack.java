package leetcode;

// https://leetcode.com/problems/min-stack/

class MinStack {
  int minimum;
  Node latestNode;

  /** initialize your data structure here. */
  public MinStack() {
  }

  /**
   * Push element x onto stack.
   */
  public void push(int x) {
    if (latestNode == null) {
      latestNode = new Node(x, null);
      minimum = x;
    } else {
      Node previousNode = latestNode;
      latestNode = new Node(x, previousNode);
      minimum = Math.min(minimum, x);
    }
  }

  /**
   *  Removes the element on top of the stack.
   */
  public void pop() {
    /*
     * This is the part which is tricky, because if you remove the node with the minimum value, you need to set a
     * new minimum value. By the brute force method, you need to traverse through the whole linked link again so
     * that you can get back new minimum, and this is O(n).
     */
    if (latestNode != null) { // Nothing to remove
      latestNode = latestNode.next;
      if (latestNode.value == minimum) {
        // Need to use brute force to look for smallest again
        locateNewMinimum();
      }
    }
  }

  private void locateNewMinimum() {
    minimum = latestNode.value;
    Node nodeIterator = latestNode.next;
    while(nodeIterator != null) {
      minimum = Math.min(minimum, nodeIterator.value);
      nodeIterator = nodeIterator.next;
    }
  }

  /**
   *  Get the top element.
   */
  public int top() {
    return (latestNode == null) ? -1 : latestNode.value;
  }

  /**
   * Retrieve the minimum element in the stack.
   */
  public int getMin() {
    return minimum;
  }

  protected class Node {
    public int value;
    public Node next;

    public Node() {}

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
