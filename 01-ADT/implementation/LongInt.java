// LongInt ADT for unbounded integers
import java.util.*;

public class LongInt {
  private final ArrayList<Integer> magnitude; // array representation of magnitude of LongInt;
  private final int sign; // sign of LongInt;

  // constructor
  // assumes no null input, input type mismatch, or leading zeros.
  public LongInt(String s) {
    String cons = HelperClass.makeWellFormed(s);
    // determine sign
    if(cons.charAt(0) == '-'){
      this.sign = -1;
      cons = cons.substring(1);
    }
    else if(cons == "0") this.sign = 0;
    else this.sign = 1;

    // init and populate array of magnitude
    this.magnitude = new ArrayList<Integer>();
    for(int i = 0; i < cons.length(); i++){
      this.magnitude.add(Character.getNumericValue(cons.charAt(i)));
    }
  }
  public LongInt(int sgn, AbsIntegerAsArray mag){
    ArrayList<Integer> magChecked = HelperClass.makeWellFormed(mag.getMagnitude());
    this.magnitude = magChecked;
    if(magChecked.size() == 1 && magChecked.get(0) == 0) this.sign = 0;
    else this.sign = sgn;
  }
  public int getSign(){
    return sign;
  }
  public ArrayList<Integer> getMagnitude(){
    return new ArrayList<Integer>(magnitude); // defensive copying
  }

  // returns 'this' + 'opnd'; Both inputs remain intact.
  public LongInt add(LongInt opnd) {
    AbsIntegerAsArray a = new AbsIntegerAsArray(this.getMagnitude());
    int asgn = this.getSign();
    AbsIntegerAsArray b = new AbsIntegerAsArray(opnd.getMagnitude());
    int bsgn = opnd.getSign();
    
    if(asgn == 0 && bsgn == 0) return new LongInt("0");
    if(asgn == 0) return new LongInt(bsgn, b);
    if(bsgn == 0) return new LongInt(asgn, a);
    AbsIntegerAsArray resultMag;
    int resultSgn;
    if(asgn * bsgn > 0){
      resultMag = a.add(b);
      resultSgn = asgn;
    }
    else{
      resultMag = a.subtractAbs(b);
      if(asgn * a.compareTo(b) > 0) resultSgn = +1;
      else if(asgn * a.compareTo(b) < 0) resultSgn = -1;
      else resultSgn = 0;
    }
    return new LongInt(resultSgn, resultMag);
  }
  // returns 'this' - 'opnd'; Both inputs remain intact.
  public LongInt subtract(LongInt opnd) {
    AbsIntegerAsArray thatMag = new AbsIntegerAsArray(opnd.getMagnitude());
    int thatSgn = -1 * opnd.getSign();
    LongInt that = new LongInt(thatSgn, thatMag);
    return this.add(that);
  }
  // returns 'this' * 'opnd'; Both inputs remain intact.
  public LongInt multiply(LongInt opnd){
      AbsIntegerAsArray thisMag = new AbsIntegerAsArray(this.getMagnitude());
      AbsIntegerAsArray opndMag = new AbsIntegerAsArray(opnd.getMagnitude());
      AbsIntegerAsArray resultMag = thisMag.multiply(opndMag);
      int resultSgn = this.getSign() * opnd.getSign();
      return new LongInt(resultSgn, resultMag);
  }
  public String toString(){
    String s = new String();
    if(sign < 0) s += '-';
    for(Integer e : magnitude) s += e;
    return HelperClass.makeWellFormed(s);
  }
  // print the value of 'this' element to the standard output.
  public void print() {
    System.out.print(this.toString());
  }
}

