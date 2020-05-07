package elementsofprogramminginterviews.graphs;

import java.util.HashSet;
import java.util.Set;

public class ComputeEnclosedRegions {
  private static final int WHITE = 1;
  private static final int BLACK = 0;
  public void printNonBoundaryBlack(int[][] array) {
    Set<int[]> preserveCells = new HashSet<>();
    for (int row = 0; row < array.length; row++) {
      setPreservedCells(array, row, 0, preserveCells);
      setPreservedCells(array, row, array[0].length - 1, preserveCells);
    }
    for (int column = 0; column < array[0].length; column++) {
      setPreservedCells(array, 0, column, preserveCells);
      setPreservedCells(array, array.length - 1, column, preserveCells);
    }
    for (int row = 0; row < array.length; row++) {
      for (int column = 0; column < array[row].length; column++) {
        int[] cell = new int[]{row, column};
        if (!preserveCells.contains(cell)) {
          array[row][column] = BLACK;
        }
      }
    }
  }

  private void setPreservedCells(int[][] array, int row, int column, Set<int[]> preserveCells) {
    if (array[row][column] != WHITE) {
      return;
    }
    if (!preserveCells.add(new int[]{row, column})) {
      return;
    }
    if (row > 0) { // Up
      setPreservedCells(array, row - 1, column, preserveCells);
    }
    if (row < array.length - 1) { // Down
      setPreservedCells(array, row + 1, column, preserveCells);
    }
    if (column > 0) { // Left
      setPreservedCells(array, row, column - 1, preserveCells);
    }
    if (column < array[0].length) { // Right
      setPreservedCells(array, row, column + 1, preserveCells);
    }
  }
}