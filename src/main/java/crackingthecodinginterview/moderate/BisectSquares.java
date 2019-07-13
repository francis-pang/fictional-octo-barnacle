package crackingthecodinginterview.moderate;

import java.util.Objects;

public class BisectSquares {
  public Line locateSquaresCuttingLine(Square firstSquare, Square secondSquare) {
    // Find out centre of first square
    Coordinate centreOfFirstSquare = calculateCentreOf(firstSquare);

    // Find out centre of second square
    Coordinate centreOfSecondSquare = calculateCentreOf(secondSquare);

    // Check if the line create cuts both square into half
    Line squaresCuttingLine = formulateLine(centreOfFirstSquare, centreOfSecondSquare);

    if (doesLineCutSquareIntoHalf(squaresCuttingLine, firstSquare) && // Check if the line cuts the first square into half
        doesLineCutSquareIntoHalf(squaresCuttingLine, secondSquare)) { // Check if the line cuts the second square into half
      return squaresCuttingLine;
    } else {
      return null;
    }
  }

  private boolean doesLineCutSquareIntoHalf(Line line, Square square) {
    double squareMinimumY = square.bottomLeft.y;
    double squareMaximumY = square.topLeft.y;
    double lineYPointAtRightSideX = line.gradient * square.bottomRight.x + line.yIntercept;
    double lineYPointAtLeftSideX = line.gradient * square.bottomLeft.x + line.yIntercept;
    double lengthOfSquare = square.topLeft.y - square.bottomLeft.y;
    if (isWithinRange(lineYPointAtRightSideX, squareMinimumY, squareMaximumY) &&
        isWithinRange(lineYPointAtLeftSideX, squareMinimumY, squareMaximumY) &&
        (lengthOfSquare == (lineYPointAtLeftSideX - square.bottomLeft.y) + (lineYPointAtRightSideX - square.topRight.y))) {
      return true;
    }

    double squareMinimumX = square.topLeft.x;
    double squareMaximumX = square.topRight.x;
    double lineXPointAtTopSideY = (square.topLeft.y - line.yIntercept) / line.gradient;
    double lineXPointAtBottomSideY = (square.bottomLeft.y - line.yIntercept) / line.gradient;
    return isWithinRange(lineXPointAtTopSideY, squareMinimumX, squareMaximumX) &&
        isWithinRange(lineXPointAtBottomSideY, squareMinimumX, squareMaximumX) &&
        (lengthOfSquare == (lineXPointAtBottomSideY - square.topLeft.x) + (lineXPointAtTopSideY - square.bottomLeft.x));
  }

  private boolean isWithinRange(double checkingNumber, double minimum, double maximum) {
    return ((checkingNumber >= minimum) && (checkingNumber <= maximum));
  }

  private Coordinate calculateCentreOf(Square square) {
    double topY = square.topLeft.y;
    double bottomY = square.bottomLeft.y;
    double midPointY = (topY + bottomY) / 2;

    double leftX = square.topLeft.x;
    double rightX = square.topRight.x;
    double midPointX = (leftX + rightX) / 2;

    return new Coordinate(midPointX, midPointY);
  }

  private Line formulateLine(Coordinate firstCoordinate, Coordinate secondCoordinate) {
    double gradient = (secondCoordinate.y - firstCoordinate.y) / (secondCoordinate.x - firstCoordinate.x);
    double yIntercept = secondCoordinate.y - (gradient * secondCoordinate.x);
    return new Line(gradient, yIntercept);
  }

  class Line {
    double gradient;
    double yIntercept;

    public Line(double gradient, double yIntercept) {
      this.gradient = gradient;
      this.yIntercept = yIntercept;
    }
  }

  class Square {
    Coordinate topLeft;
    Coordinate topRight;
    Coordinate bottomLeft;
    Coordinate bottomRight;

    public Square(Coordinate topLeft, Coordinate topRight, Coordinate bottomLeft, Coordinate bottomRight) {
      this.topLeft = topLeft;
      this.topRight = topRight;
      this.bottomLeft = bottomLeft;
      this.bottomRight = bottomRight;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Square)) return false;
      Square square = (Square) o;
      return topLeft.equals(square.topLeft) &&
          topRight.equals(square.topRight) &&
          bottomLeft.equals(square.bottomLeft) &&
          bottomRight.equals(square.bottomRight);
    }

    @Override
    public int hashCode() {
      return Objects.hash(topLeft, topRight, bottomLeft, bottomRight);
    }
  }

  class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y) {
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
}

