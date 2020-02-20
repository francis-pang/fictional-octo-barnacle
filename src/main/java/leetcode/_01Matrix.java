package leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class _01Matrix {
  public int[][] updateMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return new int[][]{};
    }

    int[][] distanceMatrix = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        distanceMatrix[row][col] = distance(matrix, row, col);
      }
    }
    return distanceMatrix;
  }

  private int distance(int[][] matrix, int row, int col) {
    if (matrix[row][col] == 0) {
      return 0;
    }
    int distance = 0;
    Set<Cord> visited = new HashSet<>();
    Queue<Cord> nextLevelQ = new ArrayDeque<>();
    nextLevelQ.add(new Cord(row, col));
    while (!nextLevelQ.isEmpty()) {
      distance++;
      Queue<Cord> q = nextLevelQ;
      nextLevelQ = new ArrayDeque<>();
      while (!q.isEmpty()) {
        Cord cord = q.poll();
        row = cord.row;
        col = cord.col;
        if (!visited.add(cord)) {
          continue;
        }
        if (row > 0) { // Up
          if (matrix[row - 1][col] == 0) {
            return distance;
          }
          nextLevelQ.add(new Cord(row - 1, col));
        }
        if (row < matrix.length - 1) { // Down
          if (matrix[row + 1][col] == 0) {
            return distance;
          }
          nextLevelQ.add(new Cord(row + 1, col));
        }

        if (col > 0) { // Left
          if (matrix[row][col - 1] == 0) {
            return distance;
          }
          nextLevelQ.add(new Cord(row, col - 1));
        }

        if (col < matrix[0].length - 1) { // Right
          if (matrix[row][col + 1] == 0) {
            return distance;
          }
          nextLevelQ.add(new Cord(row, col + 1));
        }
      }
    }
    return distance;
  }

  class Cord {
    public int row;
    public int col;

    public Cord(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Cord)) return false;
      Cord cord = (Cord) o;
      return row == cord.row &&
          col == cord.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }
}