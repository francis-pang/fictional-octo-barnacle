package crackingthecodinginterview.moderate;

import java.util.ArrayList;
import java.util.List;

/**
 * You have an integer matrix representing a plot of land, where the value at that location represents the height
 * above sea level. A value of zero indicates water. A pond is a region of water connected vertically, horizontally,
 * or diagonally. The size of the pond is the total number of connected water cells. Write a method to compute the
 * sizes of all ponds in the matrix.
 */
public class PondSizes {
  public List<Integer> sizesOfAllPonds(int[][] land) {
    List<Integer> answer = new ArrayList<>();
    locateAllPonds(answer, land);
    return answer;
  }

  private void locateAllPonds(List<Integer> ponds, int[][] land) {
    for (int row = 0; row < land.length; row++) {
      for (int column = 0; column < land[0].length; column++) {
        int sizeOfPond = extendOnePond(land, row, column);
        if (sizeOfPond > 0) {
          ponds.add(sizeOfPond);
        }
      }
    }
  }

  private int extendOnePond(int[][] land, int row, int column) {
    if (land[row][column] != 0) {
      return 0;
    }
    int sizeOfPond = 0;
    land[row][column] = -1;
    sizeOfPond++;

    int maxColumnSize = land[0].length - 1; //zero-based
    int maxRowSize = land.length - 1; // zero-based

    // Diagonally above left
    if (row > 0 && column > 0) {
      sizeOfPond += extendOnePond(land, row - 1, column - 1);
    }
    // 1 row above
    if (row > 0) {
      sizeOfPond += extendOnePond(land, row - 1, column);
    }

    // Diagonally above right
    if (row > 0 && column < maxColumnSize) {
      sizeOfPond += extendOnePond(land, row - 1, column + 1);
    }

    // 1 column left
    if (column > 0) {
      sizeOfPond += extendOnePond(land, row, column - 1);
    }

    // 1 column right
    if (column < maxColumnSize) {
      sizeOfPond += extendOnePond(land, row, column + 1);
    }

    // Diagonally below left
    if (row < maxRowSize && column > 0) {
      sizeOfPond += extendOnePond(land, row + 1, column - 1);
    }

    // 1 row below
    if (row < maxRowSize) {
      sizeOfPond += extendOnePond(land, row + 1, column);
    }

    // Diagonally below right
    if (row < maxRowSize && column < maxColumnSize) {
      sizeOfPond += extendOnePond(land, row + 1, column + 1);
    }

    return sizeOfPond;
  }
}
