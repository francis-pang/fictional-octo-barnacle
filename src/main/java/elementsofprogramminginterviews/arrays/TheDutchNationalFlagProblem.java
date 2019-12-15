package elementsofprogramminginterviews.arrays;

public class TheDutchNationalFlagProblem {
  public int[] ducthPartitiion(int[] array, int index) {
    int element = array[index];
    int lessThanBound = 0;
    int moreThanBound = array.length - 1;
    int countOfEqual = 0;

    while (lessThanBound + countOfEqual < moreThanBound) {
      int i = lessThanBound + countOfEqual;
      if (array[i] < element) {
        swap(array, lessThanBound, i);
        lessThanBound++;
      } else if (array[i] > element) {
        swap(array, moreThanBound, i);
        moreThanBound--;
      } else { // Equal
        countOfEqual++;
      }
    }

    for (int i = 0; i <= countOfEqual; i++) {
      array[i + lessThanBound] = element;
    }
    return array;
  }

  public void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
}
