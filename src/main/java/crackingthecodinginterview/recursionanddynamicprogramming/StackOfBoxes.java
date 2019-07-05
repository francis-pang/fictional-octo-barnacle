package crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class StackOfBoxes {
  public int heightOfStack(Box[] boxes) {
    // Sort the boxes first
    PriorityQueue<Box> sortedBoxesByVolume = new PriorityQueue<>();
    for (Box box : boxes) {
      sortedBoxesByVolume.add(box);
    }
    return maxHeightOfStack(sortedBoxesByVolume.poll(), sortedBoxesByVolume);
  }

  private int maxHeightOfStack(Box currentBox, PriorityQueue<Box> sortedBoxesByVolume) {
    if (currentBox == null) {
      return 0;
    }
    if (sortedBoxesByVolume.isEmpty()) {
      return currentBox.height;
    }
    List<Box> sameVolumeBoxes = new ArrayList<>();
    Box firstBox = sortedBoxesByVolume.poll();
    sameVolumeBoxes.add(firstBox);
    while (sortedBoxesByVolume.peek() != null &&
        firstBox.calculateVolume() == sortedBoxesByVolume.peek().calculateVolume()) {
      sameVolumeBoxes.add(sortedBoxesByVolume.poll());
    }
    int maxHeight = 0;
    for (Box box : sameVolumeBoxes) {
      int height;
      if (box.isBiggerThan(currentBox)) {
        height = currentBox.height + maxHeightOfStack(box, sortedBoxesByVolume);
      } else {
        height = maxHeightOfStack(currentBox, sortedBoxesByVolume);
      }
      if (height > maxHeight) {
        maxHeight = height;
      }
    }
    sortedBoxesByVolume.addAll(sameVolumeBoxes);
    return maxHeight;
  }

  static class Box implements Comparator<Box>, Comparable<Box> {
    private int width;
    private int height;
    private int depth;

    public Box(int width, int height, int depth) {
      this.width = width;
      this.height = height;
      this.depth = depth;
    }

    public boolean isBiggerThan(Box otherBox) {
      return (this.depth > otherBox.depth
          && this.height > otherBox.height
          && this.width > otherBox.width);
    }

    public int calculateVolume() {
      return width * height * depth;
    }

    @Override
    public int compare(Box o1, Box o2) {
      if (o1.calculateVolume() == o2.calculateVolume()) {
        return 0;
      } else if (o1.calculateVolume() < o2.calculateVolume()) {
        return 1;
      } else {
        return -1;
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Box)) return false;
      Box box = (Box) o;
      return width == box.width &&
          height == box.height &&
          depth == box.depth;
    }

    @Override
    public int hashCode() {
      return Objects.hash(width, height, depth);
    }

    @Override
    public int compareTo(Box o) {
      if (calculateVolume() == o.calculateVolume()) {
        return 0;
      } else if (calculateVolume() > o.calculateVolume()) {
        return 1;
      } else {
        return -1;
      }
    }
  }
}
