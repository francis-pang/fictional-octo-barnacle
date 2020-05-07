package interview.rakuten;

public class Season {
  public int solution(int[] temperatures) {
    int[] smallestTemperatureFromRight = new int[temperatures.length];

    int smallestTemperature = temperatures[temperatures.length - 1];
    for (int i = temperatures.length - 1; i >= 0; i--) {
      int temperature = temperatures[i];
      if (smallestTemperature > temperature) {
        smallestTemperature = temperature;
      }
      smallestTemperatureFromRight[i] = smallestTemperature;
    }

    int[] biggestTemperatureFromLeft = new int[temperatures.length];
    int biggestTemperature = temperatures[0];
    for (int i = 0; i < temperatures.length; i++) {
      int temperature = temperatures[i];
      if (biggestTemperature < temperature) {
        biggestTemperature = temperature;
      }
      biggestTemperatureFromLeft[i] = biggestTemperature;
    }

    for (int i = 0; i < temperatures.length - 1; i++) {
      if (biggestTemperatureFromLeft[i] < smallestTemperatureFromRight[i + 1]) {
        return i + 1;
      }
    }
    return -1;
  }
}
