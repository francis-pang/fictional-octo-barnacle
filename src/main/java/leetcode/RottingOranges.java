package leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;

public class RottingOranges {
  private static final int IMPOSSIBLE = -1;
  private static final int FRESH = 1;
  private static final int ROTTEN = 2;

  public int orangesRotting(int[][] grid) {
    HashSet<Coordinate> freshOranges = new HashSet<>();
    // Collect all fresh orange and rotten oranges
    Queue<Coordinate> nextLevelQueue = new ArrayDeque<>();
    for (int row = 0; row < grid.length; row++) {
      for (int column = 0; column < grid[0].length; column++) {
        switch (grid[row][column]) {
          case FRESH:
            freshOranges.add(new Coordinate(row, column));
            break;
          case ROTTEN:
            nextLevelQueue.add(new Coordinate(row, column));
          default:
            // Do nothing
        }
      }
    }

    if (freshOranges.isEmpty()) {
      return 0;
    }

    HashSet<Coordinate> processedOranges = new HashSet<>();
    // Start to rot the fresh
    int minute = -1;
    while (!nextLevelQueue.isEmpty()) {
      Queue<Coordinate> currentLevelQueue = nextLevelQueue;
      nextLevelQueue = new ArrayDeque<>();
      minute++;
      while (!currentLevelQueue.isEmpty()) {
        Coordinate rottenOrangeLocation = currentLevelQueue.poll();
        freshOranges.remove(rottenOrangeLocation);
        if (freshOranges.isEmpty()) {
          return minute;
        }
        if (!processedOranges.add(rottenOrangeLocation)) {
          continue;
        }
        if (rottenOrangeLocation.x < grid.length - 1 && grid[rottenOrangeLocation.x + 1][rottenOrangeLocation.y] == FRESH) {
          nextLevelQueue.add(new Coordinate(rottenOrangeLocation.x + 1, rottenOrangeLocation.y));
        }
        if (rottenOrangeLocation.x > 0 && grid[rottenOrangeLocation.x - 1][rottenOrangeLocation.y] == FRESH) {
          nextLevelQueue.add(new Coordinate(rottenOrangeLocation.x - 1, rottenOrangeLocation.y));
        }
        if (rottenOrangeLocation.y < grid[0].length - 1 && grid[rottenOrangeLocation.x][rottenOrangeLocation.y + 1] == FRESH) {
          nextLevelQueue.add(new Coordinate(rottenOrangeLocation.x, rottenOrangeLocation.y + 1));
        }
        if (rottenOrangeLocation.y > 0 && grid[rottenOrangeLocation.x][rottenOrangeLocation.y - 1] == FRESH) {
          nextLevelQueue.add(new Coordinate(rottenOrangeLocation.x, rottenOrangeLocation.y - 1));
        }
      }
    }
    return IMPOSSIBLE;
  }

  class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Coordinate)) return false;
      Coordinate that = (Coordinate) o;
      return x == that.x &&
          y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static void main(String[] args) {
    RottingOranges rottingOranges = new RottingOranges();
    System.out.println(rottingOranges.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
  }
}
