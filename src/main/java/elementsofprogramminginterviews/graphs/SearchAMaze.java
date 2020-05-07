package elementsofprogramminginterviews.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchAMaze {
  private static final int OPEN = 1;

  public List<int[]> findMazePath(int[][] maze, int[] entrance, int[] exit) {
    List<int[]> path = new ArrayList<>();
    Set<int[]> visited = new HashSet<>();

    findPathToExit(maze, entrance, exit, path, visited);
    return path;
  }

  private boolean findPathToExit(int[][] maze, int[] position, int[] exit, List<int[]> path, Set<int[]>  visited) {
    if (visited.add(position)) {
      return false;
    }
    path.add(position);
    if (Arrays.equals(position, exit)) {
      return true;
    }
    // Assume that a person can only walk in 4 directions
    int row = position[0];
    int column = position[1];
    // up
    if (row > 0 && maze[row - 1][column] == OPEN) {
      if (findPathToExit(maze, new int[]{row - 1, column}, exit, path, visited)) {
        return true;
      }
    }
    // down
    if (row < maze.length - 1 && maze[row + 1][column] == OPEN) {
      if (findPathToExit(maze, new int[]{row + 1, column}, exit, path, visited)) {
        return true;
      }
    }
    // left
    if (column > 0 && maze[row][column - 1] == OPEN) {
      if (findPathToExit(maze, new int[]{row, column - 1}, exit, path, visited)) {
        return true;
      }
    }
    // right
    if (column < maze[0].length - 1 && maze[row][column + 1] == OPEN) {
      if (findPathToExit(maze, new int[]{row, column + 1}, exit, path, visited)) {
        return true;
      }
    }
    path.remove(position);
    return false;
  }
}