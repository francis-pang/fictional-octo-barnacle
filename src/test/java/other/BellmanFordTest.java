package other;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static other.BellmanFord.Edge;
import static other.BellmanFord.Graph;
import static other.BellmanFord.Node;

class BellmanFordTest {

  @Test
  void findShortestPath() {
    Node start = new Node('s', new ArrayList<>());
    Node a = new Node('a', new ArrayList<>());
    Node b = new Node('b', new ArrayList<>());
    Node c = new Node('c', new ArrayList<>());
    Node d = new Node('d', new ArrayList<>());

    //Edge
    // Source
    Edge sToA = new Edge(5, a);
    start.outgoingEdges.add(sToA);
    Edge sToB = new Edge(15, b);
    start.outgoingEdges.add(sToB);

    // A
    Edge aToB = new Edge(6, b);
    a.outgoingEdges.add(aToB);


    // B
    Edge bToS = new Edge(15, start);
    b.outgoingEdges.add(bToS);
    Edge bToC = new Edge(2, c);
    b.outgoingEdges.add(bToC);

    Graph graph = new Graph();
    graph.nodes.add(start);
    graph.nodes.add(a);
    graph.nodes.add(b);
    graph.nodes.add(c);
    graph.nodes.add(d);

    BellmanFord bellmanFord = new BellmanFord();
    bellmanFord.findShortestPath(graph, start);
  }
}