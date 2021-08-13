import java.util.*;

public class AbsIntegerAsArray implements Comparable<AbsIntegerAsArray>{
    private final ArrayList<Integer> magnitude;

    public AbsIntegerAsArray(String s){
        String cons = HelperClass.makeWellFormed(s);
        if(cons.charAt(0) == '-') cons = cons.substring(1);
        this.magnitude = new ArrayList<Integer>();
        for(int i = 0; i < cons.length(); i++){
            this.magnitude.add(Character.getNumericValue(cons.charAt(i)));
        }
    }
    public AbsIntegerAsArray(ArrayList<Integer> mag){
        this.magnitude = mag;
    }
    public ArrayList<Integer> getMagnitude(){
        return new ArrayList<Integer>(magnitude); // defensive copying
    }
    public int getSize(){
        return magnitude.size();
    }
    // @param array representations of two non-negative integers
    // @returns +1 if a > b, 0 if a = b, -1 if a < b
    public int compareTo(AbsIntegerAsArray that){
        ArrayList<Integer> a = this.getMagnitude();
        ArrayList<Integer> b = that.getMagnitude();
        if(a.size() > b.size()) return +1;
        else if(a.size() < b.size()) return -1;
        else{
            int i = -1;
            while(++i < a.size() && a.get(i) == b.get(i));
            if(i < a.size()){
                if(a.get(i) > b.get(i)) return +1;
                else return -1;
            }
            else return 0;
        }
    }
    // public Comparator<AbsIntegerAsArray> largeness() {
    //     return new Largeness();
    // }
    // private class Largeness implements Comparator<AbsIntegerAsArray> {
    //     public int compare(AbsIntegerAsArray a, AbsIntegerAsArray b) {
    //         if (a.compareTo(b) < 0) return -1;
    //         if (a.compareTo(b) > 0) return +1;
    //         else return 0;
    //     }
    // }
    private ArrayList<Integer> getLargeArray(AbsIntegerAsArray a, AbsIntegerAsArray b){
        if(a.compareTo(b) >= 0) return a.getMagnitude();
        else return b.getMagnitude();
    }
    private ArrayList<Integer> getSmallArray(AbsIntegerAsArray a, AbsIntegerAsArray b){
        if(a.compareTo(b) >= 0) return b.getMagnitude();
        else return a.getMagnitude();
    }
    // @param array representation of two non-negative integers
    // @returns array representation of a + b
    public AbsIntegerAsArray add(AbsIntegerAsArray that){
        ArrayList<Integer> large = getLargeArray(this, that);
        ArrayList<Integer> small = getSmallArray(this, that);
        ArrayList<Integer> result = new ArrayList<Integer>(Collections.nCopies(large.size() + 1, 0));
        large = HelperClass.padLeadingZeros(large, result.size());
        small = HelperClass.padLeadingZeros(small, result.size());
        
        int carry = 0;
        for(int i = result.size() - 1; i >= 0; i--){
            int prod = carry + large.get(i) + small.get(i);
            carry = prod / 10;
            result.set(i, prod % 10);
        }
        result = HelperClass.removeLeadingZeros(result);
        return new AbsIntegerAsArray(result);
    }
    // @param array representation of two nonnegative integers
    // @returns array representation of |a - b|
    public AbsIntegerAsArray subtractAbs(AbsIntegerAsArray that){
        if(this.compareTo(that) == 0) return new AbsIntegerAsArray("0");
        ArrayList<Integer> large = getLargeArray(this, that);
        ArrayList<Integer> small = getSmallArray(this, that);
        ArrayList<Integer> result = new ArrayList<Integer>(Collections.nCopies(large.size(), 0));
        small = HelperClass.padLeadingZeros(small, result.size());

        int carry = 0;
        for(int i = result.size() - 1; i >= 0; i--){
            int prod = -carry + (large.get(i) - small.get(i));
            carry = prod < 0 ? 1 : 0;
            result.set(i, (10 + prod) % 10);
        }
        result = HelperClass.removeLeadingZeros(result);
        return new AbsIntegerAsArray(result);
    }
    // @returns array representation of this * opnd
    public AbsIntegerAsArray multiply(AbsIntegerAsArray opnd){
        ArrayList<Integer> large = getLargeArray(this, opnd);
        ArrayList<Integer> small = getSmallArray(this, opnd);
        AbsIntegerAsArray result = new AbsIntegerAsArray("0");
        
        for(int i = 0; i < small.size(); i++){
            ArrayList<Integer> mult = multiplyByDigit(large, small.get(i));
            mult = HelperClass.padTrailingZeros(mult, small.size() - (i + 1));
            AbsIntegerAsArray curr = new AbsIntegerAsArray(mult);
            result = result.add(curr);
        }
        return result;
    }
    // helper function: multiply by single non-negative digit
    private static ArrayList<Integer> multiplyByDigit(ArrayList<Integer> a, int b){
        if(b == 0) return new ArrayList<Integer>(Arrays.asList(0));
        ArrayList<Integer> result = new ArrayList<Integer>(Collections.nCopies(a.size() + 1, 0));
        a = HelperClass.padLeadingZeros(a, result.size());
        int carry = 0;
        for(int i = result.size() - 1; i >= 0; i--){
            int prod = carry + a.get(i) * b;
            carry = prod / 10;
            result.set(i, prod % 10);
        }
        return HelperClass.removeLeadingZeros(result);
    }
    public String toString(){
        String s = new String();
        for(Integer e : magnitude) s += e;
        return HelperClass.makeWellFormed(s);
    }
    // print the value of 'this' element to the standard output.
    public void print() {
        System.out.print(this.toString());
    }
}