package interview.deutschebank;

public class FrogJump {
  private static int UPWARD = 1;
  private static int DOWNWARD = 2;
  public int solution(int[] blocks) {
    // write your code in Java SE 8

    // Start from 'lowest' point
    int direction = UPWARD;
    int longestDistance = 0;

    int longestLevelLength = 0;
    int previousHeight = blocks[0];
    int distance = 1;
    int lastPeakPosition = 0;

    for (int index = 1; index < blocks.length; index++) {
      int height = blocks[index];
      if (direction == UPWARD) { // Supposed direction is to go upwards
        if (height < previousHeight) { // To continue upward, must be higher
          // It is going down, so calculate the distance so far
          distance = index - lastPeakPosition;
          if (longestDistance < distance) {
            longestDistance = distance;
          }
          lastPeakPosition = index - longestLevelLength - 1;
          direction = DOWNWARD;
        }
      } else { // Supposed direction is to go downwards
        if (height > previousHeight) { // To continue downwards, must be lower
          direction = UPWARD;
        }
      }
      if (height == previousHeight) {
        longestLevelLength++;
        continue;
      } else {
        longestLevelLength = 0;
      }
      previousHeight = height;
    }
    distance = blocks.length - lastPeakPosition;
    if (longestDistance < distance) {
      longestDistance = distance;
    }
    return longestDistance;
  }

  public static void main(String[] args) {
    FrogJump frogJump = new FrogJump();
    System.out.println(frogJump.solution(new int[]{1,5,5,2,6}));
  }
}

