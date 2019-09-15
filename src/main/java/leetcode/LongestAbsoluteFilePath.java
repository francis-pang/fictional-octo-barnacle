package leetcode;

public class LongestAbsoluteFilePath {
  static class Solution {
    private static final char LINE_SEPARATOR = '\n';
    private static final char TAB_SEPARATOR = '\t';
    private static final char FILE_INDICATOR_CHARCTER = '.';

    public int lengthLongestPath(String input) {
      int maxPathLength = 0;
      if (input == null || input.length() == 0) {
        return maxPathLength;
      }
      char[] inputCharArray = input.toCharArray();
      // Find the next directory/ file
      TrieNode currentNode = new TrieNode();
      do {
        currentNode = locateNextDirectoryOrFile(inputCharArray, currentNode);
        if (currentNode != null &&
            currentNode.isAFile &&
            currentNode.totalLength > maxPathLength) {
          maxPathLength = currentNode.totalLength;
        }
      } while (currentNode != null);
      return maxPathLength;
    }

    private TrieNode locateNextDirectoryOrFile(char[] stringArray, TrieNode rootNode) {
      // Need to consider case where it start with \n
      int directoryLevel = deriveNumberOfDirectoryLevel(stringArray, rootNode.endIndex); // Retrieve the number of level first
      TrieNode parentNode = rootNode;
      while (directoryLevel <= parentNode.depth) {
        parentNode = parentNode.parent;
      }
      int currentIndex = rootNode.endIndex + (directoryLevel - 1);
      int stringLength = 0;
      boolean foundAFile = false;
      while (currentIndex < stringArray.length) {
        if (stringArray[currentIndex] == FILE_INDICATOR_CHARCTER) {
          foundAFile = true;
        }
        if (stringArray[currentIndex] == LINE_SEPARATOR) {
          return new TrieNode(parentNode, foundAFile, currentIndex + 1, stringLength);
        } else {
          stringLength++;
        }
        currentIndex++;
      }
      if (stringLength == 0) {
        return null;
      } else {
        // By this point of time, we will have reached the end and not found a line separator
        return new TrieNode(parentNode, foundAFile, currentIndex, stringLength);
      }
    }

    private int deriveNumberOfDirectoryLevel(char[] stringArray, int startPosition) {
      int numberOfLevel = 0;
      int currentIndex = startPosition;
      while ((currentIndex) < stringArray.length) {
        if (stringArray[currentIndex] == TAB_SEPARATOR) {
          currentIndex++;
          numberOfLevel++;
        } else {
          break;
        }
      }
      return numberOfLevel + 1;
    }

    public class TrieNode {
      public int depth;
      public int totalLength;
      public TrieNode parent;
      public boolean isAFile;
      public int endIndex;

      public TrieNode(TrieNode parent, boolean isAFile, int endIndex,
                      int stringLength) {
        this.depth = parent.depth + 1;
        this.parent = parent;
        this.isAFile = isAFile;
        this.endIndex = endIndex;
        totalLength = (this.parent.depth == 0) ? stringLength : this.parent.totalLength + 1 + stringLength;
      }
      // Meant for root node
      public TrieNode() {
      }
    }
  }
}
