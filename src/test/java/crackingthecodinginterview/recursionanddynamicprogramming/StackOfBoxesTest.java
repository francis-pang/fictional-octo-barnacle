package crackingthecodinginterview.recursionanddynamicprogramming;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackOfBoxesTest {
  private static StackOfBoxes stackOfBoxes;

  @BeforeAll
  static void setUpOnce() {
    stackOfBoxes = new StackOfBoxes();
  }

  @Test
  void heightOfStack_zero() {
    assertEquals(0, stackOfBoxes.maxHeightOfStackedBoxes(new int[]{}, new int[]{}, new int[]{}));
  }

  @Test
  void heightOfStack_one() {
    assertEquals(2, stackOfBoxes.maxHeightOfStackedBoxes(new int[]{2}, new int[]{2}, new int[]{2}));
  }

  @Test
    // Test that multiple boxes, the expected answer is to have all boxes stacked on top of each other
  void heightOfStack_5SingleAllAdd() {
    assertEquals(15, stackOfBoxes.maxHeightOfStackedBoxes(
        new int[]{1, 2, 3, 4, 5},
        new int[]{1, 2, 3, 4, 5},
        new int[]{1, 2, 3, 4, 5}
    ));
  }

  @Test
  void heightOfStack_5SingleSomeNotAdd() {
    assertEquals(19, stackOfBoxes.maxHeightOfStackedBoxes(
        new int[]{10, 2, 3, 4, 5},
        new int[]{10, 2, 3, 4, 5},
        new int[]{10, 2, 2, 4, 2}
    ));
  }

  @Test
  void heightOfStack_6SplitOnce() {
    assertEquals(10, stackOfBoxes.maxHeightOfStackedBoxes(
        new int[]{3, 3, 10, 2, 1},
        new int[]{3, 2, 2, 2, 1},
        new int[]{6, 5, 1, 5, 1}
    ));
  }

  @Test
  void heightOfStack_10SplitMultiple() {
    assertEquals(44, stackOfBoxes.maxHeightOfStackedBoxes(
        new int[]{3, 9, 3, 2, 4, 20, 9, 6},
        new int[]{3, 15, 5, 2, 4, 20, 15, 6},
        new int[]{5, 10, 2, 2, 6, 20, 10, 8}
    ));
  }
}