package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LangtonsAnt {
  private Map<GridPosition, Integer> gridColorByPosition;
  // Assume white to be 1, black to be 0
  private final int BLACK_GRID = 0;
  private final int WHITE_GRID = 1;
  private final int DEFAULT_GRID_COLOR = BLACK_GRID;

  private int maxRowIndex;
  private int minRowIndex;
  private int maxColumnIndex;
  private int minColumnIndex;

  public LangtonsAnt() {
    gridColorByPosition = new HashMap<>();
    maxRowIndex = 0;
    minRowIndex = 0;
    maxColumnIndex = 0;
    minColumnIndex = 0;
  }

  public void printKMoves(int K) {
    int moveCounter = 0;
    Direction currentDirection = Direction.RIGHT; // Starting direction
    GridPosition currentPosition = new GridPosition(0, 0);
    int currentColor = BLACK_GRID;
    while (moveCounter < K) {
      int previousColor = currentColor;
      currentColor = changeColor(currentPosition, gridColorByPosition.getOrDefault(currentPosition, DEFAULT_GRID_COLOR));
      currentDirection = turnDirection(previousColor, currentDirection);
      currentPosition = makeAMove(currentDirection, currentPosition);
      moveCounter++;
    }
    printGrid();
  }

  private void printGrid() {
    for (int rowIndex = minRowIndex; rowIndex <= maxRowIndex; rowIndex++) {
      for (int columnIndex = minColumnIndex; columnIndex <= maxColumnIndex; columnIndex++) {
        GridPosition iteratingGridPosition = new GridPosition(rowIndex, columnIndex);
        int iteratingColor = gridColorByPosition.getOrDefault(iteratingGridPosition, DEFAULT_GRID_COLOR);
        String printingColor;
        switch (iteratingColor) {
          case BLACK_GRID:
            final String BLACK_COLOR = "black";
            printingColor = BLACK_COLOR;
            break;
          case WHITE_GRID:
            final String WHITE_COLOR = "white";
            printingColor = WHITE_COLOR;
            break;
          default:
            printingColor = "<INVALID>";
        }
        System.out.print(printingColor);
        final String SEPARATOR = ",";
        System.out.print(SEPARATOR);
      }
      System.out.println();
    }
  }

  private Direction turnDirection(final int color, final Direction direction) {
    if (color == BLACK_GRID) {
      return turnLeft(direction);
    } else if (color == WHITE_GRID) {
      return turnRight(direction);
    } else {
      throw new IllegalArgumentException("This isn't a valid color");
    }
  }

  private Direction turnLeft(final Direction direction) {
    switch (direction) {
      case UP:
        return Direction.LEFT;
      case DOWN:
        return Direction.RIGHT;
      case LEFT:
        return Direction.DOWN;
      case RIGHT:
        return Direction.UP;
      default:
        return null;
    }
  }

  private Direction turnRight(final Direction direction) {
    switch (direction) {
      case UP:
        return Direction.RIGHT;
      case DOWN:
        return Direction.LEFT;
      case LEFT:
        return Direction.UP;
      case RIGHT:
        return Direction.DOWN;
      default:
        return null;
    }
  }

  private GridPosition makeAMove(final Direction direction, final GridPosition currentGridPosition) {
    GridPosition newGridPosition;
    switch (direction) {
      case UP:
        newGridPosition = new GridPosition(currentGridPosition.row - 1, currentGridPosition.column);
        break;
      case DOWN:
        newGridPosition = new GridPosition(currentGridPosition.row + 1, currentGridPosition.column);
        break;
      case LEFT:
        newGridPosition = new GridPosition(currentGridPosition.row, currentGridPosition.column - 1);
        break;
      case RIGHT:
        newGridPosition = new GridPosition(currentGridPosition.row, currentGridPosition.column + 1);
        break;
      default:
        newGridPosition = currentGridPosition;
    }
    if (newGridPosition.row > maxRowIndex) {
      maxRowIndex = newGridPosition.row;
    } else if (newGridPosition.row < minRowIndex) {
      minRowIndex = newGridPosition.row;
    }

    if (newGridPosition.column > maxColumnIndex) {
      maxColumnIndex = newGridPosition.column;
    } else if (newGridPosition.column < minColumnIndex) {
      minColumnIndex = newGridPosition.column;
    }
    return newGridPosition;
  }

  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  private int changeColor(final GridPosition gridPosition, final int color) {
    int newColor;
    switch (color) {
      case BLACK_GRID:
        newColor = WHITE_GRID;
        break;
      case WHITE_GRID:
        newColor = BLACK_GRID;
        break;
      default:
        newColor = DEFAULT_GRID_COLOR;
    }
    gridColorByPosition.put(gridPosition, newColor);
    return newColor;
  }

  class GridPosition implements Comparable<GridPosition> {
    public int row;
    public int column;

    public GridPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public int compareTo(GridPosition o) {
      if (this == o) {
        return 0;
      }
      if (!(o instanceof GridPosition)) {
        throw new IllegalArgumentException("This is not a instance of " + this.getClass().getSimpleName());
      }
      GridPosition that = (GridPosition) o;
      if (this.row == that.row && this.column == that.column) {
        return 0;
      }
      if (this.row > that.row) {
        return 1;
      } else if (this.row < that.row) {
        return -1;
      } else { // Same row number
        if (this.column > that.column) {
          return 1;
        } else if (this.column < that.column) {
          return -1;
        } else { // Same column number, and same row number
          return 0;
        }
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof GridPosition)) return false;
      GridPosition that = (GridPosition) o;
      return row == that.row &&
          column == that.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, column);
    }
  }
}
