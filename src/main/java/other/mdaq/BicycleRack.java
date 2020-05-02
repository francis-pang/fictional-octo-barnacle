package other.mdaq;

/**
 * You want to park your bicycle in a bicycle parking area where bike rankcs are aligned in a row. There are already
 * N bikes parked there (each bike is attached to exactly one rack, but a rank can have multiple bikes attached to
 * it). We call ranks that already have bikes attached used.
 *
 * You want to park your bike in a rack in the parking area according to the following criteria:
 * 1. The chosen rack must lie between the first and the last used racks (inclusive).
 * 2. The distance between the chosen rack and any other used rack is as big as possible.
 *
 * A description of the bikes already parked in the racks is given racks is given in a non-empty zero-indexed array
 * A: element A[K] denotes the position of the rack to which the bike number K is attached (for O <= K < N). The
 * central position in the parking area is position 0. A positive value means that the rack is located A[K] meters to
 * the right of the central position 0; a negative value means that it's located |A[K]| meters to the left (the
 * absolute value of A[K]).
 *
 * For example, consider array such that:
 * [10, 0, 8, 2, -1, 12, 11, 3]
 *
 * The occupied racks are represented by '1's.
 * [ 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1]
 * [-1,00,01,02,03,04,05,06,07,08,09,10,11,12]
 *
 * You can attached your bike to any rack between rack -1 and rack 12(including these two racks). In order to
 * maximize the distance to any used rack, you should attach your bike either to rack 5 or rack 6. The resulting
 * distance is 2 meters (from 5 to used rack 3, or from 6 to used rack 8, respectively).
 *
 * Write a function:
 *   class BicycleRack { public int solution(int[] array); }
 *
 * that, given a non-empty zero-indexed array A of N integers, returns the largest possible distance in meters
 * between the chosen rack and any other used rack.
 *
 * Given array A shown above, the function should be return 2, as explained above.
 *
 * For the following array A:
 * [5,5]
 *
 * the function should return 0, as you can attach your bike only to rack 5.
 *
 * Write an efficient algorithm for the following assumptions:
 * - N is an integrer within the range [2, 100,000]
 * - each element of array A is an integer within the range [-1,000,000,000, 1,000,000,000]
 */
public class BicycleRack {
  public int solution(int[] array) {
    int smallest = Integer.MAX_VALUE;
    int biggest = Integer.MIN_VALUE;

    for (int element : array) {
      if (smallest > element) {
        smallest = element;
      }
      if (biggest < element) {
        biggest = element;
      }
    }

    int offSet = smallest;
    boolean[] bikeRacks = new boolean[biggest + Math.abs(offSet) + 1];
    for (int element : array) {
      int adjustBikePosition =element - offSet;
      bikeRacks[adjustBikePosition] = true;
    }

    int biggestBikeGap = 0;
    int counter = 0;
    for (int i = 0; i < bikeRacks.length; i++) {
      boolean isBikeRackOccupied = bikeRacks[i];
      if (!isBikeRackOccupied) {
        counter++;
      } else {
        if (counter > biggestBikeGap) {
          biggestBikeGap = counter;
        }
        counter = 0;
      }
    }
    return (biggestBikeGap / 2);
  }

  public static void main(String[] args) {
    int[] array = new int[]{10, 0, 8, 2, -1, 12, 11, 3};
    BicycleRack bicycleRack = new BicycleRack();
    System.out.println(bicycleRack.solution(array));
  }
}
