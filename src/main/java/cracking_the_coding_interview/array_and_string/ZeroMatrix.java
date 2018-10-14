package cracking_the_coding_interview.array_and_string;

public class ZeroMatrix {
	public int[][] convertAdjToZeroMatrix(int[][] matrix) {
		//This stores an indicator if the row needs to be zero or not.
		boolean[] rowToZero = new boolean[matrix.length];

		//This stores an indicate if the column needs to be set to zero or not
		boolean[] columnToZero = new boolean[matrix[0].length];
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[0].length; column++) {
				if (matrix[row][column] == 0) {
					rowToZero[row] = true;
					columnToZero[column] = true;
				}
			}
		}
		// Zero all the rows that has been indicated
		for (int row = 0; row < matrix.length; row++) {
			if (rowToZero[row] == true) {
				for (int column = 0; column < matrix[0].length; column++) {
					matrix[row][column] = 0;
				}
			}
		}
		// Zero all the columns that has been indicated
		for (int column = 0; column < matrix[0].length; column++) {
			if (columnToZero[column] == true) {
				for (int row = 0; row < matrix.length; row++) {
					matrix[row][column] = 0;
				}
			}
		}
		return matrix;
	}
}
