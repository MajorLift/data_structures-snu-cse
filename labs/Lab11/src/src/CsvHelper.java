import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class CsvHelper {
	public static int readFromPath(String path, LinkedList<Integer> froms, LinkedList<Integer> tos, LinkedList<Integer> values) {
		int max = -1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] items = line.split(",");

				int from = Integer.parseInt(items[0]);
				int to = Integer.parseInt(items[1]);
				int value = Integer.parseInt(items[2]);

				froms.add(from);
				tos.add(to);
				values.add(value);

				max = Math.max(Math.max(max, from), to);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1; // ERROR
		}
		return max;
	}
}
