import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphMatrix implements Graph {
	// TODO: variables
	private int[][] matrix;
	public int[] marks;
	private int n;

	GraphMatrix(String path) {
		// TODO: constructor
		LinkedList<Integer> froms = new LinkedList<>();
		LinkedList<Integer> tos = new LinkedList<>();
		LinkedList<Integer> vals = new LinkedList<>();
		this.n = CsvHelper.readFromPath(path, froms, tos, vals);
		this.matrix = new int[n][n];
		this.marks = new int[n];
		
		Iterator<Integer> fromIter = froms.iterator();
		Iterator<Integer> toIter = tos.iterator();
		Iterator<Integer> valIter = vals.iterator();
		for (int i = 0; i < froms.size(); i++) {
			int from = fromIter.next() - 1;
			int to = toIter.next() - 1;
			int val = valIter.next();
			setEdge(from, to, val);
		}
	}

	@Override
	public int n() {
		// TODO:
		return this.n;
	}

	@Override
	public void setEdge(int i, int j, int weight) {
		// TODO:
		matrix[i][j] = weight;
	}

	@Override
	public boolean isEdge(int v, int w) {
		// TODO:
		return matrix[v][w] != 0;
	}

	@Override
	public void setMark(int v, int val) {
		// TODO:
		marks[v] = val;	
	}

	@Override
	public int getMark(int v) {
		// TODO:
		return marks[v];
	}

	public static Deque<Integer> queue = new LinkedList<>();
	public static void BFS(GraphMatrix g, int v) {
		// TODO:
		System.out.println(v);
		queue.push(v);
		g.setMark(v, 1);
		while (!queue.isEmpty()) {
			int item = queue.removeLast();
			for (int i = 0; i < g.n(); i++) {
				if (g.isEdge(item, i) && (g.getMark(i) == 0)) {
					System.out.println(i);
					queue.push(i);
					g.setMark(i, 1);
				}
			}
		}	
	}
}
