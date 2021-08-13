
/** 
  Written by K.S. Koo @ DBS
  */

public class Main {
    public static void main(String[] input) {
        int size = Integer.parseInt(input[1]);
        if (input[0].contentEquals("stack")) {
            System.out.println("=== Stack Test ===");
            Stack<Integer> S = new AStack<Integer>(size);
            S.push(43); S.push(65); S.push(32); S.push(75); S.push(49);
            for (int i = 0; i < 4; i++) {
            	Integer poped = S.pop();
            	if (poped != null) System.out.println("Pop " + poped);
            }
        } else if (input[0].contentEquals("queue")) {
            System.out.println("=== Queue Test ===");
            LQueue<Integer> Q = new LQueue<Integer>(size);
            Q.enqueue(43); Q.enqueue(65); Q.enqueue(32); Q.enqueue(75); Q.enqueue(49);
            for (int i = 0; i < 4; i++) {
            	Integer dequeued = Q.dequeue();
            	if (dequeued != null) System.out.println("Dequeue " + dequeued);
            }
        }
    }
}

