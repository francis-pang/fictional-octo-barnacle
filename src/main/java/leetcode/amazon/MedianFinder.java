package leetcode.amazon;

import java.util.PriorityQueue;

class MedianFinder {
  public PriorityQueue<Integer> minHeap;
  public PriorityQueue<Integer> maxHeap;

  /**
   * initialize your data structure here.
   */
  public MedianFinder() {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
  }

  public void addNum(int num) {
    maxHeap.add(num);
    if (maxHeap.size() - minHeap.size() > 1) {
      int transferElement = maxHeap.poll();
      minHeap.add(transferElement);
    }
    if (!minHeap.isEmpty() &&
        minHeap.peek() < maxHeap.peek()) {
      swapTopElement(minHeap, maxHeap);
    }
  }

  private void swapTopElement(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
    int minElement = minHeap.poll();
    int maxElement = maxHeap.poll();
    minHeap.add(maxElement);
    maxHeap.add(minElement);
  }

  public double findMedian() {
    boolean isEqualLength = minHeap.size() == maxHeap.size();
    return (isEqualLength)
        ? (minHeap.peek() + maxHeap.peek()) / 2.0
        : maxHeap.peek();
  }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */