public class AVLNode<E extends Comparable<E>> extends BSTNode<E> {
    protected AVLNode<E> parent = null;
    protected AVLNode<E> left = null;
    protected AVLNode<E> right = null;

    public AVLNode() {
        this.key = null;
        this.size = 0;
    }

    public AVLNode(E key) {
        this.key = key;
    }

    // public AVLNode(E key, AVLNode<E> parent, AVLNode<E> left, AVLNode<E> right, int freq, int access) {
    //     this.key = key;
    //     this.parent = parent;
    //     this.left = left;
    //     this.right = right;
    //     this.size = setSize(this);
    //     this.height = setHeight(this);
    //     this.balanceFactor = setBalanceFactor(this);
    //     this.freq = freq;
    //     this.access = access;
    // }

    public AVLNode<E> Parent() {
        return this.parent;
    }

    public AVLNode<E> Left() {
        return this.left;
    }

    public AVLNode<E> Right() {
        return this.right;
    }

    public void setParent(AVLNode<E> parent) {
        this.parent = parent;
    }

    public int compareTo(AVLNode<E> that) {
        if (this.Key().compareTo(that.Key()) < 0) return -1;
        if (this.Key().compareTo(that.Key()) > 0) return +1;
        else return 0;
    }

    public void setSize() {
        this.size = setSize(this);
    }
    protected int setSize(AVLNode<E> curr) {
        if (curr == null) return 0;
        return 1 + setSize(curr.left) + setSize(curr.right);
    }

    public void setHeight() {
        this.height = setHeight(this);
    }
    protected int setHeight(AVLNode<E> curr) {
        if (curr == null) return -1;
        return 1 + Math.max(setHeight(curr.left), setHeight(curr.right));
    }

    public void setBalanceFactor() {
        this.balanceFactor = setBalanceFactor(this);
    }
    
    protected int setBalanceFactor(AVLNode<E> curr) {
        if(curr == null || curr.left == null && curr.right == null) return 0;
        if(curr.left == null && curr.right != null) return -1 * curr.right.height();
        if(curr.left != null && curr.right == null) return +1 * curr.left.height();
        return curr.left.height() - curr.right.height();
    }
}