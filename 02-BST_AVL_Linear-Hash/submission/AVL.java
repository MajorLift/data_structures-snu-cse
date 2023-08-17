package submission;
public class AVL extends BST {
    
    public AVL() { }

    // TODO:
    public void insert(String key) {
        if (key == null) throw new IllegalArgumentException("null key.");
        this.root = this.balance(this.insert(this.root, key));
    }
    
    private TNode<String> balance(TNode<String> node) {
        if (node.BalanceFactor() >= 2) {
            if (node.Left().BalanceFactor() == 1) node = rotateRight(node);
            else if (node.Left().BalanceFactor() == -1) node = rotateLeftRight(node);
            else node.setLeft(balance(node.Left()));
        }
        if (node.BalanceFactor() <= -2) {
            if (node.Right().BalanceFactor() == -1) node = rotateLeft(node);
            else if (node.Right().BalanceFactor() == 1) node = rotateRightLeft(node);
            else node.setRight(balance(node.Right()));
        }
        return node;
    }
    
    private TNode<String> rotateLeft(TNode<String> node) {
        TNode<String> x = node.Right();
        TNode<String> T = x.Left();
        node.setRight(T);
        x.setLeft(node);
        return x;
    }
    
    private TNode<String> rotateRight(TNode<String> node) {
        TNode<String> x = node.Left();
        TNode<String> T = x.Right();
        node.setLeft(T);
        x.setRight(node);
        return x;
    }
    
    private TNode<String> rotateLeftRight(TNode<String> node) {
        TNode<String> x = rotateLeft(node.Left());
        node.setLeft(x);
        return rotateRight(node);
    }
    
    private TNode<String> rotateRightLeft(TNode<String> node) {
        TNode<String> x = rotateRight(node.Right());
        node.setRight(x);
        return rotateLeft(node);
    }
}