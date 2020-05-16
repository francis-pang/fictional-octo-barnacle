package interview.goldmansachs;

import java.util.Queue;

/**
 * Game:
 * grid: int[][]
 * Stored Food location: (pre-populated) Coordinate[]
 * currentFoodLocation: coordinate
 * <p>
 * e.g North
 * - nextLocation = row - 1
 * <p>
 * snake
 * currentDirection : direction
 * currentLocation: coorindate
 * <p>
 * changeDirection(new direction)
 * <p>
 * Direction [enum]
 * N, S, E, W
 * <p>
 * Coordinate
 * int row
 * int column
 */
public class SnakeGame {
  class Coordinate {
    public int row;
    public int column;

    // Assume override equal
  }

  enum Direction {
    NORTH, SOUTH, EAST, WEST
  }

  class Snake {
    public Queue<Coordinate> occupiedLocation;
    public Coordinate headCo;
    public Direction direction;
    public int length;


    void move(Coordinate food) {
      Coordinate newHeadCo = null;
      switch (direction) {
        case NORTH:
          // row - 1
          if (headCo.row == 0) {
            // throw exception
          }
          newHeadCo = new Coordinate();
          newHeadCo.column = headCo.column;
          newHeadCo.row = headCo.row - 1;
          break;
        case SOUTH:
          break;
        case EAST:
          break;
        case WEST:
          break;
      }

      // What if the new snake head location lands on a part of its body?
      if (occupiedLocation.contains(newHeadCo)) { // existing is O(n) <- LinkedHashMap O(1)
        // do something
      }

      if (newHeadCo.equals(food)) {
        length++;
      } else {
        occupiedLocation.poll();
      }
      headCo = newHeadCo;
      occupiedLocation.add(newHeadCo);
    }
  }

  class Game {
    public int[][] grid;
    public Coordinate[] foodLocation;
    public Coordinate currentFoodLocation;
    public Snake snake;

    void changeDirection(Direction direction) {
      snake.direction = direction;
    }

  }
}
