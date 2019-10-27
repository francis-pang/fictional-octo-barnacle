package facebook.abcs.hashtables;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IceCreamParlor {

  // Complete the icecreamParlor function below.
  static int[] icecreamParlor(int m, int[] c) {
    Map<Integer, Integer> costByIndex = new HashMap<>();
    for (int i = 0; i < c.length; i++) {
      int costNeed = m - c[i];
      if (costNeed <= 0) {
        continue;
      }
      if (costByIndex.containsKey(costNeed)) {
        int[] ans = new int[2];
        ans[0] = costByIndex.get(costNeed) + 1;
        ans[1] = i + 1;
        return ans;
      }
      costByIndex.put(c[i], i);
    }
    return new int[2];
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int m = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] arr = new int[n];

      String[] arrItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
        int arrItem = Integer.parseInt(arrItems[i]);
        arr[i] = arrItem;
      }

      int[] result = icecreamParlor(m, arr);

      for (int i = 0; i < result.length; i++) {
        bufferedWriter.write(String.valueOf(result[i]));

        if (i != result.length - 1) {
          bufferedWriter.write(" ");
        }
      }

      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
