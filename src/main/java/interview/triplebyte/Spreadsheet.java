package interview.triplebyte;

import java.util.StringJoiner;

public class Spreadsheet {
  public String[][] grid;

  private Spreadsheet(String[][] grid) {
    this.grid = grid;
  }

  public static Spreadsheet createInstance(int row, int column) {
    String[][] grid = new String[row][column];
    return new Spreadsheet(grid);
  }

  public void updateCell(int row, int column, String content) {
    grid[row - 1][column - 1] = content;
  }

  public void print() {
    for (int column = 0; column < grid.length; column++) {
      StringJoiner rowString = new StringJoiner("|");
      for (int row = 0; row < grid[column].length; row++) {
        if (grid[column][row] != null) {
          rowString.add(grid[column][row]);
        } else {
          rowString.add("");
        }
      }
      System.out.println(rowString.toString());
    }
  }

  public void prettyPrint() {
    int[] maxWidths = calculateMaxPerColumn();
    for (int column = 0; column < grid.length; column++) {
      StringJoiner rowString = new StringJoiner("|");
      for (int row = 0; row < grid[column].length; row++) {
        String string = grid[column][row];
        String generatedString = generatedStringWithSpace(string, maxWidths[row]);
        rowString.add(generatedString);
      }
      System.out.println(rowString.toString());
    }
  }

  private String generatedStringWithSpace(String string, int requiredWidth) {
    StringBuilder stringBuilder;

    int remainingLengthToFill;
    if (string == null) { // build string with just spaces
      stringBuilder = new StringBuilder();
      remainingLengthToFill = requiredWidth;
    } else {
      stringBuilder = new StringBuilder(string);
      remainingLengthToFill = requiredWidth - string.length();
    }
    for (int i = 0; i < remainingLengthToFill; i++) {
      stringBuilder.append(' ');
    }
    return stringBuilder.toString();
  }

  private int[] calculateMaxPerColumn() {
    int[] maxWidths = new int[grid.length];
    for (int column = 0; column < grid.length; column++) {
      for (int row = 0; row < grid[column].length; row++) {
        String string = grid[column][row];
        if (string == null) {
          continue;
        }
        int length = string.length();
        if (length > maxWidths[row]) {
          maxWidths[row] = length;
        }
      }
    }
    return maxWidths;
  }
}
