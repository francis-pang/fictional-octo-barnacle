package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
  Calculator calculator = new Calculator();

  @Test
  void calculate_baseCase() {
    assertEquals(23.5, calculator.calculate("2*3+5/6*3+15"));
  }

  @Test
  void calculate_singleDigits() {
    assertEquals(35, calculator.calculate("3+5*7-9/3"));
  }

  @Test
  void calculate_multipleFirstOrderTogether() {
    assertEquals(109, calculator.calculate("27+7*9*3/7+5*11"));
  }

  @Test
  void calculate_multipleSecondOrderTogether() {
    assertEquals(-529, calculator.calculate("99-85+7-109+22+9-59*8"));
  }

  @Test
  void calculate_resultInZero() {
    assertEquals(0, calculator.calculate("7*0+9*0-10*0"));
  }

  @Test
  void calculate_onlyFirstOrder() {
   assertEquals(15,calculator.calculate("5+5+5"));
  }

  @Test
  void calculate_singleOperator() {
    assertEquals(1.5,calculator.calculate("3/2"));
  }
}