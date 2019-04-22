package crackingthecodinginterview.arrayandstring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroMatrixTest {
  private static ZeroMatrix zeroMatrix;

  @BeforeAll
  static void setUpOnce() {
    zeroMatrix = new ZeroMatrix();
  }

  @Test
  void convertOneByOneMatrix() {
    int[][] oneByOneMatrix = {{15}};
    assertTrue(Matrix.isTwoMatrixSame(oneByOneMatrix, zeroMatrix.convertAdjToZeroMatrix(oneByOneMatrix)));
  }

  @Test
  void convertNone2x2Matrix() {
    int[][] twoByTwoMatrix = {{15, 16},
        {14, 13}};
    assertTrue(Matrix.isTwoMatrixSame(twoByTwoMatrix, zeroMatrix.convertAdjToZeroMatrix(twoByTwoMatrix)));
  }

  @Test
  void convertOne2x2Matrix() {
    int[][] twoByTwoMatrix = {{15, 16},
        {0, 13}};
    int[][] expectedMatrix = {{0, 16},
        {0, 0}};
    assertTrue(Matrix.isTwoMatrixSame(expectedMatrix, zeroMatrix.convertAdjToZeroMatrix(twoByTwoMatrix)));
  }

  @Test
  void convertThree5x7Matrix() {
    int[][] fiveBySevenMatrix = {{1, 2, 3, 4, 5},
        {6, 0, 8, 9, 10},
        {11, 0, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 0, 25},
        {26, 27, 28, 29, 30},
        {31, 32, 33, 34, 35}};
    int[][] expectedMatrix = {{1, 0, 3, 0, 5},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {16, 0, 18, 0, 20},
        {0, 0, 0, 0, 0},
        {26, 0, 28, 0, 30},
        {31, 0, 33, 0, 35}};
    assertTrue(Matrix.isTwoMatrixSame(expectedMatrix, zeroMatrix.convertAdjToZeroMatrix(fiveBySevenMatrix)));
  }
}