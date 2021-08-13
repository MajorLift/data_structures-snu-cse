import java.util.NoSuchElementException;

public class AVL extends BST {
  protected AVLNode<String> root;
  
  public AVL() {
    this.root = null;
    this.size = 0;
  }

  protected AVLNode<String> find(AVLNode<String> curr, String key) {
    if (curr == null) return null;
    curr.incrementAccess();
    int cmp = key.compareTo(curr.Key());
    if (cmp < 0) return find(curr.Left(), key);
    else if (cmp > 0) return find(curr.Right(), key);
    else return curr;
  }

  public void insert(String key) {
    if (key == null) throw new IllegalArgumentException("null key.");
    this.root = balance(insert(root, key));
    this.size++;
  }

  protected AVLNode<String> insert(AVLNode<String> curr, String key) {
    AVLNode<String> newNode = new AVLNode<>(key);
    if (curr == null) return newNode;
    int cmp = newNode.compareTo(curr);
    if (cmp < 0) curr.setLeft(insert(curr.Left(), key));
    else if (cmp > 0) curr.setRight(insert(curr.Right(), key));
    else {
      curr.incrementFreq();
      this.size--;
    }
    return curr;
  }

  protected AVLNode<String> balance(AVLNode<String> node) {
    if (Math.abs(node.BalanceFactor()) <= 1) return node;
    if (Math.abs(node.BalanceFactor()) > 2) return balance(node);

    AVLNode<String> curr = node;
    if (curr.BalanceFactor() >= +2) {
      while (curr.BalanceFactor() >= +2) curr = curr.Left();
      curr = curr.Parent();
      // Left-Left Case
      if (curr.Left().BalanceFactor() == +1) curr = rotateRight(curr);
      // Left-Right Case
      if (curr.Left().BalanceFactor() == -1) curr = rotateLeftRight(curr);      
    }
    if (curr.BalanceFactor() <= -2) {
      while (curr.BalanceFactor() <= -2) curr = curr.Right();
      curr = curr.Parent();
      // Right-Right Case
      if (curr.Right().BalanceFactor() == -1) curr = rotateLeft(curr);
      // Right-Left Case
      if (curr.Right().BalanceFactor() == +1) curr = rotateRightLeft(curr);
    }
    return node;
  }

  protected AVLNode<String> rotateLeft(AVLNode<String> curr) {
    if (curr.BalanceFactor() > 0) throw new IllegalArgumentException("Attempted left rotate on left heavy tree.");
    AVLNode<String> x = curr.Right();
    AVLNode<String> y = curr;
    AVLNode<String> T = x.Left();
    x.setLeft(y);
    x.setParent(y.Parent());
    y.setParent(x);
    y.setRight(T);
    return x;
  }

  protected AVLNode<String> rotateRight(AVLNode<String> curr) {
    if (curr.BalanceFactor() < 0)
      throw new IllegalArgumentException("Attempted right rotate on right heavy tree.");
    AVLNode<String> x = curr.Left();
    AVLNode<String> y = curr;
    AVLNode<String> T = x.Right();
    x.setRight(y);
    x.setParent(y.Parent());
    y.setParent(x);
    y.setLeft(T);
    return x;
  }

  protected AVLNode<String> rotateLeftRight(AVLNode<String> curr) {
    if (curr.BalanceFactor() != +2)
      throw new IllegalArgumentException("Incorrect balance factor for double rotation.");
    AVLNode<String> x = rotateLeft(curr.Left());
    return rotateRight(x);
  }

  protected AVLNode<String> rotateRightLeft(AVLNode<String> curr) {
    if (curr.BalanceFactor() != -2)
      throw new IllegalArgumentException("Incorrect balance factor for double rotation.");
    AVLNode<String> x = rotateRight(curr.Right());
    return rotateLeft(x);
  }

  
  public int height() {
    setHeight();
    return this.height;
  }

  public void setHeight() {
    this.height = root.setHeight(root);
  }

  public int balanceFactor() {
    setBalanceFactor();
    return this.balanceFactor;
  }

  public void setBalanceFactor() {
    this.balanceFactor = root.setBalanceFactor(root);
  }

  public int sumFreq() {
    if (isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumFreq = sumFreq(root);
    return this.sumFreq;
  }

  protected int sumFreq(AVLNode<String> curr) {
    if (curr == null) return 0;
    return curr.Freq() + sumFreq(curr.Left()) + sumFreq(curr.Right());
  }

  public int sumProbes() {
    if (isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumAccess = sumProbes(root);
    return this.sumAccess;
  }

  protected int sumProbes(AVLNode<String> curr) {
    if (curr == null) return 0;
    return curr.Access() + sumProbes(curr.Left()) + sumProbes(curr.Right());
  }

  public void resetCounters() {
    resetFreqs(root);
    resetAccesses(root);
    this.sumFreq = this.size;
    this.sumAccess = 0;
  }

  protected void resetFreqs(AVLNode<String> curr) {
    if (curr == null) return;
    curr.resetFreq();
    resetFreqs(curr.left);
    resetFreqs(curr.right);
  }

  protected void resetAccesses(AVLNode<String> curr) {
    if (curr == null) return;
    curr.resetAccess();
    resetAccesses(curr.left);
    resetAccesses(curr.right);
  }

  public void print() {
    inorderTraversePrint(this.root);
  }

  protected void inorderTraversePrint(AVLNode<String> curr) {
    if (curr == null) return;
    inorderTraversePrint(curr.Left());
    print(curr);
    inorderTraversePrint(curr.Right());
  }

  protected void print(AVLNode<String> curr) {
    System.out.println("[" + curr.Key() + ":" + curr.Freq() + ":" + curr.Access() + "]");
  }  
}

