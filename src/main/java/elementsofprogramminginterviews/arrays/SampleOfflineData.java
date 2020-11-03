package elementsofprogramminginterviews.arrays;

import java.util.HashSet;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class SampleOfflineData {
    public int[] generateRandomSubSet(int[] array, int size) {
        Random randomGenerator = new Random();
        Set<Integer> generatedNumbers = new HashSet<>();
        IntStream randomPositionStream = randomGenerator.ints(0, array.length);
        PrimitiveIterator.OfInt iterator = randomPositionStream.iterator();
        int[] result = new int[size];
        while (generatedNumbers.size() < size) {
            int randomPosition = iterator.nextInt();
            if (generatedNumbers.add(array[randomPosition])) {
                result[generatedNumbers.size() - 1] = array[randomPosition];
            }
        }
        return result;
    }
}
