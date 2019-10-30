package crackingthecodinginterview.arrayandstring;

public class RotateMatrix {
  public void rotateImageLeft(int[][] image) {
    int imageBoundary = image.length - 1;
    // Start from the first row, we set the boundary where the swapping occurs. After each round, we reduce the
    // boundary by 2 (first and last). This is because the length of the inner square has 2 less cell (one on each
    // side). This applies the same for the column as well.
    for (int row = 0; row < imageBoundary; row++) {
      // Swap per column first, then go row by go
      for (int column = row; column < imageBoundary; column++) {
        // Determine the 4 coordinates to carry out the 4 layer swapping
        Coordinate coordinate1 = new Coordinate(row, column);
        Coordinate coordinate2 = getCopyDestination(image.length, coordinate1);
        Coordinate coordinate3 = getCopyDestination(image.length, coordinate2);
        Coordinate coordinate4 = getCopyDestination(image.length, coordinate3);
        rotate(image, coordinate1, coordinate2, coordinate3, coordinate4);
      }
      imageBoundary--;
    }
  }

  /**
   * Based on the origin coordinate, calculate the coordinate destination to copy the value in the matrix to
   *
   * @param matrixLength the length of the square matrix, needed for calculation
   * @param coordinate   origin coordinate
   * @return coordinate of copy destination
   */
  private Coordinate getCopyDestination(int matrixLength, Coordinate coordinate) {
    int newColumn = coordinate.row;
    int newRow = matrixLength - 1 - coordinate.column;
    return new Coordinate(newRow, newColumn);
  }

  private void rotate(int[][] array,
                      Coordinate coordinate1,
                      Coordinate coordinate2,
                      Coordinate coordinate3,
                      Coordinate coordinate4) {
    // Although this is a rotation algorithm, effectively we are just doing swapping in a merry-go-round fashion. We
    // want to move coordinate 1 to 2, then 2 to 3, and 3 to 4. But the sequence is reversed so that the original
    // value stored at each cells is preserved, then one temp value to store the value to be overwritten first.
    int swapValue = array[coordinate4.row][coordinate4.column];
    array[coordinate4.row][coordinate4.column] = array[coordinate3.row][coordinate3.column];
    array[coordinate3.row][coordinate3.column] = array[coordinate2.row][coordinate2.column];
    array[coordinate2.row][coordinate2.column] = array[coordinate1.row][coordinate1.column];
    array[coordinate1.row][coordinate1.column] = swapValue;
  }

  public class Coordinate {
    public int row;
    public int column;

    public Coordinate(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }

  public static void main(String[] args) {
    int[][] inputArray = {
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25},
        {26, 27, 28, 29, 30},
        {31, 32, 33, 34, 35}
    };
    System.out.println("Input:");
    printSquareArray(inputArray);
    System.out.println();
    RotateMatrix rotateMatrix = new RotateMatrix();
    rotateMatrix.rotateImageLeft(inputArray);
    System.out.println("Output:");
    printSquareArray(inputArray);
  }

  public static void printSquareArray(int[][] array) {
    for (int row = 0; row < array.length; row++) {
      for (int column = 0; column < array.length; column++) {
        System.out.print(array[row][column] + ", ");
      }
      System.out.print("\n");
    }
  }
}