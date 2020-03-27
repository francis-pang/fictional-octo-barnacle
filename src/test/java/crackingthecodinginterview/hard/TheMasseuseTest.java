package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TheMasseuseTest {
  private static TheMasseuse theMasseuse;

  @BeforeAll
  private static void setUpOnce() {
    theMasseuse = new TheMasseuse();
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_null() {
    assertEquals(-1, theMasseuse.findMaxTime(null));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_single() {
    assertEquals(5, theMasseuse.findMaxTime(new int[]{5}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_empty() {
    assertEquals(-1, theMasseuse.findMaxTime(new int[]{}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_2ElementsTakeFirst() {
    assertEquals(4, theMasseuse.findMaxTime(new int[]{3, 4}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_2ElementsTakeLast() {
    assertEquals(4, theMasseuse.findMaxTime(new int[]{4, 2}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_Multiples() {
    assertEquals(180, theMasseuse.findMaxTime(new int[]{30, 15, 60, 75, 45, 15, 15, 45}));
  }
}