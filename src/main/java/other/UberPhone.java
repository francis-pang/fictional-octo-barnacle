package other;

/*
Problem Statement: Given a matrix of Black and White, color all islands of White which are fully surrounded by Black with Black. Keep all islands of white connected to the edge as is.

Input:

B B B B
W B W B
W W W B
B B B B

Output:

B B B B
W B B B
B B B B
B B B W

*/
public class UberPhone {

  private static final char WHITE = 'W';
  private static final char BLACK = 'B';

  public static void main(String[] args) throws Exception {
    char[][] question = new char[][]{
        {'B', 'B', 'B'},
        {'B', 'W', 'B'},
        {'B', 'B', 'B'}
    };

    paintBlack(question);

    // Print the array
    for (int row = 0; row < question.length; row++) {
      for (int col = 0; col < question[0].length; col++) {
        System.out.print(question[row][col] + ",");
      }
      System.out.println();
    }
  }

  public static void paintBlack(char[][] matrix) {
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    boolean[][] preserved = new boolean[matrix.length][matrix[0].length];

    for (int row = 0; row < matrix.length; row++) {
      if (matrix[row][0] == WHITE) {
        System.out.println("FOUND WHITE at " + row + ", " + 0);
        lookForPreserve(matrix, row, 0, visited, preserved);
      }

      // Repeat for last row
      if (matrix[row][matrix[0].length - 1] == WHITE) {
        System.out.println("FOUND WHITE at " + row + ", " + (matrix[0].length - 1));
        lookForPreserve(matrix, row, matrix[0].length - 1, visited, preserved);
      }
    }


    for (int col = 0; col < matrix.length; col++) {
      if (matrix[0][col] == WHITE) {
        System.out.println("FOUND WHITE at " + 0 + ", " + col);
        lookForPreserve(matrix, 0, col, visited, preserved);
      }

      // Repeat for last column
      if (matrix[matrix.length - 1][col] == WHITE) {
        System.out.println("FOUND WHITE at " + (matrix.length - 1) + ", " + 0);
        lookForPreserve(matrix, matrix.length - 1, col, visited, preserved);
      }
    }

    // For re-coloring
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        if (matrix[row][col] == WHITE && preserved[row][col] != true) {
          matrix[row][col] = BLACK;
        }
      }
    }
  }

  public static void lookForPreserve(char[][] matrix, int row, int col, boolean[][] visited, boolean[][] preserved) {
    if (visited[row][col]) {
      return;
    }
    preserved[row][col] = true;
    visited[row][col] = true;
    // Check left
    if (col > 0 && matrix[row][col - 1] == WHITE) {
      lookForPreserve(matrix, row, col - 1, visited, preserved);
    }

    // Check right
    if (col < matrix[0].length - 1 && matrix[row][col + 1] == WHITE) {
      lookForPreserve(matrix, row, col + 1, visited, preserved);
    }

    // Check one cell below
    if (row < matrix.length - 1 && matrix[row + 1][col] == WHITE) {
      lookForPreserve(matrix, row + 1, col, visited, preserved);
    }

    // Check one cell above
    if (row > 0 && matrix[row - 1][col] == WHITE) {
      lookForPreserve(matrix, row - 1, col, visited, preserved);
    }
  }
}