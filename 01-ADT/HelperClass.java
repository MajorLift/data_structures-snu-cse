import java.util.*;

public class HelperClass{
    public static ArrayList<Integer> padLeadingZeros(ArrayList<Integer> a, int newLength){
        if(a.size() == newLength) return a;
        ArrayList<Integer> result = new ArrayList<Integer>(a);
        int numZeros = newLength - result.size();
        result.addAll(0, Collections.nCopies(numZeros, 0));
        return result;
    }
    // helper function: multiply by 10^n <=> add trailing zeros
    public static ArrayList<Integer> padTrailingZeros(ArrayList<Integer> a, int shift){
        if(shift == 0) return a;
        ArrayList<Integer> result = new ArrayList<Integer>(a);
        result.addAll(Collections.nCopies(shift, 0));
        return result;
    }
    public static ArrayList<Integer> removeLeadingZeros(ArrayList<Integer> a){
        int i = -1;
        while(++i < a.size() && a.get(i) == 0);
        if(i < a.size()) return new ArrayList<Integer>(a.subList(i, a.size()));
        else return new ArrayList<Integer>(Arrays.asList(0));
    }
    public static String removeLeadingZeros(String s){
        if(s == "" || s == null) return "";
        String curr = new String(s);

        int sgn = (curr.charAt(0) == '-') ? -1 : 1;
        if(sgn < 0) curr = curr.substring(1);

        int i = -1;
        while(++i < curr.length() && curr.charAt(i) == '0');
        if(i >= curr.length()) return "0";
        else{
            if(sgn < 0) return "-" + curr.substring(i);
            else return curr.substring(i);
        }
    }
    public static ArrayList<Integer> removeNonDigits(ArrayList<Integer> a){
        if(a == null || a.size() == 0) return null;
        ArrayList<Integer> curr = new ArrayList<>(a);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        for(Integer e : curr){
            if(digits.contains(e)) result.add(e);
        }
        return result;
    }
    public static String removeNonDigits(String s){
        if(s == "" || s == null) return "";
        String curr = new String(s);
        String result = new String();

        int sgn = (curr.charAt(0) == '-') ? -1 : 1;
        if(sgn < 0) curr = curr.substring(1);
        
        for(int i = 0; i < curr.length(); i++){
            char ch = curr.charAt(i);
            String str = Character.toString(ch);
            if(str.matches("[0-9]")) result += str;
        }
        if(sgn < 0) return "-" + result;
        else return result;
    }
    public static ArrayList<Integer> makeWellFormed(ArrayList<Integer> a){
        if(a == null || a.size() == 0) return a;
        ArrayList<Integer> result = new ArrayList<>(a);
        result = removeNonDigits(result);
        result = removeLeadingZeros(result);
        return result;
    }
    public static String makeWellFormed(String s){
        if(s == "" || s == null) return "";
        String result = new String(s);
        result = removeNonDigits(result);
        result = removeLeadingZeros(result);
        return result;
    }
}