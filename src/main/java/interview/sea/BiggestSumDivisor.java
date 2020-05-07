package interview.sea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Find the Biggest Sum Divisible by Threw
 *
 * Task
 * Given an array of n integers, we need to find the maximum possible sum of elements of the array such that it is
 * divisible by three.
 * Note:
 * The time complexity <= O(n)
 *
 * Input
 * The n elements of this array separated by comma.
 *
 * Output
 * The maximum sum
 *
 * Example
 * Input: 3,6,5,1,8
 * Output: 18
 * Explanation: Pikc numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 *
 * Input: 4
 * Output: 0
 * Explanation: Since 4 is no divisible by 3, do not pick any number.
 */
public class BiggestSumDivisor {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // Click HELP above to see examples of handling input/debug/output
    // INPUT: int n = in.nextInt();
    // DEBUG: System.out.println(n);
    // OUTPUT: System.out.println(result);

    // Write the code to solve the problem below,
    // format the "result" as specified in the problem statement
    // and finally, write the result to stdout
    // IMPORTANT: Remove all debug statements for final submission
    String string = in.nextLine();
    int[] array = convertCsvToIntArray(string);

    PriorityQueue<Integer> remainderOnePriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> remainderTwoPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    int result = 0;

    for (int element : array) {
      int remainder = element % 3;
      switch (remainder) {
        case 0:
          result += element;
          break;
        case 1:
          remainderOnePriorityQueue.add(element);
          break;
        case 2:
          remainderTwoPriorityQueue.add(element);
          break;
      }
    }

    ArrayList<Integer> remainderOneList = new ArrayList<>(remainderOnePriorityQueue);
    ArrayList<Integer> remainderTwoList = new ArrayList<>(remainderTwoPriorityQueue);

    int oneCursor = 0;
    int twoCursor = 0;

    while (oneCursor + 2 < remainderOneList.size() && twoCursor + 2 < remainderTwoList.size()) {
      int remainderOneSum =
          remainderOneList.get(oneCursor) + remainderOneList.get(oneCursor + 1) + remainderOneList.get(oneCursor + 2);

      int remainderTwoSum =
          remainderTwoList.get(twoCursor) + remainderTwoList.get(twoCursor + 1) + remainderTwoList.get(twoCursor + 2);

      int takeOneFromEachSum = remainderOneList.get(oneCursor) + remainderTwoList.get(twoCursor);

      int biggest = Math.max(Math.max(remainderOneSum, remainderTwoSum), takeOneFromEachSum);
      if (biggest == remainderOneSum) {
        result += remainderOneSum;
        oneCursor += 3;
      } else if (biggest == remainderTwoSum) {
        result += remainderTwoSum;
        twoCursor += 3;
      } else { // takeOneEach
        result += takeOneFromEachSum;
        oneCursor++;
        twoCursor++;
      }
    }

    while (oneCursor + 2 < remainderOneList.size()) {
      int remainderOneSum =
          remainderOneList.get(oneCursor) + remainderOneList.get(oneCursor + 1) + remainderOneList.get(oneCursor + 2);

      int takeOneFromEachSum = remainderOneList.get(oneCursor) + remainderTwoList.get(twoCursor);

      int biggest = Math.max(remainderOneSum, takeOneFromEachSum);
      if (biggest == remainderOneSum) {
        result += remainderOneSum;
        oneCursor += 3;
      } else { // takeOneEach
        result += takeOneFromEachSum;
        oneCursor++;
        twoCursor++;
      }
    }

    while (twoCursor + 2 < remainderTwoList.size()) {
      int remainderTwoSum =
          remainderTwoList.get(twoCursor) + remainderTwoList.get(twoCursor + 1) + remainderTwoList.get(twoCursor + 2);

      int takeOneFromEachSum = remainderOneList.get(oneCursor) + remainderTwoList.get(twoCursor);

      int biggest = Math.max(remainderTwoSum, takeOneFromEachSum);

      if (biggest == remainderTwoSum) {
        result += remainderTwoSum;
        twoCursor += 3;
      } else { // takeOneEach
        result += takeOneFromEachSum;
        oneCursor++;
        twoCursor++;
      }
    }

    System.out.println(result);
  }

  private static int[] convertCsvToIntArray(String string) {
    String[] stringArray = string.split(",");
    int[] array = new int[stringArray.length];
    for (int i = 0; i < stringArray.length; i++) {
      array[i] = Integer.parseInt(stringArray[i]);
    }
    return array;
  }
}
