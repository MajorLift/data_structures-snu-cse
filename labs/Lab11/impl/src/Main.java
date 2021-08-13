
public class Main {
	public static void main(String args[]) {
		String path = args[0];

		// Graph initialize
		GraphMatrix gm = new GraphMatrix(path);

		// Prim's Test
		System.out.println("======= Prim =======");
		GraphMatrix.Prim(gm, 0);

		// Kruskal Test
		System.out.println("======= Kruskal =======");
		GraphMatrix.Kruskal(gm);
	}
}


