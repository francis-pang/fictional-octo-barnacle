package crackingthecodinginterview.moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairsWithSum {
  public List<IntegerPair> getPairWithinArrayOfValue(int k, int[] array) {
    Map<Integer, Integer> valueByPositionMap = new HashMap<>();
    List<IntegerPair> answers = new ArrayList<>();
    for (int index = 0; index < array.length; index++) {
      int matchingNumber = k - array[index];
      if (valueByPositionMap.containsKey(matchingNumber)) {
        answers.add(new IntegerPair(array[index], matchingNumber));
        if (valueByPositionMap.get(matchingNumber) > 1) {
          valueByPositionMap.remove(matchingNumber);
        } else {
          valueByPositionMap.compute(matchingNumber, (key, value) -> value - 1);
        }
      } else {
        valueByPositionMap.compute(array[index], (key, value) -> (key == null) ? 1 : value + 1);
      }
    }
    return answers;
  }

  class IntegerPair {
    public int firstInteger;
    public int secondInteger;

    public IntegerPair(int firstInteger, int secondInteger) {
      this.firstInteger = firstInteger;
      this.secondInteger = secondInteger;
    }

    public IntegerPair() {
    }
  }
}
