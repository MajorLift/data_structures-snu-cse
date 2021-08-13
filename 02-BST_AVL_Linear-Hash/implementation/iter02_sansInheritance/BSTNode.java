public class BSTNode<E extends Comparable<E>> {
  protected E key;
  protected BSTNode<E> left = null;
  protected BSTNode<E> right = null;
  protected int size = 1;
  protected int height = 0;
  protected int balanceFactor = 0;
  protected int freq = 1;       // increment when duplicate keys are inserted
  protected int access = 0;     // increment when BSTNode is probed during search/find operation

  public BSTNode() {
    this.key = null;
    this.size = 0;
  }

  public BSTNode(E key) {
    this.key = key;
  }

  public BSTNode<E> Left() {
    return this.left;
  }

  public BSTNode<E> Right() {
    return this.right;
  }

  public E Key() {
    return this.key;
  }

  public void setLeft(BSTNode<E> left) {
    this.left = left;
  }

  public void setRight(BSTNode<E> right) {
    this.right = right;
  }

  public void setKey(E key) {
    this.key = key;
  }

  public int size() {
    return this.size;
  }

  public void setSize() {
    this.size = setSize(this);
  }

  protected int setSize(BSTNode<E> curr) {
    if (curr == null) return 0;
    return 1 + setSize(curr.left) + setSize(curr.right);
  }

  public int height() {
    return this.height;
  }

  public void setHeight() {
    this.height = setHeight(this);
  }

  protected int setHeight(BSTNode<E> curr) {
    if (curr == null) return -1;
    return 1 + Math.max(setHeight(curr.left), setHeight(curr.right));
  }

  public int BalanceFactor() {
    setBalanceFactor();
    return this.balanceFactor;
  }

  public void setBalanceFactor() {
    this.balanceFactor = setBalanceFactor(this);
  }

  protected int setBalanceFactor(BSTNode<E> curr) {
      if(curr == null || curr.left == null && curr.right == null) return 0;
      if(curr.left == null && curr.right != null) return -1 * curr.right.height();
      if(curr.left != null && curr.right == null) return +1 * curr.left.height();
      return curr.left.height() - curr.right.height();
  }

  public int compareTo(BSTNode<E> that) {
    if (this.Key().compareTo(that.Key()) < 0) return -1;
    if (this.Key().compareTo(that.Key()) > 0) return +1;
    else return 0;
  }

  public int Freq() {
    return this.freq;
  }

  public void incrementFreq() {
    this.freq++;
  }

  public void resetFreq() {
    this.freq = 1;
  }

  public int Access() {
    return this.access;
  }

  public void incrementAccess() {
    this.access++;
  }

  public void resetAccess() {
    this.access = 0;
  }
}