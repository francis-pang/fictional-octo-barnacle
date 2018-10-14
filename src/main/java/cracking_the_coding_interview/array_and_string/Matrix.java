package cracking_the_coding_interview.array_and_string;

public class Matrix {
	public static boolean isTwoMatrixSame(int[][] matrixA, int[][] matrixB) {
		if ((matrixA.length != matrixB.length) || (matrixA[0].length != matrixB[0].length)) {
			System.out.println("Matrices are not of the same length");
			return false;
		}
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				if (matrixA[i][j] != matrixB[i][j]) {
					System.out.println("Matrices are not the same.");
					printMatrix(matrixB);
					return false;
				}
			}
		}
		return true;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print("{" + matrix[i][j] + "},");
			}
			System.out.println();
		}
	}
}
