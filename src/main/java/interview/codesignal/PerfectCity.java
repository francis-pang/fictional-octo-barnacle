package interview.codesignal;

public class PerfectCity {
  double perfectCity(double[] departure, double[] destination) {
    double departureX = departure[0];
    double departureY = departure[1];
    double destinationX = destination[0];
    double destinationY = destination[1];

    double distance = 0;
    distance += getOneAxisDistance(departureX, destinationX);
    distance += getOneAxisDistance(departureY, destinationY);
    return distance;
  }

  double extractDecimal(double number1) {
    return ((number1 * 10.0) % 10) / 10.0;
  }

  double oneMinusDecimal(double number) {
    return 1.0 - number;
  }

  double getOneAxisDistance(double departure, double destination) {
    double diff = departure - destination;
    if (Math.abs(diff) < 1 && sameWholeNumber(destination, departure)) {
      double decimalDepart = extractDecimal(departure);
      double decimalDest = extractDecimal(destination);
      return Math.min(
          oneMinusDecimal(decimalDepart) + oneMinusDecimal(decimalDest),
          decimalDepart + decimalDest);
    } else {
      return Math.abs(diff);
    }
  }

  boolean sameWholeNumber(double number1, double number2) {
    return ((int) number1 - (int) number2) == 0;
  }

  public static void main(String[] args) {
    PerfectCity perfectCity = new PerfectCity();
    System.out.println(perfectCity.perfectCity(new double[]{0.9, 6}, new double[]{1.1, 5}));
  }
}
