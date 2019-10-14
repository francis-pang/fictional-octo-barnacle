package leetcode;

public class MinimumDominoRotationsForEqualRow {
  static class Solution {
    private static final int CANNOT_FORM_ALL_SAME_VALUE = Integer.MAX_VALUE;

    public int minDominoRotations(int[] A, int[] B) {
      final int CANNOT_FORM = -1;
      // Edge case
      // Zero size
      if (A == null || A.length == 0 ||
          B == null || B.length == 0 ||
          A.length != B.length) {
        return CANNOT_FORM;
      }

      if (A.length == 1) {
        return 0;
      }

      // Array A of all values of first value of array A
      int rotationNeedForArrayAUseFirstA = 0;

      // Array B of all values of first value of array A
      int rotationNeedForArrayBUseFirstA = 0;

      // Array A of all values of first value of array B
      int rotationNeedForArrayAUseFirstB = 0;

      // Array B of all values of first value of array B
      int rotationNeedForArrayBUseFirstB = 0;

      final int firstA = A[0];
      final int firstB = B[0];

      if (A[0] != B[0]) {
        rotationNeedForArrayBUseFirstA++;
        rotationNeedForArrayAUseFirstB++;
      }

      boolean canUseAToFormAllSameValueArray = true;
      boolean canUseBToFormAllSameValueArray = true;
      for (int processingIndex = 1; processingIndex < A.length; processingIndex++) {
        if (canUseAToFormAllSameValueArray) {
          if ((A[processingIndex] != firstA && B[processingIndex] != firstA)) {
            canUseAToFormAllSameValueArray = false;
          } else if (A[processingIndex] != B[processingIndex]) {
            if (A[processingIndex] == firstA) {
              rotationNeedForArrayBUseFirstA++;
            } else { // B[processingIndex] == firstA
              rotationNeedForArrayAUseFirstA++;
            }
          }
        }

        if (canUseBToFormAllSameValueArray) {
          if (A[processingIndex] != firstB && B[processingIndex] != firstB) {
            canUseBToFormAllSameValueArray = false;
          } else if (A[processingIndex] != B[processingIndex]) {
            if (A[processingIndex] == firstB) {
              rotationNeedForArrayBUseFirstB++;
            } else {// B[processingIndex] == firstB
              rotationNeedForArrayAUseFirstB++;
            }
          }
        }
      }
      if (!canUseAToFormAllSameValueArray && !canUseBToFormAllSameValueArray) {
        return CANNOT_FORM;
      }

      rotationNeedForArrayAUseFirstA = convertToInfinityForZeroValue(rotationNeedForArrayAUseFirstA);
      rotationNeedForArrayBUseFirstA = convertToInfinityForZeroValue(rotationNeedForArrayBUseFirstA);
      rotationNeedForArrayAUseFirstB = convertToInfinityForZeroValue(rotationNeedForArrayAUseFirstB);
      rotationNeedForArrayBUseFirstB = convertToInfinityForZeroValue(rotationNeedForArrayBUseFirstB);

      int minimumRotationNeedToFormAllA = calculateMinimumToFormAllArrayOfSameValue(rotationNeedForArrayAUseFirstA,
          rotationNeedForArrayBUseFirstA, canUseAToFormAllSameValueArray);

      int minimumRotationNeedToFormAllB = calculateMinimumToFormAllArrayOfSameValue(rotationNeedForArrayAUseFirstB,
          rotationNeedForArrayBUseFirstB, canUseBToFormAllSameValueArray);

      int answer = Math.min(minimumRotationNeedToFormAllA, minimumRotationNeedToFormAllB);
      return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }

    private int convertToInfinityForZeroValue(int value) {
      return (value <= 0) ? Integer.MAX_VALUE : value;
    }

    private int calculateMinimumToFormAllArrayOfSameValue(int valueA, int valueB, boolean canFormArray) {
      return (canFormArray) ? Math.min(valueA, valueB) : CANNOT_FORM_ALL_SAME_VALUE;
    }

    public static void main(String[] args) {
      Solution solution = new Solution();
      solution.minDominoRotations(new int[]{2}, new int[]{2});
    }
  }
}
