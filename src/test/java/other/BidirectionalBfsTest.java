package other;

import org.junit.jupiter.api.Test;
import other.algorithm.BidirectionalBfs;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static other.algorithm.BidirectionalBfs.Graph;
import static other.algorithm.BidirectionalBfs.Node;

class BidirectionalBfsTest {

  @Test
  void distanceBetweenTwoNode() {
    Graph graph = new Graph();

    Node node0 = new Node(00, new ArrayList<>());
    Node node1 = new Node(01, new ArrayList<>());
    Node node2 = new Node(02, new ArrayList<>());
    Node node3 = new Node(03, new ArrayList<>());
    Node node4 = new Node(04, new ArrayList<>());
    Node node5 = new Node(05, new ArrayList<>());
    Node node6 = new Node(06, new ArrayList<>());
    Node node7 = new Node(07, new ArrayList<>());
    Node node8 = new Node(8, new ArrayList<>());
    Node node9 = new Node(9, new ArrayList<>());
    Node node10 = new Node(10, new ArrayList<>());
    Node node11 = new Node(11, new ArrayList<>());
    Node node12 = new Node(12, new ArrayList<>());
    Node node13 = new Node(13, new ArrayList<>());
    Node node14 = new Node(14, new ArrayList<>());

    node0.outgoingEdges.add(node4);
    node1.outgoingEdges.add(node4);
    node4.outgoingEdges.add(node0);
    node4.outgoingEdges.add(node1);
    node4.outgoingEdges.add(node6);

    node2.outgoingEdges.add(node5);
    node3.outgoingEdges.add(node5);
    node5.outgoingEdges.add(node2);
    node5.outgoingEdges.add(node3);
    node5.outgoingEdges.add(node6);

    node6.outgoingEdges.add(node4);
    node6.outgoingEdges.add(node5);
    node6.outgoingEdges.add(node7);

    node7.outgoingEdges.add(node6);
    node7.outgoingEdges.add(node8);

    node8.outgoingEdges.add(node7);
    node8.outgoingEdges.add(node9);
    node8.outgoingEdges.add(node10);

    node9.outgoingEdges.add(node8);
    node9.outgoingEdges.add(node11);
    node9.outgoingEdges.add(node12);
    node11.outgoingEdges.add(node9);
    node12.outgoingEdges.add(node9);

    node10.outgoingEdges.add(node8);
    node10.outgoingEdges.add(node13);
    node10.outgoingEdges.add(node14);
    node13.outgoingEdges.add(node10);
    node14.outgoingEdges.add(node10);

    graph.nodes.add(node0);
    graph.nodes.add(node1);
    graph.nodes.add(node2);
    graph.nodes.add(node3);
    graph.nodes.add(node4);
    graph.nodes.add(node5);
    graph.nodes.add(node6);
    graph.nodes.add(node7);
    graph.nodes.add(node8);
    graph.nodes.add(node9);
    graph.nodes.add(node10);
    graph.nodes.add(node11);
    graph.nodes.add(node12);
    graph.nodes.add(node13);
    graph.nodes.add(node14);

    BidirectionalBfs bidirectionalBfs = new BidirectionalBfs();
    assertEquals(6, bidirectionalBfs.distanceBetweenTwoNode(graph, node0, node14));
    assertEquals(6, bidirectionalBfs.distanceBetweenTwoNode(graph, node2, node12));
  }
}