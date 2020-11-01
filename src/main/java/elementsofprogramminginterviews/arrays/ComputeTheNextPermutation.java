package elementsofprogramminginterviews.arrays;

import java.util.Arrays;
import java.util.List;

public class ComputeTheNextPermutation {
    public int[] computeTheNextPermutation(int[] permutation) {
        if (permutation.length < 2) {
            return new int[]{};
        }
        Direction direction = (permutation[1] > permutation[0]) ? Direction.ASCENDING : Direction.DESCENDING;
        int startOfSeries = 0;
        for (int i = 2; i < permutation.length; i++) {
            switch (direction) {
                case ASCENDING:
                    if (permutation[i] < permutation[i - 1]) {
                        direction = Direction.DESCENDING;
                        startOfSeries = i - 1;
                    }
                    break;
                case DESCENDING:
                    if (permutation[i] > permutation[i - 1]) {
                        direction = Direction.ASCENDING;
                        startOfSeries = i - 1;
                    }
                    break;
            }
        }
        if (direction == Direction.ASCENDING) {
            swap(permutation, startOfSeries, startOfSeries + 1);
        } else if (direction == Direction.DESCENDING && startOfSeries == 0) {
            return new int[]{};
        } else { // DESCENDING
            int valueToSwap = permutation[startOfSeries - 1];
            int firstIndexSmallerThanValue = startOfSeries;
            for (; firstIndexSmallerThanValue < permutation.length; firstIndexSmallerThanValue++) {
                if (permutation[firstIndexSmallerThanValue] < valueToSwap) {
                    break;
                }
            }
            firstIndexSmallerThanValue -= 1;
            swap(permutation, startOfSeries - 1, firstIndexSmallerThanValue);
            swapToEnd(permutation, startOfSeries);
        }
        return permutation;
    }

    public void swapToEnd(int[] array, int start) {
        for (int i = 0; i + start < array.length - ((array.length - start) / 2); i++) {
            swap(array, start + i, array.length - 1 - i);
        }
    }

    public void swap(int[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    enum Direction {
        ASCENDING, DESCENDING
    }

    public static void main(String[] args) {
        int[] permutation = new int[]{7, 13, 1, 6, 90, 60, 10, 9, 8};
        ComputeTheNextPermutation computeTheNextPermutation = new ComputeTheNextPermutation();
        int[] result = computeTheNextPermutation.computeTheNextPermutation(permutation);
        System.out.println(Arrays.toString(result));
    }
}
