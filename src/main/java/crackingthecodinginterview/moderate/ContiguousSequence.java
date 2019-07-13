package crackingthecodinginterview.moderate;

public class ContiguousSequence {
  public int biggestSumOf(int[] integers) {
    if (integers == null || integers.length == 0) {
      return 0;
    }
    int[] biggestSumArray = new int[integers.length];

    for (int index = 0; index < integers.length; index++) {
      if (index == 0) {
        biggestSumArray[index] = integers[index];
      }
      if (integers[index] < biggestSumArray[index - 1]) {
        biggestSumArray[index] = biggestSumArray[index - 1] + integers[index];
      } else {
        biggestSumArray[index] = integers[index];
      }
    }
    return biggestSumArray[integers.length];
  }
}
