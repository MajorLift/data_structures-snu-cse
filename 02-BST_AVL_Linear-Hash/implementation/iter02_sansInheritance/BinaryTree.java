import java.util.NoSuchElementException;
// import java.util.*;

public abstract class BinaryTree<E extends Comparable<E>> {
    protected TNode<E> root;
    protected int size = 0;
    protected int height = 0;
    protected int sumFreq = 0;
    protected int sumAccess = 0;

    public boolean isEmpty(){ return this.size == 0; }
    public int size(){ return this.size; }
  
    // increment access count for all nodes probed while performing search operation
    public boolean find(E key) {
      if (key == null) throw new IllegalArgumentException("null key.");
      return find(this.root, key) != null;
    }

    protected TNode<E> find(TNode<E> curr, E key) {
      if (curr == null) return null;
      curr.incrementAccess();
      int cmp = key.compareTo(curr.Key());
      if (cmp < 0) return find(curr.Left(), key);
      else if (cmp > 0) return find(curr.Right(), key);
      else return curr;
    }

    public void insert(E key) {
      if (key == null) throw new IllegalArgumentException("null key.");
      this.root = insert(root, key);
      this.size++;
    }

    // @returns altered BST(curr) where BSTNode key is inserted.
    protected TNode<E> insert(TNode<E> curr, E key) {
      TNode<E> newNode = new TNode<>(key);
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

    public int height() {
      setHeight();
      return this.height;
    }

    public void setHeight() {
      this.height = root.setHeight(root);
    }

    public int sumFreq() {
      if (isEmpty()) throw new NoSuchElementException("Empty BST.");
      this.sumFreq = sumFreq(root);
      return this.sumFreq;
    }

    protected int sumFreq(TNode<E> curr) {
      if (curr == null) return 0;
      return curr.Freq() + sumFreq(curr.Left()) + sumFreq(curr.Right());
    }

    public int sumProbes() {
      if (isEmpty()) throw new NoSuchElementException("Empty BST.");
      this.sumAccess = sumProbes(root);
      return this.sumAccess;
    }

    protected int sumProbes(TNode<E> curr) {
      if (curr == null) return 0;
      return curr.Access() + sumProbes(curr.Left()) + sumProbes(curr.Right());
    }

    public void resetCounters() {
      resetFreqs(root);
      resetAccesses(root);
      this.sumFreq = this.size;
      this.sumAccess = 0;
    }

    protected void resetFreqs(TNode<E> curr) {
      if (curr == null) return;
      curr.resetFreq();
      resetFreqs(curr.left);
      resetFreqs(curr.right);
    }

    protected void resetAccesses(TNode<E> curr) {
      if (curr == null) return;
      curr.resetAccess();
      resetAccesses(curr.left);
      resetAccesses(curr.right);
    }

    public void print() {
      inorderTraversePrint(this.root);
    }

    protected void inorderTraversePrint(TNode<E> curr) {
      if (curr == null) return;
      inorderTraversePrint(curr.Left());
      print(curr);
      inorderTraversePrint(curr.Right());
    }

    protected void print(TNode<E> curr) {
      System.out.println("[" + curr.Key() + ":" + curr.Freq() + ":" + curr.Access() + "]");
    }  
}