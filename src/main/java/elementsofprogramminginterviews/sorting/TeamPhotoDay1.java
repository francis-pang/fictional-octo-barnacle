package elementsofprogramminginterviews.sorting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TeamPhotoDay1 {
    public boolean possibleToTakePhoto(int[] teamAHeights, int[] teamBHeights) {
        // Brute force, sort both team, and check if there there is a combination that all players is shorter than the other team
        // O(n*logn) -> Time, based in place sorting
        // O(1) -> in-place sorting

        // Can do O(n)?
        // If use counting sort, then it is going to be O(nk) time, and O(k) space
        // We can set a reasonable bound of the player height, this will set the limit of k, make it a constant.
        // Use reverse sorted representation
        final int MAX_HEIGHT = 300;
        sortReverseOrder(teamAHeights, MAX_HEIGHT);
        sortReverseOrder(teamBHeights, MAX_HEIGHT);
        if (teamAHeights.length > teamBHeights.length) { // team A bigger, so had to be at the back
            return checkIfAllElementHigher(teamAHeights, teamBHeights, teamAHeights.length - teamBHeights.length);
        } else if (teamAHeights.length < teamBHeights.length) { // team B bigger, so had to be at the back
            return checkIfAllElementHigher(teamBHeights, teamAHeights, teamBHeights.length - teamAHeights.length);
        } else if (teamAHeights[0] > teamBHeights[0]) { // team A and B same size, and A is taller
            return checkIfAllElementHigher(teamAHeights, teamBHeights, 0);
        } else { // team A and B same size, and B is taller
            return checkIfAllElementHigher(teamBHeights, teamAHeights, 0);
        }
    }

    private boolean checkIfAllElementHigher(int[] biggerArray, int[] smallerArray, int offSet) {
        for (int i = 0; i < biggerArray.length; i++) {
            if (biggerArray[i + offSet] <= smallerArray[i]) {
                return false;
            }
        }
        return true;
    }

    private void sortReverseOrder(int[] array, final int MAX_SIZE) {
        int[] arrayOccurrences = new int[MAX_SIZE];
        for (int element : array) {
            arrayOccurrences[element]++;
        }
        int counter = 0;
        for (int i = MAX_SIZE - 1; i >= 0; i--) {
            while (arrayOccurrences[i] > 0) {
                array[counter] = i;
                counter++;
            }
        }
    }
}
