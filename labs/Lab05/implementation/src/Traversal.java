/**
 * Written by K.S. Koo @ DBS
 */

public class Traversal {

	/** Preorder traversal for general trees */
	static <E> void preorder(TNode<E> rt) {
		if(rt == null) return;
		System.out.print(rt.toString() + " ");
		preorder(rt.getLeft());
		preorder(rt.getRight());
	}

	/** inorder traversal for general trees */
	static <E> void inorder(TNode<E> rt) {
		if(rt == null) return;
		inorder(rt.getLeft());
		System.out.print(rt.toString() + " ");
		inorder(rt.getRight());
	}

	/** Postorder traversal for general trees */
	static <E> void postorder(TNode<E> rt) {
		if(rt == null) return;
		postorder(rt.getLeft());
		postorder(rt.getRight());
		System.out.print(rt.toString() + " ");
	}
}
