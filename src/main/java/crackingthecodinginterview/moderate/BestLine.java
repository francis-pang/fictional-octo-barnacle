package crackingthecodinginterview.moderate;

import java.util.List;

public class BestLine {
  public Line locateBestLine(List<Point> points) {
    boolean[][] visitedPoints = new boolean[points.size()][points.size()];
    int maxConnectedPoint = 0;
    Line longestLine = null;
    // Clear out the self joining point
    for (int row = 0; row < points.size(); row++) {
      for (int column = row + 1; column < points.size(); column++) {
        if (visitedPoints[row][column] == false) {
          Line line = formulateLine(points.get(row), points.get(column));
          visitedPoints[row][column] = true;
          int connectedPoints = 2;
          for (int remaining = column + 1; remaining < points.size(); remaining++) {
            if (isPointOnTheLine(points.get(remaining), line)) {
              connectedPoints++;
              visitedPoints[row][remaining] = true;
              visitedPoints[column][remaining] = true;
            }
          }
          if (connectedPoints > maxConnectedPoint) {
            longestLine = line;
            maxConnectedPoint = connectedPoints;
          }
        }
      }
    }
    return longestLine;
  }

  private boolean isPointOnTheLine(Point point, Line line) {
    double yOnTheLineGivenXCoordinate = (line.gradient * point.x) + line.yIntercept;
    return yOnTheLineGivenXCoordinate == point.y;
  }

  private Line formulateLine(Point firstPoint, Point secondPoint) {
    double gradient;
    if (firstPoint.x - secondPoint.x == 0) {
      gradient = (firstPoint.y - secondPoint.y) / (firstPoint.x);
    } else {
      gradient = (firstPoint.y - secondPoint.y) / (firstPoint.x - secondPoint.x);
    }

    double yIntercept = firstPoint.y - (gradient * firstPoint.x);
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

  class Point {
    double x;
    double y;
  }
}
