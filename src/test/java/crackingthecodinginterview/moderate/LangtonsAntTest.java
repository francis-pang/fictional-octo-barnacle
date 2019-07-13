package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LangtonsAntTest {
  private LangtonsAnt langtonsAnt;

  @BeforeEach
  void setUp() {
    langtonsAnt = new LangtonsAnt();
  }

  @Test
  void printKMoves_0() {
    langtonsAnt.printKMoves(0);
  }

  @Test
  void printKMoves_negative1() {
    langtonsAnt.printKMoves(-1);
  }

  @Test
  void printKMoves_1() {
    langtonsAnt.printKMoves(1);
  }

  @Test
  void printKMoves_35() {
    langtonsAnt.printKMoves(35);
  }

}