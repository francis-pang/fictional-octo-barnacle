package interview.palantir;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FloodFillTest {
  FloodFill floodFill = new FloodFill();

  @Test
  void findHighPoint_null() {
    int[][] expected = new int[][]{};
    assertArrayEquals(expected, floodFill.findHighPoint(null));
  }

  @Test
  void findHighPoint_zeroByZeroMap() {
    int[][] expected = new int[][]{};
    assertArrayEquals(expected, floodFill.findHighPoint(new int[][]{}));
  }

  @Test
  void findHighPoint_oneByOneMap() {
    int[][] expected = new int[][]{{1}};
    assertArrayEquals(expected, floodFill.findHighPoint(new int[][]{{1}}));
  }

  @Test
  void findHighPoint_oneByMultipleMap() {
    int[][] expected = new int[][]{{1}, {0}, {0}};
    assertArrayEquals(expected, floodFill.findHighPoint(new int[][]{{4}, {3}, {3}}));
  }

  @Test
  void findHighPoint_multipleByOneMap() {
    int[][] expected = new int[][]{{0, 0, 1}};
    assertArrayEquals(expected, floodFill.findHighPoint(new int[][]{{1, 2, 3}}));
  }

  @Test
  void findHighPoint_5x4Map() {
    int[][] expected = new int[][]
        {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}
        };

    int[][] question = new int[][]
        {
            {7, 7, 4, 14, 5},
            {6, 9, 8, 15, 13},
            {0, 0, 3, 13, 13},
            {1, 0, -4, 10, 9}
        };
    assertArrayEquals(expected, floodFill.findHighPoint(question));
  }

  @Test
  void findHighPoint_5x5Map() {
    int[][] expected = new int[][]
        {
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

    int[][] question = new int[][]
        {
            {1, 2, 1, 3, 4},
            {1, 5, 2, 2, 2},
            {4, 5, 1, 9, 7},
            {3, 5, 1, 7, 3},
            {4, 3, 1, 7, 3}
        };
    assertArrayEquals(expected, floodFill.findHighPoint(question));
  }

  @Test
  void markLowerGround_5x5Map() {
    int[][] expected = new int[][]
        {
            {0, 0, 2, 1, 1},
            {0, 0, 2, 2, 2},
            {0, 0, 2, 1, 1},
            {0, 0, 1, 1, 1},
            {0, 0, 1, 0, 1}
        };

    int[][] question = new int[][]
        {
            {1, 2, 1, 3, 4},
            {1, 5, 2, 2, 2},
            {4, 5, 1, 9, 7},
            {3, 5, 3, 7, 6},
            {4, 3, 1, 7, 3}
        };
    assertArrayEquals(expected, floodFill.findRiskScores(question));
  }

  @Test
  void markFloodArea() {
    int[][] expected = new int[][]
        {
            {0, 0, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1}
        };

    int[][] question = new int[][]
        {
            {5, 3, 9, 4},
            {12, 8, 7, 8},
            {12, 8, 14, 14}
        };
    assertArrayEquals(expected, floodFill.findHighPoints(question));
  }
}