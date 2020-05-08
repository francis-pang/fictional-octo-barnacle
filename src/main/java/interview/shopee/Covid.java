package interview.shopee;

/*
Let us represent a group of people as a binary tree. In the Covid-19 virus pandemic, if a person is infected, then every next day, any person that’s linked to an infected person is infected too. Given the root of the tree, the value(X) of the patient zero or first infected person(that person is infected in day 0), and day Y, return how many people are infected in day Y.

       1
      /  \
    2      3
   /  \
 4     5
      /  \
    6     7
            \
             8
For example:
Input is root 1, person value X is 5, day Y is 2
Output will be 7
Explain: 1,2,4,5,6,7,8 are infected on day 2

Assumption:
* All node values are distinct
* X is confirmed a node's value in the tree

class Node:
    Node left
    Node right
    int val

//implement
int solution(Node* root, int x, int y){

}
*/

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1. Traverse through the tree, add parent relationship to the tree
 * 2. Locate where is the Binary node of X
 * 3. BFS in up to Y level. count of them
 * 4. Return the count
 * <p>
 * Time: O(e), e = no. of edge + O(2^Y)
 * Space: O(n)
 */

public class Covid {
  public static class Node {
    public Node left;
    public Node right;
    public int val;
  }

  public int solution(Node root, int x, int y) {
    Map<Node, Node> parentRelationships = new HashMap<>();
    constructParentRelationship(root, parentRelationships);
    Node nodeX = findNodeX(root, x);
    if (nodeX == null) {
      return -1;
    }

    Queue<Node> nextLevel = new ArrayDeque<>();
    nextLevel.add(nodeX);
    Set<Node> visited = new HashSet<>();
    int result = 0;
    int day = 0;
    while (!nextLevel.isEmpty() && day <= y) {
      Queue<Node> q = nextLevel;
      nextLevel = new ArrayDeque<>();
      while (!q.isEmpty()) {
        Node node = q.poll();
        if (!visited.add(node)) {
          continue;
        }
        result++;
        if (node.left != null) {
          nextLevel.add(node.left);
        }
        if (node.right != null) {
          nextLevel.add(node.right);
        }
        if (parentRelationships.containsKey(node)) {
          nextLevel.add(parentRelationships.get(node));
        }
      }
    }
    return result;

  }

  private void constructParentRelationship(Node node, Map<Node, Node> rs) {
    if (node == null) {
      return;
    }
    if (node.left != null) {
      rs.put(node.left, node);
      constructParentRelationship(node.left, rs);
    }
    if (node.right != null) {
      rs.put(node.right, node);
      constructParentRelationship(node.right, rs);
    }
  }

  private Node findNodeX(Node node, int x) {
    if (node.val == x) {
      return node;
    }
    Node found = null;
    if (node.left != null) {
      found = findNodeX(node.left, x);
    }
    if (found != null) {
      return found;
    }
    if (node.right != null) {
      found = findNodeX(node.right, x);
    }
    return found;
  }
}


