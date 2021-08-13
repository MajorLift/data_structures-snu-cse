/**
 * Written by K.S. Koo @ DBS
 */

public class Main {
	// main point. 
	public static void main(String[] args) {
		TNode<String> tree = (TNodeImpl<String>) createStringTree(args);
		
		System.out.print("pre-order: ");
		Traversal.preorder(tree);
		System.out.println();
		
		System.out.print("in-order: ");
		Traversal.inorder(tree);
		System.out.println();
		
		System.out.print("post-order: ");
		Traversal.postorder(tree);
		System.out.println();

	}
	
	// create tree with input args. args' type is String
	public static TNode<String> createStringTree(String[] input) {
		if (input.length == 0) return null;
		
		// TODO:
		TNode<String> tree = new TNodeImpl<String>(input[0]);
		for (int i = 1; i < input.length; i++) {
			TNodeImpl.insertStringToBST(tree, input[i]);
		}
		
		return tree;
	}
}

