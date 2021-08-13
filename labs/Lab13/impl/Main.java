// Written by Kyoseung Koo

import java.util.Arrays;
import java.util.stream.Stream;


public class Main {
	public static void main(String[] args) throws Exception {
		// initialize
		for (int size = 1; size < 4096; size++) {
			int[] ref = Stream.iterate(0, x -> x + 1).limit(size).mapToInt(Integer::intValue).toArray();
			int[] clone = Arrays.copyOf(ref, ref.length);
			
			// shuffle
			naiveShuffle(ref);
			
			// Merge sort4
			int[] mres = Sorting.MergeSort(ref);
			sameArray(mres, clone);
			
			// Quick sort
			Sorting.QuickSort(ref);
			sameArray(ref, clone);
		}
		
		System.out.println("Done.");
	}
	
	public static void sameArray(int[] a, int[]  b) throws Exception {
		if (a.length != b.length) throw new Exception("Failed");
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) throw new Exception("Failed");
		}
	}
	
	public static void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println();
	}
	
	public static void naiveShuffle(int[] data) {
		for (int i = 0; i < data.length; i++) {
			int pos1 = (int) (Math.random() * data.length);
			int pos2 = (int) (Math.random() * data.length);
			
			int temp = data[pos1];
			data[pos1] = data[pos2];
			data[pos2] = temp;
		}
	}

}
