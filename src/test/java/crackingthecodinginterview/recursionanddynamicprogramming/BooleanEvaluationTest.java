package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanEvaluationTest {
  private static BooleanEvaluation booleanEvaluation;

  @BeforeAll
  static void setUpOnce() {
    booleanEvaluation = new BooleanEvaluation();
  }

  @Test
  void countEval_noEntry() {
    assertEquals(0, booleanEvaluation.countEval("", false));
  }

  @Test
  void countEval_sample1() {
    assertEquals(2, booleanEvaluation.countEval("1^0|0|1", false));
  }

  @Test
  void countEval_sample2() {
    assertEquals(10, booleanEvaluation.countEval("0&0&0&1^1|0", true));
  }
}