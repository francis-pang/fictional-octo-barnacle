package cracking_the_coding_interview.linked_lists.del_mid_node;

public class Node {
    private Node next;
    private Integer element;

    public Node (Integer element, Node next) {
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
