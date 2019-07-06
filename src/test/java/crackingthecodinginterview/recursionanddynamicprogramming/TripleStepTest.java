package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleStepTest {
  private static TripleStep tripleStep;

  @BeforeAll
  static void setUpOnce() {
    tripleStep = new TripleStep();
  }

  @Test
  void numberOfWayToRunUpTheStairs_1() {
    assertEquals(1, tripleStep.countNumberOfWayToRunUpTheStairs(1));
  }

  @Test
  void numberOfWayToRunUpTheStairs_2() {
    assertEquals(2, tripleStep.countNumberOfWayToRunUpTheStairs(2));
  }

  @Test
  void numberOfWayToRunUpTheStairs_3() {
    assertEquals(4, tripleStep.countNumberOfWayToRunUpTheStairs(3));
  }

  @Test
  void numberOfWayToRunUpTheStairs_4() {
    assertEquals(7, tripleStep.countNumberOfWayToRunUpTheStairs(4));
  }
}