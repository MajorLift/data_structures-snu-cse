/**
 * Written by K.S. Koo @ DBS
 */

public class Main {
	// main point. 
	public static void main(String[] args) {
		// input
		String[] input = {"F", "B", "A", "D", "C", "E", "G", "I", "H"};
		TNode<String> tree = (TNodeImpl<String>) createStringTree(input);

		// find test
		String[] findTest = {"A", "B", "T", "Z"};
		for (String test : findTest) {
			boolean pass = (TNodeImpl.findString(tree, test) == null)? false : true;
			System.out.println("find test " + test + ": " + pass);
		}
		
		// delete test
		String[] removeTest = {"B", "I"};
		for (String test : removeTest) {
			boolean pass = (TNodeImpl.removeString(tree, test) == null)? false : true;
			System.out.println("remove test " + test + ": " + pass);
		}
		
		System.out.print("in-order after deletion: ");
		Traversal.inorder(tree);
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

