package elementsofprogramminginterviews.graphs;

import java.util.HashSet;
import java.util.Set;

public class PaintABooleanMatrix {
  public int[][] paintMatrices(int[][] matrix, int[] cell) {
    Set<int[]> visited = new HashSet<>();
    colorMatrix(matrix, cell, visited);
    return matrix;
  }

  public void colorMatrix(int[][] matrix, int[] position, Set<int[]> visited) {
    if (!visited.add(position)) {
      return;
    }
    int row = position[0];
    int column = position[1];
    int color = matrix[row][column];
    int changedColor = (color == 1) ? 0 : 1;
    matrix[row][column] = changedColor;
    // Up
    if (row > 0 && matrix[row - 1][column] == color) {
      colorMatrix(matrix, new int[]{row - 1, column}, visited);
    }
    // Down
    if (row < matrix.length - 1 && matrix[row + 1][column] == color) {
      colorMatrix(matrix, new int[]{row + 1, column}, visited);
    }
    // Left
    if (column > 0 && matrix[row][column - 1] == color) {
      colorMatrix(matrix, new int[]{row, column - 1}, visited);
    }
    // Right
    if (column < matrix[0].length && matrix[row][column + 1] == color) {
      colorMatrix(matrix, new int[]{row, column + 1}, visited);
    }
  }
}