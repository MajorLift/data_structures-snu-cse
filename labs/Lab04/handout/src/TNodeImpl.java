/**
 * Written by K.S. Koo @ DBS
 */

public class TNodeImpl<E> implements TNode<E> {

	private TNodeImpl<E> parent;
	private TNodeImpl<E> left;
	private TNodeImpl<E> right;
	private E element;

	// 
	public TNodeImpl(E value) {
		// TODO:
	}

	public TNodeImpl(E value, TNodeImpl<E> par, TNodeImpl<E> left, TNodeImpl<E> right) {
		// TODO:
	}

	@Override
	public void setValue(E value) {
		// TODO:
	}

	@Override
	public E getValue() {
		// TODO:
	}

	@Override
	public TNode<E> getLeft() {
		// TODO:
	}

	@Override
	public TNode<E> getRight() {
		// TODO:
	}

	@Override
	public void setRight(TNode<E> right) {
		// TODO:
	}

	@Override
	public void setLeft(TNode<E> left) {
		// TODO:
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}
	
	// insert value into input tree named "root"
	public static void insertStringToBST(TNode<String> root, String value) {
		// TODO: find leaf node and insert value (element)
		// find leaf node
		
		// Insert
        
	}

}
