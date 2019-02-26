package leetcode.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CopyListWithRandomPointer {
    // Map to store the mapping between old Node and new Node
    Map<Node, Node> nodeMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        // Create null mapping
        nodeMap.put(null, null);

        // Create a new Node
        // Copy the value over
        // Create a new Node, meant for the next Node
        // Put the node into the map, we put this early so that we can detect self-referencing random pointer
        // Handling the Random Node, tricky
        // 2 cases:
        // 1. null: No action needed, just need to point random to null
        // 2. not null: The random Node can be a existing Node, or a new Node
        /* Store a mapping of the reference of the old Node and the new Node, so that we can
         * check if the Random Node is an existing Node which we have created before
         */
        return createNewNode(head);
    }

    private Node createNewNode(Node node) {
        // Edge case: Node is null
        if (node == null) {
            return null;
        }

        Node newNode;
        if (nodeMap.containsKey(node)) {
            newNode = nodeMap.get(node);
        } else {
            newNode = new Node();
            newNode.val = node.val;
        }
        nodeMap.put(node, newNode);

        // ASSERTION: New node shouldn't be created yet, so we will go create it
        Node newNodeNext = createNewNode(node.next);
        nodeMap.put(node, newNode);

        Node newNodeRandom;
        // ASSERTION: all the nodes in the linked list will have been created
        newNode.random = nodeMap.get(node.random); // Edge case: Handle null, taken care of null entry in map
        return newNode;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }

        @Override
        public boolean equals(Object o) {
            /*
             * In this object comparison, we do not need to check the random Node,
             * because it is not a determinant
             */
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node node = (Node) o;
            if (this.val == node.val && Objects.equals(this.next, node.next)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
