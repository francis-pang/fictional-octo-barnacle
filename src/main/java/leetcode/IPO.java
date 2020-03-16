package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IPO {
  public int findMaximizedCapital(int k, int cap, int[] profits, int[] costs) {
    Map<Set<Integer>, Integer> gains = new HashMap<>();
    Set<Integer> path = new HashSet<>();
    return maxPath(gains, cap, k, profits, costs, path);
  }

  private int maxPath(Map<Set<Integer>, Integer> gains, int cap, int k, int[] profits, int[] costs, Set<Integer> path) {
    if (gains.containsKey(path)) {
      return gains.get(path);
    }
    if (k == 0) {
      return cap;
    }
    int chosen = -1;
    int biggestCap = cap;
    for (int i = 0; i < costs.length; i++) {
      if (path.contains(i)) {
        continue;
      }
      int cost = costs[i];
      if (cost > cap) {
        continue;
      }
      int pCap = cap + profits[i];
      path.add(i);
      pCap = maxPath(gains, pCap, k - 1, profits, costs, path);
      if (pCap > biggestCap) {
        biggestCap = pCap;
        chosen = i;
      }
      path.remove(i);
    }
    if (chosen > -1) {
      path.add(chosen);
      gains.put(path, biggestCap);
    }
    path.remove(chosen);
    return biggestCap;
  }

  public static void main(String[] args) {
    IPO ipo = new IPO();
    System.out.println(ipo.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
  }
}
