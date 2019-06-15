package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 */
public class RobotInAGrid {
  public List<MovementDirection> moveFromTopLeftToBottomRight(final int[][] grid) {
    Map<GridPosition, List<MovementDirection>> foundPaths = new HashMap<>();
    int lastRow = grid.length;
    int lastColumn = grid[lastRow - 1].length;

    if (grid.length == 0) {
      return new ArrayList<>();
    }

    GridPosition bottomRight = new GridPosition(lastRow - 1, lastColumn - 1);
    moveFromTopLeftToDestination(bottomRight, grid, foundPaths);
    return foundPaths.get(bottomRight);
  }

  private void moveFromTopLeftToDestination(GridPosition destination, final int[][] grid,
                                            Map<GridPosition, List<MovementDirection>> foundPaths) {
    if (grid[destination.row][destination.column] == 0) {
      foundPaths.put(destination, null);
      return;
    }

    if (foundPaths.containsKey(destination)) {
      return;
    }

    if (destination.row > 0) {
      GridPosition oneStepDownwardToDestination = new GridPosition(destination.row - 1, destination.column);
      if (!foundPaths.containsKey(oneStepDownwardToDestination)) {
        moveFromTopLeftToDestination(oneStepDownwardToDestination, grid, foundPaths);
      }
      List<MovementDirection> pathToOneDownStepToDestination = foundPaths.get(oneStepDownwardToDestination);
      if (pathToOneDownStepToDestination != null) {
        List<MovementDirection> pathToDestination = new ArrayList<>();
        Collections.copy(pathToDestination, pathToOneDownStepToDestination);
        pathToOneDownStepToDestination.add(MovementDirection.DOWN);
        foundPaths.put(destination, pathToDestination);
      }
    }

    if (destination.column > 0 && !foundPaths.containsKey(destination)) {
      GridPosition oneStepLeftwardToDestination = new GridPosition(destination.row, destination.column - 1);
      if (!foundPaths.containsKey(oneStepLeftwardToDestination)) {
        moveFromTopLeftToDestination(oneStepLeftwardToDestination, grid, foundPaths);
      }
      List<MovementDirection> pathOneRightStepToDestination = foundPaths.get(oneStepLeftwardToDestination);
      if (pathOneRightStepToDestination != null) {
        List<MovementDirection> pathToDestination = new ArrayList<>();
        Collections.copy(pathToDestination, pathOneRightStepToDestination);
        pathOneRightStepToDestination.add(MovementDirection.RIGHT);
        foundPaths.put(destination, pathToDestination);
      }
    }

    // By now, if there isn't an entry for destination, it means that destination is not reachable
    if (!foundPaths.containsKey(destination)) {
      foundPaths.put(destination, null);
    }
  }

  public class GridPosition {
    public int row;
    public int column;

    public GridPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }

  public enum MovementDirection {
    RIGHT, DOWN
  }
}
