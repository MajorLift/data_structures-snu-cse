import java.lang.String;
import java.util.NoSuchElementException;

// Binary Search Tree implementation (duplicate keys disallowed)
public class BST{
  protected BSTNode<String> root;
  protected int size = 1;
  protected int sumFreq = size;
  protected int sumAccess = 0;

  public BST(){
    this.root = null;
    this.size = 0;
  }
  public BST(String key){
    this.root = new BSTNode<String>(key);
  }
  public BST(BSTNode<String> node){
    this.root = node;
    this.size = node.size();
    this.sumFreq = sumFreq(node);
    this.sumAccess = sumProbes(node);
  }

  public BST Left() { return new BST(this.root.Left()); }
  public BST Right() { return new BST(this.root.Right()); }
  public boolean isEmpty(){ return this.size == 0; }
  public int size(){ return this.size; }

  // increment access count for all nodes probed while performing search operation
  public boolean find(String key) {
    if(key == null || key == "" ) throw new IllegalArgumentException("null key.");
    return find(this.root, key) != null;
  }
  protected BSTNode<String> find(BSTNode<String> curr, String key){
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
  // @returns altered BST(curr) where BSTNode key is inserted.
  private BSTNode<String> insert(BSTNode<String> curr, String key){
    BSTNode<String> newNode = new BSTNode<>(key);
    if(curr == null) return newNode;
    int cmp = newNode.compareTo(curr);
    if(cmp < 0) curr.setLeft(insert(curr.Left(), key));
    else if(cmp > 0) curr.setRight(insert(curr.Right(), key));
    else curr.incrementFreq(1);
    return curr;
  }

  public int sumFreq() { 
    if(isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumFreq = sumFreq(root);
    return this.sumFreq;
  }
  protected int sumFreq(BSTNode<String> curr){
    if(curr == null) return 0;
    return curr.Freq() + sumFreq(curr.Left()) + sumFreq(curr.Right());
  }
  public int sumProbes() {
    if(isEmpty()) throw new NoSuchElementException("Empty BST.");
    this.sumAccess = sumProbes(root);
    return this.sumAccess;
  }
  protected int sumProbes(BSTNode<String> curr){
    if(curr == null) return 0;
    return curr.Access() + sumProbes(curr.Left()) + sumProbes(curr.Right());
  }
  public void resetCounters() {
    this.sumFreq = 0;
    this.sumAccess = 0;
    resetFreqs(this.root);
    resetAccesses(this.root);
  }
  protected void resetFreqs(BSTNode<String> curr){
    if(curr == null) return;
    curr.resetFreq();
    this.sumFreq += curr.Freq();
    resetFreqs(curr.Left());
    resetFreqs(curr.Right());
  }
  protected void resetAccesses(BSTNode<String> curr){
    if(curr == null) return;
    curr.resetAccess();
    this.sumAccess += curr.Access();
    resetAccesses(curr.Left());
    resetAccesses(curr.Right());
  }

  public void print() {
    inorderTraversePrint(this.root);
  }
  protected void inorderTraversePrint(BSTNode<String> curr){
    if(curr == null) return;
    inorderTraversePrint(curr.Left());
    print(curr);
    inorderTraversePrint(curr.Right());
  }
  protected void print(BSTNode<String> curr){
    System.out.println("[" + curr.Key() + ":" + curr.Freq() + ":" + curr.Access() + "]");
  }


// ????????? ???????????????, ?????? ??? ????????? ???????????? ?????? ?????????????????? ?????????. ??? ???????????? ???????????? ????????? ???????????????. ????????? ?????? ???????????????. ???????????????. ??????????????? ?????????????????? 3???????????? 7?????? ???????????? ???????????????. ???! ?????? ???????????? ?????????????????????? ????????? (?????? ?????? ???????????? ?????? ?????? ???????????????.)
// ?????? ?????? ??????

}



