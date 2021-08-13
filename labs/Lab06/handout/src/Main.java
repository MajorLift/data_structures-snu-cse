/**
 * Written by K.S. Koo @ DBS
 */

public class Main {
    // main point. 
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        OpenHash<Integer, String> T = new OpenHash<Integer, String>(size);
        System.out.println("Hash table size: " + T.size());

        // Insert test
        int[] keys1 = {1000, 9530, 3013, 9877, 2007, 1057, 9879, 1000};
        String[] vals1 = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < keys1.length; i++) {
            boolean res = T.insert(keys1[i], vals1[i]);
            if (res) System.out.println("inserted: <" + keys1[i] + ", " + vals1[i] + ">");
            else System.out.println("insert: <" + keys1[i] + ", " + vals1[i] + "> Failed!");
        }

        // Search test
        int[] keys2 = {1000, 9530, 2007, 2000};
        for (int i = 0; i < keys2.length; i++) {
            String res = T.search(keys2[i]);
            if (res != null) System.out.println("search: " + keys2[i] + " -> " + res);
            else System.out.println("search: " + keys2[i] + ": Not Found!");
        }

        // Delete test
        int[] keys3 = {2007, 1057, 9877, 2007};
        for (int i = 0; i < keys3.length; i++) {
            String res = T.remove(keys3[i]);
            if (res != null) System.out.println("delete: " + keys3[i] + " -> " + res);
            else System.out.println("delete: " + keys3[i] + ": Not Found!");
        }

        // Last search
        String res = T.search(2007);
        if (res != null) System.out.println("search: 2007 -> " + res);
        else System.out.println("search: 2007: Not Found!");
    }

}


