package facebook.abcs.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class EvenTree {
  private static int numberOfCut;

  static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
    numberOfCut = 0;
    Node root = buildTree(t_nodes, t_from, t_to);
    countCut(root);
    return numberOfCut - 1;
  }

  private static int countCut(Node root) {
    // Count all the sub-child tree size
    int sizeOfTree = 1; // Add yourself
    for (Node node : root.children) {
      sizeOfTree += countCut(node);
    }
    if (isEven(sizeOfTree)) {
      numberOfCut++;
      return 0;
    } else {
      return sizeOfTree;
    }
  }

  private static boolean isEven(int sumOfSubChildTree) {
    return sumOfSubChildTree % 2 == 0;
  }

  private static Node buildTree(int nodeCount, List<Integer> fromEdges, List<Integer> toEdges) {
    List<Node> nodeList = new ArrayList<>();
    // Init the node
    IntStream
        .rangeClosed(0, nodeCount)
        .forEach($ -> nodeList.add(new Node()));

    Queue<Integer> nodeQueue = new ArrayDeque<>();
    nodeQueue.add(1);

    while (!nodeQueue.isEmpty()) {
      int rootIndex = nodeQueue.poll();
      Node root = nodeList.get(rootIndex);
      int i = 0;
      while (i < fromEdges.size()) {
        int connectedIndex = 0;
        int fromEdgeIndex = fromEdges.get(i);
        int toEdgeIndex = toEdges.get(i);
        if (fromEdgeIndex == rootIndex) {
          connectedIndex = toEdgeIndex;
        } else if (toEdgeIndex == rootIndex) {
          connectedIndex = fromEdgeIndex;
        }
        if (connectedIndex > 0) {
          Node childNode = nodeList.get(connectedIndex);
          nodeQueue.add(connectedIndex);
          root.children.add(childNode);
          fromEdges.remove(i);
          toEdges.remove(i);
        } else {
          i++;
        }
      }
    }
    return nodeList.get(1);
  }

  public static class Node {
    public List<Node> children;
    public Node() {
      children = new ArrayList<>();
    }
  }

  public static void main(String[] args) throws IOException {
    //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader bufferedReader = new BufferedReader(new StringReader("10 9\n" +
        "2 1\n" +
        "3 1\n" +
        "4 3\n" +
        "5 2\n" +
        "6 1\n" +
        "7 2\n" +
        "8 6\n" +
        "9 8\n" +
        "10 8"));

    BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

    String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int tNodes = Integer.parseInt(tNodesEdges[0]);
    int tEdges = Integer.parseInt(tNodesEdges[1]);

    List<Integer> tFrom = new ArrayList<>();
    List<Integer> tTo = new ArrayList<>();

    IntStream.range(0, tEdges).forEach(i -> {
      try {
        String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        tFrom.add(Integer.parseInt(tFromTo[0]));
        tTo.add(Integer.parseInt(tFromTo[1]));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int res = evenForest(tNodes, tEdges, tFrom, tTo);
    System.out.println(res);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
