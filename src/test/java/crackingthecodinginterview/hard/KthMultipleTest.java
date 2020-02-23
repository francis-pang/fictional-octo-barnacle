package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthMultipleTest {
  private static KthMultiple kthMultiple;
  private static int[] primeFactors;

  @BeforeAll
  static void init() {
    kthMultiple = new KthMultiple();
    primeFactors = new int[]{3, 5, 7};
  }

  @Test
  void kNumber_1() {
    assertEquals(1, kthMultiple.getKthMultiple(primeFactors, 1));
  }

  @Test
  void kNumber_2() {
    assertEquals(3, kthMultiple.getKthMultiple(primeFactors, 2));
  }

  @Test
  void kNumber_3() {
    assertEquals(5, kthMultiple.getKthMultiple(primeFactors, 3));
  }

  @Test
  void kNumber_4() {
    assertEquals(7, kthMultiple.getKthMultiple(primeFactors, 4));
  }

  @Test
  void kNumber_5() {
    assertEquals(9, kthMultiple.getKthMultiple(primeFactors, 5));
  }

  @Test
  void kNumber_10() {
    assertEquals(45, kthMultiple.getKthMultiple(primeFactors, 10));
  }

  @Test
  void kNumber_11() {
    assertEquals(49, kthMultiple.getKthMultiple(primeFactors, 11));
  }
}