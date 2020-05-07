package interview.c3ai;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a timeseries that stores information about temperature readings for a city, return a timeseries of the same
 * length that provides, for a given day, for how many days this temperature is the highest (ie. "highest
 * temperature in X days").
 *
 * input           = [7, 3, 4, 6, 9, 1, 5, 6, 3, 7, 4, 8, 2, 10]
 * expected_output = [1, 1, 2, 3, 5, 1, 2, 3, 1, 5, 1, 7, 1, 14]
 */
public class HighestTemperatureInXDays {
  public static void main (String[] args) {
    HighestTemperatureInXDays solution = new HighestTemperatureInXDays();

    int[] test1 = {7, 3, 4, 6, 9, 1, 5, 6, 3, 7, 4, 8, 2, 10};
    System.out.println("Result: " + Arrays.toString(solution.highestTemperature(test1)));
  }

  /*
   * input           = [7, 3, 4, 6, 9, 1, 5, 6, 3, 7, 4, 8, 2, 10]
   * expected_output = [1, 1, 2, 3, 5, 1, 2, 3, 1, 5, 1, 7, 1, 14]
   */
  public int[] highestTemperature(int[] timeSeries) {
    Stack<Integer> previousLargerIndex = new Stack<>();
    Stack<Integer> previousLargerValue = new Stack<>();

    int[] result = new int[timeSeries.length];
    result[0] = 1;
    previousLargerIndex.push(0);
    previousLargerValue.push(timeSeries[0]);
    for (int index = 1; index < timeSeries.length; index++) {
      int temperature = timeSeries[index];
      boolean foundALargerElement = false;
      int largerElementIndex = -1;
      while(!foundALargerElement && !previousLargerIndex.isEmpty()) {
        int poppedValue = previousLargerValue.peek();
        if (poppedValue >= temperature) {
          foundALargerElement = true;
          largerElementIndex = previousLargerIndex.peek();
        } else {
          previousLargerIndex.pop();
          previousLargerValue.pop();
        }
      }
      if (foundALargerElement) {
        result[index] = index - largerElementIndex;
      } else {
        result[index] = index + 1;
      }
      previousLargerIndex.push(index);
      previousLargerValue.push(temperature);
    }
    return result;
  }
}
