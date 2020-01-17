package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductOfArrayExceptSelf {
  public int[] productExceptSelf(int[] array) {
    Map<Range, Integer> t = new HashMap<>();
    int[] ans = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      t.put(new Range(i, i), array[i]);
    }
    for (int i = 0; i < array.length; i++) {
      int answer = productOfArray(array, t, i);
      ans[i] = answer;
    }
    return ans;
  }

  private int productOfArray(int[] array, Map<Range, Integer> t, int index) {
    int leftP = 1;
    if (index > 0) {
      Range leftR = new Range(0,  index - 1);
      leftP = calculateP(t, leftR, array);
    }
    int rightP = 1;
    if (index < array.length- 1) {
      Range rightR = new Range(index + 1, array.length -  1);
      rightP = calculateP(t, rightR, array);
    }
    return leftP *rightP;
  }

  private int calculateP(Map<Range, Integer> t, Range r, int[] array) {
    if (t.containsKey(r)) {
      return t.get(r);
    }
    int left = r.left;
    int p;
    if (left  == 0) {
      p = array[r.right] * calculateP(t, new Range(r.left, r.right  - 1), array);
    } else {
      p = array[r.left] * calculateP(t, new Range(r.left + 1, r.right), array);
    }
    t.put(r, p);
    return p;
  }

  public class Range {
    public int left;
    public int right;

    public Range(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Range range = (Range) o;
      return left == range.left &&
          right == range.right;
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }
}
