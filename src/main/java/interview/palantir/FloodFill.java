package interview.palantir;

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
  public boolean[][] findHighPoint(int[][] elevations) {
    if (elevations == null || elevations.length == 0) {
      return new boolean[0][0];
    }

    boolean[][] answer = new boolean[elevations.length][elevations[0].length];
    int maxRow = elevations.length - 1;
    int maxColumn = elevations[0].length - 1;

    for (int row = 0; row <= maxRow; row++) {
      for (int column = 0; column <= maxColumn; column++) {
        int cellValue = elevations[row][column];
        // one row above
        if (row > 0) {
          // Check diagonally left above
          if (column > 0 && !numberIsHigher(cellValue, elevations, row - 1, column - 1)) {
            continue;
          }
          // Check directly above
          if (!numberIsHigher(cellValue, elevations, row - 1, column)) {
            continue;
          }
          // Check diagonally right above
          if (column < maxColumn && !numberIsHigher(cellValue, elevations, row - 1, column + 1)) {
            continue;
          }
        }

        // same level
        // Check one cell left
        if (column > 0 && !numberIsHigher(cellValue, elevations, row, column - 1)) {
          continue;
        }
        // Check one cell right
        if (column < maxColumn && !numberIsHigher(cellValue, elevations, row, column + 1)) {
          continue;
        }

        // one row below
        if (row < maxRow) {
          // Check diagonally left below
          if (column > 0 && !numberIsHigher(cellValue, elevations, row + 1, column - 1)) {
            continue;
          }
          // Check directly below
          if (!numberIsHigher(cellValue, elevations, row + 1, column)) {
            continue;
          }
          // Check diagonally right below
          if (column < maxColumn && !numberIsHigher(cellValue, elevations, row + 1, column + 1)) {
            continue;
          }
        }
        // If the cell passed all the check by this point of time, we know that this is a high point
        answer[row][column] = true;
      }
    }
    return answer;
  }

  public int[][] findRiskScores(int[][] elevations) {
    if (elevations == null || elevations.length == 0) {
      return new int[0][0];
    }

    boolean[][] highPoints = findHighPoint(elevations);
    int[][] answer = new int[elevations.length][elevations[0].length];
    for (int row = 0; row < elevations.length; row++) {
      for (int column = 0; column < elevations[0].length; column++) {
        if (!highPoints[row][column]) {
          continue;
        }
        markFloodArea(elevations, row, column, answer);
      }
    }
    return answer;
  }

  public void markFloodArea(int[][] elevationMap, int row, int column, int[][] answer) {
    Set<Coordinate> visitedGrid = new HashSet<>();
    markLowerGround(elevationMap, row, column, answer, visitedGrid);
  }

  private void markLowerGround(int[][] elevationMap, int row, int column, int[][] answer, Set<Coordinate> visitedGrid) {
    Coordinate coordinate = new Coordinate(row, column);
    if (visitedGrid.contains(coordinate)) {
      return;
    }
    answer[row][column]++;
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

  public boolean isCellHighPoint(int[][] elevationMap, int row, int column) {
    int maxRow = elevationMap.length - 1;
    int maxColumn = elevationMap[0].length - 1;

    int cellValue = elevationMap[row][column];
    // one row above
    if (row > 0) {
      // Check diagonally left above
      if (column > 0 && !numberIsHigher(cellValue, elevationMap, row - 1, column - 1)) {
        return false;
      }
      // Check directly above
      if (!numberIsHigher(cellValue, elevationMap, row - 1, column)) {
        return false;
      }
      // Check diagonally right above
      if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row - 1, column + 1)) {
        return false;
      }
    }

    // same level
    // Check one cell left
    if (column > 0 && !numberIsHigher(cellValue, elevationMap, row, column - 1)) {
      return false;
    }
    // Check one cell right
    if (column < maxColumn && !numberIsHigher(cellValue, elevationMap, row, column + 1)) {
      return false;
    }

    // one row below
    if (row < maxRow) {
      // Check diagonally left below
      if (column > 0 && !numberIsHigher(cellValue, elevationMap, row + 1, column - 1)) {
        return false;
      }
      // Check directly below
      if (!numberIsHigher(cellValue, elevationMap, row + 1, column)) {
        return false;
      }
      // Check diagonally right below
      return column >= maxColumn || numberIsHigher(cellValue, elevationMap, row + 1, column + 1);
    }
    // If the cell passed all the check by this point of time, we know that this is a high point
    return true;
  }

  public boolean isNeigbourHighPoint(int[][] elevationMap, int row, int column) {
    int cellValue = elevationMap[row][column];
    int maxRow = elevationMap.length - 1;
    int maxColumn = elevationMap[0].length - 1;
    // one row above
    if (row > 0) {
      // Check diagonally left above
      if (column > 0 && !numberIsHigherOrEquals(cellValue, elevationMap, row - 1, column - 1)) {
        return false;
      }
      // Check directly above
      if (!numberIsHigherOrEquals(cellValue, elevationMap, row - 1, column)) {
        return false;
      }
      // Check diagonally right above
      if (column < maxColumn && !numberIsHigherOrEquals(cellValue, elevationMap, row - 1, column + 1)) {
        return false;
      }
    }

    // same level
    // Check one cell left
    if (column > 0 && !numberIsHigherOrEquals(cellValue, elevationMap, row, column - 1)) {
      return false;
    }
    // Check one cell right
    if (column < maxColumn && !numberIsHigherOrEquals(cellValue, elevationMap, row, column + 1)) {
      return false;
    }

    // one row below
    if (row < maxRow) {
      // Check diagonally left below
      if (column > 0 && !numberIsHigherOrEquals(cellValue, elevationMap, row + 1, column - 1)) {
        return false;
      }
      // Check directly below
      if (!numberIsHigherOrEquals(cellValue, elevationMap, row + 1, column)) {
        return false;
      }
      // Check diagonally right below
      return column >= maxColumn || numberIsHigherOrEquals(cellValue, elevationMap, row + 1, column + 1);
    }
    return true;
  }

  public boolean[][] findHighPoints(int[][] elevationMap) {
    if (elevationMap == null || elevationMap.length == 0) {
      return new boolean[0][0];
    }

    boolean[][] answer = new boolean[elevationMap.length][elevationMap[0].length];
    int maxRow = elevationMap.length - 1;
    int maxColumn = elevationMap[0].length - 1;

    for (int row = 0; row <= maxRow; row++) {
      for (int column = 0; column <= maxColumn; column++) {
        if (!isCellHighPoint(elevationMap, row, column)) {
          continue;
        }
        // If the cell passed all the check by this point of time, we know that this is a high point
        answer[row][column] = true;
      }
    }
    return answer;
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

  private boolean numberIsHigherOrEquals(int number, int[][] array, int row, int column) {
    return number >= array[row][column];
  }

  private boolean numberIsHigher(int first, int[][] array, int row, int column) {
    return first > array[row][column];
  }
}
