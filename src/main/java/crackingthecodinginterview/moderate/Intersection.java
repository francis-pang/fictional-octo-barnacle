package crackingthecodinginterview.moderate;

public class Intersection {
  public Coordinate computePointOfIntersection(Coordinate firstLineStartPoint, Coordinate firstLineEndPoint,
                                               Coordinate secondLineStartPoint, Coordinate secondLineEndPoint) {
    Line firstLine = Line.with(firstLineStartPoint, firstLineEndPoint);
    Line secondLine = Line.with(secondLineStartPoint, secondLineEndPoint);
    float xCoordinate = 0;
    try {
      xCoordinate = calculateXCoordinate(firstLine, secondLine);
    } catch (ArithmeticException ex) {
      return null;
    }
    float yCoordinate = firstLine.getYCoordinate(xCoordinate);
    return new Coordinate(xCoordinate, yCoordinate);
  }

  private float calculateXCoordinate(Line firstLine, Line secondLine) {
    return (secondLine.yIntercept - firstLine.yIntercept) / (firstLine.gradient - secondLine.gradient);
  }

  public static class Line {
    public float gradient;
    public float yIntercept;

    public Line(float gradient, float yIntercept) {
      this.gradient = gradient;
      this.yIntercept = yIntercept;
    }

    public float getYCoordinate(float xCoordinate) {
      return 0;
    }

    public static Line with(Coordinate firstCoordinate, Coordinate secondCoordinate) {
      float gradient = calculateGradient(firstCoordinate, secondCoordinate);
      float yIntercept = calculateYIntercept(firstCoordinate, gradient);
      return new Line(gradient, yIntercept);
    }

    private static float calculateGradient(Coordinate firstCoordinate, Coordinate secondCoordinate) {
      float denominator = firstCoordinate.x - secondCoordinate.x;
      return (denominator == 0) ? 0 : ((firstCoordinate.y - secondCoordinate.y) / denominator);
    }

    private static float calculateYIntercept(Coordinate coordinate, float gradient) {
      return coordinate.y - gradient * coordinate.x;
    }
  }

  public class Coordinate {
    public float x;
    public float y;

    public Coordinate(float x, float y) {
      this.x = x;
      this.y = y;
    }
  }
}
