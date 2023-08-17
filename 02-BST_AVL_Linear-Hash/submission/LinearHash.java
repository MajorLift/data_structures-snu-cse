package submission;
import java.util.*;

public class LinearHash {
  private ArrayList<LinkedList<String>> table;
  private int capacity; 
  private int numRound;
  private int numWords = 0;
  private int splitindex = 0;

  // TODO: constructor for the Hash table
  public LinearHash(int HTinitSize)	{
    this.capacity = HTinitSize * 2;
    this.numRound = (int)(Math.log10((double)HTinitSize) / Math.log10((double)2));
    
    this.table = new ArrayList<>(this.capacity);
    for (int i = 0; i < this.capacity; i++) {
      table.add(new LinkedList<String>());
    }
  }

  private int hashFunc(String key, int k) {
    return (int)MyUtil.ELFhash(key, (int)Math.pow(2, k));
  }

  // TODO: insert `word' to the Hash table.
  public int insertUnique(String word) {
    if (word == null) throw new IllegalArgumentException("null input.");
    int hash = hashFunc(word, numRound);
    int extHash = hashFunc(word, numRound + 1);
    LinkedList<String> hashEntry = table.get(hash);
    LinkedList<String> extHashEntry = table.get(extHash);
    this.numWords++;
    if (hash >= this.splitindex) {
      if (hashEntry.isEmpty()){
        hashEntry.add(word);
        return hash;
      }
      else if (hashEntry.contains(word)) {
        this.numWords--;
        return -1;
      } 
      else {
        hashEntry.add(word);
        collisionSplit();
        if (!hashEntry.contains(word)) return extHash;
        else return hash;
      }
    }
    else {
      if (extHashEntry.isEmpty()) {
        extHashEntry.add(word);
        return extHash;
      }
      else if (extHashEntry.contains(word)) {
        this.numWords--;
        return -1;
      }
      else {
        extHashEntry.add(word);
        collisionSplit();
        return extHash;
      }
    }
  }

  private void collisionSplit() {
    int currIdx = this.splitindex;
    if (++this.splitindex == (int)Math.pow(2, this.numRound)) newRound();

    LinkedList<String> splitEntry = table.get(currIdx);
    ArrayList<String> moveList = new ArrayList<>();
    if (!splitEntry.isEmpty()) {
      for (String s : splitEntry) {
        int extHash = hashFunc(s, numRound + 1);
        if (extHash != currIdx) {
          moveList.add(s);
          if (!table.get(extHash).contains(s)) table.get(extHash).add(s);
        }
      }
      splitEntry.removeAll(moveList);
    }
  }

  private void newRound() {
    this.numRound++;
    this.splitindex = 0;
    for (int i = this.capacity; i < 2 * this.capacity; i++) {
      table.add(new LinkedList<String>());
    }
    this.capacity *= 2;
  }

  // TODO: look up `word' in the Hash table.
  public int lookup(String word) {
    if (word == null) throw new IllegalArgumentException("null input.");
    int hash = hashFunc(word, numRound);
    int extHash = hashFunc(word, numRound + 1);
    if (hash >= this.splitindex) {
      int result = table.get(hash).size();
      if (table.get(hash).contains(word)) return result;
      else return -1 * result;
    }
    else {
      int result = table.get(extHash).size();
      if (table.get(extHash).contains(word)) return result;
      else return -1 * result;
    }
  }

  // TODO:
  public int wordCount() { 
    return this.numWords;
  }

  // TODO: 
  public int emptyCount() {
    int count = 0;
    for (int i = 0; i < this.size(); i++) {
      if (table.get(i).isEmpty()) count++;
    }
    return count; 
  }

  // TODO: 2^k + collisions in the current round
  public int size() {
    return (int)Math.pow(2, this.numRound) + this.splitindex;
  }

  // TODO: Print keys in the hash table
  public void print() {
    for(int i = 0; i < this.size(); i++){
      LinkedList<String> sortedEntry = new LinkedList<>(table.get(i));
      StringBuilder sb = new StringBuilder();
      sb.append("[" + i + ":");
      if (!table.get(i).isEmpty()) {
        sortedEntry.sort(new StringCompare());
        for (String s : sortedEntry) sb.append(" " + s);
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  private static class StringCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
    }
  }
}