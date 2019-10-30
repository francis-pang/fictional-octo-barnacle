package crackingthecodinginterview.arrayandstring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RotateMatrixTest {
  private static RotateMatrix rotateMatrix;

  @BeforeAll
  static void setUpOnce() {
    rotateMatrix = new RotateMatrix();
  }

  @Test
  void testOneByOneMatrix() {
    int[][] oneByOneMatrix = {{4}};
    int[][] expectedMatrix = {{4}};
    rotateMatrix.rotateImageLeft(oneByOneMatrix);
    assertArrayEquals(expectedMatrix, oneByOneMatrix);
  }

  @Test
  void testTwoByTwoMatrix() {
    int[][] testMatrix = {
        {1, 2},
        {3, 4}};
    int[][] expectedMatrix = {
        {2, 4},
        {1, 3}};
    rotateMatrix.rotateImageLeft(testMatrix);
    assertArrayEquals(expectedMatrix, testMatrix);
  }

  @Test
  void testFiveByFiveMatrix() {
    int[][] testMatrix = {
        {96, 14, 62, 64, 36},
        {20, 54, 67, 25, 35},
        {94, 28, 81, 78, 74},
        {36, 64, 26, 16, 97},
        {64, 76, 90, 21, 68}
    };
    int[][] expectedMatrix = {
        {36, 35, 74, 97, 68},
        {64, 25, 78, 16, 21},
        {62, 67, 81, 26, 90},
        {14, 54, 28, 64, 76},
        {96, 20, 94, 36, 64}
    };
    rotateMatrix.rotateImageLeft(testMatrix);
    assertArrayEquals(expectedMatrix, testMatrix);
  }

  @Test
  void testThreeByThreeMatrix() {
    int[][] testMatrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    int[][] expectedMatrix = {
        {3, 6, 9},
        {2, 5, 8},
        {1, 4, 7}
    };
    rotateMatrix.rotateImageLeft(testMatrix);
    assertArrayEquals(expectedMatrix, testMatrix);
  }

  @Test
  void testFiveByFiveMatrix2() {
    int[][] testMatrix = {
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25},
        {26, 27, 28, 29, 30},
        {31, 32, 33, 34, 35}
    };
    int[][] expectedMatrix = {
        {15, 20, 25, 30, 35},
        {14, 19, 24, 29, 34},
        {13, 18, 23, 28, 33},
        {12, 17, 22, 27, 32},
        {11, 16, 21, 26, 31}
    };
    rotateMatrix.rotateImageLeft(testMatrix);
    assertArrayEquals(expectedMatrix, testMatrix);
  }
}