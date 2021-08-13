/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

import java.util.Stack;

class Pole {
  int poleNum;
  Stack<Integer> diskStack;
  
  Pole(int value) {
    this.poleNum = value;
    this.diskStack = new Stack<Integer>();
  }

  boolean equals(Pole that){
    return this.poleNum == that.poleNum;
  }

  void pushDisk(int newDiskSize){
    if(diskStack.size() == 0 || newDiskSize < diskStack.peek()) diskStack.push(newDiskSize);
  }

  Integer popDisk(){
    if(diskStack.size() > 0) return diskStack.pop();
    else return 0;
  }

  Integer getTop(){
    if(diskStack.size() > 0) return diskStack.peek();
    else return Integer.MAX_VALUE;
  }

  int size(){
    return diskStack.size();
  }

  // Override Object.toString
  public String toString() {
    return Integer.toString(poleNum);
  }

  public void print(){
    printf(this.toString());
  }
}
