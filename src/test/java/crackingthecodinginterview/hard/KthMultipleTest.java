package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthMultipleTest {
  private static KthMultiple kthMultiple;
  private static List<Integer> primeFactors;

  @BeforeAll
  static void init() {
    kthMultiple = new KthMultiple();
    primeFactors = new ArrayList<>();
    primeFactors.add(3);
    primeFactors.add(5);
    primeFactors.add(7);
  }

  @Test
  void kNumber_1() {
    assertEquals(1, kthMultiple.kNumber(primeFactors, 1));
  }

  @Test
  void kNumber_2() {
    assertEquals(3, kthMultiple.kNumber(primeFactors, 2));
  }

  @Test
  void kNumber_3() {
    assertEquals(5, kthMultiple.kNumber(primeFactors, 3));
  }

  @Test
  void kNumber_4() {
    assertEquals(7, kthMultiple.kNumber(primeFactors, 4));
  }

  @Test
  void kNumber_5() {
    assertEquals(9, kthMultiple.kNumber(primeFactors, 5));
  }

  @Test
  void kNumber_10() {
    assertEquals(45, kthMultiple.kNumber(primeFactors, 10));
  }

  @Test
  void kNumber_11() {
    assertEquals(49, kthMultiple.kNumber(primeFactors, 11));
  }
}