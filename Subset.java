import java.util.Iterator;

public class Subset {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    
    RandomizedQueue<String> s = new RandomizedQueue<String>();
    
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals(" ")) s.enqueue(item);
    }
    
    Iterator itr = s.iterator();
    while (itr.hasNext() && k > 0) {
      StdOut.println(s.dequeue());
      k--;
    }
  }
}