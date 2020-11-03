package elementsofprogramminginterviews.arrays;

import java.util.HashSet;

public class TheSudokuCheckerProblem {
    public boolean isValidPartialSudoku(int[][] sudokuBoard) {
        HashSet<Integer>[] rowSets = new HashSet[sudokuBoard.length];
        initialHashSetArray(rowSets);
        HashSet<Integer>[] columnSets = new HashSet[sudokuBoard.length];
        initialHashSetArray(columnSets);
        // We know that the grid is made of 3x3 sub-grid
        HashSet<Integer>[][] subGridSets = new HashSet[sudokuBoard.length / 3][sudokuBoard.length / 3];
        for (int i = 0; i < subGridSets.length; i++) {
            for (int j = 0; i < subGridSets.length; j++) {
                subGridSets[i][j] = new HashSet<>();
            }
        }

        for (int row = 0; row < sudokuBoard.length; row++) {
            for (int column = 0; column < sudokuBoard.length; column++) {
                int cellValue = sudokuBoard[row][column];
                if (cellValue == 0) {
                    continue;
                }
                if (!rowSets[row].add(cellValue)) {
                    return false;
                }
                if (!columnSets[column].add(cellValue)) {
                    return false;
                }
                if (!subGridSets[row / 3][column / 3].add(cellValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initialHashSetArray(HashSet<Integer>[] hashSets) {
        for (int i = 0; i < hashSets.length; i++) {
            hashSets[i] = new HashSet<>();
        }
    }
}
