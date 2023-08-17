package submission;
import java.util.NoSuchElementException;

public class BST {
  protected TNode<String> root = null;
  protected int sumFreq = 0;
  protected int sumAccess = 0; 

  // TODO: constructor for empty BST
  public BST() { }
  
  // TODO: 
  public void insert(String key) {
    if (key == null) throw new IllegalArgumentException("null key.");
    this.root = insert(this.root, key);
  }

  // @returns altered BST(node) where BSTNode key is inserted in place.
  protected TNode<String> insert(TNode<String> node, String key) {
    if (node == null || node.Key() == null) return new TNode<>(key);
    int cmp = key.compareTo(node.Key());
    if (cmp < 0) node.setLeft(insert(node.Left(), key));
    else if (cmp > 0) node.setRight(insert(node.Right(), key));
    else node.incrementFreq();
    return node;
  }

  // TODO: 
  public boolean find(String key) {
    if (key == null) throw new IllegalArgumentException("null key."); 
    return find(this.root, key) instanceof TNode<?>;
  }

  // increment access count for all nodes probed while performing search operation
  protected TNode<String> find(TNode<String> node, String key) {
    if (node == null || node.Key() == null) return null;
    node.incrementAccess();
    int cmp = key.compareTo(node.Key());
    if (cmp < 0) return find(node.Left(), key);
    if (cmp > 0) return find(node.Right(), key);
    return node;
  }

  // TODO:
  public int size() { 
    return size(this.root);
  }

  protected int size(TNode<String> node) {
    if (node == null) return 0;
    return 1 + size(node.Left()) + size(node.Right());
  }

  public boolean isEmpty() {
    return this.size() == 0;
  }

  // TODO: 
  public int sumFreq() {
    if (isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumFreq = sumFreq(this.root);
    return this.sumFreq;
  }

  protected int sumFreq(TNode<String> node) {
    if (node == null) return 0;
    return node.Freq() + sumFreq(node.Left()) + sumFreq(node.Right());
  }

  // TODO: 
  public int sumProbes() {
    if (isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumAccess = sumProbes(this.root);
    return this.sumAccess;
  }

  protected int sumProbes(TNode<String> node) {
    if (node == null) return 0;
    return node.Access() + sumProbes(node.Left()) + sumProbes(node.Right());
  }

  // TODO: 
  public void resetCounters() {
    resetFreqs(this.root);
    resetAccesses(this.root);
    this.sumFreq = 0;
    this.sumAccess = 0;
  }

  protected void resetFreqs(TNode<String> node) {
    if (node == null) return;
    node.resetFreq();
    resetFreqs(node.Left());
    resetFreqs(node.Right());
  }

  protected void resetAccesses(TNode<String> node) {
    if (node == null) return;
    node.resetAccess();
    resetAccesses(node.Left());
    resetAccesses(node.Right());
  }

  // TODO: 
  public void print() {
    inorderTraversePrint(this.root);
  }

  protected void inorderTraversePrint(TNode<String> node) {
    if (node == null) return;
    inorderTraversePrint(node.Left());
    print(node);
    inorderTraversePrint(node.Right());
  }

  protected void print(TNode<String> node) {
    System.out.println("[" + node.Key() + ":" + node.Freq() + ":" + node.Access() + "]");
  }  
}