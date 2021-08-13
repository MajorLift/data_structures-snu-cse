
public interface BinaryTree<E extends Comparable<E>> {
    public int sumFreq = 1;
    public int sumAccess = 0;

    public BST Left();
    public BST Right();

    public boolean isEmpty();
    public int size();

    public void insert(E key);
    public boolean find(E key);
    
    public int sumFreq();
    private int sumFreq(TNode<E> curr){
        if(curr == null) return 0;
        return curr.Freq() + sumFreq(curr.Left()) + sumFreq(curr.Right());
    }
    public int sumProbes();
    private int sumProbes(TNode<E> curr){
        if(curr == null) return 0;
        return curr.Access() + sumProbes(curr.Left()) + sumProbes(curr.Right());
    }
    public void resetCounters();
    private void resetFreqs(TNode<E> curr){
        if(curr == null) return;
        curr.resetFreq();
        this.sumFreq += curr.Freq();
        resetFreqs(curr.Left());
        resetFreqs(curr.Right());
    }
    private void resetAccesses(TNode<E> curr){
        if(curr == null) return;
        curr.resetAccess();
        this.sumAccess += curr.Access();
        resetAccesses(curr.Left());
        resetAccesses(curr.Right());
    }

    public void print();
    private void inorderTraversePrint(TNode<E> curr){
        if(curr == null) return;
        inorderTraversePrint(curr.Left());
        print(curr);
        inorderTraversePrint(curr.Right());
    }
    private void print(TNode<E> curr){
        System.out.println("[" + curr.Key() + ":" + curr.Freq() + ":" + curr.Access() + "]");
    }

}