package crackingthecodinginterview.hard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddWithoutPlusTest {
  static AddWithoutPlus addWithoutPlus;

  @BeforeAll
  static void setUpOnce() {
    addWithoutPlus = new AddWithoutPlus();
  }

  @Test
  void addTwoNumber_bigNumberTest() {
    assertEquals(1673, addWithoutPlus.addTwoNumber(776, 897));
  }

  @Test
  void addTwoNumber_singleNumberAdd() {
    assertEquals((9+5), addWithoutPlus.addTwoNumber(9, 5));
  }

  @Test
  void addTwoNumber_addZeroLeft() {
    assertEquals((9), addWithoutPlus.addTwoNumber(0, 9));
  }

  @Test
  void addTwoNumber_addZeroRight() {
    assertEquals((44568), addWithoutPlus.addTwoNumber(44568, 0));
  }
}