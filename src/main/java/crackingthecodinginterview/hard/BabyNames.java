package crackingthecodinginterview.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BabyNames {
  public List<NameFrequency> generateTrueBabyNamesFrequency(List<NameFrequency> nameFrequencies,
                                                            List<NamePair> synonyms) {

    // Using the below 2 map, we have create a bi-directional maps
    // The below map records the mapping from the name to the node
    Map<String, NameNode> nameToNodeMap = new HashMap<>();
    Set<NameNode> processedName = new HashSet<>(); // Records if the name has been processed before or not.

    // We have deliberately used an external counter because we aren't sure of the implementation of the list. If
    // this is a ArrayList, the access to get by index will be expensive.
    for (NameFrequency nameFrequency : nameFrequencies) {
      NameNode newNameNode = new NameNode(nameFrequency.frequency, nameFrequency.name);
      nameToNodeMap.put(nameFrequency.name, newNameNode);
      processedName.add(newNameNode);
    }

    // Form the graph adjacency matrices.
    for (NamePair namePair : synonyms) {
      // Assume that all names provided is found in the nameFrequencies
      NameNode firstNameNode = nameToNodeMap.get(namePair.firstName);
      NameNode secondNameNode = nameToNodeMap.get(namePair.secondName);
      firstNameNode.neighbours.add(secondNameNode);
      secondNameNode.neighbours.add(firstNameNode);
    }

    // Traverse the graph
    List<NameFrequency> trueFrequencyOfName = new ArrayList<>();
    while (!processedName.isEmpty()) {
      Queue<NameNode> yetToBeProcessedNodeQueue = new ArrayDeque<>();
      // Get any first element in the set
      NameNode kickOffNameNode = processedName.iterator().next();
      yetToBeProcessedNodeQueue.add(kickOffNameNode);
      int trueFrequency = 0;
      while (!yetToBeProcessedNodeQueue.isEmpty()) {
        NameNode nameNode = yetToBeProcessedNodeQueue.poll();
        processedName.remove(nameNode);
        trueFrequency += nameNode.frequency;
        for (NameNode neighbour : nameNode.neighbours) {
          if (!processedName.contains(neighbour)) {
            yetToBeProcessedNodeQueue.add(neighbour);
            processedName.remove(neighbour);
          }
        }
      }
      trueFrequencyOfName.add(new NameFrequency(kickOffNameNode.name, trueFrequency));
    }

    return trueFrequencyOfName;
  }

  class NameFrequency {
    public String name;
    public Integer frequency;

    public NameFrequency(String name, Integer frequency) {
      this.name = name;
      this.frequency = frequency;
    }
  }

  class NamePair {
    public String firstName;
    public String secondName;

    public NamePair(String firstName, String secondName) {
      this.firstName = firstName;
      this.secondName = secondName;
    }
  }

  class NameNode {
    public int frequency;
    public String name;
    public List<NameNode> neighbours;

    public NameNode(int frequency, String name) {
      this.frequency = frequency;
      this.name = name;
      this.neighbours = new ArrayList<>();
    }
  }
}
