package facebook.abcs.heaps;

import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindTheRunningMedian {
  public static class Solution {
    static double[] runningMedian(int[] a) {
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
      double[] runningMedians = new double[a.length];
      for (int index = 0; index < a.length; index++) {
        maxHeap.add(a[index]);

        // Transfer one element from maxHeap if it has more than 2 elements than minHeap
        if (maxHeap.size() - minHeap.size() > 1) {
          int elementToTransfer = maxHeap.poll();
          minHeap.add(elementToTransfer);
        }

        // Swap if the min element in minHeap is smaller than max element in maxHeap
        if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
          int minSwap = minHeap.poll();
          int maxSwap = maxHeap.poll();
          maxHeap.add(minSwap);
          minHeap.add(maxSwap);
        }

        if (maxHeap.size() == minHeap.size()) {
          runningMedians[index] = (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
          runningMedians[index] = maxHeap.peek();
        }
      }
      return runningMedians;
    }

    private static final Scanner scanner = new Scanner(new StringReader("6\n" +
        "12\n" +
        "4\n" +
        "5\n" +
        "3\n" +
        "8\n" +
        "7"));

    public static void main(String[] args) {
      int aCount = Integer.parseInt(scanner.nextLine().trim());

      int[] a = new int[aCount];

      for (int aItr = 0; aItr < aCount; aItr++) {
        int aItem = Integer.parseInt(scanner.nextLine().trim());
        a[aItr] = aItem;
      }

      double[] result = runningMedian(a);
    }
  }
}
