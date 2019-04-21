package leetcode.amazon;

import java.util.PriorityQueue;

class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> minHeap = new PriorityQueue<>();

        for (int i = 0; i < K; i++) {
            Point point = new Point();
            point.coordinates = points[i];
            point.distance = calculateDistance(point.coordinates[0], point.coordinates[1]);
            minHeap.add(point);
        }

        for (int i = K; i < points.length; i++) {
            Point point = new Point();
            point.coordinates = points[i];
            point.distance = calculateDistance(point.coordinates[0], point.coordinates[1]);
            if (point.distance < minHeap.peek().distance) {
                minHeap.poll();
                minHeap.add(point);
            }
        }
        int[][] answer = new int[K][2];
        int i = 0;
        for (Point point : minHeap) {
            answer[i++] = point.coordinates;
        }
        return answer;
    }

    private int calculateDistance(int xCoordinate, int yCoordinate) {
        return (int) (Math.pow(xCoordinate, 2) + Math.pow(yCoordinate, 2));
    }

    class Point implements Comparable {
        public int[] coordinates;
        public int distance;

        @Override
        public int compareTo(Object o) {
            Point point = (Point) o;
            return point.distance - this.distance;
        }
    }
}
