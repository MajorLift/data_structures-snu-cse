public class AVLNode<E extends Comparable<E>> extends BSTNode<E>{
    private AVLNode<E> parent = null;
    private AVLNode<E> left = null;
    private AVLNode<E> right = null;
    private int balanceFactor = 0;

    public AVLNode(){}
    public AVLNode(E key){ this.key = key; }
    public AVLNode(E key, AVLNode<E> parent, AVLNode<E> left, AVLNode<E> right){
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
        // this.height = setHeight(this);
        this.balanceFactor = setBalanceFactor(this);
    }
    public AVLNode<E> Left(){ return this.left; }
    public AVLNode<E> Right(){ return this.right; }
    public AVLNode<E> Parent(){ return this.parent; }
    public int BalanceFactor(){ return this.balanceFactor; }

    public void setLeft(AVLNode<E> left){
        this.left = left;
        if(this.parent == left) this.parent = left.parent;
        if(left == null) return;
        this.left = new AVLNode<E>(left.Key(), this, left.Left(), left.Right());
    }
    public void setRight(AVLNode<E> right){
        this.right = right;
        if(this.parent == right) this.parent = right.parent;
        if(right == null) return;
        this.right = new AVLNode<E>(right.Key(), this, right.Left(), right.Right());
    }
    public void setParent(AVLNode<E> parent){ this.parent = parent; }

    public void setBalanceFactor(){ this.balanceFactor = setBalanceFactor(this); }
    private int setBalanceFactor(AVLNode<E> curr){
        if(curr == null || curr.left == null && curr.right == null) return 0;
        if(curr.left == null && curr.right != null) return -1 * curr.right.height();
        if(curr.left != null && curr.right == null) return +1 * curr.left.height();
        return curr.Left().height() - curr.Right().height();
    }
}