package codesignal;

public class ParkingSpot {
  boolean parkingSpot(int[] carDimensions, int[][] parkingLot, int[] luckySpot) {
    final int OCCUPIED = 1;
    int carlength = carDimensions[0];
    int carWidth = carDimensions[1];
    int carLonger = Math.max(carlength, carWidth);

    int topLeftX = luckySpot[0];
    int topLeftY = luckySpot[1];
    int bottomRightX = luckySpot[2];
    int bottomRightY = luckySpot[3];
    int xLength = bottomRightX - topLeftX;
    int yLength = bottomRightY - topLeftY;
    int parkLonger = Math.max(xLength, yLength);

    // Check that car park lot is free
    for (int row = topLeftX; row <= bottomRightX; row++) {
      for (int column = topLeftY; column <= bottomRightY; column++) {
        if (parkingLot[row][column] == OCCUPIED) {
          return false;
        }
      }
    }

    boolean canPark = true;
    if (parkLonger == xLength) {
      // Check if can enter from top
      for (int row = 0; row < topLeftX; row++) {
        for (int column = topLeftY; column <= bottomRightY; column++) {
          if (parkingLot[row][column] == OCCUPIED) {
            canPark = false;
          }
        }
      }
      if (canPark) {
        return true;
      }

      // Check if can enter from bottom
      canPark = true;
      for (int row = parkingLot.length - 1; row > bottomRightX; row--) {
        for (int column = topLeftY; column <= bottomRightY; column++) {
          if (parkingLot[row][column] == OCCUPIED) {
            canPark = false;
          }
        }
      }
      if (canPark) {
        return true;
      }
    }

    if (parkLonger == yLength) {
      // Check if can enter from left
      canPark = true;
      for (int row = topLeftX; row <= bottomRightX; row++) {
        for (int column = 0; column <= topLeftX; column++) {
          if (parkingLot[row][column] == OCCUPIED) {
            canPark = false;
          }
        }
      }
      if (canPark) {
        return true;
      }

      // Check if can enter from right
      canPark = true;
      for (int row = topLeftX; row <= bottomRightX; row++) {
        for (int column = parkingLot[0].length - 1; column > bottomRightY; column--) {
          if (parkingLot[row][column] == OCCUPIED) {
            canPark = false;
          }
        }
      }
      if (canPark) {
        return true;
      }
    }
    return false;
  }
}
