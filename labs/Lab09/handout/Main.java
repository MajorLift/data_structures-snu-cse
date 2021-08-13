
public class Main {
	public static void main(String args[]) {
		String path = args[0];

		// Graph initialize
		GraphMatrix gm = new GraphMatrix(path);
		GraphList gl = new GraphList(path);

		// BFS
		System.out.println("======= BFS =======");
		GraphMatrix.BFS(gm, 0);

		// DFS
		System.out.println("======= DFS =======");
		GraphList.DFS(gl, 0);

	}

}
