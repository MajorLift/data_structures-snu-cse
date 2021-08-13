/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified for educational purposes by K.S. Koo @ DBS
  */

/** Linked queue implementation */
class LQueue<E> implements Queue<E> {
    private Link<E> front;    // Pointer to front queue node
    private Link<E> rear;     // Pointer to rear queuenode
    int size;		    // Number of elements in queue
    int maxSize;

    /** Constructors */
    public LQueue(int iSize) {
        // TODO:
    }

    public boolean isEmpty() {
        // TODO:
    }

    public boolean isFull() {
        // TODO:
    }

    /** Put element on rear */
    public void enqueue(E it) {
        // TODO: implement exception and operation
    }

    /** Remove and return element from front */
    public E dequeue() {
        // TODO: implement exception and operation
    }
}
