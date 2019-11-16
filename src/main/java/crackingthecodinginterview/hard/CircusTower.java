package crackingthecodinginterview.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircusTower {
  private Map<IntegerPair, Integer> longestSequenceAtIntegerPair;
  public int computeHighestTowerHeight(List<Person> circus) {
    longestSequenceAtIntegerPair = new HashMap<>();
    Collections.sort(circus);
    int maxLength = 0;
    for (int i = 0; i < circus.size(); i++) {
      // Add 1 here, because we have chosen the first person
      int length = getLongestSequence(i, i + 1, circus) + 1;
      if (length > maxLength) {
        maxLength = length;
      }
    }
    return maxLength;
  }

  private int getLongestSequence(int comparingIndex, int iteratingIndex, List<Person> circus) {
    IntegerPair integerPair = new IntegerPair(comparingIndex, iteratingIndex);
    if (longestSequenceAtIntegerPair.containsKey(integerPair)) {
      return longestSequenceAtIntegerPair.get(integerPair);
    }
    while (iteratingIndex < circus.size() &&
        !circus.get(iteratingIndex).canBeBelow(circus.get(comparingIndex))) {
      iteratingIndex++;
    }

    if (iteratingIndex == circus.size()) { // This mean that there is no more person who can be below
      return 0;
    } else if (iteratingIndex == circus.size() - 1) { // The last person can be below
      return 1;
    } else { // This is not the last person, and that person can be below
      int longestSequence = Math.max(
          getLongestSequence(iteratingIndex, iteratingIndex + 1, circus) + 1, // Take the person
          getLongestSequence(comparingIndex, iteratingIndex + 1, circus)// Don't take the person
      );
      longestSequenceAtIntegerPair.put(integerPair, longestSequence);
      return longestSequence;
    }
  }

  private class IntegerPair {
    public int firstNumber;
    public int secondNumber;

    public IntegerPair(int firstNumber, int secondNumber) {
      this.firstNumber = firstNumber;
      this.secondNumber = secondNumber;
    }
  }

  static class Person implements Comparable<Person> {
    public int height;
    public int weight;

    public Person(int height, int weight) {
      this.height = height;
      this.weight = weight;
    }

    public boolean canBeBelow(Person person) {
      return (this.weight > person.weight && this.height > person.height);
    }

    @Override
    public int compareTo(Person o) {
      if (this == o) {
        return 0;
      }

      if (!(o instanceof Person)) {
        return -1;
      }

      Person that = o;
      if (this.height == that.height &&
          this.weight == that.weight) {
        return 0;
      } else if (this.height < that.height) {
        return -1;
      } else if (this.height > that.height) {
        return 1;
      } else if (this.weight < that.weight) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
