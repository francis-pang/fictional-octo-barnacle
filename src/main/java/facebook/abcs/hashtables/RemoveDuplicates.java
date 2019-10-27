package facebook.abcs.hashtables;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
  public static String removeDuplicates(String string) {
    Set<Character> uniqueCharacters = new HashSet<>();
    StringBuilder answer = new StringBuilder();
    if (string == null) {
      return answer.toString();
    }
    for (char character : string.toCharArray()) {
      if (!uniqueCharacters.contains(character)) {
        uniqueCharacters.add(character);
        answer.append(character);
      }
    }
    return answer.toString();
  }
}
