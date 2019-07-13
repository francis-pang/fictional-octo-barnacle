package crackingthecodinginterview.moderate;

import java.util.ArrayList;
import java.util.List;

public class DivingBoard {
  public List<Integer> generatePossibleLengths(int shortPlankLength, int longPlankLength, int numberOfPlanks) {
    List<Integer> possibleLengths = new ArrayList<>();
    int differenceBetweenLongShortPlank = longPlankLength - shortPlankLength;
    int possibleLength = shortPlankLength * numberOfPlanks;
    possibleLengths.add(possibleLength);
    for (int numberOfLongPlanks = 1; numberOfLongPlanks < numberOfPlanks; numberOfLongPlanks++) {
      possibleLength += differenceBetweenLongShortPlank;
      possibleLengths.add(possibleLength);
    }
    return possibleLengths;
  }
}

