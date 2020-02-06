package crackingthecodinginterview.hard;

public class MaxSubmatrix {

  /*
   * Time complexity of this solution is
   * O(n^2) + O(n) * [O(n) + O(n)] ~= O(n^2)
   * 1) The first O(n^2) is to compute the accumulated sum.
   * 2) For [O(n) + O(n)], first O(n) is calculate and compare the sum of each column, second O(n) is for sliding
   * the accumulated sum along the row.
   * 3) The calculation in step 2 is repeated for (n - 1) times, so the total is O(n^2).
   *
   * Space complexity is O(n^2), to store the accumulated sum.
   */
  public int largestSum(int[][] grid) {
    int largest = Integer.MIN_VALUE;
    // Pre compute the accumulated row sum
    // First row will be empty.
    // The time complexity of this is O(n^2), because we need to visit each node on the grid to compute the
    // accumulated sum up to row X.
    // The space complexity is O(n^2) when we store all the accumulated row values as an intermediary.
    int[][] accumulatedRowSums = new int[grid.length][grid.length];
    for (int row = 0; row < grid.length - 1; row++) {
      for (int column = 0; column < grid.length; column++) {
        if (row > 0) { // Not the first row
          accumulatedRowSums[row + 1][column] += accumulatedRowSums[row][column];
        }
        // Add value at current row to the accumulated sum thus far
        accumulatedRowSums[row + 1][column] += grid[row][column];
      }
    }

    // Calculate all possible sum for size ranging from 1 to (n - 1)
    for (int subGridSize = 1; subGridSize < grid.length; subGridSize++) {
      int[] array = accumulatedRowSums[subGridSize];
      int sumRowIndex = 0;
      do {
        int sum = 0;

        // Calculate the sum of the initial grid size at (X,0) index, X is the sumRowIndex
        // Together with the next for loop, the time complexity is O(n), because you only iterate through each of the
        // array once.
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
        // Shift the accumulated sum at each column via the sliding window method, i.e take out the first value of
        // the accumulated sum, and add the new value in the grid.
        // The complexity of this is O(n)
        if (sumRowIndex < (grid.length - (subGridSize - 1))) {
          for (int column = 0; column < grid.length; column++) {
            array[column] -= grid[sumRowIndex - 1][column];
            array[column] += grid[sumRowIndex + (subGridSize - 1)][column];
          }
        }
      } while (sumRowIndex < (grid.length - (subGridSize - 1)));
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
    System.out.println(maxSubmatrix.largestSum(grid)); // Should be 7
    grid = new int[][]{
        {-9, 1, 5},
        {4, -9, 5},
        {5, 0, -9}
    };
    System.out.println(maxSubmatrix.largestSum(grid)); // Should be 5
    grid = new int[][]{
        {5, -2, 22, 22},
        {1, -10, -100, 22},
        {5, 9, 5, 6},
        {4, 10, 8, 7}
    };
    System.out.println(maxSubmatrix.largestSum(grid)); // Should be 32
  }
}
