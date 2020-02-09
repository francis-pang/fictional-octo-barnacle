package crackingthecodinginterview.hard;

import java.util.StringJoiner;

public class MaxBlackSquare {
  private static final int BLACK_CELL = 0;
  private static final int WHITE_CELL = 1;

  public int[][] findMaxBlackSquare(int[][] grid) {
    int rowStart = 0;
    int columnStart = 0;
    int size = 0;

    CellContext[][] gridContext = constructGridContext(grid);
    for (int row = 0; row < grid.length; row++) {
      for (int column = 0; column < grid.length; column++) {
        if (grid[row][column] == WHITE_CELL) {
          continue;
        }
        int maxPossibleSize = Math.min(gridContext[row][column].blackRight, gridContext[row][column].blackBelow);
        for (int i = maxPossibleSize; i > 0; i--) {
          int subSquareBottomRightRow = row + (i - 1);
          int subSquareBottomRightColumn = column + (i - 1);
          if (gridContext[subSquareBottomRightRow][subSquareBottomRightColumn].blackLeft >= maxPossibleSize &&
              gridContext[subSquareBottomRightRow][subSquareBottomRightColumn].blackAbove >= maxPossibleSize &&
              i > size) {
            rowStart = row;
            columnStart = column;
            size = i;
            break;
          }
        }
      }
    }
    int[][] maxBlackSquare = constructSquare(grid, rowStart, columnStart, size);
    return maxBlackSquare;
  }

  private int[][] constructSquare(int[][] grid, int rowStart, int columnStart, int size) {
    if (size == 0) {
      return new int[][]{};
    }
    int[][] square = new int[size][size];
    for (int i = 0; i < size; i++) {
      int row = rowStart + i;
      for (int j = 0; j < size; j++) {
        int column = columnStart + j;
        square[i][j] = grid[row][column];
      }
    }
    return square;
  }

  private CellContext[][] constructGridContext(int[][] grid) {
    CellContext[][] gridContext = new CellContext[grid.length][grid.length];
    // Initialise array
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        gridContext[i][j] = new CellContext();
      }
    }
    updateContextForLeftAndRight(grid, gridContext, Direction.LEFT);
    updateContextForLeftAndRight(grid, gridContext, Direction.RIGHT);
    updateContextForAboveAndBelow(grid, gridContext, Direction.UP);
    updateContextForAboveAndBelow(grid, gridContext, Direction.DOWN);
    return gridContext;
  }

  private void updateContextForLeftAndRight(int[][] grid, CellContext[][] gridContext, Direction direction) {
    for (int row = 0; row < grid.length; row++) {
      int column = (direction.equals(Direction.LEFT)) ? 0 : grid.length - 1;
      int accumulator = 0;
      while (column >= 0 && column < grid.length) {
        accumulator = (grid[row][column] == WHITE_CELL) ? 0 : accumulator + 1; // Reset to Zero if white, else add 1
        if (direction.equals(Direction.LEFT)) {
          gridContext[row][column].blackLeft = accumulator;
          column++;
        } else { // Right
          gridContext[row][column].blackRight = accumulator;
          column--;
        }
      }
    }
  }

  private void updateContextForAboveAndBelow(int[][] grid, CellContext[][] gridContext, Direction direction) {
    for (int column = 0; column < grid.length; column++) {
      int row = (direction.equals(Direction.UP)) ? 0 : grid.length - 1;
      int accumulator = 0;
      while (row >= 0 && row < grid.length) {
        accumulator = (grid[row][column] == WHITE_CELL) ? 0 : accumulator + 1; // Reset to Zero if white, else add 1
        if (direction.equals(Direction.UP)) {
          gridContext[row][column].blackAbove = accumulator;
          row++;
        } else { // Below
          gridContext[row][column].blackBelow = accumulator;
          row--;
        }
      }
    }
  }

  private enum Direction {
    LEFT, RIGHT, UP, DOWN
  }

  private class CellContext {
    public int blackLeft;
    public int blackRight;
    public int blackAbove;
    public int blackBelow;

    public CellContext() {
      blackLeft = 0;
      blackRight = 0;
      blackAbove = 0;
      blackBelow = 0;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", CellContext.class.getSimpleName() + "[", "]")
          .add("blackLeft=" + blackLeft)
          .add("blackRight=" + blackRight)
          .add("blackAbove=" + blackAbove)
          .add("blackBelow=" + blackBelow)
          .toString();
    }
  }

  private void printMatrix(int[][] array) {
    for (int row = 0; row < array.length; row++) {
      for (int column = 0; column < array[row].length; column++) {
        System.out.printf("%d, ", array[row][column]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{
        {1, 0, 1, 0},
        {0, 0, 0, 1},
        {1, 0, 0, 0},
        {1, 1, 1, 1}
    };
    MaxBlackSquare maxBlackSquare = new MaxBlackSquare();
    int[][] answer = maxBlackSquare.findMaxBlackSquare(grid);
    maxBlackSquare.printMatrix(answer);

    grid = new int[][]{
        {1, 1, 0, 0, 1},
        {0, 1, 0, 0, 0},
        {1, 0, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0}
    };
    answer = maxBlackSquare.findMaxBlackSquare(grid);
    maxBlackSquare.printMatrix(answer);
  }
}
