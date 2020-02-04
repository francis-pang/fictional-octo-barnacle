package elementsofprogramminginterviews.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamPhotoDay2 {
  public int biggestTeamPhoto(List<List<Integer>> teams) {
    Map<Integer, Node> graph = new HashMap<>();
    for (int i = 0; i < teams.size(); i++) {
      List<Integer> team = teams.get(i);
      team.sort((o1, o2) -> o2 - o1);
      graph.put(i, new Node(i, team));
    }

    for (Map.Entry<Integer, Node> iteratingEntry : graph.entrySet()) {
      Node iteratingNode = iteratingEntry.getValue();
      List<Integer> iteratingTeamHeights = iteratingNode.teamHeights;
      for (Map.Entry<Integer, Node> innerEntry : graph.entrySet()) {
        Node innerNode = innerEntry.getValue();
        List<Integer> innerHeights = innerNode.teamHeights;
        if (iteratingTeamHeights.size() < innerHeights.size()) {
          continue;
        }
        boolean allTaller = true;
        for (int i = 0; i < iteratingTeamHeights.size(); i++) {
          if (iteratingTeamHeights.get(i) <= innerHeights.get(i)) {
            allTaller = false;
            break;
          }
        }
        if (allTaller) {
          innerNode.incoming++;
          iteratingNode.outgoingEdges.add(innerNode);
        }
      }
    }

    int longestDistance = 0;
    for (Map.Entry<Integer, Node> iteratingEntry : graph.entrySet()) {
      if (iteratingEntry.getValue().incoming == 0) {
        int distance = findLongestLength(iteratingEntry.getValue());
        if (longestDistance < distance) {
          longestDistance = distance;
        }
      }
    }
    return longestDistance;
  }

  private int findLongestLength(Node node) {
    int longest = 0;
    for (Node neighbour : node.outgoingEdges) {
      int length = findLongestLength(neighbour);
      if (longest < length) {
        longest = length;
      }
    }
    return longest + 1;
  }

  public class Node {
    public int id;
    public List<Integer> teamHeights;
    public int incoming;
    public List<Node> outgoingEdges;

    public Node(int id, List<Integer> teamHeights) {
      this.id = id;
      this.teamHeights = teamHeights;
      outgoingEdges = new ArrayList<>();
      incoming = 0;
    }
  }
}
