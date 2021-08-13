import java.lang.reflect.Array;

import javax.annotation.processing.SupportedSourceVersion;

/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified for educational purposes by K.S. Koo @ DBS
  */

/** Array-based stack implementation */
class AStack<E> implements Stack<E> {

    private int maxSize;            // Maximum size of stack
    private int top;                // Index for top Object
    private E[] listArray;         // Array holding stack
    
    @SuppressWarnings({"unchecked"})
    /** Constructors */
    AStack(int size) {
        // TODO:
        this.listArray = (E[])new Object[size];
        this.maxSize = size;
        this.top = -1;
    }

    public boolean isEmpty() {
        // TODO:
        return top == -1;
    }

    public boolean isFull() {
        // TODO:
        return top == maxSize - 1;
    }

    /** Push "it" onto stack */
    public void push(E it) {
        // TODO: implement exception and operation
        if(this.isFull()){
            System.out.println("Stack overflow error.");
            return;
        }
        listArray[++top] = it;
    }

    /** Remove and top element */
    public E pop() {
        // TODO: implement exception and operation
        if(this.isEmpty()){
            System.out.println("Stack underflow error.");
            return null;
        }
        return listArray[top--];
    }
}
