package leetcode;

import java.util.Arrays;

public class NextPermutation {
  public void nextPermutation(int[] nums) {
    int[] descSorted = getDescSort(nums);
    int sortedElement = 0;
    boolean found = false;
    for (int i = 0; i < nums.length && !found; i++) {
      if (descSorted[i] != nums[i]) {
        sortedElement = descSorted[i];
        found = true;
      }
    }
    if (!found) {
      Arrays.sort(nums);
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == sortedElement) {
        nums[i] = nums[i- 1];
        nums[i - 1] = sortedElement;
        return;
      }
    }
  }

  private int[] getDescSort(int[] nums) {
    int[] ascArray = Arrays.copyOf(nums, nums.length);
    Arrays.sort(ascArray);
    int[] descArray = new int[ascArray.length];
    for (int i = ascArray.length - 1; i >= 0; i--) {
      descArray[ascArray.length - i - 1] = ascArray[i];
    }
    return descArray;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{7, 3, 5, 4, 6};
    NextPermutation nextPermutation = new NextPermutation();
    nextPermutation.nextPermutation(nums);
    for (int i = 0; i < nums.length; i++) {
      System.out.printf("%d,", nums[i]);
    }
    System.out.println();
  }
}
