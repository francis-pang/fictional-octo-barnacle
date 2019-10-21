package facebook.abcs.heaps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JesseAndCookies {
  public static class Solution {
    /*
     * Complete the cookies function below.
     */
    static int cookies(int sweetness, int[] array) {
      final int IMPOSSIBLE = -1;
      if (array.length == 0) {
        return IMPOSSIBLE;
      }
      PriorityQueue<Integer> cookies = new PriorityQueue<>();
      for (int cookie : array) {
        cookies.add(cookie);
      }
      int bakeCounter = 0;
      while (cookies.peek() < sweetness && cookies.size() > 1) {
        breakNewCookies(cookies);
        bakeCounter++;
      }
      if (cookies.size() == 1 &&
          cookies.peek() < sweetness) {
        return IMPOSSIBLE;
      }
      return bakeCounter;
    }

    private static void breakNewCookies(PriorityQueue<Integer> cookies) {
      int leastSweet = cookies.poll();
      int secondLeastSweet = cookies.poll();
      int combinedSweetness = leastSweet + 2 * secondLeastSweet;
      cookies.add(combinedSweetness);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
      String[] nk = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nk[0].trim());
      int k = Integer.parseInt(nk[1].trim());
      int[] A = new int[n];
      String[] AItems = scanner.nextLine().split(" ");

      for (int AItr = 0; AItr < n; AItr++) {
        int AItem = Integer.parseInt(AItems[AItr].trim());
        A[AItr] = AItem;
      }

      int result = cookies(k, A);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();
      bufferedWriter.close();
    }
  }
}
