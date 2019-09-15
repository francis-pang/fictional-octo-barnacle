package facebook.abcs.recursion;

import java.io.IOException;

public class StoreTheCandy {
  static class Result {

    /*
     * Complete the 'findNumJars' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER count
     *  2. INTEGER capacity
     */
    public static int findNumJars(int count, int capacity) {
      // Write your code here
      if (count <= capacity) {
        return 1;
      }
      if (count % 2 == 0) {
        return findNumJars(count / 2, capacity) * 2;
      } else {
        return findNumJars(count / 2, capacity) + findNumJars(count / 2 + 1, capacity);
      }
    }
  }

  public static class Solution {
    public static void main(String[] args) throws IOException {
      int result = Result.findNumJars(10, 2);
      System.out.println(result);
    }
  }

}
