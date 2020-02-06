package crackingthecodinginterview.hard;

public class MaxSubmatrix {
  public int largestSum(int[][] grid) {
    int largest = Integer.MIN_VALUE;
    for (int subGridSize = 1; subGridSize < grid.length; subGridSize++) {
      int[] array = new int[grid.length];
      for (int column = 0; column < grid.length; column++) {
        for (int row = 0; row < subGridSize; row++) {
          array[column] += grid[row][column];
        }
      }
      int sumRowIndex = 0;
      while (sumRowIndex < (grid.length - (subGridSize - 1))) {
        int sum = 0;
        for (int column = 0; column < subGridSize; column++) {
          sum += array[column];
        }
        if (largest < sum) {
          largest = sum;
        }
        for (int column = subGridSize; column < grid.length; column++) {
          sum -= array[column - subGridSize];
          sum += array[column];
          if (sum > largest) {
            largest = sum;
          }
        }
        sumRowIndex++;
        if (sumRowIndex < (grid.length - (subGridSize - 1))) {
          for (int column = 0; column < grid.length; column++) {
            array[column] -= grid[sumRowIndex - 1][column];
            array[column] += grid[sumRowIndex + (subGridSize - 1)][column];
          }
        }
      }
    }
    return largest;
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{
        {-9, 1, 5},
        {4, -4, 5},
        {5, 0, -9}
    };
    MaxSubmatrix maxSubmatrix = new MaxSubmatrix();
    System.out.println(maxSubmatrix.largestSum(grid));
  }
}
