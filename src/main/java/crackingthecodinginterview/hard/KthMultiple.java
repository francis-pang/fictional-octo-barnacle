package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Design an algorithm to find the kth number such that the only prime factors are 3, 5, and 7. Note that 3, 5, and 7
 * do not have to be factors, but it should not have any other prime factors. For example, the first several
 * multiples would be (in order) 1, 3, 5,7,9, 15, 21.
 */
public class KthMultiple {
  public int getKthMultiple(int[] primes, int k) {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> (o1 - o2) * -1);
    Set<Integer> uniqueStore = new HashSet<>();
    uniqueStore.add(1);
    priorityQueue.add(1);
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    int listIterator = 0;
    while (listIterator < k) {
      int element = list.get(listIterator);
      for (int prime : primes) {
        int multiple = prime * element;
        if (uniqueStore.contains(multiple)) {
          continue;
        }
        if (priorityQueue.size() < k) {
          list.add(multiple);
          priorityQueue.add(multiple);
          uniqueStore.add(multiple);
        } else if (priorityQueue.peek() > multiple) {
          uniqueStore.remove(priorityQueue.poll());
          priorityQueue.add(multiple);
          list.add(multiple);
          uniqueStore.add(multiple);
        }
      }
      listIterator++;
    }
    return priorityQueue.poll();
  }

  public static void main(String[] args) {
    KthMultiple kthMultiple = new KthMultiple();
    int[] primes = new int[]{3,5,7};
    System.out.println(kthMultiple.getKthMultiple(primes, 11));
  }
}
