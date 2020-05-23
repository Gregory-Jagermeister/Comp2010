package lectures;

import java.util.*; 

 class Widgets {
	 
	static boolean testJig(char a, char b) {
		// This simulates the action of the testJig
		// We have slowed the result down so we can get a
		// better measurement of the time.
		try {
			Thread.sleep(2);  // To slow things down so we can measure the time
		    } catch(InterruptedException e){e.printStackTrace();}
		 Random randomGenerator = new Random();
		 if (a!=b) return false;
		 if (a== b & a=='g') return true;
		 else { 
			 int randomint= randomGenerator.nextInt(10); // Returns a random result for two bads
			 if (randomint<5) return true;
			 else return false;
		 }
		
	 }
	 
	static int findGoodTransistorSlow(char A[]) {
		// This is O(A.length^2)
		 int j;
		 for(int i=0; i< A.length; i++) {
			int n=0;
			for(j=0; j< A.length; j++) {
				if(testJig(A[i], A[j])){ 
				n++;
				} 
			}
			if (n > A.length/2) return i;
		 }
		 return A.length;
	 }
	 
	static int findGoodTransistorFast(char A[]){
		 // This is O(A.length)
		 // Invariant: C Union A[n..A.length()] contains more than  
		 // half of its elements "g"
		 Stack<Integer> C= new Stack<Integer>();
		 int n= 0;
		 while(n< A.length) {
			 if (C.isEmpty()) { // Add c to the stack C
				 C.push(n); 
			 }
			 else {
				if (!(testJig(A[n], A[C.peek()]))) C.pop();
				else C.push(n); // Either test same, so add or remove.
			 }
			 n++; // n keeps track of which items in S are yet to be tested. 
		 }
		 return C.peek();		 
	 }
}
 
 class testWidgets {
		public static void main(String[] args) {
			// We set up an experiment where we can test the performance
			// with the aim of comparing the time spent 
			int x;
			 int n= 1001;
			 StopWatch s = new StopWatch();	
			 for (int k=10; k<n; k=k*10) {
			 char transistors[]= new char[k];
			 for(int i=0; i< k; i++) {
				 if (i< k/2-1) transistors[i]= 'b';
				 else transistors[i]= 'g';
			 }
			 System.out.println("k is " + k);
			 s.start();
			 x= Widgets.findGoodTransistorFast(transistors);
			 s.stop();
			 System.out.println("findGoodTransistorFast finds " + x);		 
			 System.out.println("findGoodTransistorFast takes " + s.getElapsedTime());
		 
			 s.start();
			 x= Widgets.findGoodTransistorSlow(transistors);
			 s.stop();
			 System.out.println("findGoodTransistorSlow finds " + x);
			 System.out.println("findGoodTransistorSlow takes " + s.getElapsedTime());		 
		}
	}
 }
