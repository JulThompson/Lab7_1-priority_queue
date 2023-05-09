package pq;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BCAMinPQ<E extends Comparable<E>> implements BCAQueue<E> {

  /*Stores items in heap starting at index 1*/
  private ArrayList<E> heap = new ArrayList<>();

  
  public BCAMinPQ() {
    /* dummy element in index 0, making actual elements start at index 1 */
    heap.add(null);
  }

  /**
   * Return index of the left child of element at index i.
   */
  private int leftChildOf(int i){
    if (2 * 1 > heap.size() -1) return -1;
    return 2 * i;
  }

  /**
   * Return index of the right child of element at index i.
   */
  private int rightChildOf(int i){
    if (2 * i + 1 > heap.size() - 1) return -1;
    return 2 * i + 1;
  }

  /**
   * Return index of the parent of element at index i.
   */
  private int parentOf(int i){
    return i / 2;
  }

  /**
   * Swap the elements at indexes i1 and i2.
   */
  private void swap(int i1, int i2){
    E temp = heap.get(i1);
    heap.set(i1, heap.get(i2));
    heap.set(i2, temp);
  }

  /**
   * Push up the element at index i (swapping with bigger parent)
   * until it is at the appropriate level. (parent is smaller)
   * This will fix the heap property if the element of index i
   * is the only element out of place.
   */
  private void pushUp(int i) {
    if (i == 1) {
      return;
    }
    E curr = heap.get(i);
    if (curr.compareTo(heap.get(parentOf(i))) > 0) {
      return;
    }
    swap(i, parentOf(i));
    pushUp(parentOf(i));
  }

  /** Adds a new element to the the queue. */
  public void enqueue(E o){
    heap.add(o);
    pushUp(heap.size()-1);
  }

  /* Try BCAMinPQTest now! You should pass 0.1 to 0.6*/


  /**
   * Returns the next item from the queue without popping it.
   * If no item, returns null
   */
  public E peek(){
    return heap.get(1);
  }

  /* Try BCAMinPQTest now! You should pass 1a*/

  /**
   * Push down the element at index i (swapping with its smallest child)
   * until it is at the appropriate level. (children are both bigger)
   * This will fix the heap property if the element of index i
   * is the only element out of place.
   */
  private void pushDown(int i) {
    E curr = heap.get(i);
    if (curr.compareTo(heap.get(leftChildOf(i))) < 0 || curr.compareTo(heap.get(rightChildOf(i))) < 0) {
      return;
    }
    if (heap.get(leftChildOf(i)).compareTo(heap.get(rightChildOf(i))) < 0) {
      swap(i, leftChildOf(i));
      pushDown(leftChildOf(i));
    } else {
      swap(i, rightChildOf(i));
      pushDown(rightChildOf(i));
    }
  }

  /**
   * Removes the smallest item from the queue and returns it.
   *
   * @exception NoSuchElementException
   *                if the queue is empty.
   */
  public E dequeue(){

    if (isEmpty())
      throw new NoSuchElementException("MinPQ is empty");

    swap(1, heap.size() - 1);
    E ret = heap.remove(heap.size() - 1);
    pushDown(1);
    return ret;
  }

  /* Try BCAMinPQTest now! You should pass the remaining tests 1b through 8, and HeapSort*/

  /**
   * Returns whether the queue is empty or not.
   */
  public boolean isEmpty(){
    return size() == 0;
  }

  /**
   * Returns the number of items currently in the queue.
   */
  public int size() {
    return heap.size()-1;  // The arraylist has an extra element at position 0, hence the subtraction of 1.
  }

  /**
   * Return item at index i
   */
  public E get(int i) {
    return heap.get(i);
  }
}
