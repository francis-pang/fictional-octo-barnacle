package other.govtech;

/**
 * There are N blocks, numbered from 0 to N-1, arranged in a row. A couple of frogs were sitting together on one
 * block when they had a terrible quarrel. Now they want to jump away from one another so that the distance between
 * them will be as large as possible. The distance between blocks numbered J and K, where J ≤ K, is computed as
 * K − J + 1. The frogs can only jump up, meaning that they can move from one block to another only if the two
 * blocks are adjacent and the second block is of the same or greater height as the first. What is the longest
 * distance that they can possibly create between each other, if they also chose to sit on the optimal starting
 * block initially?
 * Write a function:
 * <code>class Solution { public int solution(int[] blocks); }</code>
 * <p>
 * that, given an array <i>blocks</i> consisting of N integers denoting the heights of the blocks, returns the longest
 * possible distance that two frogs can make between each other starting from one of the blocks.
 * <p>
 * Examples:
 * 1. Given blocks = [2, 6, 8, 5], the function should return 3. If starting from blocks[0], the first frog can stay
 * where it is and the second frog can jump to blocks[2] (but not to blocks[3]).
 * <img src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/528e5a11d1436f60e03c5295601df025.png"
 * alt="Graphical representation of example 1.">
 * <p>
 * 2. Given blocks = [1, 5, 5, 2, 6], the function should return 4. If starting from blocks[3], the first frog can
 * jump to blocks[1], but not blocks[0], and the second frog can jump to blocks[4].
 * <img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/3bc1ca1cf8c38f73c43bdd33334645d5.png"
 * alt="Graphical representation of example 2.">
 * <p>
 * 3. Given blocks = [1, 1], the function should return 2. If starting from blocks[1], the first frog can jump to
 * blocks[0] and the second frog can stay where it is. Starting from blocks[0] would result in the same distance.
 * <img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/f0a735937c259e07cf5cd048afdca3a2.png"
 * alt="Graphical representation of example 3."></p>
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [2..200,000]
 * - each element of array blocks is an integer within the range [1..1,000,000,000]
 */
public class LargestDistanceBetweenFrog {
  private final static int GOING_DOWN = -1;
  private final static int GOING_UP = 1;

  public int solution(int[] blocks) {
    int lastPeak = 0;
    int maxDistance = 0;
    int currentTrend = GOING_DOWN;
    int sameNumberCount = 0;
    for (int i = 1; i < blocks.length; i++) {
      int difference = blocks[i] - blocks[i - 1];
      int trend;
      if (difference < 0) {
        trend = GOING_DOWN;
      } else if (difference > 0) {
        trend = GOING_UP;
      } else { // 0
        // No change to the trends
        trend = currentTrend;
        if (currentTrend == GOING_UP) {
          sameNumberCount++;
        }
      }
      if (currentTrend == trend) {
        if (currentTrend == GOING_UP && difference != 0) {
          sameNumberCount = 0;
        }
      } else if (currentTrend == GOING_DOWN && trend == GOING_UP) {
        currentTrend = GOING_UP;
      } else { //currentTrend == GOING_UP && trend == GOING_DOWN
        int distance = i - lastPeak;
        if (distance > maxDistance) {
          maxDistance = distance;
        }
        lastPeak = i - 1 - sameNumberCount;
        currentTrend = GOING_DOWN;
      }
    }
    int lastDistance = blocks.length - lastPeak;
    if (lastDistance > maxDistance) {
      maxDistance = lastDistance;
    }
    return maxDistance;
  }

  public static void main(String[] args) {
    LargestDistanceBetweenFrog largestDistanceBetweenFrog = new LargestDistanceBetweenFrog();
    System.out.println(largestDistanceBetweenFrog.solution(new int[]{6, 30, 14, 14, 6, 10, 7, 29, 21, 1, 9, 6}));
    System.out.println(largestDistanceBetweenFrog.solution(new int[]{1, 5, 5, 2, 6}));
    System.out.println(largestDistanceBetweenFrog.solution(new int[]{1, 1}));
  }
}

