package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterMind {
  public Answer guess(List<Color> solution, List<Color> guess) {
    Map<Color, Integer> incorrectSolutionGuessesByColors = new HashMap<>();
    Map<Color, Integer> missedGuessesByColors = new HashMap<>();

    Answer answer = new Answer();

    for (int i = 0; i < solution.size(); i++) {
      Color solutionColor = solution.get(i);
      // Assume that the guess has same size as solution
      Color guessColor = null;
      try {
        guessColor = guess.get(i);
      } catch (IndexOutOfBoundsException ex) {
        System.out.println("There is no element for position " + i);
        throw ex;
      }

      if (guessColor.equals(solutionColor)) {
        answer.hitCount++;
      } else {
        incorrectSolutionGuessesByColors.compute(solutionColor, (color, count) -> (count == null) ? 1 : count + 1);
        missedGuessesByColors.compute(guessColor, (color, count) -> (count == null) ? 1 : count + 1);
      }
    }

    incorrectSolutionGuessesByColors.forEach((solutionColor, solutionCount) -> {
      int guessCount = missedGuessesByColors.getOrDefault(solutionColor, 0);
      answer.pseudoHitCount += Math.min(solutionCount, guessCount);
    });
    return answer;
  }

  class Answer {
    int hitCount;
    int pseudoHitCount;

    public Answer() {
      hitCount = 0;
      pseudoHitCount = 0;
    }
  }

  enum Color {
    RED, YELLOW, GREEN, BLUE
  }
}

