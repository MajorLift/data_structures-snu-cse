
interface TNode<E extends Comparable<E>> {

    public TNode<E> Left();
    public TNode<E> Right();
    public E Key();
    public void setLeft(TNode<E> left);
    public void setRight(TNode<E> right);

    public int compareTo(TNode<E> that);
    // public boolean isLeaf();

    public int height();
    private int setHeight(TNode<E> curr){
        if(curr == null) return -1;
        return 1 + Math.max(setHeight(curr.Left()), setHeight(curr.Right()));
    }
  
    public int size();
    private int setSize(TNode<E> curr){
        if(curr == null) return 0;
        return 1 + setSize(curr.Left()) + setSize(curr.Right());
    }

    public int Freq();
    public void incrementFreq();
    public void resetFreq();

    public int Access();
    public void incrementAccess();
    public void resetAccess();


}