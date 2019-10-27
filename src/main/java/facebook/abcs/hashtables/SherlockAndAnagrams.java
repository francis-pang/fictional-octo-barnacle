package facebook.abcs.hashtables;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SherlockAndAnagrams {
  private static final int ASCII_OFFSET = 97;
  // Complete the sherlockAndAnagrams function below.
  private static int sherlockAndAnagrams(String s) {
    int count = 0;
    for (int comparisonLength = 1; comparisonLength < s.length(); comparisonLength++) {
      List<Double> hashValues = new ArrayList<>();
      for (int startPos = 0; startPos < s.length(); startPos++) {
        double subStringHashValue = 0;
        for (int j = 0; j < comparisonLength; j++) {
          if (startPos + j < s.length()) {
            subStringHashValue += calculateHashedValue(s.charAt(startPos + j));
          }
        }
        for (double element : hashValues) {
          if (Math.abs(element - subStringHashValue) < 0.01) {
            count++;
          }
        }
        hashValues.add(subStringHashValue);
      }
    }
    return count;
  }

  private static double calculateHashedValue(char character) {
    int value = character;
    return value * Math.pow(2.4, value - ASCII_OFFSET);
  }

  public static void Main(String[] args) {
    System.out.println(sherlockAndAnagrams("ifailuhkqq"));
    assert sherlockAndAnagrams("ifailuhkqq") == 3;
  }

  public static void main(String[] args) throws IOException {
    final Scanner scanner = new Scanner(new FileReader("D:\\code\\fictional-octo-barnacle\\src\\main\\java\\facebook" +
        "\\abcs\\hashtables\\Sherlock-and-Anagrams-input03.txt"));
    final Scanner answer = new Scanner(new FileReader("D:\\code\\fictional-octo-barnacle\\src\\main\\java\\facebook" +
        "\\abcs\\hashtables\\Sherlock-and-Anagrams-output03.txt"));
    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String s = scanner.nextLine();

      int result = sherlockAndAnagrams(s);
      int answerInt = answer.nextInt();
      if (result != answerInt) {
        System.out.println(qItr + " is wrong: answer=" + answerInt + ", actual=" + result);
      }
    }
    scanner.close();
  }
}
