package facebook.abcs.graph;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class JourneyToTheMoon {
  static BigInteger journeyToMoon(int n, int[][] sameCountryAstronautPairs) {
    final int NOT_DEFINED = -1;
    int[] astronautCountry = new int[n];
    for (int i = 0; i < n; i++) {
      astronautCountry[i] = NOT_DEFINED;
    }
    List<Set<Integer>> sameCountryAstronautsList = new ArrayList<>();
    for (int[] astronautsPair : sameCountryAstronautPairs) {
      int astronautA = astronautsPair[0];
      int astronautB = astronautsPair[1];
      if (astronautCountry[astronautA] == NOT_DEFINED
          && astronautCountry[astronautB] == NOT_DEFINED) {
        // No country found for this pair yet
        Set<Integer> newCountry = new HashSet<>();
        sameCountryAstronautsList.add(newCountry);
        newCountry.add(astronautA);
        newCountry.add(astronautB);
        int countryIndex = sameCountryAstronautsList.size() - 1;
        astronautCountry[astronautA] = countryIndex;
        astronautCountry[astronautB] = countryIndex;
      } else if (astronautCountry[astronautA] != NOT_DEFINED
          && astronautCountry[astronautB] != NOT_DEFINED
          && astronautCountry[astronautB] != astronautCountry[astronautA]) {
        // Both astronauts belong to a set, but different set
        // Shift B to A
        int toBeEmptySetIndex = astronautCountry[astronautB];
        Set<Integer> toBeEmptySet = sameCountryAstronautsList.get(toBeEmptySetIndex);
        Set<Integer> expandingSet = sameCountryAstronautsList.get(astronautCountry[astronautA]);
        int astronautACountryIndex = astronautCountry[astronautA];
        toBeEmptySet.forEach(astronautIndex -> {
          astronautCountry[astronautIndex] = astronautACountryIndex;
          expandingSet.add(astronautIndex);
        });
        sameCountryAstronautsList.set(toBeEmptySetIndex, new HashSet<>());
      } else if (astronautCountry[astronautA] != NOT_DEFINED) {
        astronautCountry[astronautB] = astronautCountry[astronautA];
        sameCountryAstronautsList.get(astronautCountry[astronautA]).add(astronautB);
      } else if (astronautCountry[astronautB] != NOT_DEFINED) {
        astronautCountry[astronautA] = astronautCountry[astronautB];
        sameCountryAstronautsList.get(astronautCountry[astronautB]).add(astronautA);
      }
    }

    // Calculate paired sum
    BigInteger sum = BigInteger.ZERO;

    // found out size of unpaired astronaut
    int unpairedAstronautCount = 0;
    for (int i = 0; i < n; i++) {
      if (astronautCountry[i] == -1) {
        unpairedAstronautCount++;
      }
    }
    if (unpairedAstronautCount > 1) {
      sum = sum.add(countNChoose2(unpairedAstronautCount));
    }

    int remainingCountryCount = n;
    if (unpairedAstronautCount == 0 && sameCountryAstronautsList.size() == 1) {
      return sum;
    }
    for (Set<Integer> countrySet : sameCountryAstronautsList) {
      int countrySize = countrySet.size();
      remainingCountryCount -= countrySize;
      sum = sum.add(BigInteger.valueOf(remainingCountryCount * countrySize));
    }
    return sum;
  }

  private static BigInteger countNChoose2(int astronautCount) {
    return binomial(astronautCount, 2);
  }

  static BigInteger binomial(final int N, final int K) {
    BigInteger ret = BigInteger.ONE;
    for (int k = 0; k < K; k++) {
      ret = ret.multiply(BigInteger.valueOf(N - k))
          .divide(BigInteger.valueOf(k + 1));
    }
    return ret;
  }

  public static void main(String[] args) {
//    final Scanner scanner = new Scanner("13 8\n0 10\n1 11\n9 6\n2 9\n12 0\n0 4\n5 1\n1 11"); // 66
//    final Scanner scanner = new Scanner("5 3\n0 1\n2 3\n0 4"); // 6
//    final Scanner scanner = new Scanner("2 1\n0 1"); // 0
    final Scanner scanner = new Scanner("100000 2\n1 2\n3 4"); // 4999949998
//    final Scanner scanner = new Scanner("10 7\n0 2\n1 8\n1 4\n2 8\n2 6\n3 5\n6 9"); // 23
    String[] np = scanner.nextLine().split(" ");

    int n = Integer.parseInt(np[0]);

    int p = Integer.parseInt(np[1]);

    int[][] astronaut = new int[p][2];

    for (int i = 0; i < p; i++) {
      String[] astronautRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 2; j++) {
        int astronautItem = Integer.parseInt(astronautRowItems[j]);
        astronaut[i][j] = astronautItem;
      }
    }

    BigInteger result = journeyToMoon(n, astronaut);
    System.out.println(result);
    scanner.close();
  }
}
