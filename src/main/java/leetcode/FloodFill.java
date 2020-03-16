package leetcode;

public class FloodFill {
  public static void main(String[] args) {
    int[][] image = {{0, 0, 0}, {0, 1, 1}};
    Solution solution = new Solution();
    solution.floodFill(image, 1, 1, 1);
  }

  static class Solution {
    public int[][] floodFill(int[][] image, int startRow, int startColumn, int newColor) {
      // Greedy algorithm
      int originalColor = image[startRow][startColumn];

      if (originalColor == newColor) { //Same color, no need to change
        return image;
      }
      // Change the color of its own cells
      image[startRow][startColumn] = newColor;

      // Check up
      if (startRow > 0 && image[startRow - 1][startColumn] == originalColor) {
        floodFill(image, startRow - 1, startColumn, newColor);
      }

      // Check down
      if (startRow < image.length - 1 && image[startRow + 1][startColumn] == originalColor) {
        floodFill(image, startRow + 1, startColumn, newColor);
      }

      // Check left
      if (startColumn > 0 && image[startRow][startColumn - 1] == originalColor) {
        floodFill(image, startRow, startColumn - 1, newColor);
      }

      // Check right
      if (startColumn < image[0].length - 1 && image[startRow][startColumn + 1] == originalColor) {
        floodFill(image, startRow, startColumn + 1, newColor);
      }

      return image;
    }
  }
}
