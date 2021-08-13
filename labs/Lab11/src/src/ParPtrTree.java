/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** General Tree class implementation for UNION/FIND */
class ParPtrTree {
  private Integer [] array;      // Node array

  public ParPtrTree(int size) {
    array = new Integer[size];   // Create node array
    for (int i=0; i<size; i++)
      array[i] = null;
  }

  /** Determine if nodes are in different trees */
  public boolean differ(int a, int b) {
    Integer root1 = FIND(a);     // Find root of node a
    Integer root2 = FIND(b);     // Find root of node b
    return root1 != root2;       // Compare roots
  }

  /** Merge two subtrees */
  public void UNION(int a, int b) {
    Integer root1 = FIND(a);     // Find root of node a
    Integer root2 = FIND(b);     // Find root of node b
    if (root1 != root2) array[root2] = root1; // Merge
  }
  
  public Integer FIND(Integer curr) {
	  if (array[curr] == null) return curr; // At root
	  array[curr] = FIND(array[curr]);
	  return array[curr];
  }

}
