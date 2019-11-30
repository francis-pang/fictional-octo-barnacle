package palantir;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FloodFill {
  /**
   * #1. Given a 2D map of integers representing the elevation point of a ground, find all "High Points" defined as
   * follows: The maximum point which is strictly greater than all of the adjacent neighbours. The adjacent
   * neighbours are in 8 directions. Your function should return NxM matrix of ones/zeros. Ones if that point is a
   * "High Point" in the corresponding elevation map
   */
  public int[][] findHighPoint(int[][] elevationMap) {
    if (elevationMap == null || elevationMap.length == 0) {
      return new int[0][0];
    }

    int[][] answer = new int[elevationMap.length][elevationMap[0].length];
    int maxRow = elevationMap.length - 1;
    int maxColumn = elevationMap[0].length - 1;

    for (int row = 0; row <= maxRow; row++) {
      for (int column = 0; column <= maxColumn; column++) {
        int cellValue = elevationMap[row][column];
        // one row above
        if (row > 0) {
          // Check diagonally left above
          if (column > 0 && !numberIsHigher(cellValue, elevationMap, row - 1, column - 1)) {
            continue;
          }
          // Check directly above
          if (!numberIsHigher(cellValue, elevationMap, row - 1, column)) {
            continue;
          }
          // Check diagonally right above
          if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row - 1, column + 1)) {
            continue;
          }
        }

        // same level
        // Check one cell left
        if (column > 0 && !numberIsHigher(cellValue, elevationMap, row, column - 1)) {
          continue;
        }
        // Check one cell right
        if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row, column + 1)) {
          continue;
        }

        // one row below
        if (row < maxRow) {
          // Check diagonally left below
          if (column > 0 && !numberIsHigher(cellValue, elevationMap, row + 1, column - 1)) {
            continue;
          }
          // Check directly below
          if (!numberIsHigher(cellValue, elevationMap, row + 1, column)) {
            continue;
          }
          // Check diagonally right below
          if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row + 1, column + 1)) {
            continue;
          }
        }
        // If the cell passed all the check by this point of time, we know that this is a high point
        answer[row][column] = 1;
      }
    }
    return answer;
  }

  public int[][] markFloodArea(int[][] elevationMap, int startRow, int startColumn) {
    if (elevationMap == null || elevationMap.length == 0) {
      return new int[][]{};
    }

    Set<Coordinate> visitedGrid = new HashSet<>();
    int[][] answer = new int[elevationMap.length][elevationMap[0].length];
    markLowerGround(elevationMap, startRow, startColumn, answer, visitedGrid);
    return answer;
  }

  private void markLowerGround(int[][] elevationMap, int row, int column, int[][] answer, Set<Coordinate> visitedGrid) {
    answer[row][column] = 1;
    Coordinate coordinate = new Coordinate(row, column);
    if (visitedGrid.contains(coordinate)) {
      return;
    }
    visitedGrid.add(coordinate);
    int cellValue = elevationMap[row][column];
    int maxRow = elevationMap.length - 1;
    int maxColumn = elevationMap[0].length - 1;

    if (row > 0) {
      // Check diagonally left above
      if (column > 0 && numberIsHigher(cellValue, elevationMap, row - 1, column - 1)) {
        markLowerGround(elevationMap, row - 1, column - 1, answer, visitedGrid);
      }
      // Check directly above
      if (numberIsHigher(cellValue, elevationMap, row - 1, column)) {
        markLowerGround(elevationMap, row - 1, column, answer, visitedGrid);
      }
      // Check diagonally right above
      if (column < maxColumn && numberIsHigher(cellValue, elevationMap, row - 1, column + 1)) {
        markLowerGround(elevationMap, row - 1, column + 1, answer, visitedGrid);
      }
    }

    // same level
    // Check one cell left
    if (column > 0 && numberIsHigher(cellValue, elevationMap, row, column - 1)) {
      markLowerGround(elevationMap, row, column - 1, answer, visitedGrid);
    }
    // Check one cell right
    if (column < maxColumn && numberIsHigher(cellValue, elevationMap, row, column + 1)) {
      markLowerGround(elevationMap, row, column + 1, answer, visitedGrid);
    }

    // one row below
    if (row < maxRow) {
      // Check diagonally left below
      if (column > 0 && numberIsHigher(cellValue, elevationMap, row + 1, column - 1)) {
        markLowerGround(elevationMap, row + 1, column - 1, answer, visitedGrid);
      }
      // Check directly below
      if (numberIsHigher(cellValue, elevationMap, row + 1, column)) {
        markLowerGround(elevationMap, row + 1, column, answer, visitedGrid);
      }
      // Check diagonally right below
      if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row + 1, column + 1)) {
        markLowerGround(elevationMap, row + 1, column + 1, answer, visitedGrid);
      }
    }
  }

  public class Coordinate {
    public int row;
    public int column;

    public Coordinate(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Coordinate)) return false;
      Coordinate that = (Coordinate) o;
      return row == that.row &&
          column == that.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, column);
    }
  }

  private boolean numberIsHigher(int first, int[][] array, int row, int column) {
    return first > array[row][column];
  }
}
