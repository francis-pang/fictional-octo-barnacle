package other;

import org.junit.jupiter.api.Test;
import other.algorithm.Dijkstra;

import java.util.ArrayList;

import static other.algorithm.Dijkstra.Edge;
import static other.algorithm.Dijkstra.Node;

class DijkstraTest {

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

    Dijkstra.Graph graph = new Dijkstra.Graph();
    graph.nodes.add(start);
    graph.nodes.add(a);
    graph.nodes.add(b);
    graph.nodes.add(c);
    graph.nodes.add(d);

    Dijkstra dijkstra = new Dijkstra();
    dijkstra.findShortestPath(graph, start);
  }
}
