package crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuildOrder {
  private Map<Character, List<Character>> adjacencyMap;
  private Map<Character, Boolean> visitedMap;
  private List<Character> buildOrder;

  public List<Character> formulateBuildOrder(List<Character> projects, List<Pair> dependencies)
      throws NoValidBuildOrderException {
    // Going to use list of list for now
    adjacencyMap = new HashMap<>();
    visitedMap = new HashMap<>();

    // Build the skeleton structure of Adjacency Map
    Set<Character> projectWithoutDependenciesSet = new HashSet<>(projects);

    for (char project : projects) {
      adjacencyMap.put(project, new ArrayList<>());
      visitedMap.put(project, false);
    }

    // Build the 'no dependent list' and Adjacency List
    for (Pair dependency : dependencies) {
      projectWithoutDependenciesSet.remove(dependency.dependee);
      if (!adjacencyMap.containsKey(dependency.dependee)) {
        // throw error
      } else {
        List<Character> dependentList = adjacencyMap.get(dependency.dependee);
        dependentList.add(dependency.dependent);
      }
    }

    // Formulate the build order now
    buildOrder = new ArrayList<>(); // Can use LinkedHashSet
    projectWithoutDependenciesSet.forEach(noDependencyProject -> {
      depthFirstTraverse(noDependencyProject);
    });

    if (buildOrder.size() != projects.size()) {
      throw new NoValidBuildOrderException();
    }

    Collections.reverse(buildOrder);
    return buildOrder;
  }

  private void depthFirstTraverse(Character character) {
    if (visitedMap.get(character)) {
      return;
    }
    visitedMap.put(character, true);
    List<Character> dependentProjects = adjacencyMap.get(character);
    dependentProjects.forEach(project -> {
      depthFirstTraverse(project);
    });
    buildOrder.add(character);
  }

  public class Pair {
    // Dependent depends on the dependee
    public Character dependee;
    public Character dependent;
  }

  public class NoValidBuildOrderException extends RuntimeException {
  }
}
