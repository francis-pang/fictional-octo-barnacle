package crackingthecodinginterview.moderate;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses). compute
 * the result.
 */
public class Calculator {
  short numberOfFirstOrderOperator = 0;
  Stack<ArithmeticExpression> secondOrderStack = new Stack<>();
  Queue<ArithmeticExpression> expressionQueue = new ArrayDeque<>();

  public double calculate(String expression){
    StringBuilder operand = new StringBuilder(); // Add a positive sign in front first
    char previousOperator = ' ';
    char expressionOperator = '+';
    for (char character : expression.toCharArray()) {
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
    double leftOperand = (firstArithmeticExpression.operator == '+') ? firstArithmeticExpression.value :
        (firstArithmeticExpression.value * -1);
    while(!expressionQueue.isEmpty()) {
      ArithmeticExpression arithmeticExpression = expressionQueue.poll();
      if ((arithmeticExpression.operator == '+' || arithmeticExpression.operator == '-') && numberOfFirstOrderOperator > 0) {
        numberOfFirstOrderOperator--;
        expressionQueue.offer(new ArithmeticExpression('+', leftOperand));
        leftOperand = (arithmeticExpression.operator == '+') ? arithmeticExpression.value :
            (arithmeticExpression.value * -1);
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

  private double calculateSingleArithmeticOperation (double firstNumber, double secondNumber, char operator) {
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
    public double value;

    public ArithmeticExpression() {
    }

    public ArithmeticExpression(char operator, double value) {
      this.operator = operator;
      this.value = value;
    }
  }
}
