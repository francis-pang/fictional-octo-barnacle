package crackingthecodinginterview.moderate;

/**
 * Write methods to implement the multiply, subtract, and divide operations for integers.
 * The results of all of these are integers. Use only the add operator.
 */
public class Operations {
  public static int subtract(int firstNumber, int secondNumber) {
    // The answer assume that the firstNumber minus the secondNumber, so in other words, it's negative notation of
    // the secondNumber add the firstNumber

    // Retrieve the negative notation of the secondNumber
    int negativeSecondNumber = convertToNegativeForm(secondNumber);

    // Perform the additional
    int answer = firstNumber + negativeSecondNumber;
    // Return the answer
    return answer;
  }

  private static int convertToInteger(String twoComplementNumber) {
    return (int) Long.parseLong(twoComplementNumber, 2);
  }

  private static String convertToBinaryStringWithZeroPadding(int number) {
    return String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0');
  }

  private static int convertToNegativeForm(int number) {
    final char ONE = '1';
    final char ZERO = '0';
    String numberString = convertToBinaryStringWithZeroPadding(number);
    StringBuilder stringBuilder = new StringBuilder();
    // Invert the bit
    for (char character : numberString.toCharArray()) {
      if (character == ONE) {
        stringBuilder.append(ZERO);
      } else if (character == ZERO) {
        stringBuilder.append(ONE);
      }
    }
    int invertedNumber = convertToInteger(stringBuilder.toString());
    return invertedNumber + 1;
  }


  public static int multiply(int firstNumber, int secondNumber) {
    // The brute force method is to add second number time of first number
    if (firstNumber == 0 || secondNumber == 0) {
      return 0;
    }

    boolean negativeAnswer = needToConvertToNegativeAnswer(firstNumber, secondNumber);
    int positiveOnlyFirstNumber = convertToAbs(firstNumber);
    int positiveOnlySecondNumber = convertToAbs(secondNumber);

    int answer = 0;
    for (int counter = 0; counter < positiveOnlySecondNumber; counter++) {
      answer += positiveOnlyFirstNumber;
    }

    return (negativeAnswer) ? convertToNegativeForm(answer) : answer;
  }

  private static boolean needToConvertToNegativeAnswer(int firstNumber, int secondNumber) {
    return (firstNumber < 0) ^ (secondNumber < 0);
  }

  private static int convertToAbs(int number) {
    return (number < 0) ? convertToNegativeForm(number) : number;
  }

  public static int divide(int firstNumber, int secondNumber) throws ArithmeticException {
    if (firstNumber == 0) {
      return 0;
    }
    if (secondNumber == 0) {
      throw new ArithmeticException("Cannot divide by zero");
    }
    boolean negativeAnswer = needToConvertToNegativeAnswer(firstNumber, secondNumber);
    int positiveOnlyFirstNumber = convertToAbs(firstNumber);
    int positiveOnlySecondNumber = convertToAbs(secondNumber);

    int answer = 0;
    while ((answer + 1) * positiveOnlySecondNumber <= positiveOnlyFirstNumber) {
      answer++;
    }

    return (negativeAnswer) ? convertToNegativeForm(answer) : answer;
  }
}
