package interview.mdaq;

/**
 * In the army, each soldier has an assigned rank. A soldier of rank X has to report to (any) soldier of rank X + 1.
 * Many soldiers can report to the same superior. Write a function:
 * class ArmyRank { public int solution(int[] soliderRanks); }
 *
 * that, given an array ranks consisting of soldiers' ranks, returns the number of soldiers who can report to some
 * superior.
 *
 * Examples:
 * 1. Given ranks = [3,4,3,0,2,2,3,0,0], your function should return 5, because:
 * - Three soldiers of rank 3 (ranks[0], ranks[2], ranks[6]) may report to a soldier of rank 4(ranks[1]).
 * - Two soliders of rank 2 may report to any solider of rank 3.
 *
 * 2. Given ranks = [4,2,0], your function should return 0.
 *
 * 3. Given ranks = [4,4,3,3,1,0], your function should return 3, because:
 * - A soldier of rank 0 can report to a soldier of rank 1.
 * - Two soldiers of rank 3 can report to any soldier of rank 4.
 *
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [2, 100,000]
 * - each element of array ranks is an integer within the range [0, 1,000,000,000]
 */
public class ArmyRank {
  public int solution(int[] soliderRanks) {
    // write your code in Java SE 8
    int biggestRank = 0;
    for (int soliderRank : soliderRanks) {
      if (biggestRank < soliderRank) {
        biggestRank = soliderRank;
      }
    }

    int[] rankCounts = new int[biggestRank + 1];
    for (int soliderRank : soliderRanks) {
      rankCounts[soliderRank]++;
    }

    int result = 0;
    for (int i = 0; i < rankCounts.length - 1; i++) {
      int currentRankCount = rankCounts[i];
      int nextRankCount = rankCounts[i + 1];
      if (nextRankCount > 0) {
        result += currentRankCount;
      }
    }
    return result;
  }
}
