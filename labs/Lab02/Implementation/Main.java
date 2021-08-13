/** 
    Written by K.S. Koo @ DBS
*/

public class Main {
	public static void main(String[] input) {
		int num = Integer.parseInt(input[1]);
		if (input[0].contentEquals("fib")) {
			System.out.println("fibr("+num+") is " +FibTest.fibi(num));
		} else if (input[0].contentEquals("toh")) {
			ToHTest.TOH(num);
		}
	}
}
