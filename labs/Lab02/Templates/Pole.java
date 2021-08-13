/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

class Pole {
  int poleNum;
  
  Pole(int value) {
    poleNum = value;
  }

  // Override Object.toString
  public String toString() {
    return Integer.toString(poleNum);
  }
}
