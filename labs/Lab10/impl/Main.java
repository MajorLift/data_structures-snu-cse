
public class Main {
	public static void main(String args[]) {
		String path = args[0];

		// Graph initialize
		GraphMatrix gm = new GraphMatrix(path);
		int distanceFromZero[] = new int[gm.n()];
		int allPairShortest[][] = new int[gm.n()][gm.n()];

		// Dijkstra Test
		System.out.println("======= Dijkstra =======");
		boolean s = GraphMatrix.Dijkstra(gm, 0, distanceFromZero);
		if (s) {
			for (int i = 0; i < gm.n(); i++) {
				System.out.print(distanceFromZero[i] + " ");
			}
			System.out.println();			
		} else {
			System.out.println("negative edge detected!");
		}
		

		// Floyd Test
		System.out.println("======= Floyd =======");
		boolean s2 = GraphMatrix.Floyd(gm, allPairShortest);
		if (s2) {
			for (int i = 0; i < gm.n(); i++) {
				for (int j = 0; j < gm.n(); j++) {
					System.out.print(allPairShortest[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		} else {
			System.out.println("negative cycle detected!");
		}

	}
}

