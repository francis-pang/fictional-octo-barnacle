package facebook.abcs.graph;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class SnakesAndLaddersTheQuickestWayUp {
  static int quickestWayUp(int[][] ladders, int[][] snakes) {
    int numberOfStep = 0;
    boolean[] processGrid = new boolean[101];
    Queue<Integer> unprocessedGrids;
    Queue<Integer> nextLevelGrids = new ArrayDeque<>();
    nextLevelGrids.add(1);

    Map<Integer, Integer> jumpsByStartPoints = buildLadderOrSnakeMap(ladders, snakes);

    while (!nextLevelGrids.isEmpty()) {
      unprocessedGrids = nextLevelGrids;
      nextLevelGrids = new ArrayDeque<>();
      numberOfStep++;
      while (!unprocessedGrids.isEmpty()) {
        int gridIndex = unprocessedGrids.poll();
        if (processGrid[gridIndex]) {
          continue;
        }
        for (int i = 6; i >= 0; i--) {
          int destination = gridIndex + i;
          if (jumpsByStartPoints.containsKey(destination)) {
            destination = jumpsByStartPoints.get(destination);
            nextLevelGrids.add(destination);
          }
          if (destination == 100) {
            return numberOfStep;
          } else if (destination < 100) {
            nextLevelGrids.add(destination);
          }
        }
        processGrid[gridIndex] = true;
      }
    }
    return -1;
  }

  private static Map<Integer, Integer> buildLadderOrSnakeMap(int[][] ladders, int[][] snakes) {
    Map<Integer, Integer> pointsMapByStartPoint = new HashMap<>();
    Arrays.stream(ladders).forEach(point -> {
      int start = point[0];
      int end = point[1];
      pointsMapByStartPoint.put(start, end);
    });
    Arrays.stream(snakes).forEach(point -> {
      int start = point[0];
      int end = point[1];
      pointsMapByStartPoint.put(start, end);
    });
    return pointsMapByStartPoint;
  }

  public static void main(String[] args) throws FileNotFoundException {
    final Scanner scanner = new Scanner("2\n" +
        "3\n" +
        "32 62\n" +
        "42 68\n" +
        "12 98\n" +
        "7\n" +
        "95 13\n" +
        "97 25\n" +
        "93 37\n" +
        "79 27\n" +
        "75 19\n" +
        "49 47\n" +
        "67 17\n" +
        "4\n" +
        "8 52\n" +
        "6 80\n" +
        "26 42\n" +
        "2 72\n" +
        "9\n" +
        "51 19\n" +
        "39 11\n" +
        "37 29\n" +
        "81 3\n" +
        "59 5\n" +
        "79 23\n" +
        "53 7\n" +
        "43 33\n" +
        "77 21 ");
    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[][] ladders = new int[n][2];

      for (int i = 0; i < n; i++) {
        String[] laddersRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 2; j++) {
          int laddersItem = Integer.parseInt(laddersRowItems[j]);
          ladders[i][j] = laddersItem;
        }
      }

      int m = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[][] snakes = new int[m][2];

      for (int i = 0; i < m; i++) {
        String[] snakesRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 2; j++) {
          int snakesItem = Integer.parseInt(snakesRowItems[j]);
          snakes[i][j] = snakesItem;
        }
      }

      int result = quickestWayUp(ladders, snakes);
      System.out.println(result);
    }
  }
}
