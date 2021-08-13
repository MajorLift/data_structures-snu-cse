/**
 * Written by K.S. Koo @ DBS
 */

interface TNode<E> {

	public TNode<E> getLeft();
	public TNode<E> getRight();
	public E getValue();
	
	public void setRight(TNode<E> value);
	public void setLeft(TNode<E> value);
	public void setValue(E value);
	
}
