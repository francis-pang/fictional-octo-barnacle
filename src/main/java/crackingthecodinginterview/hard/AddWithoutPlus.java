package crackingthecodinginterview.hard;

/**
 * Add Without Plus: Write a function that adds two numbers. You should not use + or any arithmetic
 * operators.
 */
public class AddWithoutPlus {
  public int addTwoNumber(int firstNumber, int secondNumber) {
    char[] firstNumberInCharArray =
        new StringBuilder(Integer.toBinaryString(firstNumber)).reverse().toString().toCharArray();
    char[] secondNumberInCharArray =
        new StringBuilder(Integer.toBinaryString(secondNumber)).reverse().toString().toCharArray();

    Result resultForAdditionOfBits = new Result();
    resultForAdditionOfBits.carryOver = false;
    StringBuilder resultString = new StringBuilder();

    for (int counter = 0; counter < Math.max(firstNumberInCharArray.length, secondNumberInCharArray.length); counter++) {
      char firstNumberBit = (firstNumberInCharArray.length > counter) ? firstNumberInCharArray[counter] : '0';
      char secondNumberBit = (secondNumberInCharArray.length > counter) ? secondNumberInCharArray[counter] : '0';
      resultForAdditionOfBits = addTwoBit(resultForAdditionOfBits.carryOver, firstNumberBit, secondNumberBit);
      resultString.append(resultForAdditionOfBits.resultBit);
    }
    if (resultForAdditionOfBits.carryOver) {
      resultString.append('1');
    }
    return Integer.parseUnsignedInt(resultString.reverse().toString(), 2); //stub
  }

  private Result addTwoBit(boolean carryOver, char firstBit, char secondBit) {
    byte carryOverBit = (byte) ((carryOver) ? 1 : 0);

    int combinedBinary =
        (carryOverBit << 2) | (Integer.decode(String.valueOf(firstBit)) << 1) | (Integer.decode(String.valueOf(secondBit)));
    Result resultOfAddition = new Result();
    switch(combinedBinary) {
      case 1:
      case 2:
      case 4:
        resultOfAddition.carryOver = false;
        resultOfAddition.resultBit = '1';
        break;
      case 7:
        resultOfAddition.carryOver = true;
        resultOfAddition.resultBit = '1';
        break;
      case 0:
        resultOfAddition.carryOver = false;
        resultOfAddition.resultBit = '0';
        break;
      default:
        resultOfAddition.carryOver = true;
        resultOfAddition.resultBit = '0';
    }
    return resultOfAddition;
  }

  protected class Result {
    public char resultBit;
    public boolean carryOver;
  }
}
