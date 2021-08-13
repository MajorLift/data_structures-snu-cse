import java.util.Random;

/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified by Kyoseung Koo
  */

/** Min-heap implementation */
public class MinHeap {
    private int[] Heap;   // Pointer to the heap array
    private int size;   // Maximum size of the heap
    private int n;      // Number of things in heap

    public MinHeap(int[] h, int num, int max) { 
        Heap = h;
        n = num;
        size = max;
        buildheap();
    }

    /** Return current size of the heap */
    public int heapsize() {
        return n;
    }

    /** Is pos a leaf position? */
    public boolean isLeaf(int pos) {
    	// TODO:
    }

    /** Return position for left child of pos */
    public int leftchild(int pos) {
    	// TODO:
    }

    /** Return position for right child of pos */
    public int rightchild(int pos) {
    	// TODO:
    }

    /** Return position for parent */
    public int parent(int pos) {
    	// TODO:
    }

    /** Heapify contents of Heap */
    public void buildheap() {
    	// TODO:
    }

    /** Insert into heap */
    public void insert(Integer val) {
    	// TODO:
    }

    /** Put element in its correct place */
    private void siftdown(int pos) {
    	// TODO:
    }

    public Integer removemin() {     // Remove minimum value
    	// TODO:
    }

    /** Remove element at specified position */
    public Integer remove(int pos) {
    	// TODO:
    }

    /** Swap two Objects in an array
      @param A The array
      @param p1 Index of one Object in A
      @param p2 Index of another Object A
      */
    public static void swap(int[] A, int p1, int p2) {
        int temp = A[p1];
        A[p1] = A[p2];
        A[p2] = temp;
    }
    
    static private Random value = new Random(); // Hold the Random class object
    public static void randomShuffle(int[] A) {
	  for (int i = A.length; i > 0; i--) {// for each i
	    swap(A, i-1, Math.abs(value.nextInt()) % i);  //   swap A[i-1] with
	  }
	}
}
