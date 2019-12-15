package elementsofprogramminginterviews.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiplyTwoArbitraryPrecisionIntegers {
  public int[] multiple(int[] a, int[] b) {
    List<Integer> product = new ArrayList<>();
    int carryOver = 0;
    for (int i = a.length - 1; i >= 0; i--) {
      int offSet = a.length - i - 1;
      for (int j = b.length - 1; j >= 0; j--) {
        int productOfNum = a[i] * b[j] + carryOver;
        int position = offSet + (b.length - 1 - j);
        if (product.size() > position) {
          productOfNum += product.get(position);
          int digit = productOfNum % 10;
          product.set(position, digit);
        } else {
          int digit = productOfNum % 10;
          product.add(digit);
        }
        carryOver = productOfNum / 10;
      }
      int poisition = offSet + b.length;
      while (carryOver > 0) {
        if (product.size() > poisition) {
          carryOver += product.get(poisition);
          int digit = carryOver % 10;
          product.set(poisition, digit);
        } else {
          int digit = carryOver % 10;
          product.add(digit);
        }
        carryOver /= 10;
      }
    }
    Collections.reverse(product);
    return product.stream().mapToInt(j -> j).toArray();
  }
}
