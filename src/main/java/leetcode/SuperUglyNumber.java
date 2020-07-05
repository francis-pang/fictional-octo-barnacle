package leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SuperUglyNumber {
  public int nthSuperUglyNumber(int n, int[] primes) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    if (n <= 0 || primes.length == 0) {
      return -1;
    }
    Set<Integer> unique = new HashSet<>();
    n -= 2;
    for (int prime : primes) {
      pq.add(prime);
      unique.add(prime);
    }
    while (n > 0) {
      int smallest = pq.poll();
      for (int prime : primes) {
        int multiply = smallest * prime;
        if (unique.add(multiply)) {
          pq.add(multiply);
        }
      }
      n--;
    }
    return pq.poll();
  }
}