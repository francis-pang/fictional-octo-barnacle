package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfusingNumber {
  public boolean confusingNumber(int number) {
    int[] digits = convertToIntArray(number);
    Map<Integer, Integer> table = buildTable();
    StringBuilder sb = new StringBuilder();
    for (int digit : digits) {
      if (!table.containsKey(digit)) {
        return false;
      }
      sb.append(table.get(digit));
    }
    int reversed = Integer.parseInt(sb.reverse().toString());
    return reversed != number;
  }

  private Map<Integer, Integer> buildTable() {
    Map<Integer, Integer> table = new HashMap<>();
    table.put(0, 0);
    table.put(1, 1);
    table.put(6, 9);
    table.put(8, 8);
    table.put(9, 6);
    return table;
  }

  private int[] convertToIntArray(int number) {
    ArrayList<Integer> list = new ArrayList<>();
    while (number > 9) {
      list.add(number % 10);
      number /= 10;
    }
    list.add(number);
    Collections.reverse(list);
    int[] array = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      array[i] = list.get(i);
    }
    return array;
  }
}