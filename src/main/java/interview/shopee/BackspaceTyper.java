package interview.shopee;

/*
 * abc_ => ab
 * ac_b_b => ab
 *
 * Typing:
 * abc+ => ab
 * ac+b+b => ab
 * result: True
 * Assume: lower case and underscore
 * underscore -> backspace
 *
 * Input: 2 string
 * Output: if the interpreted result is equal
 *
 * Brute force:
 * 1. parse the first string, then form the 1st word derive
 * 2. parse the 2nd string, then form the 2nd word derive
 * 3. Comparision
 * 4a. Same => true
 * 4b. Different => false
 * Time: O(a+b) + O(max(a,b))
 * Space: O(a+b)
 *
 * Optimised:
 * 1. Parse both array, note all the underscore position
 * 2. After both pass, charAt comparison
 *
 * Optimised:
 * 1. Do comparison on the go
 * 2. See backspace, count, go back that space
 *
 * Time: O(a+b) O(min(a,b))
 * Space: O(a+b)
 *
 * Start to compare from backwards
 * Time: O(a+b)
 * Space: O(1)
 */

public class BackspaceTyper {
  private static boolean isStringSame(String a, String b) {
    int aPos = a.length() - 1;
    int bPos = b.length() - 1;
    int aBack = 0;
    int bBack = 0;

    do {
      if (a.charAt(aPos) == '_') {
        aBack++;
        aPos--;
      }
      while (aBack > 0 && aPos >= 0) {
        if (a.charAt(aPos) == '_') {
          aBack++;
        } else {
          aBack--;
        }
        aPos--;
      }
      if (aBack > 0 || bPos < 0) {
        return false;
      }
      char aChar = a.charAt(aPos);

      if (b.charAt(bPos) == '_') {
        bBack++;
        bPos--;
      }
      while (bBack > 0 && bPos >= 0) {
        if (b.charAt(bPos) == '_') {
          bBack++;
        } else {
          bBack--;
        }
        bPos--;
      }

      if (bBack > 0 || bPos < 0) {
        return false;
      }
      char bChar = b.charAt(aPos);
      if (bChar == aChar) {
        aPos--;
        bPos--;
      } else {
        return false;
      }
    } while (aPos >= 0 && bPos >= 0);
    return aPos == 0 && bPos == 0;
  }
}

