package crackingthecodinginterview.sortingandsearching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 */
public class GroupAnagrams {
  public void groupAnagrams(String[] strings) {
    Map<String, Range> positionsOfString = new HashMap<>();
    for (int index = 0; index < strings.length; index++) {
      String sortedString = sortString(strings[index]);
      if (positionsOfString.containsKey(sortedString)) {
        int endOfRange = positionsOfString.get(sortedString).end;
        swap(strings, positionsOfString, endOfRange + 1, index);
      } else {
        positionsOfString.put(sortedString, new Range(index, index));
      }
    }
  }

  private String sortString(String string) {
    char[] charArray = string.toCharArray();
    Arrays.sort(charArray);
    return Arrays.toString(charArray);
  }

  private void swap(String[] strings, Map<String, Range> positionsOfString, int swapA, int swapB) {
    String sortedString = sortString(strings[swapA]);
    Range range = positionsOfString.get(sortedString);
    int rangeStart = range.start;
    int rangeEnd = range.end;
    if (range.end - range.start > 0) {
      swap(strings, positionsOfString, range.end + 1, swapB);
      swap(strings, swapA, range.end + 1);
    } else {
      swap(strings, positionsOfString, swapA, swapB);
    }
    positionsOfString.put(sortedString, new Range(rangeStart, rangeEnd + 1));
  }

  private void swap(String[] strings, int swapA, int swapB) {
    String temp = strings[swapB];
    strings[swapB] = strings[swapA];
    strings[swapA] = temp;
  }

  class Range {
    int start;
    int end;

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
