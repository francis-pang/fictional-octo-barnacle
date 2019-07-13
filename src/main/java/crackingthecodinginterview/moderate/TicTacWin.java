package crackingthecodinginterview.moderate;

/**
 * Tic Tac Win: Design an algorithm to figure out if someone has won a game of tic-tac-toe.
 */
public class TicTacWin {
  public boolean winsTicTacToe(byte[][] ticTacToeBoard) {
    // Check for edge case

    // Assumption: This is a NxN square tic tac toe board
    // Diagonal check
    byte firstElement = ticTacToeBoard[0][0];
    boolean hasWonTheGame = true;
    for (int counter = 1; counter < ticTacToeBoard.length; counter++) {
      if (firstElement != ticTacToeBoard[counter][counter]) {
        hasWonTheGame = false;
        break;
      }
    }

    if (hasWonTheGame) {
      return true;
    }

    byte lastElement = ticTacToeBoard[0][ticTacToeBoard.length - 1];
    hasWonTheGame = true;
    for (int counter = 1; counter < ticTacToeBoard.length; counter++) {
      if (lastElement != ticTacToeBoard[counter][ticTacToeBoard.length - 1 - counter]) {
        hasWonTheGame = false;
      }
    }

    if (hasWonTheGame) {
      return true;
    }

    // Horizontal
    boolean[] stillHaveChanceToWin = new boolean[ticTacToeBoard.length];
    for (boolean chance : stillHaveChanceToWin) {
      chance = true;
    }

    hasWonTheGame = true;
    for (int row = 1; row < ticTacToeBoard[0].length; row++) {
      if (ticTacToeBoard[0][row - 1] != ticTacToeBoard[0][row]) {
        hasWonTheGame = false;
        break;
      }
    }

    if (hasWonTheGame) {
      return true;
    }

    for (int column = 1; column < ticTacToeBoard.length; column++) {
      hasWonTheGame = true;
      for (int row = 0; row < ticTacToeBoard[column].length; row++) {
        // Check for past row
        if (stillHaveChanceToWin[column]) {
          stillHaveChanceToWin[column] = (ticTacToeBoard[column - 1][row] == ticTacToeBoard[column][row]);
        }

        if (hasWonTheGame // Enter only if it has been the same symbol
            && (row > 0) //Skip the first row to avoid index out of bound
            && ticTacToeBoard[column][row - 1] != ticTacToeBoard[column][row]) {
          hasWonTheGame = false;
        }
      }
      if (hasWonTheGame) {
        return true;
      }
    }
    for (boolean chance : stillHaveChanceToWin) {
      if (chance) {
        return true;
      }
    }
    return false;
  }
}
