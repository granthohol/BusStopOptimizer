

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that only returns buses from another iterator that go to a particular destination
 * 
 * @author Grant Hohol
 *
 */
public class BusFilteredIterator implements Iterator<Bus> {
  
  /**
   * the iterator we are filtering
   */
  private Iterator<Bus> baseIterator; 
  
  /**
   * the destination BusStop we are filtering by
   */
  private BusStop destination; 
  
  /**
   * the next bus to be returned, or null if there aren't anymore
   */
  private Bus next; 
  
  private boolean first; 
  
  /**
   * Constructs a new BusFilteredIterator that filters the given iterator to return only Bus-es
   * that stop at the given destintion
   * 
   * @param iterator - the iterator we are filtering
   * 
   * @param destination - the desired destination
   */
  BusFilteredIterator(Iterator<Bus> iterator, BusStop destination){ 
    this.destination = destination; 
    this.baseIterator = iterator; 
    this.next = next(); 
    first = true; 
  }

  /**
   * returns true if there is another Bus that goes to the destination in the iterator
   * 
   * @return true if a call next() will return another Bus; false otherwise
   */
  @Override
  public boolean hasNext() {
    return next != null; 
  }

  /**
   * returns the next bus and advances the iterator until the next bus it will return
   * 
   * @return buses from the iterator baseIterator that go to the destination stop
   * 
   * @throws NoSuchElementException if called when there is no next Bus
   */
  @Override
  public Bus next() {
    advanceToNext(); 
    return next; 
  }
  
  /**
   * Private helper method that advances this iterator 
   * It will iterate over 'this.iterator' until it reaches a Bus that stops at destination
   * Then it will store that Bus in next
   */
  private void advanceToNext() { 
    if (first && next != null) { 
      first = false;
      return; 
    }
    while (this.baseIterator.hasNext()) {
      Bus curr = this.baseIterator.next(); 
      if (curr.goesTo(destination)) { 
        this.next = curr; 
        return;  // exit the loop if a matching bus is found
      }
    }
    // handle the case when no bus goes to the destination
    this.next = null;  
  }

}
