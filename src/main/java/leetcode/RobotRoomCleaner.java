package leetcode;

import java.util.BitSet;
import java.util.Stack;

/**
 * Given a robot cleaner in a room modeled as a grid.
 * Each cell in the grid can be empty or blocked.
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * <p>
 * Notes:
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem
 * "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room
 * layout and the initial robot's position.
 * The robot's initial position will always be in an accessible cell.
 * The initial direction of the robot will be facing up.
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * Assume all four edges of the grid are all surrounded by wall.
 */
public class RobotRoomCleaner {
  // This is the robot's control interface.
  // You should not implement it, or speculate about its implementation
  interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();

    void turnRight();

    // Clean the current cell.
    void clean();
  }

  static class Solution {
    private final Direction INITIAL_FACING_DIRECTION = Direction.UP;
    private Robot robot;
    private Stack<Cell> cleaningPath; // Stores all the path that has not been cleaned before

    public void cleanRoom(Robot robot) {
      this.robot = robot;
      cleaningPath = new Stack<>();
      // Initially, the robot is facing UP in an accessible cell, so let's create the cell
      Cell startCell = new Cell();
      cleaningPath.push(startCell);
      Direction endingDirection = clearACell(startCell, INITIAL_FACING_DIRECTION);
      while (!cleaningPath.isEmpty()) {
        endingDirection = clearACell(cleaningPath.pop(), endingDirection);
      }
    }

    /**
     * Clean the cell and attempted to move to all its neigbour
     *
     * @param cell              the cell in the grid to clear, i.e making sure all 4 direction is cleaned
     * @param startingDirection the starting facing direction
     * @return ending facing direction after the cell is cleared
     */
    private Direction clearACell(Cell cell, final Direction startingDirection) {
      Direction currectDirection = startingDirection;
      robot.clean();
      while (!cell.isAllNeighbourVisited()) {
        Direction unvisitedDirection = cell.getNextUnvisitedDirection(currectDirection);
        if (unvisitedDirection == null) {
          return currectDirection;
        }
        if (!unvisitedDirection.equals(currectDirection)) {
          turnToFaceDirection(currectDirection, unvisitedDirection, robot);
          currectDirection = unvisitedDirection;
        }
        Cell newCell = null;
        if (robot.move()) {
          newCell = new Cell();
          cleaningPath.push(newCell);
          newCell.visitNeigbour(currectDirection.reverseDirection(), cell);
          clearACell(newCell, currectDirection);
        }
        cell.visitNeigbour(currectDirection, newCell);
      }
      return currectDirection;
    }

    private void turnToFaceDirection(Direction sourceDirection, Direction targetDirection, Robot robot) {
      System.out.println("Turning direction now." +
          "sourceDirection=" + (sourceDirection == null ? "Null" : sourceDirection.name()) +
          "; targetDirection=" + ((targetDirection == null) ? "null" : targetDirection.name()) +
          ".");
      switch (sourceDirection) {
        case LEFT:
          switch (targetDirection) {
            case UP:
              robot.turnRight();
              break;
            case RIGHT:
              robot.turnRight();
              robot.turnRight();
              break;
            case DOWN:
              robot.turnLeft();
              break;
          }
          break;
        case UP:
          switch (targetDirection) {
            case LEFT:
              robot.turnLeft();
              break;
            case RIGHT:
              robot.turnRight();
              break;
            case DOWN:
              robot.turnRight();
              robot.turnRight();
              break;
          }
          break;
        case RIGHT:
          switch (targetDirection) {
            case LEFT:
              robot.turnRight();
              robot.turnRight();
              break;
            case UP:
              robot.turnLeft();
              break;
            case DOWN:
              robot.turnRight();
              break;
          }
          break;
        case DOWN:
          switch (targetDirection) {
            case LEFT:
              robot.turnRight();
              break;
            case RIGHT:
              robot.turnLeft();
              break;
            case UP:
              robot.turnRight();
              robot.turnRight();
              break;
          }
          break;
      }
    }

    /**
     * Lists all possible relative position of a neighbouring cell to this particular cell
     */
    public enum Direction {
      LEFT(0), UP(1), RIGHT(2), DOWN(3);

      private int directionBit;

      Direction(int directionBit) {
        this.directionBit = directionBit;
      }

      public int getDirectionBit() {
        return directionBit;
      }

      public static Direction resolve(int directionBit) {
        for (Direction direction : values()) {
          if (directionBit == direction.directionBit) {
            return direction;
          }
        }
        return null;
      }

      public Direction reverseDirection() {
        switch (Direction.resolve(this.directionBit)) {
          case LEFT:
            return RIGHT;
          case UP:
            return DOWN;
          case RIGHT:
            return LEFT;
          case DOWN:
            return UP;
          default:
            return null;
        }
      }
    }

    /**
     * This represent a cell in the grid
     */
    public class Cell {
      private static final int NUMBER_OF_NEIGHBOURS = 4;
      public Cell leftNeighbour;
      public Cell rightNeighbour;
      public Cell aboveNeighbour;
      public Cell belowNeighbour;
      public BitSet vistedNeighbours;

      public Cell() {
        vistedNeighbours = new BitSet(NUMBER_OF_NEIGHBOURS);
      }

      public boolean isAllNeighbourVisited() {
        return vistedNeighbours.cardinality() == NUMBER_OF_NEIGHBOURS;
      }

      public void visitNeigbour(Direction direction, Cell neighbour) {
        if (neighbour != null) {
          switch (direction) {
            case UP:
              aboveNeighbour = neighbour;
              break;
            case DOWN:
              belowNeighbour = neighbour;
              break;
            case LEFT:
              leftNeighbour = neighbour;
              break;
            case RIGHT:
              rightNeighbour = neighbour;
              break;
          }
        }
        vistedNeighbours.set(direction.getDirectionBit());
      }

      public Direction getNextUnvisitedDirection(Direction currentFacingDirection) {
        int nextClearBit = vistedNeighbours.nextClearBit(currentFacingDirection.getDirectionBit());
        if (nextClearBit > NUMBER_OF_NEIGHBOURS) {
          nextClearBit = vistedNeighbours.previousClearBit(currentFacingDirection.getDirectionBit());
        }
        return Direction.resolve(nextClearBit);
      }
    }
  }
}
