package facebook.abcs.stacksandqueues;

import java.util.Scanner;
import java.util.Stack;

/**
 * <p>A queue is an abstract data type that maintains the order in which elements were added to it, allowing the oldest
 * elements to be removed from the front and new elements to be added to the rear. This is called a
 * First-In-First-Out (FIFO) data structure because the first element added to the queue (i.e., the one that has
 * been waiting the longest) is always the first one to be removed.
 * </p>
 * <p>
 * A basic queue has the following operations:
 * <ul>
 *   <li>Enqueue: add a new element to the end of the queue.</li>
 *   <li>Dequeue: remove the element from the front of the queue and return it.</li>
 * </ul>
 * </p>
 * <p>
 * In this challenge, you must first implement a queue using <i>two</i> stacks. Then process <b>q</b> queries, where
 * each query is one of the following <b>3</b> types:
 * 1. <i>1 x</i>: Enqueue element into the end of the queue.
 * 2. <i>2</i> : Dequeue the element at the front of the queue.
 * 3. <i>3</i> : Print the element at the front of the queue.
 * </p>
 *
 * <b>Input Format</b>
 * The first line contains a single integer, <b>q</b>, denoting the number of queries.
 * Each line <b>i</b> of the <b>q</b> subsequent lines contains a single query in the form described in the problem
 * statement above. All three queries start with an integer denoting the query <b>type</b>, but only query is followed
 * by an additional space-separated value, <b>x</b>, denoting the value to be enqueued.
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= q <= 10^5</li>
 *   <li>1 <= type <= 3</li>
 *   <li>1 <= |x| <= 10^9</li>
 *   <li>It is guaranteed that a valid answer always exists for each query of type <b>3</b>.</li>
 * </ul>
 *
 * <b>Output Format</b>
 * For each query of type <b>3</b>, print the value of the element at the front of the queue on a new line.
 *
 * <b>Sample Input</b>
 * 10
 * 1 42
 * 2
 * 1 14
 * 3
 * 1 28
 * 3
 * 1 60
 * 1 78
 * 2
 * 2
 *
 * <b>Sample Output</b>
 * 14
 * 14
 *
 * <b>Explanation</b>
 * We perform the following sequence of actions:
 * 1. Enqueue <b>42; queue = {42}</b>.
 * 2. Dequeue the value at the head of the queue, <b>42; queue = {}</b>.
 * 3. Enqueue <b>14; queue = {14}</b>.
 * 4. Print the value at the head of the queue, <b>14; queue = {14}</b>.
 * 5. Enqueue <b>28; queue = {14, 28}</b>.
 * 6. Print the value at the head of the queue, <b>14; queue = {14, 28}</b>.
 * 7. Enqueue <b>60, queue = {14, 28, 60}</b>.
 * 8. Enqueue <b>78, queue={14, 28, 60, 78}</b>.
 * 9. Dequeue the value at the head of the queue, <b>14; queue = {28, 60, 78}</b>.
 * 10. Dequeue the value at the head of the queue, <b>28; queue= {60, 78}</b>.
 */
public class QueueUsingTwoStacks {
  public static class Solution {
    public static void main(String[] args) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner input = new Scanner(System.in);
      // first line of input contains an integer , N. The next N lines each contain an above mentioned query.
      int numberOfLines = input.nextInt();

      Stack<Integer> inOrderstack = new Stack<>();
      Stack<Integer> OutOfOrderStack = new Stack<>();
      // Start reading input
      int currentLine = 0;
      while (currentLine < numberOfLines) {
        int queryOption = input.nextInt();
        switch (queryOption) {
          case 1:
            int newElement = input.nextInt();
            insertNewElement(inOrderstack, newElement);
            break;
          case 2:
            removeTopElement(inOrderstack, OutOfOrderStack);
            break;
          case 3:
            printFrontOfQueue(inOrderstack, OutOfOrderStack);
            break;
          default:
            throw new IllegalArgumentException("Invalid option");
        }
        currentLine++;
      }
    }

    private static void printFrontOfQueue(Stack<Integer> inOrderStack, Stack<Integer> outOfOrderStack) {
      if (outOfOrderStack.isEmpty()) {
        while (!inOrderStack.isEmpty()) {
          outOfOrderStack.push(inOrderStack.pop());
        }
      }
      if (!outOfOrderStack.isEmpty()) {
        System.out.println(outOfOrderStack.peek());
      }
    }

    private static void removeTopElement(Stack<Integer> inOrderStack, Stack<Integer> outOfOrderStack) {
      if (outOfOrderStack.isEmpty()) {
        while (!inOrderStack.isEmpty()) {
          outOfOrderStack.push(inOrderStack.pop());
        }
      }
      if (!outOfOrderStack.isEmpty()) {
        outOfOrderStack.pop();
      }
    }

    private static void insertNewElement(Stack<Integer> stack, int newElement) {
      stack.push(newElement);
    }
  }
}
