package other;

import org.junit.jupiter.api.Test;
import other.algorithm.ShortestPathBfs;

import java.util.ArrayList;

import static other.algorithm.ShortestPathBfs.Graph;
import static other.algorithm.ShortestPathBfs.Node;

class ShortestPathBfsTest {

  @Test
  void findShortestPath() {
    Node start = new Node('s', new ArrayList<>());
    Node a = new Node('a', new ArrayList<>());
    Node b = new Node('b', new ArrayList<>());
    Node c = new Node('c', new ArrayList<>());
    Node d = new Node('d', new ArrayList<>());

    //Edge
    // Source
    start.outgoingEdges.add(a);
    start.outgoingEdges.add(b);

    // A
    a.outgoingEdges.add(start);
    a.outgoingEdges.add(b);

    // B
    b.outgoingEdges.add(a);
    b.outgoingEdges.add(start);
    b.outgoingEdges.add(c);

    Graph graph = new Graph();
    graph.nodes.add(start);
    graph.nodes.add(a);
    graph.nodes.add(b);
    graph.nodes.add(c);
    graph.nodes.add(d);

    ShortestPathBfs shortestPathBfs = new ShortestPathBfs();
    shortestPathBfs.findShortestPath(graph, start);
  }
}