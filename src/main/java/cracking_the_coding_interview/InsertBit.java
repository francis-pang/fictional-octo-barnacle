package cracking_the_coding_interview;

public class InsertBit {
	public int insertBit (int m, int n, int i, int j) {
		System.out.println("Testing with m=" + Integer.toBinaryString(m) + 
				", n=" + Integer.toBinaryString(n) + 
				", i=" + Integer.toBinaryString(i) + 
				", j=" + Integer.toBinaryString(j));
		// Empty n from bit i to j
		// Create a bit string 0000...11...
		int clearBit = ~0;
		clearBit = clearBit << j + 1;
		System.out.println("After clearing left bit, clearBit= " + Integer.toBinaryString(clearBit));
		int rightClearBit = 1;
		int a = 0;
		do {
			rightClearBit = (int) (rightClearBit + Math.pow(2, a++));
		} while(a < i - 1);
		System.out.println("Creating right clear bit, rightClearBit= " + Integer.toBinaryString(rightClearBit));
		clearBit = clearBit | rightClearBit;
		System.out.println("After clearing right bit, clearBit= " + Integer.toBinaryString(clearBit));
		n = n & clearBit;
		System.out.println("After clear bit, n= " + Integer.toBinaryString(n));
		
		// Shift m to starting position
		m = m << i;
		System.out.println("After shift bit, m= " + Integer.toBinaryString(m));
		
		// Insert m to n
		n = n | m;
		System.out.println("Final n= " + Integer.toBinaryString(n));
		return n; // stub
	}
}
