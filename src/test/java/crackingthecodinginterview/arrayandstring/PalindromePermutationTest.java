package crackingthecodinginterview.arrayandstring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PalindromePermutationTest {
  static PalindromePermutation palindromePermutation;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    palindromePermutation = new PalindromePermutation();
  }

  @Test
  final void testEmptyString() {
    assertTrue(palindromePermutation.isStringAPermutationOfPalindrome(""));
  }

  @Test
  final void testLengthOneString() {
    assertTrue(palindromePermutation.isStringAPermutationOfPalindrome("z"));
  }

  @Test
  final void testLengthTwoString() {
    assertFalse(palindromePermutation.isStringAPermutationOfPalindrome("ab"));
  }

  @Test
  final void testOddLengthString() {
    assertTrue(palindromePermutation.isStringAPermutationOfPalindrome("aa  cc  b"));
    assertFalse(palindromePermutation.isStringAPermutationOfPalindrome("ab,,,caa"));
  }

  @Test
  final void testEvenLengthString() {
    assertTrue(palindromePermutation.isStringAPermutationOfPalindrome("dfdf  a,a"));
    assertFalse(palindromePermutation.isStringAPermutationOfPalindrome("abcabd"));
  }
}
