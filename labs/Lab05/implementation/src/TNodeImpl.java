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
		this.parent = null;
		this.left = null;
		this.right = null;
		this.element = value;
	}

	public TNodeImpl(E value, TNodeImpl<E> par, TNodeImpl<E> left, TNodeImpl<E> right) {
		this.parent = par;
		this.left = left;
		this.right = right;
		this.element = value;
	}

	@Override
	public void setValue(E value) {
		this.element = value;
	}

	@Override
	public E getValue() {
		return this.element;
	}

	@Override
	public TNode<E> getLeft() {
		return this.left;
	}

	@Override
	public TNode<E> getRight() {
		return this.right;
	}

	@Override
	public void setRight(TNode<E> right) {
		this.right = (TNodeImpl<E>) right;
	}

	@Override
	public void setLeft(TNode<E> left) {
		this.left = (TNodeImpl<E>) left;
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}

	private static TNode<String> find(TNode<String> root, String value){
		TNode<String> curr = root;
		if(curr == null) return null;
		int cmp = value.compareTo(curr.getValue());
		if(cmp < 0) return find(curr.getLeft(), value);
		else if(cmp > 0) return find(curr.getRight(), value);
		else return curr;
	}

	private boolean contains(TNode<String> root, String value){
		return find(root, value) != null;
	}

	public static String findString(TNode<String> root, String value){
		TNode<String> foundNode = find(root, value);
		if(foundNode == null) return null;
		else return foundNode.getValue();
	}
	
	public static TNode<String> removeString(TNode<String> root, String value){
		TNode<String> delNode = find(root, value);
		if(delNode == null) return null;
		if(delNode.getLeft() == null) delNode = delNode.getRight();
		else if(delNode.getRight() == null) delNode = delNode.getLeft();
		else{
			TNode<String> tmp = delNode.getRight();
			delNode.setValue(getMin(tmp).getValue());
			delNode.setRight(removeMin(tmp));
		}
		return root;
	}
	
	public static TNode<String> getMin(TNode<String> root){
		TNode<String> curr = root;
		if(curr.getLeft() == null) return curr;
		else return getMin(curr.getLeft());
	}

	public static TNode<String> removeMin(TNode<String> root){
		TNode<String> curr = root;
		if(curr.getLeft() == null) return curr.getRight();
		curr.setLeft(removeMin(curr.getLeft()));
		return root;
	}

	
	// public static TNode<String> removeMin(TNode<String> root){
	// 	TNode<String> curr = root;
	// 	if(curr == null) return curr;
	// 	if(curr.getLeft() == null) return curr.getRight(); // exclude min, which is curr
	// 	TNode<String> parent = curr;
	// 	while(curr.getLeft() != null) {
	// 		parent = curr;
	// 		curr = curr.getLeft();
	// 	}
	// 	// any child of curr will still be lesser than parent, of which curr is leftchild
	// 	parent.setLeft(curr.getRight());
	// 	return root;
	// }
	
	// insert value into input tree named "root"
	public static void insertStringToBST(TNode<String> root, String value) {
		// TODO: find leaf node and insert value (element)
		TNode<String> curr = root;
		if(curr == null) {
			curr = new TNodeImpl<String>(value);
			return;
		}
		int cmp = value.compareTo(curr.getValue());
		if(cmp < 0) {
			insertStringToBST(curr.getLeft(), value);
			if(curr.getLeft() == null){
				curr.setLeft(new TNodeImpl<String>(value));
				return;
			}
		}
		else {
			insertStringToBST(curr.getRight(), value);
			if(curr.getRight() == null){
				curr.setRight(new TNodeImpl<String>(value));
				return;
			}
		}
	}
}
