
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
    Modified for educational purposes by K.S. Koo @ DBS
*/

import java.io.*;

public class FibTest {

	/**
	 * Iteratively generate and return the n'th Fibonacci number
	 */
	static long fibi(int n) {
		// fibr(91) is the largest value that fits in a long
		// TODO:
		if(n <= 0 || n > 91) return -1;
		long a = 0;
		long b = 1;
		long c = 1;
		for(int i = 0; i < n; i++){
			a = b;
			b = c;
			c = a + b;
		}
		return c;
	}

	/**
	 * Recursively generate and return the n'th Fibonacci number
	 */
	static long fibr(int n) {
		// fibr(91) is the largest value that fits in a long
		if (n <= 0 || n > 91) return -1;
		if ((n == 1) || (n == 2))return 1; // Base case
		return fibr(n - 1) + fibr(n - 2); // Recursive call
	}

}
