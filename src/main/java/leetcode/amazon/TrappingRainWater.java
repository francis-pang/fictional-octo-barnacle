package leetcode.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrappingRainWater {
    public int trap(int[] height) {
        // Find first non zero point as starting point
        int startPosition = -1;
        do {
            startPosition++;
        } while (startPosition < height.length && height[startPosition] == 0);
        if (startPosition == height.length) {
            return 0;
        }
        int endPosition = height.length;
        do {
            endPosition--;
        } while (endPosition >= 0 && height[endPosition] == 0);
        return trapWaterBetweenRange(height, startPosition, endPosition);
    }

    private int trapWaterBetweenRange(int[] height, int start, int end) {
        System.out.println("Iterating from start " + start + " to end " + end);
        // Maintain 2 arrays to store the all the positions of the 2 highest elevation
        List<Integer> firstHighestElevationList = new ArrayList<Integer>();
        List<Integer> secondHighestElevationList = new ArrayList<Integer>();
        int firstMaxHeight = 1;
        int secondMaxHeight = 1;

        /*
         * Iterate through each element in the height array within the given range, to locate the 2 highest points
         * across the graph. There can be a few cases:
         * 1. Exactly 2 highest points of same height
         * 2. More than 2 highest points of same height
         * 3. One point of highest height and one point of 2nd highest height
         * 4. One point of highest height and multiple points of 2nd highest height
         */
        for (int position = start; position <= end; position++) {
            int barHeight = height[position];
            if (barHeight > firstMaxHeight) {
                firstMaxHeight = barHeight;
                firstHighestElevationList.clear();
                firstHighestElevationList.add(position);
            } else if (barHeight == firstMaxHeight) {
                firstHighestElevationList.add(position);
            }
        }

        System.out.println("Highest point found = " + firstMaxHeight + ". 2nd highest point found = " + secondMaxHeight);

        int totalWaterTrap = 0;
        switch (firstHighestElevationList.size()) {
            case 0:
                break;
            case 1: // Only 1 highest point found, so need to combine 2 list
                for (int position = start; position <= end; position++) {
                    int barHeight = height[position];
                    if (barHeight > secondMaxHeight && barHeight != firstMaxHeight) {
                        secondMaxHeight = barHeight;
                        secondHighestElevationList.clear();
                        secondHighestElevationList.add(position);
                    } else if (barHeight == secondMaxHeight) {
                        secondHighestElevationList.add(position);
                    }
                }
                firstHighestElevationList.addAll(secondHighestElevationList);
                Collections.sort(firstHighestElevationList);
            default: // 2 or more
                Integer[] firstHighestElevations = new Integer[firstHighestElevationList.size()];
                firstHighestElevations = firstHighestElevationList.toArray(firstHighestElevations);
                System.out.println("All the highest points are " + firstHighestElevationList.toString());

                if (firstHighestElevations[0] != start) {
                    System.out.println("Looping through the uncovered start from " + start + " to " + firstHighestElevations[0]);
                    totalWaterTrap += trapWaterBetweenRange(height, start, firstHighestElevations[0]);
                }

                for (int i = 0; i < firstHighestElevations.length - 1; i++) {
                    int leftHighPointPosition = firstHighestElevations[i];
                    int rightHighPointPosition = firstHighestElevations[i + 1];
                    for (int diffIndex = leftHighPointPosition + 1; diffIndex < rightHighPointPosition; diffIndex++) {
                        System.out.println("Adding " + (Math.min(height[leftHighPointPosition], height[rightHighPointPosition]) - height[diffIndex]) + " to the total");
                        totalWaterTrap += Math.min(height[leftHighPointPosition], height[rightHighPointPosition]) - height[diffIndex];
                    }
                }

                if (firstHighestElevations[firstHighestElevations.length - 1] != end) {
                    System.out.println("Looping through the uncovered last part from " + firstHighestElevations[firstHighestElevations.length - 1] + " to " + end);
                    totalWaterTrap += trapWaterBetweenRange(height,
                            firstHighestElevations[firstHighestElevations.length - 1], end);
                }
                break;
        }
        return totalWaterTrap;
    }
}