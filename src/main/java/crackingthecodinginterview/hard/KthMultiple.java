package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7. Note that 3, 5, and 7
 * do not have to be factors, but it should not have any other prime factors. For example, the first several
 * multiples would be (in order) 1, 3, 5,7,9, 15, 21.
 */
public class KthMultiple {
  private static final int FIRST_NUMBER = 1;
  private static final int ERROR_NUMBER = 0;
  private List<Integer> multiples;

  public int kNumber(List<Integer> primeFactors, int k) {
    Collections.sort(primeFactors);
    multiples = new ArrayList<>();
    int[] multiples = new int[Math.max(k, primeFactors.size() + 1)];

    // First number
    if (k == 1) {
      return FIRST_NUMBER;
    }
    multiples[0] = 1;

    // Initialise first set of prime
    for (int i = 0; i < primeFactors.size(); i++) {
      multiples[1 + i] = primeFactors.get(i);
    }

    if (primeFactors.isEmpty()) {
      return ERROR_NUMBER;
    }

    return getKMultiple(multiples, k, primeFactors);
  }

  private int getKMultiple(int[] array, int index, List<Integer> primeFactors) {
    if (index <= primeFactors.size() + 1) {
      return array[index - 1];
    }
    int position = index / primeFactors.size();
    if (array[position] == 0) {
      array[position] = getKMultiple(array, position, primeFactors);
    }
    int reminder = (index - 2) % primeFactors.size();
    return array[position] * primeFactors.get(reminder);
  }
}
