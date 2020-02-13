package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class RecursiveMultiply {
  public int multiplyRecursively(int firstNumber, int secondNumber) {
    Map<Integer, Integer> table = new HashMap<>();
    table.put(1, Math.max(firstNumber, secondNumber));
    return multiply(Math.max(firstNumber, secondNumber), Math.min(firstNumber, secondNumber), table);

  }

  private int multiply(int number1, int number2, Map<Integer, Integer> table) {
    if (table.containsKey(number2)) {
      return table.get(number2);
    }
    int result;
    if (number2 % 2 == 0) { // even
      result = multiply(number1, number2 / 2, table) + multiply(number1, number2 / 2, table);
    } else { // odd
      result = multiply(number1, number2 / 2, table) + multiply(number1, number2 / 2, table) +
          multiply(number1, 1, table);
    }
    table.put(number2, result);
    return result;
  }

  public static void main(String[] args) {
    RecursiveMultiply recursiveMultiply = new RecursiveMultiply();
    int result = recursiveMultiply.multiplyRecursively(987561, 59866);
    System.out.println("Answer=" + result);
  }
}
