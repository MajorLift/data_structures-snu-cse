/**
 Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer

 Modified by K.S. Koo @ DBS
*/

import java.io.*;

public class OpenHash<Integer, E> {
    private int M;
    private KVPair<Integer, E>[] HT;

    @SuppressWarnings("unchecked") // Generic array allocation
    OpenHash(int m) {
        M = m;
        HT = (KVPair<Integer, E>[]) new KVPair[M];
    }

    public int size() {
        // TODO:
    }

    private int h(Integer key) {
        // TODO:
    }

    /** Insert record r with key k into HT */
    boolean insert(Integer k, E r) {
        // TODO:
    }

    /** Search in hash table HT for the record with key k */
    E search(Integer k) {
        // TODO:
    }

    E remove(Integer k) {
        // TODO:
    }
}

