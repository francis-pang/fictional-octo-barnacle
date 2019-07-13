package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationsTest {
  /**
   * Plan:
   * 1. +ve - +ve = +ve
   * 2. +ve - +ve = -ve
   * 3. -ve - -ve = -ve
   * 4. -ve - -ve = +ve
   */

  @Test
  void subtract_positiveMinusPositiveGetPositive() {
    assertEquals(7, Operations.subtract(15, 8));
  }

  @Test
  void subtract_positiveMinusPositiveGetNegative() {
    assertEquals(-1, Operations.subtract(7, 8));
  }

  @Test
  void subtract_positiveMinusPositiveGetZero() {
    assertEquals(0, Operations.subtract(7, 7));
  }

  @Test
  void subtract_negativeMinusNegativeGetNegative() {
    assertEquals(-3, Operations.subtract(-10, -7));
  }

  @Test
  void subtract_negativeMinusNegativeGetPositive() {
    assertEquals(3, Operations.subtract(-7, -10));
  }

  /**
   * +ve * +ve = +ve
   * +ve * -ve = -ve
   * -ve * -ve = +ve
   * +ve * 0 = 0
   */

  @Test
  void multiple_timeZeroGetZero() {
    assertEquals(0, Operations.multiply(0, 77));
  }

  @Test
  void multiple_positiveTimesPositiveGetPositive() {
    assertEquals(154, Operations.multiply(2, 77));
  }

  @Test
  void multiple_negativeTimesNegativeGetPositive() {
    assertEquals(154, Operations.multiply(-2, -77));
  }

  @Test
  void multiple_negativeTimesPositiveGetPositive() {
    assertEquals(-154, Operations.multiply(2, -77));
  }

  @Test
  void divide_positiveDividePositive() {
    assertEquals(7, Operations.divide(35, 5));
  }

  @Test
  void divide_negativeDividePositive() {
    assertEquals(-9, Operations.divide(-90, 10));
  }

  @Test
  void divide_negativeDivideNegative() {
    assertEquals(-15, Operations.divide(105, -7));
  }

  @Test
  void divide_positiveDivideNegative() {
    assertEquals(-1, Operations.divide(6, -5));
  }

  @Test
  void divide_zeroDivide() {
    assertEquals(0, Operations.divide(0, -5));
  }

  @Test
  void divide_divideByZero() {
    assertThrows(ArithmeticException.class, () -> {
      Operations.divide(-5, 0);
    });
  }
}