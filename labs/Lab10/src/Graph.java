/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
    Modified by Kyoseung Koo
*/

/** Graph ADT */
public interface Graph { // Graph class ADT

	/** @return The number of vertices */
	public int n();

	public void setEdge(int i, int j, int weight);

	/** Determine if an edge is in the graph */
	public boolean isEdge(int v, int w);
	
	public int getEdge(int v, int w);

	/**
	 * Set the mark value for a vertex
	 * 
	 * @param v   The vertex
	 * @param val The value to set
	 */
	public void setMark(int v, int val);

	/**
	 * Get the mark value for a vertex
	 * 
	 * @param v The vertex
	 * @return The value of the mark
	 */
	public int getMark(int v);
}
