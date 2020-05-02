package other.sea;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Merge arrays
 *
 * Task
 * Given two sorted arrays of integers. Merge them into one single sorted array.
 * Note:
 * Try best to optimize the time complexity.
 *
 * Input
 * The first line is first input sorted array separated by comma.
 * The second line is second input sorted array separated by comma.
 *
 * Output
 * The output array separated by comma.
 *
 * Example
 * Input:
 * 1,3,3,4,5,8,9
 * 2,3,6,7,10
 *
 * Output:
 * 1,2,3,3,3,4,5,6,7,8,9,10
 */
public class MergeArrays {
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

    String firstLine = in.nextLine();
    String secondLine = in.nextLine();

    int[] firstArray = convertCsvToIntArray(firstLine);
    int[] secondArray = convertCsvToIntArray(secondLine);

    int firstCursor = 0;
    int secondCursor = 0;
    StringJoiner stringJoiner = new StringJoiner(",");

    while (firstCursor < firstArray.length && secondCursor < secondArray.length) {
      int firstNumber = firstArray[firstCursor];
      int secondNumber = secondArray[secondCursor];

      if (secondNumber > firstNumber) {
        stringJoiner.add(Integer.toString(firstNumber));
        firstCursor++;
      } else {
        stringJoiner.add(Integer.toString(secondNumber));
        secondCursor++;
      }
    }

    while (firstCursor < firstArray.length) {
      int firstNumber = firstArray[firstCursor];
      stringJoiner.add(Integer.toString(firstNumber));
      firstCursor++;
    }

    while (secondCursor < secondArray.length) {
      int secondNumber = secondArray[secondCursor];
      stringJoiner.add(Integer.toString(secondNumber));
      secondCursor++;
    }
    System.out.println(stringJoiner.toString());
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
