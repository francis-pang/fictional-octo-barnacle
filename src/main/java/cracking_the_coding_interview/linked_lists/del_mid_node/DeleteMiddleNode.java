package cracking_the_coding_interview.linked_lists.del_mid_node;

public class DeleteMiddleNode {
    public void deleteMiddleNode (Node node) {
        do {
            Node nextNode = node.getNext();
            node.setElement(nextNode.getElement());
            node.setNext(nextNode.getNext());
        } while(node.getNext() != null);
    }
}
