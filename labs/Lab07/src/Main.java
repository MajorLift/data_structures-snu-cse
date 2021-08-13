/**
 * Written by K.S. Koo @ DBS
 */

public class Main {
    // main point. 
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        LinearHash<Integer> T = new LinearHash<Integer>(size);
        System.out.println("Hash table size: " + T.size());

        // Insert test
        for (int i = 1; i < args.length; i++) {
            T.insert(Integer.parseInt(args[i]), Integer.parseInt(args[i]));
        }

        // Search test
        for (int i = 0; i < args.length; i++) {
            T.search(Integer.parseInt(args[i]));
        }
    }
}


