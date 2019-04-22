package crackingthecodinginterview.linkedlists;

public class DeleteMiddleNode {
  public void deleteMiddleNode(Node node) {
    do {
      Node nextNode = node.getNext();
      node.setElement(nextNode.getElement());
      node.setNext(nextNode.getNext());
    } while (node.getNext() != null);
  }

  static class Node {
    private Node next;
    private Integer element;

    public Node(Integer element, Node next) {
      this.element = element;
      this.next = next;
    }

    public Node getNext() {
      return this.next;
    }

    public Integer getElement() {
      return this.element;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public void setElement(Integer element) {
      this.element = element;
    }
  }

}
