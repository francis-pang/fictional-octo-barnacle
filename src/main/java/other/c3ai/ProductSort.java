package other.c3ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <div style="display:none;font-size:1px;color:#333333;line-height:1px;overflow:hidden;">Sort an array by element frequency.</div>
 * In a warehouse, a manager would like to sort the items in the stock. Given an array of <em>n</em> item values, sort the array in ascending order, first by the number of items with a certain value,&nbsp; then by the values themselves.
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Example</strong></p>
 *
 * <p><em>n = 6</em></p>
 *
 * <p><em>items = [4, 5, 6, 5, 4, 3]</em></p>
 *
 * <p>&nbsp;</p>
 *
 * <ul>
 * 	<li>There are 2 values that occur twice<i>: [4, 4, 5, 5].</i>
 * </li>
 * 	<li>There are 2 values that occur once<i>: [3, 6].</i>
 * </li>
 * 	<li>The array of items sorted by quantity and then by value in ascending order is<em> [3, 6, 4,&nbsp; 4, 5, 5]</em>
 * </li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Function Description </strong></p>
 *
 * <p>Complete the function <em>itemsSort </em>in the editor below.</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>itemsSort has the following parameter(s):</p>
 *
 * <p>&nbsp;&nbsp;&nbsp;&nbsp;<em>int items[n]:</em>&nbsp; an array of integers to sort</p>
 *
 * <p>Returns:</p>
 *
 * <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <em>int[n]:</em> An array of integers sorted as described.</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Constraints</strong></p>
 *
 * <ul>
 * 	<li><em>1 ≤ n ≤ 2 × 10<sup>5</sup></em></li>
 * 	<li><em>1 ≤ items[i] ≤ 10<sup>6</sup></em></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <!--       <StartOfInputFormat> DO NOT REMOVE THIS LINE-->
 *
 * <details><summary class="section-title">Input Format for Custom Testing</summary>
 *
 * <div class="collapsable-details">
 * <p>Input from stdin will be processed as follows and passed to the function.</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>The first line contains an integer <em>n</em>, the size of the integer array <em>items</em>.</p>
 *
 * <p>The next <em>n</em> lines each contain an integer, <em>items[i]</em>.</p>
 * </div>
 * </details>
 *
 * <details open="open"><summary class="section-title">Sample Case 0</summary>
 *
 * <div class="collapsable-details">
 * <p><strong>Sample Input 0</strong></p>
 *
 * <pre>STDIN &nbsp;       Function
 * ----- &nbsp;       --------
 * 5 &nbsp; &nbsp; &nbsp;&nbsp; → &nbsp;  items[] size n = 5
 * 3&nbsp;&nbsp;&nbsp;&nbsp;    → &nbsp;  items  = [3, 1, 2, 2, 4]
 * 1
 * 2
 * 2
 * 4</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Sample Output 0</strong></p>
 *
 * <pre>1
 * 3
 * 4
 * 2
 * 2</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Explanation</strong></p>
 * &nbsp;
 *
 * <ul>
 * 	<li>There is a quantity of 2&nbsp;for the item&nbsp; 2&nbsp;<i>&nbsp;: [2, 2]</i>
 * </li>
 * 	<li>There is a quantity of 1&nbsp;for the items&nbsp; 1, 3 and 4&nbsp;<i>&nbsp;: [1],&nbsp;&nbsp;[3], [4]</i>
 * </li>
 * 	<li>The array of items sorted by quantity and then by value in ascending order is<em> [ 1, 3, 4, 2, 2]</em>
 * </li>
 * </ul>
 * </div>
 * </details>
 *
 * <details><summary class="section-title">Sample Case 1</summary>
 *
 * <div class="collapsable-details">
 * <p><strong>Sample Input 1</strong></p>
 *
 * <pre>STDIN &nbsp;   Function
 * -----   &nbsp; --------
 * 10&nbsp; &nbsp; → &nbsp; items[] size n = 10
 * 8&nbsp;&nbsp;&nbsp;&nbsp; → &nbsp; items = [8, 5, 5, 5, 5, 1, 1, 1, 4, 4]
 * 5
 * 5
 * 5
 * 5
 * 1
 * 1
 * 1
 * 4
 * 4
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Sample Output 1</strong></p>
 *
 * <pre>8
 * 4
 * 4
 * 1
 * 1
 * 1
 * 5
 * 5
 * 5
 * 5
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>Explanation</strong></p>
 * &nbsp;
 *
 * <ul>
 * 	<li>There is a quantity of 4&nbsp;for the item&nbsp;&nbsp;5&nbsp;<i>&nbsp;:[5, 5,&nbsp;5, 5]</i>
 * </li>
 * 	<li>There is a quantity of 3&nbsp;for the item&nbsp;&nbsp;1&nbsp;<i>&nbsp;:[1, 1, 1]</i>
 * </li>
 * 	<li>There is a quantity of&nbsp; 2 for the item&nbsp; 4&nbsp;<i>&nbsp;:[4, 4]</i>
 * </li>
 * 	<li>There is a quantity of 1&nbsp;&nbsp;for the items 8&nbsp;<i>: [8]</i>
 * </li>
 * 	<li>The array of items sorted by quantity and then by value in ascending order is <em>[ 8, 4, 4, 1, 1, 1, 5, 5, 5, 5]</em>
 * </li>
 * </ul>
 * </div>
 */
public class ProductSort {
  public static List<Integer> itemsSort(List<Integer> items) {
    // Write your code here
    Map<Integer, Integer> countById = new HashMap<>();
    for (int element : items) {
      countById.compute(element, (k, count) -> (count == null) ? 1 : count + 1);
    }

    ArrayList<NumberWithCount> collection = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : countById.entrySet()) {
      int number = entry.getKey();
      int count = entry.getValue();
      NumberWithCount numberWithCount = new NumberWithCount(count, number);
      collection.add(numberWithCount);
    }
    Collections.sort(collection);
    ArrayList<Integer> result = new ArrayList<>();
    for (NumberWithCount element : collection) {
      int count = element.count;
      int number = element.number;
      for (int i = 0; i < count; i++) {
        result.add(number);
      }
    }
    return result;
  }

  static class NumberWithCount implements Comparable<NumberWithCount> {
    public int count;
    public int number;

    public NumberWithCount(int count, int number) {
      this.count = count;
      this.number = number;
    }

    @Override
    public int compareTo(NumberWithCount that) {
      if (this.count != that.count) {
        return this.count - that.count;
      } else if (this.number != that.number) {
        return this.number - that.number;
      } else {
        return 0;
      }
    }
  }
}

