package crackingthecodinginterview.arrayandstring;

public class StringCompression {
  public String compressString(String string) {
    if (string.length() < 2) {
      return string;
    }
    char[] charSequence = new char[string.length()];
    int[] charCounter = new int[string.length()];
    int position = 0;
    char[] stringInCharArray = string.toCharArray();
    for (char character : stringInCharArray) {
      if (charSequence[position] != character) {
        charSequence[++position] = character;
      }
      charCounter[position]++;
    }
    if (string.length() < position * 2) {
      return string;
    }
    StringBuilder compressedString = new StringBuilder();
    for (int i = 1; i <= position; i++) {
      compressedString.append(charSequence[i])
          .append(charCounter[i]);
    }
    return compressedString.toString();
  }
}
