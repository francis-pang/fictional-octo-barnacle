package leetcode;

import java.util.Arrays;

public class _1bitAnd2bitCharacters {
  private static final int NOT_FILLED = -1;
  private static final int TRUE = 1;
  private static final int FALSE = 0;

  public boolean endWithFirst(String s) {
    String rs = reverse(s);
    int[] results = new int[s.length()];
    Arrays.fill(results, NOT_FILLED);
    return convert(endWithZero(rs, 1, results));
  }

  private boolean convert(int endWithZero) {
    return endWithZero == TRUE;
  }

  private int endWithZero(String rs, int pos, int[] results) {
    if (results[pos] != NOT_FILLED) {
      return results[pos];
    }
    int result = FALSE;
    char c = rs.charAt(pos);
    char n = 0;
    if (pos < rs.length()) {
      n = rs.charAt(pos + 1);
    }
    if (c == '0') {
      if (pos == rs.length() - 1) {
        results[pos] = TRUE;
        return result;
      }
      result = endWithZero(rs, pos + 1, results);
      if (result != TRUE && n == '1') {
        if (pos + 1 == rs.length() - 1) {
          results[pos] = TRUE;
          return result;
        }
        result = endWithZero(rs, pos + 2, results);
      }
    } else if (n == '1') { // c == '1'
      if (pos + 1 == rs.length() - 1) {
        results[pos] = TRUE;
        return result;
      }
      result = endWithZero(rs, pos + 2, results);
    }
    results[pos] = result;
    return result;
  }

  private String reverse(String string) {
    return new StringBuilder(string).reverse().toString();
  }

  public boolean isOneBitCharacter(int[] bits) {
    int currentPos = 0;
    while (currentPos < bits.length) {
      if (currentPos == bits.length - 1) {
        return true;
      }
      int bit = bits[currentPos];
      if (bit == 0) {
        currentPos++;
      } else {
        currentPos += 2;
      }
    }
    return false;
  }
}
