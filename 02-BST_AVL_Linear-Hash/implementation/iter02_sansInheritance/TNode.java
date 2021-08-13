public class TNode<E extends Comparable<E>> {
  protected E key;
  protected TNode<E> left = null;
  protected TNode<E> right = null;
  protected int size = 1;
  protected int height = 0;
  protected int freq = 1;       // increment when duplicate keys are inserted
  protected int access = 0;     // increment when TNode is probed during search/find operation

  public TNode() {
    this.key = null;
    this.size = 0;
  }

  public TNode( E key) {
    this.key = key;
  }

  public TNode<E> Left() {
    return this.left;
  }

  public TNode<E> Right() {
    return this.right;
  }

  public E Key() {
    return this.key;
  }

  public void setLeft(TNode<E> left) {
    this.left = left;
  }

  public void setRight(TNode<E> right) {
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

  protected int setSize( TNode<E> curr) {
    if (curr == null) return 0;
    return 1 + setSize(curr.left) + setSize(curr.right);
  }

  public int height() {
    return this.height;
  }

  public void setHeight() {
    this.height = setHeight(this);
  }

  protected int setHeight( TNode<E> curr) {
    if (curr == null) return -1;
    return 1 + Math.max(setHeight(curr.left), setHeight(curr.right));
  }

  public int compareTo( TNode<E> that) {
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