package other.sea;

import java.util.Scanner;

public class Base7 {
  private static final int BASE_7 = 7;

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
    String base7Representation = convertToBase7(input);
    System.out.println(base7Representation);
  }

  private static String convertToBase7(int number) {
    if (number == 0) {
      return "0";
    }
    StringBuilder stringBuilder = new StringBuilder();
    boolean isNegative = number < 0;
    number = Math.abs(number);
    while (number != 0) {
      int remainder = number % BASE_7;
      stringBuilder.insert(0, remainder);
      number /= BASE_7;
    }
    if (isNegative) {
      stringBuilder.insert(0, '-');
    }
    return stringBuilder.toString();
  }
}

