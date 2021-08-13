// Test Main for LongInt ADT.
//	File IO is done through TextInputStream class.

import java.io.*;

public class MainInt {
  public static void main(String args[]) throws IOException
  {
    if (args.length != 1) {
	System.err.println("Usage: java MainInt input-file");
	System.exit(0);
    }
    TextInputStream ifs = new TextInputStream(args[0]);

    while(ifs.ready()) {
	String opnd1 = ifs.readWord();
	String op = ifs.readWord();
	String opnd2 = ifs.readWord();

	LongInt long1 = new LongInt(opnd1);
	LongInt long2 = new LongInt(opnd2);
	LongInt long3 = null;

	switch(op.charAt(0)) {
	case '+':
		long3 = long1.add(long2); break;
	case '-':
		long3 = long1.subtract(long2); break;
	case '*':
		long3 = long1.multiply(long2); break;
	}

	//System.out.print(opnd1+" "+op+" "+opnd2+" = ");
	long1.print(); System.out.print(" "+op+" ");
	long2.print(); System.out.print(" = ");
	long3.print(); System.out.println();
    }
  }
}
