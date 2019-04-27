package crackingthecodinginterview.stacksandqueues;

/**
 * Three in One: Describe how you could use a single array to implement three stacks.
 */
public class ThreeInOne {
  /*
   * First try to implement a stack with 1 array first, then try to do it for 3 stacks
   * Do it for integer stack first
   */
  class Stack {
    int[] content = new int[Short.MAX_VALUE];
    final byte STACK_NUMBER = 3;
    // There is no need to store the first item, because we know that the first item is to be stored at location
    // zero, in a zero based array.
    short lastItemStack1 = -3;
    short lastItemStack2 = -2;
    short lastItemStack3 = -1;

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param item the item to be pushed onto this stack.
     * @return the {@code item} argument.
     * @see java.util.Vector#addElement
     */
    public int push​(byte stackNumber, int item) {
      // Assume that there is no overflowing issue
      switch (stackNumber) {
        case 1:
          lastItemStack1 += STACK_NUMBER;
          content[lastItemStack1] = item;
          break;
        case 2:
          lastItemStack2 += STACK_NUMBER;
          content[lastItemStack2] = item;
          break;
        case 3:
          lastItemStack3 += STACK_NUMBER;
          content[lastItemStack3] = item;
          break;
        default:
          System.out.println("Invalid stack number chosen");
      }
      return item;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack (the last item
     * of the {@code Vector} object).
     */
    public int pop(byte stackNumber) {
      int itemPopped = 0;
      switch (stackNumber) {
        case 1:
          if (lastItemStack1 == -3) {
            return 0;
          }
          itemPopped = content[lastItemStack1];
          lastItemStack1 -= STACK_NUMBER;
        case 2:
          if (lastItemStack2 == -2) {
            return 0;
          }
          itemPopped = content[lastItemStack2];
          lastItemStack2 -= STACK_NUMBER;
        case 3:
          if (lastItemStack3 == -1) {
            return 0;
          }
          itemPopped = content[lastItemStack3];
          lastItemStack3 -= STACK_NUMBER;
        default:
          System.out.println("Invalid stack number chosen");
      }
      return itemPopped;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack (the last item
     * of the {@code Vector} object).
     */
    public int peek(byte stackNumber) {
      // Handle the case when the stack is empty
      int itemPopped = 0;
      switch (stackNumber) {
        case 1:
          if (lastItemStack1 == -3) {
            return 0;
          }
          itemPopped = content[lastItemStack1];
        case 2:
          if (lastItemStack2 == -2) {
            return 0;
          }
          itemPopped = content[lastItemStack2];
        case 3:
          if (lastItemStack3 == -1) {
            return 0;
          }
          itemPopped = content[lastItemStack3];
        default:
          System.out.println("Invalid stack number chosen");
      }
      return itemPopped;
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    public boolean empty(byte stackNumber) {
      switch (stackNumber) {
        case 1:
          return (lastItemStack1 == -3);
        case 2:
          return (lastItemStack2 == -2);
        case 3:
          return (lastItemStack3 == -1);
        default:
          System.out.println("Invalid stack number chosen");
          return false;
      }
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * If the object {@code o} occurs as an item in this stack, this
     * method returns the distance from the top of the stack of the
     * occurrence nearest the top of the stack; the topmost item on the
     * stack is considered to be at distance {@code 1}. The {@code equals}
     * method is used to compare {@code o} to the
     * items in this stack.
     *
     * @param item the desired object.
     * @return the 1-based position from the top of the stack where
     * the object is located; the return value {@code -1}
     * indicates that the object is not on the stack.
     */
    public int search​(byte stackNumber, int item) {
      int counter;
      switch (stackNumber) {
        case 1:
          counter = 0;
          break;
        case 2:
          counter = 1;
          break;
        case 3:
          counter = 2;
          break;
        default:
          System.out.println("Invalid stack number chosen");
          return -1;
      }

      for (; counter <= lastItemStack1; counter += STACK_NUMBER) {
        if (item == content[counter]) {
          return (counter + 1); //FIXME
        }
      }
      return -1;
    }
  }
}
