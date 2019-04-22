package crackingthecodinginterview.arrayandstring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class RotateMatrixTest {
  private static RotateMatrix rotateMatrix;

  @BeforeAll
  static void setUpOnce() {
    rotateMatrix = new RotateMatrix();
  }

  @Test
  void testOneByOneMatrix() {
    int[][] oneByOneMatrix = {{4}};
    assertTrue(Matrix.isTwoMatrixSame(oneByOneMatrix, rotateMatrix.rotateTwoDimensionalMatrix(oneByOneMatrix)));
  }

  @Test
  void testTwoByTwoMatrix() {
    int[][] testMatrix = {{1, 2},
        {3, 4}};
    int[][] expectedMatrix = {{2, 4},
        {1, 3}};
    assertTrue(Matrix.isTwoMatrixSame(expectedMatrix, rotateMatrix.rotateTwoDimensionalMatrix(testMatrix)));
  }

  @Test
  void testFiveByFiveMatrix() {
    int[][] testMatrix = {{96, 14, 62, 64, 36},
        {20, 54, 67, 25, 35},
        {94, 28, 81, 78, 74},
        {36, 64, 26, 16, 97},
        {64, 76, 90, 21, 68}};
    int[][] expectedMatrix = {{36, 35, 74, 97, 68},
        {64, 25, 78, 16, 21},
        {62, 67, 81, 26, 90},
        {14, 54, 28, 64, 76},
        {96, 20, 94, 36, 64}};
    assertTrue(Matrix.isTwoMatrixSame(expectedMatrix, rotateMatrix.rotateTwoDimensionalMatrix(testMatrix)));
  }
}