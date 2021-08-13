// Written by Kyoseung Koo

import java.util.stream.Stream;


public class Main {
	public static void main(String[] args) {
		// initialize
		int size = Integer.valueOf(args[0]);
		int[] ref = Stream.iterate(0, x -> x + 1).limit(size).mapToInt(Integer::intValue).toArray();
		int[] array = new int[size];
		
		// shuffle
		naiveShuffle(ref);
		
		// initial copy
		System.arraycopy(ref, 0, array, 0, size);
		System.out.print("Initial array\t: "); printArray(array);
		
		// Bubble sort
		System.arraycopy(ref, 0, array, 0, size);
		Sorting.BubbleSort(array);
		System.out.print("Bubble Sort\t: "); printArray(array);
		
		// selection sort
		System.arraycopy(ref, 0, array, 0, size);
		Sorting.SelectionSort(array);
		System.out.print("Selection Sort\t: "); printArray(array);
		
		// Insertion sort
		System.arraycopy(ref, 0, array, 0, size);
		Sorting.InsertionSort(array);
		System.out.print("Insertion Sort\t: "); printArray(array);
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
