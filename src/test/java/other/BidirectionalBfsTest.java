package other;

import org.junit.jupiter.api.Test;
import other.algorithm.BidirectionalBfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static other.algorithm.BidirectionalBfs.Node;

class BidirectionalBfsTest {

  @Test
  void distanceBetweenTwoNode() {
    Node nodeA = new Node('a');
    Node nodeB = new Node('b');
    Node nodeC = new Node('c');
    Node nodeD = new Node('d');
    Node nodeE = new Node('e');
    Node nodeF = new Node('f');
    Node nodeG = new Node('g');
    Node nodeH = new Node('h');
    Node nodeI = new Node('i');
    Node nodeJ = new Node('j');
    Node nodeK = new Node('k');
    Node nodeL = new Node('l');
    Node nodeM = new Node('m');
    Node nodeN = new Node('n');
    Node nodeO = new Node('o');

    nodeA.neighbours.add(nodeE);
    nodeB.neighbours.add(nodeE);
    nodeE.neighbours.add(nodeA);
    nodeE.neighbours.add(nodeB);
    nodeE.neighbours.add(nodeK);

    nodeC.neighbours.add(nodeJ);
    nodeD.neighbours.add(nodeJ);
    nodeJ.neighbours.add(nodeC);
    nodeJ.neighbours.add(nodeD);
    nodeJ.neighbours.add(nodeK);

    nodeK.neighbours.add(nodeE);
    nodeK.neighbours.add(nodeJ);
    nodeK.neighbours.add(nodeH);

    nodeH.neighbours.add(nodeK);
    nodeH.neighbours.add(nodeI);

    nodeI.neighbours.add(nodeH);
    nodeI.neighbours.add(nodeJ);
    nodeI.neighbours.add(nodeK);

    nodeJ.neighbours.add(nodeI);
    nodeJ.neighbours.add(nodeL);
    nodeJ.neighbours.add(nodeM);
    nodeL.neighbours.add(nodeJ);
    nodeM.neighbours.add(nodeJ);

    nodeK.neighbours.add(nodeI);
    nodeK.neighbours.add(nodeN);
    nodeK.neighbours.add(nodeO);
    nodeN.neighbours.add(nodeK);
    nodeO.neighbours.add(nodeK);

    BidirectionalBfs bidirectionalBfs = new BidirectionalBfs();
    assertEquals(6, bidirectionalBfs.findShortestPath(nodeA, nodeO).size());
    assertEquals(6, bidirectionalBfs.findShortestPath(nodeC, nodeM).size());
  }
}