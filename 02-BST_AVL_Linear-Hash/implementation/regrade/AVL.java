public class AVL extends BST {
    
    protected TNode<String> insert(TNode<String> node, String key) {
        if (node == null || node.Key() == null) return new TNode<>(key);
        if (key.compareTo(node.Key()) < 0) {
            node.setLeft(insert(node.Left(), key));
            if (node.BalanceFactor() == +2) {
                if (key.compareTo(node.Left().Key()) < 0) node = rotateRight(node);
                else node = rotateLeftRight(node);
            }
        }
        else if (key.compareTo(node.Key()) > 0) {
            node.setRight(insert(node.Right(), key));
            if (node.BalanceFactor() == -2) {
                if (key.compareTo(node.Right().Key()) >= 0) node = rotateLeft(node);
                else node = rotateRightLeft(node);
            }
        }
        else node.incrementFreq();
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