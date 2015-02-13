import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] a;         // array of items
  private int N;            // number of elements on stack

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[1];
    N = 0;
  }

  // is the queue empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the queue
  public int size() {
    return N;
  }

  private void resize(int capacity) {
    Item[] temp = (Item[]) new Object[capacity];

    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }

    a = temp;
  }

  private void shuffle() {
    Item[] temp = (Item[]) new Object[N];
    int j = 0;
    
    for (int i = 0; i < N; i++) {
      if (a[i] != null) {
        temp[j] = a[i];
        j++;
      }
    }
    
    a = temp;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();

    if (N == a.length) resize(2*a.length);    // double size of array if necessary
    a[N++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (size() == 0) throw new NoSuchElementException();

    int rand = StdRandom.uniform(N);

    Item item = a[rand];
    
    a[rand] = null;
   
    // shuffle array
    shuffle();
    
    N--;

    // shrink size of array if necessary
    if (N > 0 && N == a.length/4) resize(a.length/2);
    return item;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (size() == 0) throw new NoSuchElementException();

    int rand = StdRandom.uniform(N);
    Item item = a[rand];
    return item;
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new ReverseArrayIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ReverseArrayIterator implements Iterator<Item> {
    private int i;

    public ReverseArrayIterator() {
      i = N;
    }

    public boolean hasNext() {
      return i > 0;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      return a[--i];
    }
  }
  
  private void printQueue() {
    for (int i = 0; i < N; i++) {
      System.out.print(a[i] + " ");
    }
  }
  
  // unit testing
  public static void main(String[] args) {
    RandomizedQueue<String> s = new RandomizedQueue<String>();
    
    s.enqueue("a");
    s.enqueue("b");
    s.enqueue("c");
    s.enqueue("d");
  
    s.dequeue();
    
    Iterator itr = s.iterator();
    while (itr.hasNext()) {
      Object element = itr.next();
      System.out.print(element + " ");
    }
    
    StdOut.println();
    StdOut.println("(" + s.size() + " left on queue)");
  }
}