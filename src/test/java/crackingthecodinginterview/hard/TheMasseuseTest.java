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
    assertEquals(-1, theMasseuse.getMaximumNumberOfMinutesForMasseuse(null));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_single() {
    assertEquals(5, theMasseuse.getMaximumNumberOfMinutesForMasseuse(new int[]{5}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_empty() {
    assertEquals(0, theMasseuse.getMaximumNumberOfMinutesForMasseuse(new int[]{}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_2ElementsTakeFirst() {
    assertEquals(4, theMasseuse.getMaximumNumberOfMinutesForMasseuse(new int[]{3, 4}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_2ElementsTakeLast() {
    assertEquals(4, theMasseuse.getMaximumNumberOfMinutesForMasseuse(new int[]{4, 2}));
  }

  @Test
  void getMaximumNumberOfMinutesForMasseuse_Multiples() {
    assertEquals(180, theMasseuse.getMaximumNumberOfMinutesForMasseuse(new int[]{30, 15, 60, 75, 45, 15, 15, 45}));
  }
}