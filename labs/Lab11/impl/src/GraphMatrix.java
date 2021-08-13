import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class GraphMatrix implements Graph {

	private int[][] matrix; // The edge matrix
	public int[] mark; // The mark array

	GraphMatrix(String path) {
		LinkedList<Integer> froms = new LinkedList<>();
		LinkedList<Integer> tos = new LinkedList<>();
		LinkedList<Integer> values = new LinkedList<>();
		int max = CsvHelper.readFromPath(path, froms, tos, values);

		// make linked lists
		int n = max;
		matrix = new int[n][n];
		mark = new int[n];

		// fill the graph
		Iterator<Integer> fromIterator = froms.iterator();
		Iterator<Integer> toIterator = tos.iterator();
		Iterator<Integer> valueIterator = values.iterator();
		for (int i = 0; i < froms.size(); i++) {
			int from = fromIterator.next() - 1;
			int to = toIterator.next() - 1;
			int value = valueIterator.next();

			setEdge(from, to, value);
			setEdge(to, from, value);
		}
	}

	@Override
	public int n() {
		return mark.length;
	}

	/** Set the weight for an edge */
	@Override
	public void setEdge(int i, int j, int weight) {
		matrix[i][j] = weight;
	}

	/** Determine if an edge is in the graph */
	@Override
	public boolean isEdge(int v, int w) {
		return !(matrix[v][w] == 0);
	}
	
	/** Determine if an edge is in the graph */
	@Override
	public int getEdge(int v, int w) {
		return matrix[v][w];
	}

	@Override
	public void setMark(int v, int val) {
		mark[v] = val;
	}

	@Override
	public int getMark(int v) {
		return mark[v];
	}
	
	public static boolean isAllMakred(GraphMatrix g) {
		for (int i = 0; i < g.n(); i++) {
			if (g.getMark(i) == 0) return false; 
		}
		
		return true;
	}
	
	public static int getMinWithoutMark(GraphMatrix g, int[] d) {
		int min = Integer.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < d.length; i++) {
			if (g.getMark(i) == 0 && min > d[i]) {
				min = d[i];
				idx = i;
			}
		}
		
		return idx;
	}

	public static void Prim(GraphMatrix g, int s) {
		// TODO:
	}
	
	public static void Kruskal(GraphMatrix g) {
		// TODO:
	}
}
