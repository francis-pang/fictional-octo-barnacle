package cracking_the_coding_interview.trees_graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RouteBetweenNodes {
    public boolean isThereARouteBetweenTwoNodes(DirectedGraph graph, DirectedNode node1, DirectedNode node2) {
        /*
         * Going to use breadth first search for this
         */
        Queue<DirectedNode> queue = new LinkedList<DirectedNode>();

        // Initialisation stage - Put the first element into the queue first
        queue.add(node1);

        /*
         * Main loop
         * 1. Pop an element out of the queue
         * 2. Make the node as visited
         * 3. Retrieves its neighbours and check if it is node2
         * 4. Pops the neighbours into the queue
         */
        while(!queue.isEmpty()) {
            DirectedNode processingNode = queue.remove();
            System.out.println("Processing node " + processingNode.getValue() + " now");
            processingNode.visited = true;
            for (DirectedNode node : processingNode.getConnectedNodes()) {
                System.out.println("Pop out node " + node.getValue() + " from Processing Node " + processingNode.getValue());
                if (node.visited) {
                    continue;
                } else if (node2.equals(node)) {
                    return true;
                } else {
                    queue.add(node);
                }
            }
        }
        return false; // By this point, all the node connected indirectly to node1 will have been processed
    }
}
