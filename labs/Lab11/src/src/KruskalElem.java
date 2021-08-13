/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Implementation for Kruskal's algorithm */
class KruskalElem implements Comparable<KruskalElem> {
  private int v, w, weight;

  public KruskalElem(int inweight, int inv, int inw)
    { weight = inweight; v = inv; w = inw; }
  public int v1() { return v; }
  public int v2() { return w; }
  public int key() { return weight; }
  public int compareTo(KruskalElem that) {
    if (weight < that.key()) return -1;
    else if  (weight == that.key()) return 0;
    else return 1;
  }
}
