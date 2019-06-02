package crackingthecodinginterview.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class BreadthFirstSearchTest {
  @Test
  void testNoElementFound() {
    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

    System.out.println("Tree contains " + breadthFirstSearch.printTree(null));
    assertNull(breadthFirstSearch.locateNode(null, 2));
  }

  @Test
  void testSingleElementFound() {
    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

    BreadthFirstSearch.Node root = new BreadthFirstSearch.Node(1);

    System.out.println("Tree contains " + breadthFirstSearch.printTree(root));
    assertEquals(root, breadthFirstSearch.locateNode(root, 1));
  }

  @Test
  void testSingleElementNotFound() {
    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

    BreadthFirstSearch.Node root = new BreadthFirstSearch.Node(1);

    System.out.println("Tree contains " + breadthFirstSearch.printTree(root));
    assertNull(breadthFirstSearch.locateNode(root, 2));
  }

  @Test
  void testMultipleElementFound() {
    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

    BreadthFirstSearch.Node root = new BreadthFirstSearch.Node(1);
    BreadthFirstSearch.Node node2 = new BreadthFirstSearch.Node(2);
    BreadthFirstSearch.Node node3 = new BreadthFirstSearch.Node(3);
    BreadthFirstSearch.Node node4 = new BreadthFirstSearch.Node(4);
    BreadthFirstSearch.Node node5 = new BreadthFirstSearch.Node(5);
    BreadthFirstSearch.Node node6 = new BreadthFirstSearch.Node(6);
    BreadthFirstSearch.Node node7 = new BreadthFirstSearch.Node(7);
    BreadthFirstSearch.Node node8 = new BreadthFirstSearch.Node(8);

    root.children.add(node2);
    root.children.add(node3);
    node2.children.add(node4);
    node3.children.add(node6);
    node3.children.add(node7);
    node4.children.add(node5);
    node4.children.add(node8);

    System.out.println("Tree contains " + breadthFirstSearch.printTree(root));
    assertEquals(node5, breadthFirstSearch.locateNode(root, 5));
  }

  @Test
  void testMultipleElementNotFound() {
    BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

    BreadthFirstSearch.Node root = new BreadthFirstSearch.Node(1);
    BreadthFirstSearch.Node node2 = new BreadthFirstSearch.Node(2);
    BreadthFirstSearch.Node node3 = new BreadthFirstSearch.Node(3);
    BreadthFirstSearch.Node node4 = new BreadthFirstSearch.Node(4);
    BreadthFirstSearch.Node node5 = new BreadthFirstSearch.Node(5);

    root.children.add(node2);
    root.children.add(node3);
    node2.children.add(node4);
    node4.children.add(node5);

    System.out.println("Tree contains " + breadthFirstSearch.printTree(root));
    assertNull(breadthFirstSearch.locateNode(root, 9));
  }
}