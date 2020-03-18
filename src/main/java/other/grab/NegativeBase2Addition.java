package other.grab;

/**
 * <p>In base −2, integers are represented by sequences of bits in the following way. Bits are ordered from the least to the most significant. Sequence B of N bits represents the number: <b>sum</b>{ B[i]*(−2)<sup>i</sup>  for  i = 0..N−1 }. The empty sequence represents 0. For example:</p>
 * <tt style="white-space:pre-wrap">100111 represents -23
 * 001011 represents -12
 * 10011  represents 9
 * 001    represents 4,
 * because:
 * <p>
 * | 1 | -2 | 4 | -8 | 16 | -32 | ... |
 * |---|----|---|----|----|-----|-----|
 * | 1 | 0  | 0 | 1  | 1  | 1   |     | = 1 + 0 + 0 + (-8) + 16 + (-32) = -23
 * | 0 | 0  | 1 | 0  | 1  | 1   |     | = 0 + 0 + 4 + 0    + 16 + (-32) = -12
 * | 1 | 0  | 0 | 1  | 1  |     |     | = 1 + 0 + 0 + (-8) + 16         = 9
 * | 0 | 0  | 1 |    |    |     |     | = 0 + 0 + 4                     = 4</tt>
 * <p>Note that such a representation is suitable for both positive and negative integers.</p>
 * <p>Write a function:</p>
 * <blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int[] solution(int[] A, int[] B); }</tt></p></blockquote>
 * <p>that, given two arrays of bits:</p>
 * <blockquote><ul style="margin: 10px;padding: 0px;"><li>A of length M, containing a sequence representing some integer X, and</li>
 * <li>B of length N, containing a sequence representing some integer Y,</li>
 * </ul>
 * </blockquote><p>returns the shortest sequence of bits representing X + Y.</p>
 * <p>Result array should be returned as an array of integers.</p>
 * <p>For example, given A = [0,1,1,0,0,1,0,1,1,1,0,1,0,1,1] (X = 5730) and B = [0,0,1,0,0,1,1,1,1,1,0,1] (Y = −2396), the function should return [0,1,0,1,1,0,0,0,1,0,1,1,1] (X + Y = 3334).</p>
 * <p>Write an <b><b>efficient</b></b> algorithm for the following assumptions:</p>
 * <blockquote><ul style="margin: 10px;padding: 0px;"><li>M and N are integers within the range [<span class="number">0</span>..<span class="number">100,000</span>];</li>
 * <li>each element of array A is an integer that can have one of the following values: 0, 1;</li>
 * <li>each element of array B is an integer that can have one of the following values: 0, 1.</li>
 * </ul>
 */
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