package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TowersOfHanoiTest {
  private static TowersOfHanoi towersOfHanoi;

  @BeforeAll
  static void setUpOnce() {
    towersOfHanoi = new TowersOfHanoi();
  }

  @Test
  void moveDisksFromFirstTowerToLast_1Disk() {
    towersOfHanoi.moveTowerOfHanoi(1);
  }

  @Test
  void moveDisksFromFirstTowerToLast_2Disk() {
    towersOfHanoi.moveTowerOfHanoi(2);
  }

  @Test
  void moveDisksFromFirstTowerToLast_3Disk() {
    towersOfHanoi.moveTowerOfHanoi(3);
  }
}