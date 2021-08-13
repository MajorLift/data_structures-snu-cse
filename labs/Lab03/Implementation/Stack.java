/** Source code example for "A Practical Introduction to Data
  Structures and Algorithm Analysis, 3rd Edition (Java)" 
  by Clifford A. Shaffer
  Copyright 2008-2011 by Clifford A. Shaffer

  Modified for educational purposes by K.S. Koo @ DBS
  */

/** Stack ADT */
public interface Stack<E> {

    public boolean isEmpty();

    public boolean isFull();

    /** Push an element onto the top of the stack.
      @param it The element being pushed onto the stack. */
    public void push(E it);

    /** Remove and return the element at the top of the stack.
      @return The element at the top of the stack. */
    public E pop();
};
