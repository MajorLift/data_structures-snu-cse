// Written by Kyoseung Koo

public class Sorting {
	
	/*
	 * Perform bubble sort with input argument (array).
	 * Larger (or smaller) value float to surface (like bubbles!).
	 * @param data an input array and an output sorted array.
	 */
	public static void NaiveBubbleSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = data.length - 1; j > i; j--) {
				if (data[j - 1] > data[j]) swap(data[j - 1], data[j]);
			}
		}
	}

	public static void BubbleSort(int[] data) {
		int bound = -1;
		while (bound < data.length - 1) {
			int tmp = data.length - 1;
			for (int i = data.length - 1; i > bound + 1; i--) {
				if (data[i - 1] < data[i]) { 
					swap(data[i], data[i - 1]);
					tmp = i - 1;
				}
			}
			bound = tmp;
		}
	}
	
	/*
	 * Perform selection sort with input argument (array).
	 * Select minimum value and swap with head.
	 * @param data an input array and an output sorted array.
	 */
	public static void SelectionSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[minIdx]) minIdx = j;
			}
			swap(data[i], data[minIdx]);
		}
	}

	/*
	 * Perform insertion sort with input argument (array).
	 * Insert an element into sorted list. Similar to how we put money in our wallet!
	 * @param data an input array and an output sorted array.
	 */
	public static void InsertionSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int tmp = data[i];
			int j = i;
			while (--j >= 0 && data[j] > tmp) data[j + 1] = data[j];
			data[j + 1] = tmp;
		}
	}

}
