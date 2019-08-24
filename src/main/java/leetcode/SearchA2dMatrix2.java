package leetcode;

public class SearchA2dMatrix2 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    //[[3,3,8,13,13,18],[4,5,11,13,18,20],[9,9,14,15,23,23],[13,18,22,22,25,27],[18,22,23,28,30,33],[21,25,28,30,35,35],[24,25,33,36,37,40]]
    //21
    System.out.println(solution.searchMatrix(new int[][]{{3, 3, 8, 13, 13, 18}, {4, 5, 11, 13, 18, 20}, {9, 9, 14, 15, 23, 23}, {13, 18, 22, 22, 25, 27}, {18, 22, 23, 28, 30, 33}, {21, 25, 28, 30, 35, 35}, {24, 25, 33, 36, 37, 40}},
        21));
  }

  static class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
      if (matrix == null || matrix.length == 0) {
        return false;
      }
      return diagonalBinarySearch(matrix, target, 0, matrix.length - 1, 0, 0, matrix[0].length - 1, 0);
    }

    private boolean diagonalBinarySearch(int[][] matrix, int number, int rowLow, int rowHigh,
                                         int previousRowMid, int columnLow, int columnHigh, int previousColumnMid) {
      if (rowLow > rowHigh || columnLow > columnHigh) {
        return levelBinarySearchWithRepeat(matrix, number, previousRowMid, previousColumnMid);
      }
      int rowMid = calculateMidPoint(rowLow, rowHigh);
      int columnMid = calculateMidPoint(columnLow, columnHigh);

      if (matrix[rowMid][columnMid] == number) {
        return true;
      } else if (matrix[rowMid][columnMid] > number) {
        rowHigh = rowMid - 1;
        columnHigh = columnMid - 1;
      } else { // matrix[rowMid][columnMid] < number
        rowLow = rowMid + 1;
        columnLow = columnMid + 1;
      }
      return diagonalBinarySearch(matrix, number, rowLow, rowHigh, rowMid, columnLow, columnHigh, columnMid);
    }

    private boolean levelBinarySearchWithRepeat(int[][] matrix, int number, int rowMid, int columnMid) {
      boolean numberIsFound = levelBinarySearch(matrix, number, rowMid, columnMid);
      if (numberIsFound) {
        return true;
      }
      /*
       * At this point, the result cell is null, so we need to search along the next/ previous cell. First we need to
       * check if it is the next OR previous cell that we search along.
       */
      boolean cellIsAtLeftEdge = columnMid == 0;
      boolean cellIsAtRightEdge = columnMid == matrix[0].length;
      boolean cellIsAtTopEdge = rowMid == 0;
      boolean cellIsAtBottonEdge = rowMid == matrix.length - 1;

      // Single cell matrix
      if (cellIsAtBottonEdge && cellIsAtTopEdge && cellIsAtLeftEdge && cellIsAtRightEdge) {
        return false;
      }

      if (matrix[rowMid][columnMid] > number) { // We will try to move one cell diagonally left-above
        // Check for any boundary case
        if (cellIsAtLeftEdge && cellIsAtTopEdge) { // first cell
          return false;
        } else if (cellIsAtLeftEdge) { // along the vertical left edge, so search along previous column only
          return levelBinarySearch(matrix, number, rowMid, columnMid - 1);
        } else if (cellIsAtTopEdge) { // along the horizontal left edge, so search along previous row only
          return levelBinarySearch(matrix, number, rowMid - 1, columnMid);
        } else { // No boundary issue, search the diagonally left-above cell
          return levelBinarySearch(matrix, number, rowMid - 1, columnMid - 1);
        }
      } else { // We will try to move one cell diagonally right-below
        if (cellIsAtRightEdge && cellIsAtBottonEdge) { // last cell
          return false;
        } else if (cellIsAtRightEdge) { // along the vertical right cell, so search along next column only
          return levelBinarySearch(matrix, number, rowMid, columnMid + 1);
        } else if (cellIsAtBottonEdge) { // along the horizontal right cell, so search along next row only
          return levelBinarySearch(matrix, number, rowMid + 1, columnMid);
        } else { // No boundary issue, so search the diagonally right-below cell
          return levelBinarySearch(matrix, number, rowMid + 1, columnMid + 1);
        }
      }
    }

    private boolean levelBinarySearch(int[][] matrix, int number, int rowMid, int columnMid) {
      boolean isNumberFound;
      if (rowMid >= matrix.length || rowMid < 0 ||
          columnMid >= matrix[0].length || columnMid < 0) {
        return false;
      }
      if (matrix[rowMid][columnMid] > number) {
        isNumberFound = horizontalBinarySearch(matrix, number, rowMid, 0, columnMid);
        return (isNumberFound || (verticalBinarySearch(matrix, number, columnMid, 0, rowMid)));
      } else { // matrix[rowMid][columnMid] < number
        isNumberFound = horizontalBinarySearch(matrix, number, rowMid, columnMid, matrix[0].length - 1);
        return (isNumberFound || (verticalBinarySearch(matrix, number, columnMid, 0, matrix.length - 1)));
      }
    }

    private boolean horizontalBinarySearch(int[][] matrix, int number, int row, int low, int high) {
      if (low > high) {
        return false;
      }
      int mid = calculateMidPoint(low, high);
      if (mid >= matrix[0].length || mid < 0) {
        return false;
      }
      if (matrix[row][mid] == number) {
        return true;
      } else if (matrix[row][mid] > number) {
        high = mid - 1;
      } else { // matrix[row][mid] < number
        low = mid + 1;
      }
      return horizontalBinarySearch(matrix, number, row, low, high);
    }

    private boolean verticalBinarySearch(int[][] matrix, int number, int column, int low, int high) {
      if (low > high) {
        return false;
      }
      int mid = calculateMidPoint(low, high);
      if (mid >= matrix.length || mid < 0) {
        return false;
      }
      if (matrix[mid][column] == number) {
        return true;
      } else if (matrix[mid][column] > number) {
        high = mid - 1;
      } else { // matrix[mid][column] < number
        low = mid + 1;
      }
      return verticalBinarySearch(matrix, number, column, low, high);
    }

    private int calculateMidPoint(int low, int high) {
      return (int) Math.ceil((low + high) / 2.0);
    }
  }
}
