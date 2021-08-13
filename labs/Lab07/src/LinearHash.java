/**
 Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer

 Modified by K.S. Koo @ DBS
*/

import java.util.ArrayList;

public class LinearHash<E> {
    private int mExp;
    
// TODO: Choose your favorite
   private ArrayList<KVPair<Integer, E>> HT;
//    private KVPair<Integer, E>[] HT;
    
    private int splitindex;

    @SuppressWarnings("unchecked") // Generic array allocation
    LinearHash(int m) {
    	mExp = m;
    	// TODO:
        
    }

    public int size() {
        // TODO:
    }

    private int h(int key) {
        // TODO:
    }
    
    private int h_extended(int key) {
        // TODO:
    }
    
    boolean insert(Integer k, E r) {
        // TODO: Consider split
    }

    E search(Integer k) {
        // TODO: Consider split
    }
}

