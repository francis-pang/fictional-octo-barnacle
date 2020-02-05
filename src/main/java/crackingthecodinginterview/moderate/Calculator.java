package crackingthecodinginterview.moderate;

import java.util.Stack;

/**
 * Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses). compute
 * the result.
 */
public class Calculator {
  public double answer(String s) {
    Stack<Double> stack = new Stack<>();
    boolean negativeFirst = false;
    Parsed p;
    if (s.charAt(0) == '-') {
      negativeFirst = true;
      p = parseNumber(s, 1);
    } else {
      p = parseNumber(s, 0);
    }
    if (negativeFirst) {
      stack.push(p.val * -1);
    } else {
      stack.push(p.val);
    }
    int pos = p.nextPos;
    while (pos < s.length()) {
      char op = s.charAt(pos);
      p = parseNumber(s, pos + 1);
      if (op == '*' || op == '/') {
        double num = stack.pop();
        if (op == '*') {
          p.val = num * p.val;
        } else {
          p.val = num / p.val;
        }
      } else if (op == '-') {
        p.val *= -1;
      }
      stack.push(p.val);
      pos = p.nextPos;
    }
    double ans = 0;
    while (!stack.isEmpty()) {
      ans += stack.pop();
    }
    return ans;
  }

  private Parsed parseNumber(String s, int pos) {
    double number = 0;
    while (pos < s.length()) {
      char c = s.charAt(pos);
      if (!Character.isDigit(c)) {
        break;
      }
      number = (number * 10) + Character.digit(c, 10);
      pos++;
    }
    return new Parsed(number, pos);
  }

  private class Parsed {
    public double val;
    public int nextPos;

    public Parsed(double val, int nextPos) {
      this.val = val;
      this.nextPos = nextPos;
    }
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    System.out.println(calculator.answer("-2*3+5/6*3+15"));
  }
}
