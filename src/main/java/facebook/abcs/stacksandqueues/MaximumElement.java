package facebook.abcs.stacksandqueues;

import java.util.Scanner;
import java.util.Stack;

/**
 * You have an empty sequence, and you will be given queries. Each query is one of these three types:
 * <quote>
 * 1 x -Push the element x into the stack.
 * 2 -Delete the element present at the top of the stack.
 * 3 -Print the maximum element in the stack.
 * </quote>
 *
 * <b>Input Format</b>
 * The first line of input contains an integer, . The next lines each contain an above mentioned query. (It is
 * guaranteed that each query is valid.)
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= N <= 10^5</li>
 *   <li>1 < x <= 10^9</li>
 *   <li>1 <= type <= 3</li>
 * </ul>>
 *
 * <b>Output Format</b>
 * For each type 3 query, print the maximum element in the stack on a new line.
 *
 * <b>Sample Input</b>
 * 10
 * 1 97
 * 2
 * 1 20
 * 2
 * 1 26
 * 1 20
 * 2
 * 3
 * 1 91
 * 3
 * <b>Sample Output</b>
 * 26
 * 91
 */
public class MaximumElement {
  public static class Solution {
    public static void main(String[] args) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner input = new Scanner(System.in);
      // first line of input contains an integer , N. The next N lines each contain an above mentioned query.
      int numberOfLines = input.nextInt();

      Stack<Integer> stack = new Stack<>();
      int maximumElement = Integer.MIN_VALUE;

      // Start reading input
      int currentLine = 0;
      while (currentLine < numberOfLines) {
        int queryOption = input.nextInt();
        switch (queryOption) {
          case 1:
            int newElement = input.nextInt();
            maximumElement = insertNewElement(stack, newElement, maximumElement);
            break;
          case 2:
            maximumElement = removeTopElement(stack, maximumElement);
            break;
          case 3:
            System.out.println(maximumElement);
            break;
          default:
            throw new IllegalArgumentException("Invalid option");
        }
        currentLine++;
      }
    }

    private static int removeTopElement(Stack<Integer> stack, int maximumElement) {
      int removedElement = stack.pop();
      if (maximumElement == removedElement) {
        maximumElement = findNewMaximumElement(stack);
      }
      return maximumElement;
    }

    private static int findNewMaximumElement(Stack<Integer> stack) {
      int maximumElement = Integer.MIN_VALUE;
      for (final int element : stack) {
        if (maximumElement < element) {
          maximumElement = element;
        }
      }
      return maximumElement;
    }

    private static int insertNewElement(Stack<Integer> stack, int newElement, int maximumElement) {
      stack.push(newElement);
      return (maximumElement < newElement) ? newElement : maximumElement;
    }
  }
}
