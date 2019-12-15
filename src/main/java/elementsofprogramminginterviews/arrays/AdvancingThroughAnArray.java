package elementsofprogramminginterviews.arrays;

public class AdvancingThroughAnArray {
  public boolean winGame(int[] board) {
    int[] answer = new int[board.length];
    // Iniitalise to all 0
    answer[board.length - 1] = 1;
    return fillAnswer(board, answer, 0);
  }

  private boolean fillAnswer(int[] board, int[] memo, int startIndex) {
    int TRUE = 1;
    int FALSE = -1;
    int UNFILL = 0;
    if (memo[startIndex] != UNFILL) {
      return memo[startIndex] == TRUE;
    }
    int naxStep = board[startIndex];
    for (int i = 1; i <= naxStep; i++) {
      if (fillAnswer(board, memo, startIndex + 1)) {
        memo[startIndex] = TRUE;
        return true;
      }
    }
    memo[startIndex] = FALSE;
    return false;
  }
}
