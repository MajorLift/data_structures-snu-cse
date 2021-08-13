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
        this.Heap = h;
        this.n = num;
        this.size = max;
        buildheap();
    }

    /** Return current size of the heap */
    public int heapsize() {
      return Heap.length;
    }

    /** Is pos a leaf position? */
    public boolean isLeaf(int pos) {
      // TODO:
      return leftchild(pos) >= n;
    }

    /** Return position for left child of pos */
    public int leftchild(int pos) {
      // TODO:
      return 2 * pos + 1;
    }

    /** Return position for right child of pos */
    public int rightchild(int pos) {
      // TODO:
      return 2 * pos + 2;
    }

    /** Return position for parent */
    public int parent(int pos) {
      // TODO:
      return (pos - 1) / 2;
    }

    /** Heapify contents of Heap */
    public void buildheap() {
      // TODO:
      int i = n / 2 - 1;
      while (i >= 0) {
        siftdown(i--);
      }
    }

    /** Insert into heap */
    public void insert(Integer val) {
      // TODO:
      Heap[n++] = val;
      int i = n - 1;
      while (i > 0) {
        if (Heap[parent(i)] <= Heap[i]) return;
        else swap(Heap, i, parent(i));
        i = parent(i);
      }
    }

    /** Put element in its correct place */
    private void siftdown(int pos) {
      // TODO:
      while (!isLeaf(pos)) {
        int smallerChild;
        if (rightchild(pos) >= n || Heap[leftchild(pos)] <= Heap[rightchild(pos)]) smallerChild = leftchild(pos);
        else smallerChild = rightchild(pos);
        if (Heap[pos] <= Heap[smallerChild]) return;
        else swap(Heap, pos, smallerChild);
        pos = smallerChild;
      }
    }

    public Integer removemin() {     // Remove minimum value
      // TODO:
      return remove(0);
    }

    /** Remove element at specified position */
    public Integer remove(int pos) {
      // TODO:
      int result = Heap[pos];
      Heap[pos] = Heap[--n];
      siftdown(pos);
      return result;
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
