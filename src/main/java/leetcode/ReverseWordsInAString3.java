package leetcode;

public class ReverseWordsInAString3 {
  public String reverseWords(String s) {
    s = s.trim();
    String[] split = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String word : split) {
      sb.append(reverse(word));
      sb.append(" ");
    }
    String ans = sb.toString();
    return ans.substring(0, ans.length() - 1);
  }

  public String reverse(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }
}
