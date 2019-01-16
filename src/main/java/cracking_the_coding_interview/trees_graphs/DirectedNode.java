package cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class DirectedNode {
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
