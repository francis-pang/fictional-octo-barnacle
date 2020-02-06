package crackingthecodinginterview.moderate;

public class ContiguousSequence {
  public int biggestSum(int[] array) {
    int endP = condense(array);
    int biggest = Integer.MIN_VALUE;
    int pos = -1;
    while (biggest <= 0 && pos <= endP) {
      pos++;
      if (biggest < array[pos]) {
        biggest = array[pos];
      }
    }
    boolean usedPrevious = true;
    while ((pos + 2) <= endP) {
      int sum = Integer.MIN_VALUE;
      if (usedPrevious) {
        sum = biggest + array[pos + 1] + array[pos + 2];
      }
      int nextPos = array[pos + 2];
      int big = max(biggest, sum, nextPos);
      if (big == biggest) {
        usedPrevious = false;
      } else {
        usedPrevious = true;
        biggest = big;
      }
      pos += 2;
    }
    return biggest;
  }

  private int condense(int[] array) {
    int endP = 0;
    int accmulator = 0;
    int start = 0;
    for (; start < array.length; start++) {
      if (array[start] != 0) {
        accmulator = array[start];
        break;
      }
    }
    if (accmulator == 0) {
      return 0;
    }
    for (int i = start + 1; i < array.length; i++) {
      if (array[i] == 0) {
        continue;
      }
      if ((accmulator < 0 && array[i] < 0) ||
          (accmulator > 0 && array[i] > 0)) {
        accmulator += array[i];
      } else {
        array[endP++] = accmulator;
        accmulator = array[i];
      }
    }
    array[endP] = accmulator;
    return endP;
  }

  private int max(int num1, int num2, int num3) {
    return Math.max(Math.max(num1, num2), num3);
  }

  public static void main(String[] args) {
    ContiguousSequence contiguousSequence = new ContiguousSequence();
    int answer = contiguousSequence.biggestSum(new int[]{0, 2, -8, -1, 3, -2, -3, 4, 5, 10});
    System.out.println(answer);
    answer = contiguousSequence.biggestSum(new int[]{0, 0, 0, 0});
    System.out.println(answer);
    answer = contiguousSequence.biggestSum(new int[]{-5, -4, -5, -4});
    System.out.println(answer);
    answer = contiguousSequence.biggestSum(new int[]{2, -8, 5, -3, 6, -7, 5, -5, 0});
    System.out.println(answer);
  }
}
