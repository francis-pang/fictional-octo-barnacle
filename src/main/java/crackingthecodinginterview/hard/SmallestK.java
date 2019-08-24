package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Design an algorithm to find the smallest K numbers in an array.
 */
public class SmallestK {
  public List<Integer> findSmallestKFrom(int[] array, int k) {
    PriorityQueue<Integer> smallestKElements = new PriorityQueue<>(k, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (o1 == o2) {
          return 0;
        } else if (o1 > o2) {
          return -1;
        } else {
          return 1;
        }
      }
    });

    // Put in first k element
    for (int i = 0; i < k; i++) {
      smallestKElements.offer(array[i]);
    }

    for (int i = k; i < array.length; i++) {
      int biggestElementInHeap = smallestKElements.peek();
      if (biggestElementInHeap > array[i]) {
        smallestKElements.poll();
        smallestKElements.offer(array[i]);
      }
    }

    List<Integer> smallestKList = new ArrayList<>();
    smallestKElements.forEach(element -> {
      smallestKList.add(element);
    });
    return smallestKList;
  }
}
