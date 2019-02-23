package leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

class CutOffTreesforGolfEvent {
    // Used to store the shortest path between 2 point
    Map<Pair<TreeCoordinate, TreeCoordinate>, Integer> shortestDistanceBetweenPoints = new HashMap<>();
    int[][] forestArr;

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return -1;
        }

        // Convert list of list into 2D array
        // Put height of tree into sorted map, so that we can plot the travelling path later
        // Convert to array = O(n)
        forestArr = new int[forest.size()][];
        SortedMap<Integer, TreeCoordinate> treeHeightMap = new TreeMap<>();
        for (int row = 0; row < forest.size(); row++) {
            List<Integer> rowList = forest.get(row);
            forestArr[row] = new int[rowList.size()];
            for(int column = 0; column < rowList.size(); column++) {
                forestArr[row][column] = rowList.get(column);
                if (forestArr[row][column] > 0) {
                    treeHeightMap.put(forestArr[row][column], new TreeCoordinate(row, column));
                }
            }
        }

        // Change strategy: Pre-compute the shortest path for whole map
        populateAllPairsShortestPath();

        // Calculate distance to each point
        int totalTravelDistance = 0;
        TreeCoordinate currentPosition = new TreeCoordinate(0,0);
        for (Map.Entry<Integer, TreeCoordinate> treeHeightMapEntry: treeHeightMap.entrySet()) {
            Pair<TreeCoordinate, TreeCoordinate> path = new Pair<>(currentPosition, treeHeightMapEntry.getValue());
            int shortestDistance = 0;
            try {
                shortestDistance = shortestDistanceBetweenPoints.get(new Pair<>(currentPosition, treeHeightMapEntry.getValue()));
            } catch (NullPointerException ex) {
                System.out.println("Can't find distance between " + currentPosition.toString() + " and " + treeHeightMapEntry.getValue().toString());
            }
            if (shortestDistance == -1) {
                return -1;
            } else {
                totalTravelDistance += shortestDistance;
                currentPosition = treeHeightMapEntry.getValue();
            }
        }

        return totalTravelDistance;
    }

    private void populateAllPairsShortestPath() {
        // Using Floyd-Warshall algorithm
        // Initialisation stage

        // Set distance to self 0
        for (int row = 0; row < forestArr.length; row++) {
            for (int column = 0; column < forestArr.length; column++) {
                shortestDistanceBetweenPoints.put(new Pair<>(new TreeCoordinate(row, column), new TreeCoordinate(row,
                        column)), 0);
            }
        }

        // Set all possible edge either 1 or -1

    }

    private void insertNewDistanceBetween(TreeCoordinate source, TreeCoordinate destination, int preShortestDistance) {
        Integer shortestDistance = shortestDistanceBetweenPoints.get(new Pair<>(source, destination));
        if ((forestArr[destination.x][destination.y] == 0 || forestArr[source.x][source.y] == 0) ) {
            shortestDistance = -1;
        }  else if (preShortestDistance == -1) { //last known path is invalid
            if (shortestDistance == null) { // No known path yet
                shortestDistance = -1;
            } else {
                shortestDistance = shortestDistance; // remained, no change
            }
        } else { // last known path is valid
            if (shortestDistance == null) {
                shortestDistance = preShortestDistance + 1;
            } else {
                shortestDistance = Math.min(shortestDistance, preShortestDistance + 1);
            }
        }
        shortestDistanceBetweenPoints.put(new Pair<>(source, destination), shortestDistance);
        shortestDistanceBetweenPoints.put(new Pair<>(destination, source), shortestDistance);
    }


    class Pair<E, F> {
        public E firstElement;
        public F secondElement;

        public Pair(E firstElement, F secondElement) {
            this.firstElement = firstElement;
            this.secondElement = secondElement;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(firstElement, pair.firstElement) &&
                    Objects.equals(secondElement, pair.secondElement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstElement, secondElement);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
                    .add("firstElement=" + firstElement)
                    .add("secondElement=" + secondElement)
                    .toString();
        }
    }

    class TreeCoordinate {
        public int x;
        public int y;

        public TreeCoordinate() {
        }

        public TreeCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeCoordinate)) return false;
            TreeCoordinate that = (TreeCoordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TreeCoordinate.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("y=" + y)
                    .toString();
        }
    }

    public static void main(String[] args) {
        CutOffTreesforGolfEvent cutOffTreesforGolfEvent = new CutOffTreesforGolfEvent();
        //[[54581641,64080174,24346381,69107959],[86374198,61363882,68783324,79706116],[668150,92178815,89819108,94701471],[83920491,22724204,46281641,47531096],[89078499,18904913,25462145,60813308]]
        List<Integer> row1 = new ArrayList<>();
        row1.add(54581641);
        row1.add(64080174);
        row1.add(24346381);
        row1.add(69107959);
        List<Integer> row2 = new ArrayList<>();
        row2.add(86374198);
        row2.add(61363882);
        row2.add(68783324);
        row2.add(79706116);
        List<Integer> row3 = new ArrayList<>();
        row3.add(668150);
        row3.add(92178815);
        row3.add(89819108);
        row3.add(94701471);
        List<Integer> row4 = new ArrayList<>();
        row4.add(83920491);
        row4.add(22724204);
        row4.add(46281641);
        row4.add(47531096);
        List<Integer> row5 = new ArrayList<>();
        row5.add(89078499);
        row5.add(18904913);
        row5.add(25462145);
        row5.add(60813308);

        List<List<Integer>> forest = new ArrayList<>();
        forest.add(row1);
        forest.add(row2);
        forest.add(row3);
        System.out.println(cutOffTreesforGolfEvent.cutOffTree(forest));
    }
}