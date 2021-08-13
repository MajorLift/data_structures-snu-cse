/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified for educational purposes by K.S. Koo @ DBS
  */

/** Queue ADT */
public interface Queue<E> {

    public boolean isEmpty();

    public boolean isFull();

    /** Place an element at the rear of the queue.
      @param it The element being enqueued. */
    public void enqueue(E it);

    /** Remove and return element at the front of the queue.
      @return The element at the front of the queue. */
    public E dequeue();
}
