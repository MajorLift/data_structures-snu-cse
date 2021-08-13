/**
 Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer

 Modified by K.S. Koo @ DBS
*/

/** Container class for a key-value pair */
class KVPair<Integer, E> {
    private KVPair<Integer, E> next;
    private Integer k;
    private E e;

    /** Constructors */
    KVPair() { k = null; e = null; next = null; }
    KVPair(Integer kval, E eval) { k = kval; e = eval; next = null; }

    /** Data member access functions */
    public Integer getKey() { return k; }
    public E getValue() { return e; }
    public boolean hasNext() { return next != null; }
    public KVPair<Integer, E> getNext() { return next; }
    public void setNext(KVPair<Integer, E> input) { next = input; }
}
