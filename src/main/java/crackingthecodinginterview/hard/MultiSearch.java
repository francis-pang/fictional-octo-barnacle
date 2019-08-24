package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiSearch {
  public List<String> multiSearch(String b, String[] T) {
    Map<Character, List<Integer>> positionsOfCharacter = new HashMap<>();
    for (int index = 0; index < b.length(); index++) {
      char character = b.charAt(index);
      if (positionsOfCharacter.containsKey(character)) {
        positionsOfCharacter.get(character).add(index);
      } else {
        List<Integer> newList = new ArrayList<>();
        newList.add(index);
        positionsOfCharacter.put(character, newList);
      }
    }

    List<String> foundString = new ArrayList<>();
    for (String string : T) {
      char firstChar = string.charAt(0);
      for (Integer position : positionsOfCharacter.getOrDefault(firstChar, new ArrayList<>())) {
        String subString = b.substring(position, string.length());
        if (subString.equals(string)) {
          foundString.add(string);
        }
      }
    }
    return foundString;
  }
}
