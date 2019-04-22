package crackingthecodinginterview.bitmanipulation;

public class FlipBitToWin {
  public int longestSequenceOfOnes(int number) {
    if (number < 10) {
      return 1;
    }
    char[] numberBinaryForm = Integer.toBinaryString(number)
        .toCharArray(); // Convert the number into string of binary
    int longestSequence = 0;
    int lastOnesLength = 0;
    int currentOnesLength = 0;
    int currentZeroLength = 0;
    for (char bit : numberBinaryForm) {
      if (bit == '1') {
        currentOnesLength++;
      }
      // bit == 0
      else if (currentOnesLength == 0) { // Check if this is the first zero
        currentZeroLength++;
      } else {
        longestSequence = calculateLongestSequence(longestSequence, lastOnesLength, currentOnesLength,
            currentZeroLength);
        lastOnesLength = currentOnesLength;
        currentOnesLength = 0;
        currentZeroLength = 1;
      }
    }
    if (numberBinaryForm[numberBinaryForm.length - 1] == '1') { //Do it one for the last substring of 1s
      longestSequence = calculateLongestSequence(longestSequence, lastOnesLength, currentOnesLength,
          currentZeroLength);
    }
    return longestSequence;
  }

  private int calculateLongestSequence(int longestSequence, int lastOnesLength, int currentOnesLength,
                                       int currentZeroLength) {
    switch (currentZeroLength) {
      case 0:
        return ((longestSequence < currentOnesLength) ? currentOnesLength : longestSequence);
      case 1:
        int flippedStringLength = lastOnesLength + 1 + currentOnesLength;
        return ((longestSequence < flippedStringLength) ? flippedStringLength : longestSequence);
      default:
        return ((longestSequence < currentOnesLength + 1) ? currentOnesLength + 1 : longestSequence);
    }
  }
}
