package interview.moka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class SellingProducts {

  /*
   * Complete the 'deleteProducts' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY ids
   *  2. INTEGER m
   */

  public static int deleteProducts(List<Integer> ids, int m) {
    // Write your code here
    Map<Integer, Integer> idCountTable = new HashMap<>();
    for (int id : ids) {
      idCountTable.compute(id, (key, count) -> (count == null) ? 1 : count + 1);
    }
    PriorityQueue<Count> countPriorityQueue = new PriorityQueue<>();
    for (Map.Entry<Integer, Integer> entry : idCountTable.entrySet()) {
      Count count = new Count(entry.getKey(), entry.getValue());
      countPriorityQueue.add(count);
    }

    while (m > 0) {
      Count count = countPriorityQueue.poll();
      m -= count.count;
    }

    return (m == 0) ? countPriorityQueue.size() : countPriorityQueue.size() + 1;
  }

  static class Count implements Comparable<Count>{
    public int id;
    public int count;

    @Override
    public int compareTo(Count o) {
      return this.count - o.count;
    }

    public Count(int id, int count) {
      this.id = id;
      this.count = count;
    }
  }
}