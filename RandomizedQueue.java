public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] a;         // array of items
  private int N;            // number of elements on stack
  
  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[2];
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

  // add the item
  public void enqueue(Item item) {
    if (N == a.length) resize(2*a.length);    // double size of array if necessary
    a[N++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    int rand = StdRandom.uniform(N);
    
    Item item = a[rand];
    a[rand] = null;                              // to avoid loitering
    N--;
    
    // shrink size of array if necessary
    if (N > 0 && N == a.length/4) resize(a.length/2);
    return item; 
  }

  // return (but do not remove) a random item
  public Item sample() {

  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {

  }

  // unit testing
  public static void main(String[] args) {

  }
}