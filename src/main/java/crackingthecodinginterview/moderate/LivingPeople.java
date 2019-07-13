package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivingPeople {
  class LifeSpan {
    int birthYear;
    int deathYear;

    public LifeSpan(int birthYear, int deathYear) {
      this.birthYear = birthYear;
      this.deathYear = deathYear;
    }
  }

  public int getYearWithHighestPopulation(List<LifeSpan> populationLifeSpans) {
    if (populationLifeSpans == null || populationLifeSpans.size() == 0) {
      return 0;
    }
    Map<Integer, Integer> birthYearOccurrences = new HashMap<>();
    Map<Integer, Integer> deathYearOccurrences = new HashMap<>();

    for (LifeSpan lifeSpan : populationLifeSpans) {
      birthYearOccurrences.compute(lifeSpan.birthYear, (birthYear, peopleCount) -> (peopleCount == null) ? 1 : peopleCount + 1);
      deathYearOccurrences.compute(lifeSpan.deathYear, (deathYear, peopleCount) -> (peopleCount == null) ? 1 :
          peopleCount + 1);
    }

    int maxPopulation = 0;
    int maxYear = 0;
    for (int currentYear = 1900; currentYear <= 2000; currentYear++) {
      int population =
          birthYearOccurrences.getOrDefault(currentYear, 0) -
              deathYearOccurrences.getOrDefault(currentYear - 1, 0);
      if (population > maxPopulation) {
        maxPopulation = population;
        maxYear = currentYear;
      }
    }
    return maxYear;
  }
}
