package crackingthecodinginterview.treesandgraphs;

import crackingthecodinginterview.treesandgraphs.RouteBetweenNodes.DirectedNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteBetweenNodesTest {
    private static RouteBetweenNodes routeBetweenNodes;
  private static RouteBetweenNodes.DirectedGraph directedGraph;
    private static DirectedNode directedNode1;
    private static DirectedNode directedNode2;
    private static DirectedNode directedNode3;
    private static DirectedNode directedNode4;
    private static DirectedNode directedNode5;
    private static DirectedNode directedNode6;

    @BeforeAll
    static void setUpOnce() {
        directedNode1 = new DirectedNode(1);
        directedNode2 = new DirectedNode(2);
        directedNode3 = new DirectedNode(3);
        directedNode4 = new DirectedNode(4);
        directedNode5 = new DirectedNode(5);
        directedNode6 = new DirectedNode(6);

        // Add edge
        directedNode1.addConnectedNode(directedNode2);
        directedNode1.addConnectedNode(directedNode4);
        directedNode2.addConnectedNode(directedNode3);
        directedNode3.addConnectedNode(directedNode4);
        directedNode4.addConnectedNode(directedNode1);
        directedNode5.addConnectedNode(directedNode6);

        DirectedNode[] directedNodes = new DirectedNode[6];
        directedNodes[0] = directedNode1;
        directedNodes[1] = directedNode2;
        directedNodes[2] = directedNode3;
        directedNodes[3] = directedNode4;
        directedNodes[4] = directedNode5;
        directedNodes[5] = directedNode6;
      directedGraph = new RouteBetweenNodes.DirectedGraph(directedNodes);

        routeBetweenNodes = new RouteBetweenNodes();
    }

    @BeforeEach
    void setUp() {
        directedGraph.clearNodesVisit();
    }

    @Test
    public void testDirectlyConnected() {
        assertTrue(routeBetweenNodes.isThereARouteBetweenTwoNodes(directedGraph, directedNode1, directedNode2));
        assertTrue(routeBetweenNodes.isThereARouteBetweenTwoNodes(directedGraph, directedNode5, directedNode6));
    }

    @Test
    public void testIndirectlyConnected() {
        assertTrue(routeBetweenNodes.isThereARouteBetweenTwoNodes(directedGraph, directedNode1, directedNode3));
    }

    @Test
    public void testNoLinkageNodes() {
        assertFalse(routeBetweenNodes.isThereARouteBetweenTwoNodes(directedGraph, directedNode1, directedNode6));
    }

    @Test
    public void testNoLinkageInLinkedGraph() {
        assertTrue(routeBetweenNodes.isThereARouteBetweenTwoNodes(directedGraph, directedNode3, directedNode2));
    }
}