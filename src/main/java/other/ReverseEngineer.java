package other;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReverseEngineer {
  public Map<String, Integer> findOutValuesOfEachLetter(List<String> letters) {
    // First scan the list to know what is the letters available, and also the relationship between the letters
    Set<Character> letterSet = new HashSet<>();

    int orderOfAppearance = 0;
    for (String input : letters) {
      char[] inputCharArray = input.toCharArray();
      for (char character : inputCharArray) {
        // New character
        letterSet.add(character);
      }
    }
    return null;
  }

  public class LetterRepresenation {
    public char letter;
    public List<Relationship> relationshipWithOtherLetters;

    public LetterRepresenation(char letter, List<Relationship> relationshipWithOtherLetters) {
      this.letter = letter;
      this.relationshipWithOtherLetters = relationshipWithOtherLetters;
    }
  }

  public class Relationship {
    public char letter;
    public boolean isMultipler;
    public int difference;

    public Relationship(char letter, boolean isMultipler, int difference) {
      this.letter = letter;
      this.isMultipler = isMultipler;
      this.difference = difference;
    }
  }
}
