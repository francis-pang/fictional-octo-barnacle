package leetcode;

public class NQueens2 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.totalNQueens(2));
  }

  static class Solution {
    public int totalNQueens(int n) {
      int[][] chessboard = new int[n][n];

      int numberOfWaysToPlaceQueens = placeQueenOnBoard(chessboard, 0);
      return numberOfWaysToPlaceQueens;
    }

    private int placeQueenOnBoard(int[][] chessboard, int row) {
      int numberOfWaysToPlaceQueens = 0;
      // mark the first grid first.
      for (int column = 0; column < chessboard.length; column++) {
        if (chessboard[row][column] == 0) {
          if (row == chessboard.length - 1) {
            numberOfWaysToPlaceQueens++;
          } else {
            markKillPath(chessboard, row, column, false);
            numberOfWaysToPlaceQueens += placeQueenOnBoard(chessboard, row + 1);
            markKillPath(chessboard, row, column, true);
          }
        }
      }
      return numberOfWaysToPlaceQueens;
    }

    private void markKillPath(int[][] chessboard, int row, int column, boolean undo) {
      // Mark out the entire row
      for (int i = 0; i < chessboard.length; i++) {
        if (undo) {
          chessboard[i][column]--;
        } else {
          chessboard[i][column]++;
        }
      }

      // Mark out the entire column
      for (int j = 0; j < chessboard.length; j++) {
        if (j == column) { // Skip to avoid double counting
          continue;
        }
        if (undo) {
          chessboard[row][j]--;
        } else {
          chessboard[row][j]++;
        }
      }

      // Mark out diagonally towards top left
      int traversingRow = row - 1;
      int traversingColumn = column - 1;
      while (traversingColumn >= 0 && traversingRow >= 0) {
        if (undo) {
          chessboard[traversingRow][traversingColumn]--;
        } else {
          chessboard[traversingRow][traversingColumn]++;
        }
        traversingColumn--;
        traversingRow--;
      }

      // Mark out diagonally towards bottom right
      traversingRow = row + 1;
      traversingColumn = column + 1;
      while (traversingColumn < chessboard.length && traversingRow < chessboard.length) {
        if (undo) {
          chessboard[traversingRow][traversingColumn]--;
        } else {
          chessboard[traversingRow][traversingColumn]++;
        }
        traversingColumn++;
        traversingRow++;
      }

      // Mark out diagonally towards top right
      traversingRow = row - 1;
      traversingColumn = column + 1;
      while (traversingColumn < chessboard.length && traversingRow >= 0) {
        if (undo) {
          chessboard[traversingRow][traversingColumn]--;
        } else {
          chessboard[traversingRow][traversingColumn]++;
        }
        traversingColumn++;
        traversingRow--;
      }

      // Mark out diagonally towards bottom left
      traversingRow = row + 1;
      traversingColumn = column - 1;
      while (traversingColumn >= 0 && traversingRow < chessboard.length) {
        if (undo) {
          chessboard[traversingRow][traversingColumn]--;
        } else {
          chessboard[traversingRow][traversingColumn]++;
        }
        traversingColumn--;
        traversingRow++;
      }
    }
  }
}
