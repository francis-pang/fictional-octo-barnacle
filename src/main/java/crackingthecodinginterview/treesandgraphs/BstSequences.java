package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BstSequences {
  public static List<List<BinaryTreeNode>> getAllPossibleInsertionSequences(BinaryTreeNode root) {
    // Node on next level
    List<List<BinaryTreeNode>> nodesByLevel = new ArrayList<>();
    List<BinaryTreeNode> nodesOnNextLevel = new ArrayList<>();
    nodesOnNextLevel.add(root);

    // Building the list of node on each level
    while (nodesOnNextLevel.size() > 0) {
      List<BinaryTreeNode> nodesOnSameLevel = new ArrayList<>();
      nodesOnSameLevel.addAll(nodesOnNextLevel);
      nodesByLevel.add(nodesOnSameLevel);
      nodesOnNextLevel.clear();
      for (BinaryTreeNode node : nodesOnSameLevel) {
        if (node.left != null) {
          nodesOnNextLevel.add(node.left);
        }
        if (node.right != null) {
          nodesOnNextLevel.add(node.right);
        }
      }
    }

    if (nodesByLevel.size() == 1) {
      return nodesByLevel;
    } else {
      List<List<BinaryTreeNode>> allPossibleSequences = new ArrayList<>();
      for (List<BinaryTreeNode> currentLevelNodes : nodesByLevel) {
        allPossibleSequences = mergeTwoBinaryNodeList(allPossibleSequences, processSameLayerNodes(currentLevelNodes,
            currentLevelNodes.remove(0)));
      }
      return allPossibleSequences;
    }
  }

  private static List<List<BinaryTreeNode>> mergeTwoBinaryNodeList(List<List<BinaryTreeNode>> preNodesList,
                                                                   List<List<BinaryTreeNode>> postNodesList) {
    if (preNodesList.size() == 0) {
      return postNodesList;
    }
    List<List<BinaryTreeNode>> mergedList = new ArrayList<>();
    for (List<BinaryTreeNode> preNodes : preNodesList) {
      for (List<BinaryTreeNode> postNodes : postNodesList) {
        List<BinaryTreeNode> combinedNodes = new ArrayList<>();
        combinedNodes.addAll(preNodes);
        combinedNodes.addAll(postNodes);
        mergedList.add(combinedNodes);
      }
    }
    return mergedList;
  }

  private static List<List<BinaryTreeNode>> processSameLayerNodes(List<BinaryTreeNode> nodes, BinaryTreeNode node) {
    if (nodes.size() == 0) {
      List<BinaryTreeNode> singleNodeList = new ArrayList<>();
      singleNodeList.add(node);
      List<List<BinaryTreeNode>> singleList = new ArrayList<>();
      singleList.add(singleNodeList);
      return singleList;
    }
    List<List<BinaryTreeNode>> listOfCombinationsWithoutNewNode = processSameLayerNodes(nodes, nodes.remove(0));
    List<List<BinaryTreeNode>> listOfCombinationsWithNewNode = new ArrayList<>();
    for (List<BinaryTreeNode> allCombinationNodes : listOfCombinationsWithoutNewNode) {
      for (int i = 0; i < listOfCombinationsWithoutNewNode.size(); i++) {
        List<BinaryTreeNode> withoutNewNodes = new ArrayList<>();
        withoutNewNodes.addAll(allCombinationNodes);
        withoutNewNodes.add(i, node);
        listOfCombinationsWithNewNode.add(withoutNewNodes);
      }
      // Last position
      allCombinationNodes.add(node);
      listOfCombinationsWithNewNode.add(allCombinationNodes);
    }
    return listOfCombinationsWithNewNode;
  }

  public static class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", BinaryTreeNode.class.getSimpleName() + "[", "]")
          .add("value=" + value)
          .toString();
    }
  }

  public static void main(String[] args) {
    BinaryTreeNode n1 = new BinaryTreeNode(1);
    BinaryTreeNode n2 = new BinaryTreeNode(2);
    BinaryTreeNode n3 = new BinaryTreeNode(3);
    BinaryTreeNode n4 = new BinaryTreeNode(4);

    n3.left = n2;
    n3.right = n4;
    n2.left = n1;

    System.out.println(getAllPossibleInsertionSequences(n2));
  }
}
