public class TNode<E extends Comparable<E>> {
  
  private E key = null;
  private TNode<E> left = null;
  private TNode<E> right = null;
  private int height = 0;
  private int balanceFactor = 0;
  private int freq = 1;       // increment when duplicate keys are inserted
  private int access = 0;     // increment when TNode is probed during search/find operation

  public TNode() { }

  public TNode(E key) {
    this.key = key;
    this.height = 1;
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
    this.setHeight();
    this.setBalanceFactor();
  }

  public void setRight(TNode<E> right) {
    this.right = right;
    this.setHeight();
    this.setBalanceFactor();
  }

  public void setKey(E key) {
    this.key = key;
  }

  public int height() {
    return this.height;
  }

  public void setHeight() {
    this.height = setHeight(this);
  }

  private int setHeight(TNode<E> node) {
    if (node == null) return 0;
    int Lheight = 0, Rheight = 0;
    if (node.Left() != null) Lheight = node.Left().height();
    if (node.Right() != null) Rheight = node.Right().height();
    return 1 + Math.max(Lheight, Rheight);
  }

  public int BalanceFactor() {
    return this.balanceFactor;
  }

  public void setBalanceFactor() {
    this.balanceFactor = setBalanceFactor(this);
  }

  private int setBalanceFactor(TNode<E> node) {
      if(node == null) return 0;
      int Lheight = 0, Rheight = 0;
      if (node.Left() != null) Lheight = node.Left().height();
      if (node.Right() != null) Rheight = node.Right().height();
      return Lheight - Rheight;
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