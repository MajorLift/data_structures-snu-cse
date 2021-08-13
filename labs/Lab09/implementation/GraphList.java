import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphList implements Graph {
	// TODO: variables
	public LinkedList<ListNode>[] vertices;
	public int[] marks;
	private int numVertices;
	private int numEdges;

	private class ListNode {
		int key, value;

		ListNode(int k, int v) {
			this.key = k;
			this.value = v;
		}
	}

	GraphList(String path) {
		// TODO: constructor
		LinkedList<Integer> froms = new LinkedList<>();
		LinkedList<Integer> tos = new LinkedList<>();
		LinkedList<Integer> vals = new LinkedList<>();

		this.numVertices = CsvHelper.readFromPath(path, froms, tos, vals);
		marks = new int[numVertices];
		vertices = new LinkedList[numVertices];
		for (int i = 0; i < numVertices; i++) {
			vertices[i] = new LinkedList<ListNode>();
		}

		Iterator<Integer> fromsIter = froms.iterator();
		Iterator<Integer> tosIter = tos.iterator();
		Iterator<Integer> valsIter = vals.iterator();
		for (int i = 0; i < froms.size(); i++) {
			int from = fromsIter.next() - 1;
			int to = tosIter.next() - 1;
			int val = valsIter.next();
			setEdge(from, to, val);
		}	
	}

	@Override
	public int n() {
		// TODO:
		return this.numVertices;
	}

	@Override
	public void setEdge(int i, int j, int weight) {
		// TODO:
		int index = 0;
		for (Iterator<ListNode> iter = vertices[i].iterator(); iter.hasNext(); index++) {
			ListNode item = iter.next();
			// vertices[i].contains(new ListNode(j, weight))
			if (item.key == j) {
				item.value = weight;
				return;
			}
			else if (item.key > j) break;
		}
		if (vertices[i].size() == index) vertices[i].addLast(new ListNode(j, weight));
		else vertices[i].add(index, new ListNode(j, weight));
		this.numEdges++;
	}

	@Override
	public boolean isEdge(int v, int w) {
		// TODO:
		for (Iterator<ListNode> iter = vertices[v].iterator(); iter.hasNext();) {
			ListNode item = iter.next();
			if (item.key == w) return true;
		}
		return false;
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

	public static void DFS(GraphList g, int v) {
		// TODO:
		System.out.println(v);
		g.setMark(v, 1);
		for (Iterator<ListNode> iter = g.vertices[v].iterator(); iter.hasNext();) {
			ListNode item = iter.next();
			if (g.isEdge(v, item.key) && g.getMark(item.key) == 0) {
				DFS(g, item.key);
			}
		}
	}
}
