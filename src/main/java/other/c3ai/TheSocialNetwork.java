package other.c3ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <div class="ps-content">
 * <div class="preheader" style="display:none;">Assemble user groups based on the criteria for a valid group.</div>
 *
 * <p>A social network has <em>n</em> active users, numbered from <em>0</em> to <em>n — 1</em>, who selectively friend other users to create groups of friends within the network. We define the following:</p>
 *
 * <ul>
 * 	<li>Two users, <em>x</em> and <em>y</em>, are <em>direct</em> friends if they friend each other on the network.</li>
 * 	<li>Two users, <em>x</em> and <em>z</em>, are <em>indirect</em> friends if there exists some direct friend, <em>y</em>, common to both users <em>x</em> and <em>z</em>.</li>
 * 	<li>Two users, <em>x</em> and <em>y</em>, belong to the same <em>group</em> if they are friends (either directly or indirectly) with each other. In other words, if user <em>x</em> is part of a group, then all of user <em>x</em>'s friends and friends of friends belong to the same group.</li>
 * 	<li>We describe the number of people in each group as an array of <em>n</em> integers, <em>counts</em>, where each <em>counts<sub>i</sub></em> (<em>0 ≤ i &lt; n</em>) denotes the total number of users in the group that user <em>i</em> belongs to. For example, if <em>counts = [3, 3, 3, 3, 3, 1, 3]</em>, then there are three groups; users <em>0</em>, <em>1</em>, <em>2</em>, <em>3</em>, <em>4</em>, and <em>6</em> are in one of two <em>3</em>-person groups, and user <em>5</em> is in a <em>1</em>-person group.
 * 	<table style="width: 50%;">
 * 		<colgroup>
 * 			<col style="width: 30%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 			<col style="width: 10%;">
 * 		</colgroup>
 * 		<tbody>
 * 			<tr>
 * 				<th style="text-align: right;">ID</th>
 * 				<td>0</td>
 * 				<td>1</td>
 * 				<td>2</td>
 * 				<td>3</td>
 * 				<td>4</td>
 * 				<td>5</td>
 * 				<td>6</td>
 * 			</tr>
 * 			<tr>
 * 				<th style="text-align: right;">Group Size</th>
 * 				<td>3</td>
 * 				<td>3</td>
 * 				<td>3</td>
 * 				<td>3</td>
 * 				<td>3</td>
 * 				<td>1</td>
 * 				<td>3</td>
 * 			</tr>
 * 		</tbody>
 * 	</table>
 * 	</li>
 * 	<li>A group is <em>valid</em> if all the users in the group have minimal ID numbers. In other words, a group of size <em>k</em> must contain the <em>k</em> smallest ID numbers belonging to a group of that size with respect to the smallest user ID in the group. For example, if <em>counts = [3, 3, 3, 3, 3, 1, 3]</em>, then the grouping <em>[0, 1, 2]</em>, <em>[3, 4, 6]</em>, and <em>[5]</em> is valid; however, the grouping <em>[0, 1, 4]</em>, <em>[2, 3, 6]</em>, and <em>[5]</em> is <em>not valid</em> because the group <em>[0, 1, 4]</em> does not contain the three smallest user IDs for the set of user IDs belonging to <em>3</em>-person groups (i.e., <em>{0, 1, 2, 3, 4, 6}</em>).
 * 	<details title="Click bar to open/close the example."><summary class="section-title">Example</summary>
 * 	<figure style="background-color: transparent;"><img alt="Valid groups for counts = [3, 3, 3, 3, 3, 1, 3]" src="https://s3.amazonaws.com/istreet-assets/RqCmzBHuKKdjASs0K4Xhxw/The-Social-Network-PS-Valid.png"> <img alt="Valid and Invalid groups for counts = [3, 3, 3, 3, 3, 1, 3]" src="https://s3.amazonaws.com/istreet-assets/bK_6rL2ICxx11-f0On1tZA/The-Social-Network-PS-Invalid.png">
 * 	<figcaption>The grouping on the right is <em>invalid</em> because the first group does not contain the three smallest possible user IDs for that grouping (i.e., user ID <em>4</em> is the fourth smallest user ID that can go in a group of <em>3</em> and is also greater than or equal to the smallest ID in the group, <em>0</em>).</figcaption>
 * 	</figure>
 * 	</details>
 * 	</li>
 * 	<li>We print the information for each valid group on a new line in the format <code style="color:black;">user<sub>smallest ID</sub> … user<sub>largest ID</sub></code>, where the users within each group are ordered by ascending ID and the groups themselves are ordered by ascending <em>user<sub>smallest ID</sub></em>. For example, we print the valid grouping <em>[0, 1, 2]</em>, <em>[3, 4, 6]</em>, and <em>[5]</em> for <em>counts = [3, 3, 3, 3, 3, 1, 3]</em> as:
 * 	<pre style="width:200px;">0 1 2
 * 3 4 6
 * 5</pre>
 * 	</li>
 * </ul>
 *
 * <p>Complete the <em>socialGraphs</em> function. It has one parameter: an array of <em>n</em> integers, <em>counts</em>, where each <em>counts<sub>i</sub></em> denotes the total number of users in the group that user <em>i</em> belongs to. The function must print the information for each <em>valid group</em> in the format specified above.</p>
 *
 * <p class="section-title" title="Locked stub code reads input from stdin and passes it to the function.">Input Format</p>
 *
 * <p>The first line contains an integer, <em>n</em>, describing the number of elements in <em>counts</em> (i.e., the total number of users active on the social network).<br>
 * Each line <em>i</em> of <em>n</em> subsequent lines (where <em>0 ≤ i &lt; n</em>) contains an integer describing <em>counts<sub>i</sub></em>.</p>
 *
 * <p class="section-title" title="Guarantees about the test case dataset.">Constraints</p>
 *
 * <ul>
 * 	<li><em>1 ≤ n ≤ 2 × 10<sup>5</sup></em></li>
 * 	<li>
 * <em>1 ≤ counts<sub>i</sub> ≤ n</em>, where <em>0 ≤ i &lt; n</em>.</li>
 * 	<li>It is guaranteed that a valid grouping always exists for the given <em>counts</em> array.</li>
 * </ul>
 *
 * <p class="section-title" title="Locked stub code in the editor prints the returned value(s) to STDOUT.">Output Format</p>
 *
 * <p>The function must <em>print</em> the information for each <em>valid group</em> on a new line. The users within a group must be ordered by ascending user ID, and each group must be ordered by ascending smallest user ID.</p>
 *
 * <details open="open"><summary class="section-title">Sample Case 0</summary>
 *
 * <p class="section-title">Sample Input</p>
 *
 * <pre>4
 * 2
 * 2
 * 2
 * 2</pre>
 *
 * <p class="section-title">Sample Output</p>
 *
 * <pre>0 1
 * 2 3</pre>
 *
 * <p class="section-title">Explanation</p>
 *
 * <p>We express <em>counts = [2, 2, 2, 2]</em> as the following table of group sizes:</p>
 *
 * <table style="width: 40%;">
 * 	<tbody>
 * 		<tr>
 * 			<th style="text-align: right;">ID</th>
 * 			<td>0</td>
 * 			<td>1</td>
 * 			<td>2</td>
 * 			<td>3</td>
 * 		</tr>
 * 		<tr>
 * 			<th style="text-align: right;">Group Size</th>
 * 			<td>2</td>
 * 			<td>2</td>
 * 			<td>2</td>
 * 			<td>2</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 *
 * <p>The <em>valid</em> grouping here is the groups <em>[0, 1]</em> and <em>[2, 3]</em>:</p>
 *
 * <figure style="background-color:transparent;"><img alt="Sample Case 0" src="https://s3.amazonaws.com/istreet-assets/MykgaOrz0qQPQs22Lzbqwg/The-Social-Network-Sample0.png">
 * <figcaption>Possible groupings for <em>counts = [2, 2, 2, 2]</em>.</figcaption>
 * </figure>
 *
 * <p>We then print each group on a new line, where the groups and their user IDs are listed in ascending order.</p>
 * </details>
 *
 * <details title="Click bar to open/close the example."><summary class="section-title">Sample Case 1</summary>
 *
 * <p class="section-title">Sample Input</p>
 *
 * <pre>3
 * 1
 * 1
 * 1</pre>
 *
 * <p class="section-title">Sample Output</p>
 *
 * <pre>0
 * 1
 * 2</pre>
 *
 * <p class="section-title">Explanation</p>
 *
 * <p>We express <em>counts = [1, 1, 1]</em> as the following table of group sizes:</p>
 *
 * <table style="width: 40%;">
 * 	<tbody>
 * 		<tr>
 * 			<th style="text-align: right;">ID</th>
 * 			<td>0</td>
 * 			<td>1</td>
 * 			<td>2</td>
 * 		</tr>
 * 		<tr>
 * 			<th style="text-align: right;">Group Size</th>
 * 			<td>1</td>
 * 			<td>1</td>
 * 			<td>1</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 *
 * <p>The only possible grouping here is the <em>valid</em> one where we have three groups, <em>[0]</em>, <em>[1]</em>, and <em>[2]</em>:</p>
 *
 * <figure style="background-color:transparent;"><img alt="Sample Case 1" src="https://s3.amazonaws.com/istreet-assets/6BpjiR16X3V2TGmCuJ8HgQ/The-Social-Network-Sample1.png">
 * <figcaption>Grouping for <em>counts = [1, 1, 1]</em>.</figcaption>
 * </figure>
 *
 * <p>We then print each group on a new line, ordered by ascending smallest user ID.</p>
 * </details>
 *
 * <details title="Click bar to open/close the example."><summary class="section-title">Sample Case 2</summary>
 *
 * <p class="section-title">Sample Input</p>
 *
 * <pre>5
 * 2
 * 1
 * 1
 * 2
 * 1
 *
 * </pre>
 *
 * <p class="section-title">Sample Output</p>
 *
 * <pre>0 3
 * 1
 * 2
 * 4</pre>
 *
 * <p class="section-title">Explanation</p>
 *
 * <p>We express <em>counts = [2, 1, 1, 2, 1]</em> as the following table of group sizes:</p>
 *
 * <table style="width: 50%;">
 * 	<tbody>
 * 		<tr>
 * 			<th style="text-align: right;">ID</th>
 * 			<td>0</td>
 * 			<td>1</td>
 * 			<td>2</td>
 * 			<td>3</td>
 * 			<td>4</td>
 * 		</tr>
 * 		<tr>
 * 			<th style="text-align: right;">Group Size</th>
 * 			<td>2</td>
 * 			<td>1</td>
 * 			<td>1</td>
 * 			<td>2</td>
 * 			<td>1</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 *
 * <p>The only possible grouping here is the <em>valid</em> one where we have four groups, <em>[0, 3]</em>, <em>[1]</em>, <em>[2]</em>, and <em>[4]</em>:</p>
 *
 * <figure style="background-color:transparent;"><img alt="Sample Case 2" src="https://s3.amazonaws.com/istreet-assets/LyKCrE_UNkcGa01lLQbWqQ/The-Social-Network-Sample2.png">
 * <figcaption>Grouping for <em>counts = [2, 1, 1, 2, 1]</em>.</figcaption>
 * </figure>
 *
 * <p>We then print each group on a new line, ordered by ascending smallest user ID.</p>
 * </details>
 * </div>
 */
public class TheSocialNetwork {
  public static void socialGraphs(List<Integer> counts) {
    // Write your code here
    Map<Integer, ArrayList<Integer>> groupContentByGroupSize = new HashMap<>();
    ArrayList<ArrayList<Integer>> results = new ArrayList<>();
    // Storing the grouping
    for (int id = 0; id < counts.size(); id++) {
      int groupSize = counts.get(id);
      if (!groupContentByGroupSize.containsKey(groupSize)) {
        ArrayList<Integer> newArrayList = new ArrayList<>();
        groupContentByGroupSize.put(groupSize, newArrayList);
        results.add(newArrayList);
      }
      ArrayList<Integer> groupContent = groupContentByGroupSize.get(groupSize);
      groupContent.add(id);
      if (groupContent.size() == groupSize) {
        groupContentByGroupSize.remove(groupSize);
      }
    }

    // Printing
    for (ArrayList<Integer> groupContent : results) {
      for (int id : groupContent) {
        System.out.printf("%d ", id);
      }
      System.out.println();
    }
  }
}
