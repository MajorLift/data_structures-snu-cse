/**
 Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer

 Modified by K.S. Koo @ DBS
*/

import java.io.*;

public class OpenHash<Integer, E> {
    private KVPair<Integer, E>[] HT;
    private int M;

    @SuppressWarnings("unchecked") // Generic array allocation
    OpenHash(int m) {
        M = m;
        HT = (KVPair<Integer, E>[]) new KVPair[M];
    }

    public int size() {
        // TODO:
        // return this.size;
        return this.M;
    }

    private int h(Integer key) {
        // TODO:
        return (int)key % M;
    }

    /** Insert record r with key k into HT */
    boolean insert(Integer k, E r) {
        // TODO:
        if(HT[h(k)] == null){
            HT[h(k)] = new KVPair(k, r);
            return true;
        }
        else{
            KVPair<Integer, E> curr = HT[h(k)];
            while(curr != null){
                if(curr.getKey().equals(k)) return false;
                else if(curr.getNext() == null){
                    curr.setNext(new KVPair(k, r));
                    return true;
                }
                else curr = curr.getNext();
            }
            return false;
        }
    }

    /** Search in hash table HT for the record with key k */
    E search(Integer k) {
        // TODO:
        if(HT[h(k)] == null) return null;
        KVPair<Integer, E> curr = HT[h(k)];
        while(curr != null){
            if(curr.getKey().equals(k)) return curr.getValue();
            else curr = curr.getNext();
        }
        return null;
    }

    E remove(Integer k) {
        // TODO:
        if(HT[h(k)] == null) return null;
        KVPair<Integer, E> curr = HT[h(k)];
        KVPair<Integer, E> prev = new KVPair();
        prev.setNext(curr);
        while(curr != null){
            if(curr.getKey().equals(k)){
                prev.setNext(curr.getNext());
                return curr.getValue();
            }
            else{
                curr = curr.getNext();
                prev = prev.getNext();
            }
        }
        return null;
    }
}

