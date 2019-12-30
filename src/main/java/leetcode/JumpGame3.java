package leetcode;

import java.util.HashSet;
import java.util.Set;

public class JumpGame3 {
  public boolean canReach(int[] array, int start) {
    Set<Integer> visited = new HashSet<>();
    return vAll(array, start, visited);
  }

  public boolean vAll(int[] array, int index, Set<Integer> visited) {
    visited.add(index);
    int value = array[index];
    if (value == 0) {
      return true;
    }
    int posPath = value + index;
    boolean result = false;
    if (posPath < array.length && !visited.contains(posPath)) {
      result = vAll(array, posPath, visited);
    }
    if (result) {
      return true;
    }
    int negPath = index - value;
    if (negPath >= 0 && !visited.contains(negPath)) {
      return vAll(array, negPath, visited);
    }
    visited.remove(index);
    return false;
  }
}
