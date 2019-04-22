package crackingthecodinginterview.arrayandstring;

public class RotateMatrix {
  int matrixLength;
  int[][] matrix;

  public int[][] rotateTwoDimensionalMatrix(int[][] matrix) {
    this.matrixLength = matrix.length;
    if (matrixLength < 2) {
      return matrix;
    }
    this.matrix = matrix;
    int leftBound = 0;
    int rightBound = matrixLength - 1;
    do {
      for (int i = leftBound; i < rightBound; i++) {
        fourWayRotate(leftBound, i);
      }
      leftBound++;
      rightBound--;
    } while (leftBound < rightBound);
    return this.matrix;
  }

  private void fourWayRotate(int x, int y) {
    int a = x;
    int b = y;
    int temp = matrix[x][y];
    for (int i = 0; i < 3; i++) {
      int swap = this.matrixLength - a - 1;
      matrix[a][b] = matrix[b][swap];
      a = b;
      b = swap;
    }
    matrix[a][b] = temp;
  }
}
