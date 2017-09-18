/* 
 * ReversibleQueue.java
 * Emily Van Laarhoven
 * CS230 Exam 2, Problem 3
 * Due: 11/12/16 @11:59pm
 */

/*
 * I believe this code works perfectly.  I have tested it on Strings and Integers 
 * and I have tested it on queues of size 1,2,3,4, and 5.
 */

package javafoundations;

import javafoundations.exceptions.*;

public class ReversibleQueue<T> extends LinkedQueue<T> {
  
  /* 
   * takes no arguments and reverses the elements stored in the queue
   * using recursion with a base case of just two elements and flipping them
   * by enqueueing the dequeued first element, and successively dequeuing 
   * before the each reduced case and enqueueing after it is complete
   */
  public void reverse() {
    if (this.size()<=2) { //base case size is 0, 1, or 2
      this.enqueue(this.dequeue()); // dequeue first element if any and enqueue at end of queue
    }
    else {
      T temp = this.dequeue();  //dequeue first element
      reverse(); //recurse on queue of size n-1
      this.enqueue(temp); //enqueue stored first element at end
    }   
  }
  
  /* 
   * main method is for testing purposes only
   */
  public static void main (String [] args) {
   ReversibleQueue<String> test = new ReversibleQueue<String>();
   test.enqueue("A");
   test.enqueue("B");
   test.enqueue("C");
   test.enqueue("D");
   test.enqueue("E");
   System.out.println("test = \n"+test);
   test.reverse();
   System.out.println("reverse = \n"+test);
   
   ReversibleQueue<Integer> test2 = new ReversibleQueue<Integer>();
   test2.enqueue(1);
   test2.enqueue(2);
   test2.enqueue(3);
   test2.enqueue(4);
   test2.enqueue(5);
   System.out.println("test2 = \n"+test2);
   test2.reverse();
   System.out.println("reverse = \n"+test2);
  }
  
  
}