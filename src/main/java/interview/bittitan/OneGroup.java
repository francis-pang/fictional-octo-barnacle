package interview.bittitan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OneGroup {
  static class Result {

    /*
     * Complete the 'onesGroups' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY grid
     *  2. INTEGER_ARRAY queries
     */
    private static final int TAKEN = 1;
    private static final int EMPTY = 0;

    public static List<Integer> onesGroups(List<List<Integer>> grid, List<Integer> queries) {
      // Write your code here
      int[][] matrix = new int[grid.size()][grid.get(0).size()];
      for (int i = 0; i < grid.size(); i++) {
        List<Integer> row = grid.get(i);
        for (int j = 0; j < row.size(); j++) {
          matrix[i][j] = row.get(j);
        }
      }

      Map<Integer, Integer> countOfConnected1s = new HashMap<>();
      for (int row = 0; row < matrix.length; row++) {
        for (int column = 0; column < matrix[0].length; column++) {
          if (matrix[row][column] == TAKEN) {
            int size = getSizeOfConnectedCells(matrix, row, column);
            countOfConnected1s.compute(size, (k, count) -> (count == null) ? 1 : count + 1);
          }
        }
      }

      List<Integer> result = new ArrayList<>();
      for (int query : queries) {
        int count = countOfConnected1s.getOrDefault(query, 0);
        result.add(count);
      }
      return result;
    }

    private static int getSizeOfConnectedCells(int[][] matrix, int row, int column) {
      matrix[row][column] = EMPTY;

      int leftCount = 0;
      if (row > 0 && matrix[row - 1][column] == TAKEN) {
        leftCount = getSizeOfConnectedCells(matrix, row - 1, column);
      }

      int rightCount = 0;
      if (row < matrix.length - 1 && matrix[row + 1][column] == TAKEN) {
        rightCount = getSizeOfConnectedCells(matrix, row + 1, column);
      }

      int upCount = 0;
      if (column > 0 && matrix[row][column - 1] == TAKEN) {
        upCount = getSizeOfConnectedCells(matrix, row, column - 1);
      }

      int downCount = 0;
      if (column < matrix[0].length - 1 && matrix[row][column + 1] == TAKEN) {
        downCount = getSizeOfConnectedCells(matrix, row, column + 1);
      }
      return leftCount + rightCount + upCount + downCount + 1;
    }
  }

  public static class Solution {
    public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int gridRows = Integer.parseInt(bufferedReader.readLine().trim());
      int gridColumns = Integer.parseInt(bufferedReader.readLine().trim());

      List<List<Integer>> grid = new ArrayList<>();

      IntStream.range(0, gridRows).forEach(i -> {
        try {
          grid.add(
              Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                  .map(Integer::parseInt)
                  .collect(toList())
          );
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });

      int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

      List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
        try {
          return bufferedReader.readLine().replaceAll("\\s+$", "");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      })
          .map(String::trim)
          .map(Integer::parseInt)
          .collect(toList());

      List<Integer> result = Result.onesGroups(grid, queries);

      bufferedWriter.write(
          result.stream()
              .map(Object::toString)
              .collect(joining("\n"))
              + "\n"
      );

      bufferedReader.close();
      bufferedWriter.close();
    }
  }
}
