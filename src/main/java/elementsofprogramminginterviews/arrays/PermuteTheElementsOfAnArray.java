package elementsofprogramminginterviews.arrays;

public class PermuteTheElementsOfAnArray {
    public void permuteArray(int[] numbers, int[] permutations) {
        for (int lastArrangedNumber = 0; lastArrangedNumber < numbers.length; lastArrangedNumber++) {
            int oldLocation = lastArrangedNumber;
            int newLocation = permutations[lastArrangedNumber];

            if (oldLocation == newLocation ) {
                continue;
            }
                int substitutedNumber = numbers[lastArrangedNumber];
                do {
                    int substitutingNumber = substitutedNumber;
                    substitutedNumber = numbers[newLocation];
                    numbers[newLocation] = substitutingNumber;
                    oldLocation = newLocation;
                    newLocation = permutations[newLocation];
                    permutations[oldLocation] = oldLocation;
                }while(permutations[newLocation] != newLocation);
        }
    }

    public static void main(String[] args) {

    }
}
