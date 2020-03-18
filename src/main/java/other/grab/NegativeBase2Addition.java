package other.grab;

public class NegativeBase2Addition {
  public int[] solution(int[] A, int[] B) {
    // write your code in Java SE 8
    long aRep = convertToNumber(A);
    long bRep = convertToNumber(B);
    long answer = aRep + bRep;
    return convertToBase(answer);
  }

  private int[] convertToBase(long answer) {
    if (answer == 0) {
      return new int[]{0};
    }
    StringBuilder convertedStringBuilder = new StringBuilder();
    while (answer != 0) {
      long reminder = answer % (-2);
      answer /= (-2);
      if (reminder < 0) {
        reminder += 2;
        answer += 1;
      }
      convertedStringBuilder.append(reminder);
    }
    return convertToIntArray(convertedStringBuilder.toString());
  }

  private int[] convertToIntArray(String string) {
    int[] array = new int[string.length()];
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      array[i] = Character.digit(c, 10);
    }
    return array;
  }

  private long convertToNumber(int[] a) {
    long total = 0;
    for (int i = 0; i < a.length; i++) {
      double representation = Math.pow(-2, i);
      if (a[i] == 1) {
        total += (long) representation;
      }
    }
    return total;
  }
}