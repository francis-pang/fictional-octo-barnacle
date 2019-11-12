package facebook.abcs.graph;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class SnakesAndLaddersTheQuickestWayUp {
  static int quickestWayUpDP(int[][] ladders, int[][] snakes) {
    // This will be method which we use bottom-up iterative dynamic programming to get the minimum steps
    int[] minimumSteps = new int[101];
    for (int i = 0; i < minimumSteps.length; i++) {
      minimumSteps[i] = Integer.MAX_VALUE;
    }
    minimumSteps[1] = 0;

    int[] ladderEndPoints = convertLadderOrSnakeToOneDimensionArray(ladders);
    int[] snakeEndPoints = convertLadderOrSnakeToOneDimensionArray(snakes);

    for (int currentGridPos = 1; currentGridPos < 100; currentGridPos++) {
      // So this means that there is a snake at the current grid, and we need to add the number of steps by the
      // number of steps at the tails of the snake (end points).
      int minimumStepFromCurrentGridPos = minimumSteps[currentGridPos];
      int snakeEndPoint = snakeEndPoints[currentGridPos];
      if (snakeEndPoint != 0) {
        // Taken this step from https://www.hackerrank.com/challenges/the-quickest-way-up/forum/comments/91508
        if (minimumSteps[snakeEndPoint] < minimumStepFromCurrentGridPos) {
          minimumSteps[snakeEndPoint] = minimumStepFromCurrentGridPos;
          currentGridPos = snakeEndPoint;
        }
      }
      int nextMinimumStep = minimumStepFromCurrentGridPos + 1;
      for (int dice = 1; dice <= 6; dice++) {
        int destinationGrid = currentGridPos + dice;
        if (destinationGrid > 100) {
          continue;
        }
        if (minimumSteps[destinationGrid] == 0 || nextMinimumStep < minimumSteps[destinationGrid]) {
          minimumSteps[destinationGrid] = nextMinimumStep;
        }
        if (ladderEndPoints[destinationGrid] != 0) {
          int ladderEndPoint = ladderEndPoints[destinationGrid];
          if (nextMinimumStep < minimumSteps[ladderEndPoint]) {
            minimumSteps[ladderEndPoint] = nextMinimumStep;
          }
        }
      }
    }
    if (minimumSteps[100] == 0) {
      return -1;
    } else {
      return minimumSteps[100];
    }
  }

  private static int[] convertLadderOrSnakeToOneDimensionArray(int[][] journeyPoints) {
    int[] endPoints = new int[101];
    Arrays.stream(journeyPoints)
        .forEach(journey -> {
          int start = journey[0];
          int end = journey[1];
          endPoints[start] = end;
        });
    return endPoints;
  }

  static int quickestWayUpBfs(int[][] ladders, int[][] snakes) {
    // This will be the method which we use BFS to build the graph and get the minimum steps
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
    final Scanner scanner = new Scanner("3\n" +
        "2\n" +
        "3 54\n" +
        "37 100\n" +
        "1\n" +
        "56 33\n" +
        "2\n" +
        "3 57\n" +
        "8 100\n" +
        "1\n" +
        "88 44\n" +
        "1\n" +
        "7 98\n" +
        "1\n" +
        "99 1");
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

      int result = quickestWayUpDP(ladders, snakes);
      System.out.println(result);
    }
  }
}
