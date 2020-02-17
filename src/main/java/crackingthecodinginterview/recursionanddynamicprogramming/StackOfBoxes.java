package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StackOfBoxes {
  public int maxHeightOfStackedBoxes(int[] heights, int[] widths, int[] depths) {
    ArrayList<Box> boxes = constructBox(heights, widths, depths);
    Map<Integer, Integer> memoTable = new HashMap<>();
    Box infinityBigBox = new Box(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    return maxHeightOfStackedBoxes(boxes, memoTable, 0, infinityBigBox);
  }

  private int maxHeightOfStackedBoxes(ArrayList<Box> boxes, Map<Integer, Integer> memoTable, int index,
                                      Box boxLimitation) {
    if (memoTable.containsKey(index)) {
      return memoTable.get(index);
    }
    int maxHeight = boxes.get(boxes.size() - 1).height;
    if (index == boxes.size() - 1) {
      memoTable.put(index, maxHeight);
      return boxes.get(boxes.size() - 1).height;
    }
    int iteratingIndex = index;
    while (iteratingIndex < boxes.size()) {
      Box box = boxes.get(iteratingIndex);
      if (box.isSmaller(boxLimitation)) {
        break;
      }
      iteratingIndex++;
    }
    if (iteratingIndex == boxes.size()) {
      return 0;
    }
    int maxWithThisBox = boxes.get(iteratingIndex).height + maxHeightOfStackedBoxes(boxes, memoTable,
        iteratingIndex + 1, boxes.get(iteratingIndex));
    int maxSkippingThisBox = maxHeightOfStackedBoxes(boxes, memoTable, iteratingIndex + 1, boxLimitation);
    int max = Math.max(maxWithThisBox, maxSkippingThisBox);
    memoTable.put(index, max);
    return max;
  }

  private ArrayList<Box> constructBox(int[] heights, int[] widths, int[] depths) {
    ArrayList<Box> sortedBoxes = new ArrayList<>();
    for (int i = 0; i < heights.length; i++) {
      sortedBoxes.add(new Box(heights[i], widths[i], depths[i]));
    }
    Collections.sort(sortedBoxes);
    return sortedBoxes;
  }

  class Box implements Comparable<Box> {
    public int height;
    public int width;
    public int depth;

    public Box(int height, int width, int depth) {
      this.height = height;
      this.width = width;
      this.depth = depth;
    }

    public boolean isSmaller(Box bigger) {
      return (this.height < bigger.height && depth < bigger.depth && width < bigger.width);
    }

    @Override
    public int compareTo(Box o) {
      if (this.height != o.height) {
        return this.height - o.height;
      }
      if (this.width != o.width) {
        return this.width - o.width;
      }
      if (this.depth != o.depth) {
        return this.depth - o.depth;
      }
      return 0;
    }
  }

  public static void main(String[] args) {

  }
}
