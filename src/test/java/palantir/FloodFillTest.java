package palantir;

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
}