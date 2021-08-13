
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
     Modified for educational purposes by K.S. Koo @ DBS
*/

import java.io.*;

public class ToHTest {

	/**
	 * Compute the moves to solve a Tower of Hanoi puzzle. Function move does (or
	 * prints) the actual move of a disk from one pole to another.
	 * 
	 * @param n     The number of disks
	 * @param start The start pole
	 * @param goal  The goal pole
	 * @param temp  The other pole
	 */
	void TOH(int n, Pole start, Pole goal, Pole temp) {
		// TODO:
		if(n == 0) return;
		temp = n - (start + goal);
		
		TOH(n - 1, start, goal, temp);
	}

	int counter;
	Pole start;
	Pole goal;
	Pole temp;

	/** Implement a move... print out what happens */
	void move(Pole A, Pole B) {
		Integer moveDisk = A.pop();
		B.push(moveDisk);
		System.out.println(counter + ": Move " + A + " to " + B);
		counter++;
	}

	void swap(Pole a, Pole b){
		Pole tmp = b;
		b = a;
		a = tmp;
	}

	public ToHTest() {
		counter = 1;
		start = new Pole(1);
		goal = new Pole(2);
		temp = new Pole(3);
	}

	/** Successful call to TOH */
	public static void TOH(int count) {
		ToHTest obj = new ToHTest();
		obj.TOH(count, obj.start, obj.goal, obj.temp);
	}
}
