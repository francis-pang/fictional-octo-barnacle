package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a method to return all subsets of a set.
 * Answer is in {@link leetcode.Subsets}
 */
public class PowerSet {
  public Set<Set<Integer>> powerSet(Set<Integer> set) {
    if (set.isEmpty()) {
      return new HashSet<>();
    }
    final Set<Set<Integer>> combinedSet = new HashSet<>();
    final Iterator<Integer> iterator = set.iterator();
    final int element = iterator.next();
    set.remove(element);
    Set<Set<Integer>> spiltSet = powerSet(set);
    for (Set<Integer> smallerSet : spiltSet) {
      smallerSet.add(element);
      combinedSet.add(smallerSet);
    }
    return combinedSet;
  }
}
