import java.util.NoSuchElementException;

public class AVL extends BST{
  private AVLNode<String> root;
  private int height = 0;

  public AVL(){
    this.root = null;
    this.size = 0;
  }
  public AVL(String key){
    this.root = new AVLNode<String>(key);
  }
  public AVL(AVLNode<String> node){
    this.sumFreq = sumFreq(node);
    this.sumAccess = sumProbes(node);
    node = new AVLNode<>(node.Key(), null, node.Left(), node.Right());
    this.root = node;
    this.size = node.size();
    this.height = node.height();
  }

  public AVL Left() { return new AVL(root.Left()); }
  public AVL Right() { return new AVL(root.Right()); }
  public int height() { return this.height; }

  // increment access count for all nodes probed while performing search operation
  public boolean find(String key) {
    if(key == null || key == "" ) throw new IllegalArgumentException("null key.");
    return find(this.root, key) != null;
  }
  protected AVLNode<String> find(AVLNode<String> curr, String key){
    if(curr == null) return null;
    curr.incrementAccess(1);
    int cmp = key.compareTo(curr.Key());
    if(cmp < 0) return find(curr.Left(), key);
    else if(cmp > 0) return find(curr.Right(), key);
    else return curr;
  }
  public void insert(String key) {
    if(key == null) throw new IllegalArgumentException("null key.");
    this.root = insert(this.root, key);
    this.size++;
  }
  private AVLNode<String> insert(AVLNode<String> curr, String key){
    if(curr == null) return new AVLNode<String>(key);
    int cmp = key.compareTo(curr.Key());
    if(cmp == 0){
      curr.incrementFreq(1);
      return curr;
    }
    else if(cmp < 0){
      curr.setLeft(insert(curr.Left(), key));
      if(curr.BalanceFactor() == +2){
          if(curr.compareTo(curr.Left()) < 0){
            curr = rotateRight(curr);
          }
          else curr = rotateLeftRight(curr);
      }
    }
    else{  // cmp < 0
      curr.setRight(insert(curr.Right(), key));
      if(curr.BalanceFactor() == -2){
        if(curr.compareTo(curr.Right()) >= 0){
          curr = rotateLeft(curr);
        }
        else curr = rotateRightLeft(curr);
      }
    }
    curr.setHeight();
    return curr;
  }
  private AVLNode<String> rotateLeft(AVLNode<String> curr){
    if(curr.BalanceFactor() > 0) throw new IllegalArgumentException("Attempted left rotate on left heavy tree.");
    // if(curr.BalanceFactor() < -1 || curr.BalanceFactor() > +1) throw new IllegalArgumentException("Balance factor out of range.");
    AVLNode<String> x = curr.Right();
    AVLNode<String> y = curr;
    AVLNode<String> T = x.Left();
    x.setLeft(y);
    y.setRight(T);
    return x;
  }
  private AVLNode<String> rotateRight(AVLNode<String> curr){
    if(curr.BalanceFactor() < 0) throw new IllegalArgumentException("Attempted right rotate on right heavy tree.");
    // if(curr.BalanceFactor() > +1 || curr.BalanceFactor() < -1) throw new IllegalArgumentException("Balance factor out of range.");
    AVLNode<String> x = curr.Left();
    AVLNode<String> y = curr;
    AVLNode<String> T = x.Right();
    x.setRight(y);
    y.setLeft(T);
    return x;
  }
  private AVLNode<String> rotateLeftRight(AVLNode<String> curr){
    if(curr.BalanceFactor() != +2) throw new IllegalArgumentException("Insufficient balance factor for double rotation.");
    AVLNode<String> x = rotateLeft(curr.Left());
    return rotateRight(x);
  }
  private AVLNode<String> rotateRightLeft(AVLNode<String> curr){
    if(curr.BalanceFactor() != -2) throw new IllegalArgumentException("Insufficient balance factor for double rotation.");
    AVLNode<String> x = rotateRight(curr.Right());
    return rotateLeft(x);
  }

  public int sumFreq() { 
    if(this.isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumFreq = sumFreq(root);
    return this.sumFreq;
  }
  protected int sumFreq(AVLNode<String> curr){
    if(curr == null) return 0;
    return curr.Freq() + sumFreq(curr.Left()) + sumFreq(curr.Right());
  }
  public int sumProbes() {
    if(this.isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumAccess = sumProbes(root);
    return this.sumAccess;
  }
  protected int sumProbes(AVLNode<String> curr){
    if(curr == null) return 0;
    return curr.Access() + sumProbes(curr.Left()) + sumProbes(curr.Right());
  }
  public void resetCounters() {
    this.sumFreq = 0;
    this.sumAccess = 0;
    resetFreqs(root);
    resetAccesses(root);
  }
  protected void resetFreqs(AVLNode<String> curr){
    if(curr == null) return;
    curr.resetFreq();
    this.sumFreq += curr.Freq();
    resetFreqs(curr.Left());
    resetFreqs(curr.Right());
  }
  protected void resetAccesses(AVLNode<String> curr){
    if(curr == null) return;
    curr.resetAccess();
    this.sumAccess += curr.Access();
    resetAccesses(curr.Left());
    resetAccesses(curr.Right());
  }

  public void print() {
    inorderTraversePrint(this.root);
  }
  protected void inorderTraversePrint(AVLNode<String> curr){
    if(curr == null) return;
    inorderTraversePrint(curr.Left());
    print(curr);
    inorderTraversePrint(curr.Right());
  }
  protected void print(AVLNode<String> curr){
    System.out.println("[" + curr.Key() + ":" + curr.Freq() + ":" + curr.Access() + "]");
  }
}

