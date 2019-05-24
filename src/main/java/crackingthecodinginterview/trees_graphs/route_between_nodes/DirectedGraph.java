package crackingthecodinginterview.trees_graphs.route_between_nodes;

import java.util.Arrays;

public class DirectedGraph {
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
