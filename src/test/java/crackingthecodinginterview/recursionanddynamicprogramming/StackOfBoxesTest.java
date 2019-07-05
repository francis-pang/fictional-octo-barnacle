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
    assertEquals(0, stackOfBoxes.heightOfStack(new StackOfBoxes.Box[0]));
  }

  @Test
  void heightOfStack_one() {
    StackOfBoxes.Box[] boxes = new StackOfBoxes.Box[1];
    boxes[0] = new StackOfBoxes.Box(2, 2, 2);
    assertEquals(2, stackOfBoxes.heightOfStack(boxes));
  }

  @Test
  void heightOfStack_5SingleAllAdd() {
    StackOfBoxes.Box[] boxes = new StackOfBoxes.Box[5];
    for (int i = 0; i < boxes.length; i++) {
      int dimension = i + 1;
      boxes[i] = new StackOfBoxes.Box(dimension, dimension, dimension);
    }
    assertEquals(15, stackOfBoxes.heightOfStack(boxes));
  }

  @Test
  void heightOfStack_5SingleSomeNotAdd() {
    StackOfBoxes.Box[] boxes = new StackOfBoxes.Box[5];
    boxes[0] = new StackOfBoxes.Box(10, 10, 10); // 100
    boxes[1] = new StackOfBoxes.Box(2, 2, 2);    // 4
    boxes[2] = new StackOfBoxes.Box(3, 3, 2);    // 12
    boxes[3] = new StackOfBoxes.Box(4, 4, 4);    // 64
    boxes[4] = new StackOfBoxes.Box(5, 5, 2);    // 50
    assertEquals(16, stackOfBoxes.heightOfStack(boxes));
  }

  @Test
  void heightOfStack_6SplitOnce() {
    StackOfBoxes.Box[] boxes = new StackOfBoxes.Box[5];
    boxes[0] = new StackOfBoxes.Box(3, 3, 6);  // 54
    boxes[1] = new StackOfBoxes.Box(3, 2, 5);  // 30
    boxes[2] = new StackOfBoxes.Box(10, 2, 1); // 20
    boxes[3] = new StackOfBoxes.Box(2, 2, 5);  // 20
    boxes[4] = new StackOfBoxes.Box(1, 1, 1);  // 1
    assertEquals(6, stackOfBoxes.heightOfStack(boxes));
  }

  @Test
  void heightOfStack_10SplitMultiple() {
    StackOfBoxes.Box[] boxes = new StackOfBoxes.Box[8];
    boxes[0] = new StackOfBoxes.Box(3, 3, 5);
    boxes[1] = new StackOfBoxes.Box(9, 15, 10);
    boxes[2] = new StackOfBoxes.Box(3, 5, 2);
    boxes[3] = new StackOfBoxes.Box(2, 2, 2);
    boxes[4] = new StackOfBoxes.Box(4, 4, 6);
    boxes[5] = new StackOfBoxes.Box(20, 20, 20);
    boxes[6] = new StackOfBoxes.Box(9, 15, 10);
    boxes[7] = new StackOfBoxes.Box(6, 6, 8);
    assertEquals(50, stackOfBoxes.heightOfStack(boxes));
  }

  @Test
  void heightOfStack() {

  }
}