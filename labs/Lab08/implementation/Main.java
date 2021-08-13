import java.util.Random;

/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified by Kyoseung Koo
  */

public class Main {
    // main point. 
    public static void main(String[] args) {
        test1();		// min-heap test with removemin 1
        test2();		// min-heap test with removemin 2
        test3();		// random remove test
    }
    
    public static void test1() {
    	System.out.println("======= test1() =======");
    	
        int i;
        int B[] = {73, 6, 57, 88, 60, 34, 83, 72, 48, 85};
        MinHeap BH = new MinHeap(B, 10, 10);
        
        // to print
        StringBuilder out = new StringBuilder();
        for (i=0; i<10; i++) out.append(BH.removemin() + " ");
        System.out.println(out.toString());
    }  

    public static void test2() {
    	System.out.println("======= test2() =======");
    	
        int i;
        int A[] = new int[20];
        for (i=0; i<20; i++) A[i] = i;
        MinHeap.randomShuffle(A);
        MinHeap AH = new MinHeap(A, 20, 20);
        
        // to print
        StringBuilder out = new StringBuilder();
        for (i=0; i<20; i++) out.append(AH.removemin() + " ");
        System.out.println(out.toString());
    }

    public static void test3() {
    	System.out.println("======= test3() =======");
        Random value = new Random(); // Hold the Random class object
        int i;
        int A[] = new int[20];
        int error = 0;
        for (int test=0; test<1000; test++) {
            for (i=0; i<20; i++) A[i] = i;
            MinHeap.randomShuffle(A);
            MinHeap AH = new MinHeap(A, 20, 20);
            for (i=20; i>=1; i--) {
                int x = Math.abs(value.nextInt()) % i;
                if (x >= i) error++;
                AH.remove(x);
            }
        }
        System.out.println("Error count: " + String.valueOf(error));
    }
    
      

}

