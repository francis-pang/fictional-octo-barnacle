package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PondSizesTest {
  private static PondSizes pondSizes;

  @BeforeAll
  static void setUpOnce() {
    pondSizes = new PondSizes();
  }

  @Test
  void sizesOfAllPonds_sample1() {
    int[][] pond = {
        {0, 2, 1, 0},
        {0, 1, 0, 1},
        {1, 1, 0, 1},
        {0, 1, 0, 1}};
    assertThat(pondSizes.sizesOfAllPonds(pond)).containsExactlyInAnyOrder(2, 4, 1);
  }
}