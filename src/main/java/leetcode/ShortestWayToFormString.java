package leetcode;

public class ShortestWayToFormString {
  class Solution {
    private static final int TARGET_CANNOT_BE_FORM = -1;

    public int shortestWay(String source, String target) {
      if (source == null || source.length() == 0 ||
          target == null || target.length() == 0) {
        return TARGET_CANNOT_BE_FORM;
      }
      if (source.equals(target)) {
        return 1;
      }
      int currentTargetIndex = 0;
      int minimumSubsequence = 0;
      char[] sourceArray = source.toCharArray();
      char[] targetArray = target.toCharArray();
      while (currentTargetIndex != TARGET_CANNOT_BE_FORM &&
          currentTargetIndex < target.length()) {
        minimumSubsequence++;
        currentTargetIndex = nextCharacter(sourceArray, targetArray, currentTargetIndex);
      }
      return (currentTargetIndex == TARGET_CANNOT_BE_FORM) ? TARGET_CANNOT_BE_FORM : minimumSubsequence;
    }

    /**
     * Find the next characters of the target string which hasn't been found a subsequence
     *
     * @param source           the source string
     * @param target           the target string
     * @param targetStartIndex
     * @return next start index of the remaining string array, -1 if there is character which can't be found,
     * outOfBound if all characters in the array is found
     */
    private int nextCharacter(char[] source, char[] target, int targetStartIndex) {
      char characterToLocate = target[targetStartIndex];
      int endIndex = targetStartIndex;
      for (int index = 0; index < source.length; index++) {
        if (source[index] == characterToLocate) {
          endIndex++;
          if (endIndex == target.length) {
            break;
          }
          characterToLocate = target[endIndex];
        }
      }
      return (endIndex == targetStartIndex) ? TARGET_CANNOT_BE_FORM : endIndex;
    }
  }
}
