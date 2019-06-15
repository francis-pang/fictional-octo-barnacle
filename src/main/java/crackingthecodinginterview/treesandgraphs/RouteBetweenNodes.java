package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

  public static class DirectedGraph {
        private DirectedNode[] directedNodes;

        public DirectedGraph(DirectedNode[] directedNodes) {
            this.directedNodes = directedNodes;
        }

        public void clearNodesVisit() {
            for (DirectedNode node : directedNodes) {
                node.visited = false;
            }
        }

        public DirectedNode[] getDirectedNodes() {
            return directedNodes;
        }

        public void setDirectedNodes(DirectedNode[] directedNodes) {
            this.directedNodes = directedNodes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DirectedGraph)) return false;
            DirectedGraph that = (DirectedGraph) o;
            return Arrays.equals(directedNodes, that.directedNodes);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(directedNodes);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("DirectedGraph{");
            sb.append("directedNodes=").append(Arrays.toString(directedNodes));
            sb.append('}');
            return sb.toString();
        }
    }

  public static class DirectedNode {
        private List<DirectedNode> connectedNodes;
        public boolean visited;
        private int value;

        public DirectedNode(int value) {
            this.value = value;
            this.connectedNodes = new ArrayList<>();
        }

        public DirectedNode(int value, List<DirectedNode> connectedNodes) {
            this.value = value;
            this.connectedNodes = connectedNodes;
        }

        public void addConnectedNode (DirectedNode directedNode) {
            this.connectedNodes.add(directedNode);
        }

        public List<DirectedNode> getConnectedNodes() {
            return this.connectedNodes;
        }

        public void setConnectedNodes(List<DirectedNode> connectedNodes) {
            this.connectedNodes = connectedNodes;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DirectedNode)) return false;
            DirectedNode that = (DirectedNode) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(connectedNodes, visited, value);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("DirectedNode{");
            sb.append("connectedNodes=").append(connectedNodes);
            sb.append(", visited=").append(visited);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

}
