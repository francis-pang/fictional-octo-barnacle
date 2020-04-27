package Igloohome;

import java.util.Scanner;

/**
 * Have the function CaesarCipher(str,num) take the str parameter and perform a Caesar Cipher shift on it using the
 * num parameter as the shifting number. A Caesar Cipher works by shifting each letter in the string N places in the
 * alphabet (in this case N will be num). Punctuation, spaces, and capitalization should remain intact. For example
 * if the string is "Caesar Cipher" and num is 2 the output should be "Ecguct Ekrjgt".
 *
 * Examples
 * Input: "Hello" & num = 4
 * Output: Lipps
 * Input: "abc" & num = 0
 * Output: abc
 */
public class CaesarCipher {
  public static String CaesarCipher(String string, int num) {
    if (num == 0) {
      return string;
    }
    StringBuilder replacedString = new StringBuilder();
    for (int i = 0; i < string.length(); i++) {
      char character = string.charAt(i);
      if (!Character.isAlphabetic(character)) {
        replacedString.append(character);
      }
      replacedString.append((char) (character + num));
    }
    // code goes here
    return replacedString.toString();
  }

  public static void main (String[] args) {
    // keep this function call here
    Scanner s = new Scanner(System.in);
    String line = s.nextLine();
    String[] strings = line.split(" ");
    String lineExcludeNumber = line.substring(0, line.lastIndexOf(" "));
    System.out.print(CaesarCipher(lineExcludeNumber, Integer.parseInt(strings[strings.length - 1])));
  }
}
