package other.sea;

import java.util.Scanner;

public class FloorTile {
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
    int input = in.nextInt();
    int result = fibonacci(input);
    System.out.println(result);
  }

  private static int fibonacci(int number) {
    switch (number) {
      case 0:
        return 0;
      case 1:
        return 1;
      case 2:
        return 2;
    }
    int first = 1;
    int second = 2;
    int result = -1;
    for (int i = 2; i < number; i++) {
      result = first + second;
      first = second;
      second = result;
    }
    return result;
  }
}
