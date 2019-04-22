package crackingthecodinginterview.bitmanipulation;

public class InsertBit {
	public int insertBit (int m, int n, int i, int j) {
		// Empty n from bit i to j
		// Create a bit string 0000...11...
		int clearBit = ~0;
		clearBit = clearBit << j + 1;
		int rightClearBit = 1;
		for(int a = 0; a < i -1; a++){
			rightClearBit = (int) (rightClearBit + Math.pow(2, a++));
		}
		clearBit = clearBit | rightClearBit;
		n = n & clearBit;
		// Shift m to starting position
		m = m << i;
		// Insert m to n
		n = n | m;
		return n; // stub
	}
}
