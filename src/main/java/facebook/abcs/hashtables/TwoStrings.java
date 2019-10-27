package facebook.abcs.hashtables;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TwoStrings {
  private static final String FOUND = "YES";
  private static final String NOT_FOUND = "NO";
  private static final int ASCII_OFFSET = 97;

  // Complete the twoStrings function below.
  private static String twoStrings(String s1, String s2) {
    // Convert s1 to boolean array
    boolean[] occurrenceArray = buildOccurrenceArray(s1);
    for (int i = 0; i < s2.length(); i++) {
      final int codePoint = Character.codePointAt(s2, i);
      final int offSetAsciiValue = codePoint - ASCII_OFFSET;
      if (occurrenceArray[offSetAsciiValue]) {
        return FOUND;
      }
    }
    return NOT_FOUND;
  }

  private static boolean[] buildOccurrenceArray(String string) {
    boolean[] charactersExistence = new boolean[26];
    for (int i = 0; i < string.length(); i++) {
      final int codePoint = Character.codePointAt(string, i);
      final int offSetAsciiValue = codePoint - ASCII_OFFSET;
      charactersExistence[offSetAsciiValue] = true;
    }
    return charactersExistence;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String s1 = scanner.nextLine();
      String s2 = scanner.nextLine();
      String result = twoStrings(s1, s2);

      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }
    bufferedWriter.close();
    scanner.close();
  }
}
