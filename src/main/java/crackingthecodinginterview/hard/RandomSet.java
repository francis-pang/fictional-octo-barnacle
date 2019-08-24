package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSet {
  public List<Integer> generateIntegerFromArray(int numberOfPick, int[] array) {
    List<Integer> picks = new ArrayList<>();
    for (int i = 0; i < numberOfPick; i++) {
      picks.add(array[ThreadLocalRandom.current().nextInt(0, array.length)]);
    }
    return picks;
  }
}
