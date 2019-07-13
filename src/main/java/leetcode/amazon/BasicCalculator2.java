package leetcode.amazon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BasicCalculator2 {
  short numberOfFirstOrderOperator = 0;
  Stack<ArithmeticExpression> secondOrderStack = new Stack<>();
  Queue<ArithmeticExpression> expressionQueue = new ArrayDeque<>();

  public int calculate(String s) {
    StringBuilder operand = new StringBuilder(); // Add a positive sign in front first
    char previousOperator = ' ';
    char expressionOperator = '+';
    for (char character : s.toCharArray()) {
      if (character == ' ') {
        continue;
      } else if (Character.isDigit(character)) {
        operand.append(character);
      } else { // Must be one of the operator
        evaluateOperator(expressionOperator, previousOperator, Integer.valueOf(operand.toString()));
        // Reset the value
        operand = new StringBuilder();
        previousOperator = expressionOperator;
        expressionOperator = character;
      }
    }
    if (secondOrderStack.empty() && expressionQueue.isEmpty()) {
      return Integer.valueOf(operand.toString());
    }
    // Last expression
    evaluateOperator(expressionOperator, previousOperator, Integer.valueOf(operand.toString()));

    // Clear the remaining stack
    while (!secondOrderStack.empty()) {
      expressionQueue.offer(secondOrderStack.pop());
    }

    // Evaluate the re-ordered expression
    ArithmeticExpression firstArithmeticExpression = expressionQueue.poll();
    int leftOperand = (firstArithmeticExpression.operator == '+') ? firstArithmeticExpression.value :
        (firstArithmeticExpression.value * -1);
    while(!expressionQueue.isEmpty()) {
      ArithmeticExpression arithmeticExpression = expressionQueue.poll();
      if ((arithmeticExpression.operator == '+' || arithmeticExpression.operator == '-') && numberOfFirstOrderOperator > 0) {
        numberOfFirstOrderOperator--;
        expressionQueue.offer(new ArithmeticExpression('+', leftOperand));
        leftOperand = (arithmeticExpression.operator == '+') ? arithmeticExpression.value :
            Math.negateExact(arithmeticExpression.value);
      } else {
        leftOperand = calculateSingleArithmeticOperation(leftOperand, arithmeticExpression.value,
            arithmeticExpression.operator);
      }
    }
    return leftOperand;
  }

  private void evaluateOperator(char expressionOperator, char previousOperator, int value) {
    ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
    switch(expressionOperator) {
      case '+':
      case '-':
        secondOrderStack.push(new ArithmeticExpression(expressionOperator, value));
        break;
      case '*':
      case '/':
        if (previousOperator == '+'
            || previousOperator == '-') {
          expressionQueue.offer(secondOrderStack.pop());
        }
        arithmeticExpression.operator = expressionOperator;
        arithmeticExpression.value = value;
        expressionQueue.offer(new ArithmeticExpression(expressionOperator, value));
        numberOfFirstOrderOperator++;
        break;
      default:
        throw new IllegalArgumentException("Unknown operator " + expressionOperator); //Should not happened
    }
  }

  private int calculateSingleArithmeticOperation (int firstNumber, int secondNumber, char operator) {
    switch(operator) {
      case '+':
        return firstNumber + secondNumber;
      case '-':
        return firstNumber - secondNumber;
      case '*':
        return firstNumber * secondNumber;
      case '/':
        return firstNumber / secondNumber;
      default:
        throw new IllegalArgumentException("Unknown operator " + operator); //Should not happened
    }
  }

  protected class ArithmeticExpression {
    public char operator;
    public int value;

    public ArithmeticExpression() {
    }

    public ArithmeticExpression(char operator, int value) {
      this.operator = operator;
      this.value = value;
    }
  }
}