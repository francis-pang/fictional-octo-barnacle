package crackingthecodinginterview.moderate;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Given two arrays of integers, compute the pair of values (one value in each
 * array) with the smallest (non-negative) difference. Return the difference.
 */
public class SmallestDifference {
  private int smallestDifference;
  private Pair smallestDifferencePair;

  public Pair computeSmallestDifferencePair(List<Integer> listA, List<Integer> listB) {
    // Sorting O(a * log(a))
    Collections.sort(listA);
    Collections.reverse(listA);

    // Sorting O(b * log(b))
    Collections.sort(listB);
    Collections.reverse(listB);
    smallestDifference = Integer.MAX_VALUE;
    findSmallestDifferent(listA.listIterator(), listB.listIterator(), listB.listIterator());
    return smallestDifferencePair;
  }

  private void findSmallestDifferent(ListIterator<Integer> pivotListIterator,
                                     ListIterator<Integer> fasterListIterator,
                                     ListIterator<Integer> slowerListIterator) {
    Integer pivotInteger = pivotListIterator.next();
    Integer movingInteger = fasterListIterator.next();
    Integer previousMovingInteger = fasterListIterator.previous();
    while (pivotInteger < movingInteger && movingInteger != null) {
      previousMovingInteger = movingInteger;
      if (previousMovingInteger != null) {
        compareForSmallerDifferent(pivotInteger, previousMovingInteger);
      }
      movingInteger = fasterListIterator.next();
      slowerListIterator.next(); //Slower iterator will always be one step slower than the fast one
    }
    if (previousMovingInteger != null) {
      compareForSmallerDifferent(pivotInteger, previousMovingInteger);
    }
    if (movingInteger == null) {
      return;
    }
    findSmallestDifferent(slowerListIterator, pivotListIterator, pivotListIterator);
  }

  private void compareForSmallerDifferent(int a, int b) {
    int difference = Math.abs(a - b);
    if (difference < smallestDifference) {
      smallestDifferencePair = new Pair(a, b);
    }
  }


  public static class Pair {
    private int a;
    private int b;

    public Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      Pair pair = (Pair) o;
      if ((a == pair.a && b == pair.b) ||
          (a == pair.b && b == pair.a)) {
        return true;
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
          .add("a=" + a)
          .add("b=" + b)
          .toString();
    }
  }
}
