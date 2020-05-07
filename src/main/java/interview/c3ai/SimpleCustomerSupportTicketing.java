package interview.c3ai;

import java.util.Stack;

/**
 * <div class="candidate-rich-text"><div id="e74ojct5-instruction">
 * <p>The customer support for an e-commerce business is responsible for answering all open tickets on time. Each open and&nbsp;closed tickets are&nbsp;represented by different open and closed braces respectively, based on the level of complexity to resolve them.&nbsp;&nbsp;For example, the braces '( [ {' are open tickets and need a matching&nbsp;braces '} ] )', in that order to close these tickets.</p>
 *
 * <p>The braces in a string are called balanced if the following conditions are respected:</p>
 *
 * <ul>
 * 	<li>All open braces must be closed and each closed brace must have a matching open brace</li>
 * 	<li>Any set of nested braces must be closed in order</li>
 * </ul>
 *
 * <p>Given a 2-demensional array of strings of&nbsp; braces, verify that the braces in each string are balanced.&nbsp; Return 'YES' if all conditions are valid and 'NO' otherwise.</p>
 *
 * <div class="ps-content-wrapper-v0">
 * <p class="section-title">Example</p>
 * <em>braces = ['[{}]', '[{]}']</em>
 *
 * <ul>
 * 	<li>The braces in the first string '[{}]' are balanced, because all braces are closed and all nested braces are closed in order.</li>
 * 	<li>The braces in the second string '[{]}' are not balanced,&nbsp;because the nested brace '{' was not closed first, so, the order was not respected.</li>
 * 	<li>The result is ['YES', 'NO']</li>
 * </ul>
 *
 * <p class="section-title">Function Description</p>
 *
 * <p>Complete the function <em>braces</em> in the editor below. It has the following properties:</p>
 *
 * <table class="function-params">
 * 	<tbody>
 * 		<tr>
 * 			<td class="headers">Parameters</td>
 * 			<td class="params-table-cell">
 * 			<table class="params-table">
 * 				<tbody>
 * 					<tr>
 * 						<th>Name</th>
 * 						<th>Type</th>
 * 						<th class="description">Description</th>
 * 					</tr>
 * 					<tr>
 * 						<td class="code">values</td>
 * 						<td class="code">String Array</td>
 * 						<td class="left">Input Array of Strings</td>
 * 					</tr>
 * 				</tbody>
 * 			</table>
 * 			</td>
 * 		</tr>
 * 		<tr>
 * 			<td class="headers">Return</td>
 * 			<td class="left">The function must return an array of strings where the string at each index <em>i</em> denotes whether or not the braces were balanced in a <em>values<sub>i</sub>.</em>&nbsp; The array should consist of strings &quot;YES&quot; or &quot;NO&quot; aligned with their indexes in <em>values</em>.</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 * -->
 *
 * <p class="section-title">Function Description</p>
 *
 * <p>Complete the function <em>matchingBraces </em>in the editor below.</p>
 *
 * <p><em>matchingBraces </em> has the following parameter(s):</p>
 *
 * <p>&nbsp;&nbsp;&nbsp; <em>string braces[n]:</em> an array of strings to analyze</p>
 *
 * <p>Returns:</p>
 *
 * <p>&nbsp;&nbsp;&nbsp;&nbsp;<em>string[]: </em>an array of strings consisting of '<em>YES'</em> or '<em>NO' </em>where the string at each index <em>i</em> denotes whether or not the braces were balanced in <em>braces[i].</em>&nbsp;</p>
 *
 * <p class="section-title">Constraints</p>
 *
 * <ul>
 * 	<li>1 ≤ <em>n</em> ≤ 15</li>
 * 	<li>1 ≤ length of each braces<em>[i]&nbsp;</em> ≤ 100</li>
 * 	<li>Each <em>braces[i]&nbsp;</em>consists of (, ), {, }, [, and ] only.</li>
 * </ul>
 * <!--       <StartOfInputFormat> DO NOT REMOVE THIS LINE-->
 *
 * <details title="Click bar to open/close the example."><summary class="section-title">Input Format For Custom Testing</summary>
 *
 * <div class="collapsable-details">
 * <p>Input from stdin will be processed as follows and passed to the function:</p>
 *
 * <p>The first line contains an integer <em>n</em>, the number of elements in braces.</p>
 *
 * <p>Each of the next <em>n</em> lines contains a string that describes <em>braces[i]<sub> </sub></em>where <em>0 ≤ i &lt; n</em>.</p>
 * </div>
 * </details>
 * <!--        </StartOfInputFormat> DO NOT REMOVE THIS LINE-->
 *
 * <details open="open"><summary class="section-title">Sample Case 0</summary>
 *
 * <div class="collapsable-details">
 * <p class="section-title">Sample Input</p>
 *
 * <pre>STDIN     Function
 * -----     -----
 * 2      →  braces[] size n = 2
 * {}[]() →  braces = ['{}[]()', '{[}]}']
 * {[}]}</pre>
 *
 * <p class="section-title">&nbsp;</p>
 *
 * <p class="section-title">Sample Output</p>
 *
 * <pre>YES
 * NO</pre>
 *
 * <p class="section-title">&nbsp;</p>
 *
 * <p class="section-title">Explanation</p>
 *
 * <ul>
 * 	<li>The braces in the first string '{}[]()' are balanced, because all braces are closed&nbsp;</li>
 * 	<li>The braces in the second string '{[}]}' are not balanced,&nbsp;because the nested braces '{[' were not closed in order '}]'&nbsp; and not all open and closed braces matches</li>
 * 	<li>The result is ['YES', 'NO']</li>
 * </ul>
 * </div>
 * </details>
 * </div>
 * </div>
 */
