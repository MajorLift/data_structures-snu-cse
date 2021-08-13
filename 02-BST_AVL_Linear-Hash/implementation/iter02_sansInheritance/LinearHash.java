import java.util.*;

public class LinearHash {
  ArrayList<LinkedList<String>> table;
  int size = getSize();
  int capacity = getCapacity(); 
  int numRound = 1;
  int numWords = 0;
  int currCollisions = 0;
  int splitindex = 0;

  // constructor for the Hash table
  public LinearHash(int HTinitSize)	{
    this.table = new ArrayList<>(HTinitSize);
    for(int i = 0; i < HTinitSize; i++){
      table.add(new LinkedList<String>());
    }
  }

  // insert `word' to the Hash table.
  public int insertUnique(String word) {
    int hash = hashFunc(word, numRound);
    int extHash = hashFunc(word, numRound + 1);
    LinkedList<String> hashEntry = table.get(hash);
    LinkedList<String> extHashEntry = table.get(extHash);
    this.numWords++;
    if(hash >= this.splitindex){
      if(hashEntry.isEmpty()){
        hashEntry.add(word);
        return hash;
      }
      else if(hashEntry.contains(word)){
        this.numWords--;
        return -1;
      } 
      else if(!hashEntry.isEmpty()){
        hashEntry.add(word);
        collisionSplit();
        return extHash;
      }
      else{ hashEntry.add(word); return hash; } 
    }
    else{
      if(extHashEntry.isEmpty()){
        extHashEntry.add(word);
        return extHash;
      }
      else if(extHashEntry.contains(word)){
        this.numWords--;
        return -1;
      }
      else if(!extHashEntry.isEmpty()){
        extHashEntry.add(word);
        collisionSplit();
        return extHash;
      }
      else{ extHashEntry.add(word); return extHash; }
    }
  }
  private void collisionSplit(){
    LinkedList<String> currEntry = table.get(this.splitindex++);
    ArrayList<String> moveList = new ArrayList<>();
    for(String s : currEntry){
      int hash = hashFunc(s, numRound);
      int extHash = hashFunc(s, numRound + 1);
      if(hash != extHash){
        table.get(extHash).add(s);
        moveList.add(s);
      }
    }
    currEntry.removeAll(moveList);
    this.currCollisions++;
    if(this.currCollisions == (int)Math.pow(2, numRound)) newRound();
  }

  // look up `word' in the Hash table.
  public int lookup(String word) {
    int hash = hashFunc(word, numRound);
    int extHash = hashFunc(word, numRound+1);
    if(hash >= splitindex()){
      int result = table.get(hash).size();
      if(table.get(hash).contains(word)) return result;
      else return -1 * result;
    }
    else{
      int result = table.get(extHash).size();
      if(table.get(extHash).contains(word)) return result;
      else return -1 * result;
    }
  }
  
  private void newRound(){
      this.numRound++;
      this.splitindex = 0;
      this.currCollisions = 0;
      this.size = getSize();
      this.capacity = getCapacity();
      this.table = new ArrayList<>(this.capacity);
  }

  public int hashFunc(String key, int k){
    return (int)MyUtil.ELFhash(key, (int)Math.pow(2, k));
  }
  // public int hashFunc(String key, int k){
  //   return (int)hashStr(key, k+1) % k;
  // }

  public int wordCount() { return this.numWords; }
  public int emptyCount() { return this.size - this.numWords; }
  public int splitindex() { return this.splitindex; }
  public int size() { this.size = getSize(); return this.size; }
  // 2^k + collisions in the current round
  private int getSize(){
    return (int)Math.pow(2, this.numRound) + this.currCollisions;
  }
  private int getCapacity(){
    return (int)Math.pow(2, this.numRound + 1);
  }
  // Print keys in the hash table
  public void print(){
    for(int i = 0; i < table.size(); i++){
      if(!table.get(i).isEmpty()){
        System.out.println(i + ": " + table.get(i).toArray().toString());
      }
    }
  }   		
}

