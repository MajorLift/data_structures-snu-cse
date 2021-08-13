import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvHelper {
    public static int readFromPath(String path, LinkedList<Integer> froms, LinkedList<Integer> tos, LinkedList<Integer> vals) {
        int numVertices = -1;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row = null;
            while((row = csvReader.readLine()) != null) {
                String[] items = row.split(",");
                
                int from = Integer.parseInt(items[0]);
                int to = Integer.parseInt(items[1]);
                int val = Integer.parseInt(items[2]);

                froms.add(from);
                tos.add(to);
                vals.add(val);

                numVertices = Math.max(Math.max(numVertices, from), to);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return numVertices;
    }
}