package lectures;
import java.io.*;

/*
 * This is a demonstrator class to illustrate how to test the performance of 
 * the join method from the linked list class.
 * 
 * Note that when preparing an experiment, make sure that you have a hypothesis
 * which captures your expectations of performance based on the design of the
 * method's implementation. For the experiment below, as discussed in lectures, 
 * we hypothesised that the performance of front.join(back) was sensitive to
 * the length of front, but not of back. We were then able to design two experiments
 * which confirmed this hypothesis.
 * 
 */

public class LLTest {
	public static void main(String[] args) {		
		// Make a list of integers
		
		SLList front=new SLList();
		SLList back= new SLList();
		
	// Experimentally testing your programs	
	// I want to test the performance of join.	
				
		StopWatch s = new StopWatch(); // Make a stopwatch...
		int size= 10;
		for (size= 0; size< 10; size++) { // You can vary the length of at most 1 of the parameters.
			// First make a back of the right size
			back= new SLList();
			for (int i= 0; i< size*500000; i++){
				back.add("b");
			}
			
		// Now test	for that size of back
		
			front= new SLList();	// Make a front - it is the constant as size varies
			for (int i= 0; i< 100000; i++)
				front.add("a");
		
			s.start();		
			back.join(front);
			s.stop();
		// Make sure that you only have the stopwatch running when the
		// method is executing. Do not (for example) include the time it takes
		// to create the data set.
		
		System.out.println("elapsed time in milliseconds: " + "for this run  "+ size + " is " + s.getElapsedTime());
		}// End experiment for this size
	}
}
