package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BooleanEvaluation {
  private Map<Character, TruthTable> operatorTruthTable;
  private Map<String, ResultPair> resultMapByExpression;

  public int countEval(String booleanExpression, boolean result) {
    if (booleanExpression.length() == 0) {
      return 0;
    }
    preCompute();
    computeResultPairForString(booleanExpression);
    ResultPair expressionResultPair = resultMapByExpression.get(booleanExpression);
    if (result) {
      return expressionResultPair.numberOfTrue;
    } else {
      return expressionResultPair.numberOfFalse;
    }
  }

  private ResultPair computeResultPairForString(String booleanExpression) {
    if (resultMapByExpression.containsKey(booleanExpression)) {
      return resultMapByExpression.get(booleanExpression);
    }

    // We know that each operator is at all the odd index of the string, so we can use this knowledge to spilt the
    // string to get left and right side operand
    int numberOfTrue = 0;
    int numberOfFalse = 0;
    for (int splitIndex = 1; splitIndex < booleanExpression.length(); splitIndex += 2) {
      String leftOperand = booleanExpression.substring(0, splitIndex);
      ResultPair leftResultPair = computeResultPairForString(leftOperand);
      String rightOperand = booleanExpression.substring(splitIndex + 1);
      ResultPair rightResultPair = computeResultPairForString(rightOperand);
      char operator = booleanExpression.charAt(splitIndex);
      TruthTable operatorTable = operatorTruthTable.get(operator);
      // Calculate true first
      numberOfTrue += calculateNumberOfOccurrence(operatorTable.truthOperandPairs, leftResultPair, rightResultPair);
      numberOfFalse += calculateNumberOfOccurrence(operatorTable.fakeOperandPairs, leftResultPair, rightResultPair);
    }
    resultMapByExpression.put(booleanExpression, new ResultPair(numberOfTrue, numberOfFalse));
    return new ResultPair(numberOfTrue, numberOfFalse);
  }

  private int calculateNumberOfOccurrence(List<BooleanOperandPair> booleanOperandPairs, ResultPair leftResultPair,
                                          ResultPair rightResultPair) {
    int numberOfOccurrence = 0;
    for (BooleanOperandPair booleanOperandPair : booleanOperandPairs) {
      int numberOfLeftOccurrence = (booleanOperandPair.leftOperand)
          ? leftResultPair.numberOfTrue
          : leftResultPair.numberOfFalse;

      int numberOfRightOccurrence = (booleanOperandPair.rightOperand)
          ? rightResultPair.numberOfTrue
          : rightResultPair.numberOfFalse;
      numberOfOccurrence += numberOfLeftOccurrence * numberOfRightOccurrence;
    }
    return numberOfOccurrence;
  }

  private void preCompute() {
    // Create all the different possible boolean operand pairs
    BooleanOperandPair bothTrue = new BooleanOperandPair(true, true);
    BooleanOperandPair bothFalse = new BooleanOperandPair(false, false);
    BooleanOperandPair leftFalseRightTrue = new BooleanOperandPair(false, true);
    BooleanOperandPair leftTrueRightFalse = new BooleanOperandPair(true, false);

    // Memorization data structure
    resultMapByExpression = new HashMap<>();
    final String TRUE_SYMBOL = "1";
    ResultPair trueResultPair = new ResultPair(1, 0);
    resultMapByExpression.put(TRUE_SYMBOL, trueResultPair);
    final String FALSE_SYMBOL = "0";
    ResultPair falseResultPair = new ResultPair(0, 1);
    resultMapByExpression.put(FALSE_SYMBOL, falseResultPair);

    // Create the truth table for all the bitwise operators
    // AND
    List<BooleanOperandPair> andTrueList = new ArrayList<>();
    andTrueList.add(bothTrue);
    List<BooleanOperandPair> andFalseList = new ArrayList<>();
    andFalseList.add(bothFalse);
    andFalseList.add(leftFalseRightTrue);
    andFalseList.add(leftTrueRightFalse);
    TruthTable andTruthTable = new TruthTable(andTrueList, andFalseList);

    // OR
    List<BooleanOperandPair> orTrueList = new ArrayList<>();
    orTrueList.add(bothTrue);
    orTrueList.add(leftFalseRightTrue);
    orTrueList.add(leftTrueRightFalse);
    List<BooleanOperandPair> orFalseList = new ArrayList<>();
    orFalseList.add(bothFalse);
    TruthTable orTruthTable = new TruthTable(orTrueList, orFalseList);

    // XOR
    List<BooleanOperandPair> xorTrueList = new ArrayList<>();
    xorTrueList.add(leftFalseRightTrue);
    xorTrueList.add(leftTrueRightFalse);
    List<BooleanOperandPair> xorFalseList = new ArrayList<>();
    xorFalseList.add(bothTrue);
    xorFalseList.add(bothFalse);
    TruthTable xorTruthTable = new TruthTable(xorTrueList, xorFalseList);

    operatorTruthTable = new HashMap<>();
    final char AND_OPERATOR = '&';
    final char OR_OPERATOR = '|';
    final char XOR_OPERATOR = '^';
    operatorTruthTable.put(AND_OPERATOR, andTruthTable);
    operatorTruthTable.put(OR_OPERATOR, orTruthTable);
    operatorTruthTable.put(XOR_OPERATOR, xorTruthTable);
  }

  class TruthTable {
    final List<BooleanOperandPair> truthOperandPairs;
    final List<BooleanOperandPair> fakeOperandPairs;

    public TruthTable(List<BooleanOperandPair> truthOperandPairs, List<BooleanOperandPair> fakeOperandPairs) {
      this.truthOperandPairs = truthOperandPairs;
      this.fakeOperandPairs = fakeOperandPairs;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TruthTable)) return false;
      TruthTable that = (TruthTable) o;
      return truthOperandPairs.equals(that.truthOperandPairs) &&
          fakeOperandPairs.equals(that.fakeOperandPairs);
    }

    @Override
    public int hashCode() {
      return Objects.hash(truthOperandPairs, fakeOperandPairs);
    }
  }

  class ResultPair {
    int numberOfTrue;
    int numberOfFalse;

    public ResultPair(int numberOfTrue, int numberOfFalse) {
      this.numberOfTrue = numberOfTrue;
      this.numberOfFalse = numberOfFalse;
    }

    public ResultPair() {
    }
  }

  /**
   * This class defines the type of boolean operands combination you can have.
   */
  class BooleanOperandPair {
    final boolean leftOperand;
    final boolean rightOperand;

    public BooleanOperandPair(boolean leftOperand, boolean rightOperand) {
      this.leftOperand = leftOperand;
      this.rightOperand = rightOperand;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof BooleanOperandPair)) return false;
      BooleanOperandPair that = (BooleanOperandPair) o;
      return leftOperand == that.leftOperand &&
          rightOperand == that.rightOperand;
    }

    @Override
    public int hashCode() {
      return Objects.hash(leftOperand, rightOperand);
    }
  }
}
