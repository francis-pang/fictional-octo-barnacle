package facebook.abcs.recursion;

import java.util.Scanner;

public class NumpathsInAMxnGrid {
  public static class Solution {

    public static void main(String[] args) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      String[] inputs = input.split("\\s");
      int m = Integer.valueOf(inputs[0]);
      int n = Integer.valueOf(inputs[1]);
      System.out.println(getNumberOfPathsFromGrid(m, n));
    }

    private static int getNumberOfPathsFromGrid(int length, int width) {
      return getNumberOfPathsRecurisvelyFrom(1, 1, length, width);
    }

    private static int getNumberOfPathsRecurisvelyFrom(int row, int column, int length, int width) {
      if (row >= length || column >= width) {
        return 1;
      }
      return (getNumberOfPathsRecurisvelyFrom(row + 1, column, length, width)) +
          getNumberOfPathsRecurisvelyFrom(row, column + 1, length, width);
    }
  }
}
