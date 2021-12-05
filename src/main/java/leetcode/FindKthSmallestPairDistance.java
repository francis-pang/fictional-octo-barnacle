package leetcode;

import java.util.Arrays;

public class FindKthSmallestPairDistance {
  public int smallestDistancePair(int[] array, int k) {
    Arrays.sort(array);

    int arrayLength = array.length;
    int leftBound = 0; // minimum possible distance
    int rightBound = array[arrayLength - 1] - array[0]; // maximum possible distance

    // leftBound and rightBound have nothing to do with the array value
    while (leftBound < rightBound) {
      int count = 0;
      int midPoint = (leftBound + rightBound) / 2; // mid-point is the 'trial and error' value

      // note that the value of j is not changed when i is increased in every new iteration of the first loop
      /**
       * The time complexity of this for loop is O(n). WHY?
       * Answer: even though we have nested loops for i and j, the overall time complexity is linear instead of square.
       * This is because for each iteration on i, the index j is monotonically increasing without going back to 0. This
       * is known as the classic two-pointer technique.
       **/
      for (int i = 0, j = 0; i < arrayLength; i++) { // iterate through all possible 'smaller value'
        // while (array[j] - array[i] <= midPoint) is true, it meant that the distance of array[j] & array[i] is less
        // than the guess, so continue increment j until the difference is more than the guess.
        while ((j < arrayLength) && (array[j] - array[i] <= midPoint)) { // array[j] - array[i] is to calculate the distance
          j++;
        }
        count += j - i - 1; // the minus one is there to claim the biggest j that HAS NOT exceeded the formula (array[j] - array[i] <= midPoint)
        // Can design an easy early exit check if (count > k) for small optimisation
      }

      if (count < k) {
        leftBound = midPoint + 1;
      } else {
        rightBound = midPoint;
      }
    }

    return leftBound;
  }
}
