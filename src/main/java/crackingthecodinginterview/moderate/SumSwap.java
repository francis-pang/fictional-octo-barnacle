package crackingthecodinginterview.moderate;

import java.util.HashSet;
import java.util.Set;

public class SumSwap {
  public IntegerPair swapThesePairForSameSum(int[] firstIntegerArray, int[] secondIntegerArray) {
    if (firstIntegerArray == null || firstIntegerArray.length == 0 ||
        secondIntegerArray == null || secondIntegerArray.length == 0) {
      return null;
    }

    Set<Integer> firstIntegerSet = new HashSet<>();
    int sumOfFirstArray = calculateTotalValueOf(firstIntegerArray, firstIntegerSet);

    Set<Integer> secondIntegerSet = new HashSet<>();
    int sumOfSecondArray = calculateTotalValueOf(secondIntegerArray, secondIntegerSet);
    if (firstIntegerSet.size() > secondIntegerSet.size()) {
      return findSwappingIntegers(secondIntegerSet, sumOfSecondArray, firstIntegerSet, sumOfFirstArray);
    } else {
      return findSwappingIntegers(firstIntegerSet, sumOfFirstArray, secondIntegerSet, sumOfSecondArray);
    }
  }

  private IntegerPair findSwappingIntegers(Set<Integer> smallerSet, int smallerSetTotal, Set<Integer> biggerSet,
                                           int biggerSetTotal) {
    for (Integer element : smallerSet) {
      int matchingNumber = biggerSetTotal + element - smallerSetTotal;
      if (biggerSet.contains(matchingNumber)) {
        return new IntegerPair(element, matchingNumber);
      }
    }
    return null;
  }

  private int calculateTotalValueOf(int[] array, Set<Integer> integerSet) {
    int sum = 0;
    for (int number : array) {
      sum += number;
      integerSet.add(number);
    }
    return sum;
  }

  class IntegerPair {
    public int firstInteger;
    public int secondInteger;

    public IntegerPair(int firstInteger, int secondInteger) {
      this.firstInteger = firstInteger;
      this.secondInteger = secondInteger;
    }
  }
}
