package pqheap.student;


public class Heap {
	private static int maxHeap = 20;
	private static int[] items;
	private static int size;
	
	public Heap() {
		items = new int[maxHeap];
		size = 0;
	}
	
	public float pqMed(Integer[] a){

		if(a.length % 2 == 0){
			int midOne = a[a.length/2];
			int midTwo = a[(a.length/2) + 1];

			return (midOne + midTwo)/2;
		}else{
			return a[a.length/2];
		}
	}

	public float pqMedV2(Integer[] a){
		
		Heap heap = new Heap();
		// add array to the heap
		for (int i = 0; i < a.length; i++) {
			heap.heapInsert(a[i]);
		}

		if(a.length % 2 == 0){
			float midOne = heap.heapTop();
			heap.heapDelete();
			float midTwo = heap.heapTop();

			return (midOne + midTwo)/2;
		}else{
			return heap.heapTop();
		}
	}

	private static void swap (int i, int j) {
		// swaps elements items[i] and items[j]
		// note that Java can't do a regular swap()
		int temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
	
	public int heapTop () {
		return items[0]; 
	}
	
	public void heapInsert (int newItem) {
		if (size < maxHeap) {
			// place the new item at the end of the heap
		    items[size] = newItem;
		    
		    // trickle new item up to its proper position
		    int place = size;
		    int parent = (place - 1)/2;
		    while ( (parent >= 0) &&  (items[place] > items[parent] ))
		    {  // swap items[place] and items[parent]
		          swap(place, parent);	         
		          // move up to parent
		          place = parent;
			      parent = (place - 1)/2;
		    } // end while
		    
		    ++size;
		}		
	} // end heapInsert
	
	public void heapDelete()
	// Method: Swaps the last item in the heap with the root
	// and trickles it down to its proper position.
	{
		if (size > 0 )
		{	  
			items[0] = items[--size];
			heapRebuild(0);
		} // end if
	} // end heapDelete
	
	public void heapPrint() {
		System.out.print("heap is:");
		for (int i = 0; i < size; i++) {
			System.out.print(" " + items[i]);
		}
		System.out.println("");
	}
	
	private static void heapRebuild(int root)
	{
	   // if the root is not a leaf and the root's search key
	   // is less than the larger of the search keys in the
	   // root's children
	   int child = 2 * root + 1; // index of root's left
	                             // child, if any

	   if ( child < size )
	   {  // root is not a leaf, so it has a left child at child
	      int rightChild = child + 1; // index of right child,
	                                  // if any

	      // if root has a right child, find larger child
	      if ( (rightChild < size) &&
	           (items[rightChild] > items[child]) )
	         child = rightChild; // index of larger child

	      // if the root's value is smaller than the
	      // value in the larger child, swap values
	      if ( items[root] < items[child] )
	      {   
	         swap(root, child);
	         // transform the new subtree into a heap
	         heapRebuild(child);
	      } // end if
	   } // end if

	   // if root is a leaf, do nothing
	}


	
	public static void main (String[] args) {
		Heap h = new Heap();
		h.heapInsert(10);
		h.heapInsert(12);
		h.heapInsert(5);
		h.heapInsert(14);
		h.heapInsert(15);
		h.heapInsert(9);
	
		h.heapPrint();
		System.out.println(h.heapTop());
		h.heapDelete();
		h.heapPrint();
		System.out.println(h.heapTop());
		h.heapDelete();
		h.heapPrint();
		System.out.println(h.heapTop());
		h.heapDelete();
		h.heapPrint();
		System.out.println(h.heapTop());
		h.heapDelete();
		

	}
}

