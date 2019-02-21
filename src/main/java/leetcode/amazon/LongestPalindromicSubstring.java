package leetcode.amazon;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        if (s.length() == 2) {
            return (s.charAt(0) == s.charAt(1)) ? s : s.substring(0,1);
        }
        // Use dynamic programming to expand outwards, and store a map to store true or false
        Set<Range> evenStartLengthPalindromeSet = new HashSet<>();
        // Even
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                System.out.println("Found a new palindrome: " + s.substring(i - 1, i + 1));
                evenStartLengthPalindromeSet.add(new Range(i - 1, i));
            }
        }
        String longestEvenStartString = "";
        if (evenStartLengthPalindromeSet.size() > 0) {
            longestEvenStartString = getLongestPalindrome(s, evenStartLengthPalindromeSet);
        }

        Set<Range> oddStartLengthPalindromeSet = new HashSet<>();
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i - 2) == s.charAt(i)) {
                System.out.println("Found a new palindrome: " + s.substring(i - 2, i + 1));
                oddStartLengthPalindromeSet.add(new Range(i - 2, i));
            }
        }
        String longestOddStartString = "";
        if (oddStartLengthPalindromeSet.size() > 0) {
            longestOddStartString = getLongestPalindrome(s, oddStartLengthPalindromeSet);
        }
        if ("".equals(longestEvenStartString) && "".equals(longestOddStartString)) {
            return s.substring(0, 1);
        } else if (longestEvenStartString.length() > longestOddStartString.length()) {
            return longestEvenStartString;
        } else {
            return longestOddStartString;
        }
    }

    private String getLongestPalindrome(String s, Set<Range> palindromeSet) {
        Set<Range> longestPalindromeSet = palindromeSet;
        Set<Range> remainingPalindrome;
        do {
            remainingPalindrome = extractRemainingPalindrome(s, longestPalindromeSet);
            if (remainingPalindrome.size() > 0) {
                longestPalindromeSet = remainingPalindrome;
            }
        } while(remainingPalindrome.size() > 0);
        for (Range range : longestPalindromeSet) {
            return s.substring(range.start, range.end + 1);
        }
        return "";
    }

    private Set<Range> extractRemainingPalindrome(String s, Set<Range> existingPalindrome) {
        Set<Range> remainingSet = new HashSet<>();
        for (Range range : existingPalindrome) {
            if (range.start > 0 && range.end < s.length() - 1 &&
                    s.charAt(range.start - 1) == s.charAt(range.end + 1)) {
                System.out.println("Found a new palindrome: " + s.substring(range.start - 1, range.end + 2));
                remainingSet.add(new Range(range.start - 1, range.end + 1));
            }
        }
        return remainingSet;
    }

    public class Range {
        public int start;
        public int end;

        public Range() {}

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String longest = longestPalindromicSubstring.longestPalindrome(
                "cbbd");
        System.out.println("Longest is " + longest);
    }
}
