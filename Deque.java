import java.util.Iterator;
import java.util.NoSuchElementException;
// Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports inserting and
// removing items from either the front or the back of the data structure.
// Create a generic data type Deque that implements the following API:
public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;

  private class Node {
    private Item item;
    private Node next;
    private Node prev;
  }

  // Size
  private int N;

  // construct an empty deque
  public Deque() {
     first = null;
     last = null;
     N = 0;
  }

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() { return new DequeIterator(); }

  private class DequeIterator implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext()  { return current != null;                     }
    public void remove()      { throw new UnsupportedOperationException();  }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
      }
  }

  // is the deque empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the deque
  public int size() {
    return N;
  }

  // insert the item at the front
  public void addFirst(Item item) {
    if (item == null) { throw new java.lang.NullPointerException(); }

    if (size() == 0) {
      first = new Node();
      first.item = item;
      first.next = null;
      first.prev = null;
      last = new Node();
      last = first;
    } else {
      Node oldFirst = first;
      first = new Node();
      first.next = oldFirst;
      first.prev = null;
      first.item = item;
    }

    N++;
  }

  // insert the item at the end
  public void addLast(Item item) {
    if(size() == 0) {
      last = new Node();
      last.item = item;
      last.next = null;
      last.prev = null;
      first = new Node();
      first = last;
    } else {
      Node oldLast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      last.prev = oldLast;
    }

    N++;
  }

  // delete and return the item at the front
  public Item removeFirst() {
    if (!first) { throw new java.lang.NoSuchElementException(); }

    Node oldFirst = first;
    first = new Node();
    first = oldFirst.next;
    N--;
    return oldFirst.item;
  }

  // delete and return the item at the end
  public Item removeLast() {
    if (!last) { throw new java.lang.NoSuchElementException(); }

    Node oldLast = last;
    last = new Node();
    last = oldLast.prev;

    N--;
    return oldLast.item;
  }

  // unit testing
  public static void main(String[] args) {
    Deque<String> d = new Deque<String>();
    d.addLast("a");
    StdOut.println(d.removeLast());
    d.addFirst("a");
    StdOut.println(d.removeFirst());

    //d.addFirst("b");
    StdOut.println(d.first.item + ", " + d.last.item);

    //StdOut.print(d.addFirst() + " ")
//    while (!StdIn.isEmpty()) {
//      String item = StdIn.readString();
//      if (!item.equals("-")) s.push(item);
//      else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
//     }

    StdOut.println("(" + d.size() + " left on stack)");
  }
}