package crackingthecodinginterview.recursionanddynamicprogramming;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the
 * stairs.
 */
public class TripleStep {
  public int countNumberOfWayToRunUpTheStairs(int steps) {
    int[] waysForStep = new int[steps + 1];

    return countNumberOfWayToRunUpTheStairs(steps, waysForStep);
  }

  private int countNumberOfWayToRunUpTheStairs(int steps, int[] waysForStep) {
    if (steps <= 0) {
      return 0;
    }
    if (waysForStep[steps] != 0) {
      return waysForStep[steps];
    }
    int numberOfWay = 0;
    switch (steps) {
      case 1:
      case 2:
      case 3:
        numberOfWay++;
        break;
      default:
        break;
    }
    waysForStep[steps] = numberOfWay +
        countNumberOfWayToRunUpTheStairs(steps - 1, waysForStep) +
        countNumberOfWayToRunUpTheStairs(steps - 2, waysForStep) +
        countNumberOfWayToRunUpTheStairs(steps - 3, waysForStep);
    return waysForStep[steps];
  }
}