public class SimpleCustomerSupportTicketing {
  private static final String RESOLVED = "YES";
  private static final String NOT_RESOLVED = "NO";

  private static final char OPEN_ROUND_BRACKET = '(';
  private static final char CLOSE_ROUND_BRACKET = ')';
  private static final char OPEN_SQUARE_BRACKET = '[';
  private static final char CLOSE_SQUARE_BRACKET = ']';
  private static final char OPEN_CURLY_BRACKET = '{';
  private static final char CLOSE_CURLY_BRACKET = '}';

  // Complete the braces function below.
  static String[] braces(String[] values) {
    String[] answer = new String[values.length];

    for (int i = 0; i < values.length; i++) {
      String braceString = values[i];
      boolean isResolved = resolveBraceString(braceString);
      answer[i] = (isResolved) ? RESOLVED : NOT_RESOLVED;
    }
    return answer;
  }

  private static boolean resolveBraceString(String brace) {
    char[] braceArray = brace.toCharArray();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < braceArray.length; i++) {
      char braceChar = braceArray[i];
      if (braceChar == OPEN_ROUND_BRACKET || braceChar == OPEN_SQUARE_BRACKET || braceChar == OPEN_CURLY_BRACKET) {
        stack.push(braceChar);
        continue;
      }
      // By now we can be certain that it is a close brace
      if (stack.isEmpty()) {
        return false;
      }
      char braceToBeResolve = stack.pop();
      switch (braceChar) {
        case CLOSE_ROUND_BRACKET:
          if (braceToBeResolve != OPEN_ROUND_BRACKET) {
            return false;
          }
          break;
        case CLOSE_SQUARE_BRACKET:
          if (braceToBeResolve != OPEN_SQUARE_BRACKET) {
            return false;
          }
          break;
        case CLOSE_CURLY_BRACKET:
          if (braceToBeResolve != OPEN_CURLY_BRACKET) {
            return false;
          }
          break;
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    SimpleCustomerSupportTicketing simpleCustomerSupportTicketing = new SimpleCustomerSupportTicketing();

    String[] result = braces(new String[]{"{}[]()"});
    for (String a : result) {
      System.out.printf("%s, ", a);
    }
  }
}