package facebook.abcs.hashtables;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FourSumToZero {
  public static class Solution {
    public static void main(String[] args) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner scanner = new Scanner(System.in);
      int[] a = readElementFromInput(scanner);
      int[] b = readElementFromInput(scanner);
      int[] c = readElementFromInput(scanner);
      int[] d = readElementFromInput(scanner);
      System.out.print(sumToZero(a, b, c, d));
    }

    private static int sumToZero(int[] a, int[] b, int[] c, int[] d) {
      if (a.length == 0) {
        return 0;
      }
      int count = 0;
      Map<Integer, Integer> dMap = new HashMap<>();
      for (int num : d) {
        dMap.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
      }
      for (int aNum : a) {
        for (int bNum : b) {
          for (int cNum : c) {
            int sum = aNum + bNum + cNum;
            int lookFor = 0 - sum;
            count += dMap.getOrDefault(lookFor, 0);
          }
        }
      }
      return count;
    }

    private static int[] readElementFromInput(Scanner scanner) {
      String line = scanner.nextLine();
      String[] numberStrings = line.split(" ");
      int[] array = new int[numberStrings.length];
      for (int i = 0; i < numberStrings.length; i++) {
        int element = Integer.parseInt(numberStrings[i]);
        array[i] = element;
      }
      return array;
    }
  }
}
