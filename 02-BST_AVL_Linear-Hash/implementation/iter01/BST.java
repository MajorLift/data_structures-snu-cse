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


// 조교님 안녕하세요, 저는 이 수업을 수강하고 있는 서종선이라고 합니다. 한 학기동안 여러모로 신세가 많았습니다. 조교님 너무 잘생겼어요. 사랑합니다. 종강하는날 서울대입구역 3번출구앞 7시에 기다리고 있겠습니다. 아! 혹시 좋아하는 색깔잇으신가요? 호호호 (제가 꽃을 사가려고 묻는 말은 아닙ㄴ디ㅏ.)
// 그럼 이만 총총

}



