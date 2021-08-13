/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified for educational purposes by K.S. Koo @ DBS
  */

/** Linked queue implementation */
class LQueue<E> implements Queue<E> {
    private Link<E> front;    // Pointer to front queue node
    private Link<E> rear;     // Pointer to rear queue node
    int size;		    // Number of elements in queue
    int maxSize;

    /** Constructors */
    public LQueue(int iSize) {
        // TODO:
        front = new Link<E>(null, null);
        rear = new Link<E>(null, null);
        this.size = 0;
        this.maxSize = iSize;
    }

    public boolean isEmpty() {
        // TODO:
        return size == 0;
    }

    public boolean isFull() {
        // TODO:
        return size == maxSize;
    }

    /** Put element on rear */
    public void enqueue(E it) {
        // TODO: implement exception and operation
        if(this.isFull()){
            System.out.println("Queue is Full.");
            return;
        }
        Link<E> newLink = new Link<E>(it, null);
        if(!this.isEmpty()){
            rear.setNext(newLink);
            rear = newLink;
            size++;
            return;
        }
        else{
            front.setElement(it);
            rear = front;
            size++;
            return;
        }
    }

    /** Remove and return element from front */
    public E dequeue() {
        // TODO: implement exception and operation
        if(this.isEmpty()){
            System.out.println("Queue is Empty.");
            return null;
        }
        Link<E> tmp = front;
        // front.setNext(null);     unnecessary in java (garbage collection)
        front = tmp.next();
        size--;
        return tmp.element();
    }
}
