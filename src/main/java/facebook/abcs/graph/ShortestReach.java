package facebook.abcs.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ShortestReach {
  static int[] bfs(int n, int m, int[][] edges, int s) {
    int[] minDistance = new int[n - 1];
    Queue<Integer> nextLevelNode = new ArrayDeque<>();
    Queue<Integer> unvisitedNodes;
    List<List<Integer>> adjList = generateAdjList(edges, n);
    nextLevelNode.offer(s);
    int distance = -6;
    while (!nextLevelNode.isEmpty()) {
      unvisitedNodes = nextLevelNode;
      nextLevelNode = new ArrayDeque<>();
      distance += 6;
      while (!unvisitedNodes.isEmpty()) {
        int node = unvisitedNodes.poll();
        int index = -1;
        if (node > s) {
          index = node - 2;
        } else if (node < s) {
          index = node - 1;
        }
        if (index >= 0 && minDistance[index] == 0) {
          minDistance[index] = distance;
        }
        for (int element : adjList.get(node)) {
          nextLevelNode.offer(element);
        }
        adjList.get(node).clear();
      }
    }

    for (int i = 0; i < minDistance.length; i++) {
      if (minDistance[i] == 0) {
        minDistance[i] = -1;
      }
    }
    return minDistance;
  }

  private static List<List<Integer>> generateAdjList(int[][] edges, int n) {
    // Initial array list
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int[] pairs : edges) {
      int start = pairs[0];
      int end = pairs[1];
      adjList.get(start).add(end);
      adjList.get(end).add(start);
    }
    return adjList;
  }

  public static void main(String[] args) {
    int[] answer = bfs(5, 3, new int[][]{
        {4, 2},
        {1, 2},
        {1, 3}
    }, 1);
  }
}
