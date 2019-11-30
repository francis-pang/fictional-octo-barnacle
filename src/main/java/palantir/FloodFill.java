package palantir;

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

  private boolean numberIsHigher(int first, int[][] array, int row, int column) {
    return first > array[row][column];
  }
}
