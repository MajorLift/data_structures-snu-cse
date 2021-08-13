import java.util.Arrays;
import java.util.Stack;

// Written by Kyoseung Koo

public class Sorting {
	
	/*
	 * Perform merge sort with input argument (array).
	 * Split all elements into buckets and merge two buckets incrementally.
	 * @param data is an input array.
	 * Return output sorted array.
	 */
	public static int[] MergeSort(int[] data) {
		if (data.length <= 1) return data;
		int[] Left = MergeSort(Arrays.copyOfRange(data, 0, data.length / 2 - 1));
		int[] Right = MergeSort(Arrays.copyOfRange(data, data.length / 2, data.length - 1));
		return Merge(Left, Right);
	}

	private static int[] Merge(int[] A, int[] B) {
		int[] result = new int[A.length + B.length];
		for (int i = j = k = 0; i < A.length && j < B.length;) {
			if (A[i] < B[j]) result[k++] = A[i++];
			else result[k++] = B[j++];
		}
		while (i < A.length) result[k++] = A[i++]; 
		while (j < B.length) result[k++] = B[i++];
		return result;
	}
	
	/*
	 * Perform quick sort with input argument (array).
	 * Left side of pivot is smaller, right side of pivot is larger.
	 * @param data is an input array and an output sorted array.
	 */
	public static void QuickSort(int[] data) {
		QuickSort(data, 0, data.length - 1);
	}

	private static void QuickSort(int[] data, int left, int right) {
		if (left >= right) return;
		int pivotIdx = (left + right) / 2;
		int pivot = data[pivotIdx];
		swap(data[pivotIdx], data[left]);
		for (int i = left, j = right + 1; i < j;) {
			while (i < right && data[++i] < pivot);
			while (j > left && data[--j] > pivot);
			if (i < j) swap(data[i], data[j]);
		}
		swap(data[j], data[left]);
		QuickSort(data, left, j - 1);
		QuickSort(data, j + 1, right);
	}

	public static void QuickIter(int[] data) {
		Stack<E> stack = new Stack();
		stack.push(0);
		stack.push(data.length - 1);
		while (!stack.isEmpty()) {
			int right = stack.pop();
			int left = stack.pop();
			
		}
	}
}
