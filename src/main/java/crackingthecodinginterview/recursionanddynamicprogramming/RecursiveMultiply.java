package crackingthecodinginterview.recursionanddynamicprogramming;

public class RecursiveMultiply {
  public int multiplyRecursively(int firstNumber, int secondNumber) {
    if (secondNumber == 1) {
      return firstNumber;
    }

    return firstNumber + multiplyRecursively(firstNumber, secondNumber - 1);
  }
}
