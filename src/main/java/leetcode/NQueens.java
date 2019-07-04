package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.solveNQueens(4));
  }

  static class Solution {
    public List<List<String>> solveNQueens(int n) {
      if (n <= 0) {
        return new ArrayList<>();
      }
      List<List<String>> answer = new ArrayList<>();
      int[][] chessboard = new int[n][n];
      placeNQueens(answer, chessboard, 0);
      return answer;
    }

    /**
     * @param placementWays the details of all the possible way to place each of the queen
     * @param chessboard    an simulation of the chessboard, negative value on the cell represents the position of a
     *                      queen while a positive value represents marked cell, which no queen can be placed on it, and
     *                      zero represents cell of no indication.
     * @param row           the index of the processing row number
     */
    private void placeNQueens(List<List<String>> placementWays, int[][] chessboard, int row) {
      // chessboard.length is instead of chessboard[0].length used because the chessboard is NxN sized
      for (int column = 0; column < chessboard.length; column++) {
        if (chessboard[row][column] == 0) { // No indication, can put a queen there
          chessboard[row][column] = -1; // marked as a queen
          // If this is the last row, it means that we have managed to place the last queen on the chessboard
          if (row == chessboard.length - 1) {
            addNewCombination(placementWays, chessboard);
          } else {
            markKillZone(chessboard, row, column, false);
            placeNQueens(placementWays, chessboard, row + 1);
            // Revert the marking so that the board can be reused for next iteration within the for loop
            markKillZone(chessboard, row, column, true);
          }
          chessboard[row][column] = 0;
        }
      }
    }

    private void markKillZone(int[][] chessboard, int row, int column, boolean reverse) {
      // Mark out the entire row
      for (int i = 0; i < chessboard.length; i++) {
        if (chessboard[i][column] >= 0) {
          if (reverse) {
            chessboard[i][column]--;
          } else {
            chessboard[i][column]++;
          }
        }
      }

      // Mark out the entire column
      for (int j = 0; j < chessboard.length; j++) {
        if (chessboard[row][j] >= 0) { // Skip to avoid double counting
          if (reverse) {
            chessboard[row][j]--;
          } else {
            chessboard[row][j]++;
          }
        }
      }

      // Mark out diagonally towards top left
      int traversingRow = row - 1;
      int traversingColumn = column - 1;
      while (traversingColumn >= 0 && traversingRow >= 0) {
        if (chessboard[traversingRow][traversingColumn] >= 0) {
          if (reverse) {
            chessboard[traversingRow][traversingColumn]--;
          } else {
            chessboard[traversingRow][traversingColumn]++;
          }
        }
        traversingColumn--;
        traversingRow--;
      }

      // Mark out diagonally towards bottom right
      traversingRow = row + 1;
      traversingColumn = column + 1;
      while (traversingColumn < chessboard.length && traversingRow < chessboard.length) {
        if (chessboard[traversingRow][traversingColumn] >= 0) {
          if (reverse) {
            chessboard[traversingRow][traversingColumn]--;
          } else {
            chessboard[traversingRow][traversingColumn]++;
          }
        }
        traversingColumn++;
        traversingRow++;
      }

      // Mark out diagonally towards top right
      traversingRow = row - 1;
      traversingColumn = column + 1;
      while (traversingColumn < chessboard.length && traversingRow >= 0) {
        if (chessboard[traversingRow][traversingColumn] >= 0) {
          if (reverse) {
            chessboard[traversingRow][traversingColumn]--;
          } else {
            chessboard[traversingRow][traversingColumn]++;
          }
          traversingColumn++;
          traversingRow--;
        }
      }

      // Mark out diagonally towards bottom left
      traversingRow = row + 1;
      traversingColumn = column - 1;
      while (traversingColumn >= 0 && traversingRow < chessboard.length) {
        if (chessboard[traversingRow][traversingColumn] >= 0) {
          if (reverse) {
            chessboard[traversingRow][traversingColumn]--;
          } else {
            chessboard[traversingRow][traversingColumn]++;
          }
        }
        traversingColumn--;
        traversingRow++;
      }
    }

    private void addNewCombination(List<List<String>> placementWays, int[][] chessboard) {
      List<String> placement = new ArrayList<>();
      for (int[] row : chessboard) {
        StringBuilder queenArrangementBuilder = new StringBuilder();
        for (int column : row) {
          if (column == -1) {
            final char QUEEN_SQUARE = 'Q';
            queenArrangementBuilder.append(QUEEN_SQUARE);
          } else {
            final char EMPTY_SQUARE = '.';
            queenArrangementBuilder.append(EMPTY_SQUARE);
          }
        }
        placement.add(queenArrangementBuilder.toString());
      }
      placementWays.add(placement);
    }
  }
}
