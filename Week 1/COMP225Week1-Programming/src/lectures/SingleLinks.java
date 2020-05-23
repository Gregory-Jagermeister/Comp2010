/**
 * This is based on the linked list class provided in Data Structures and Algorithms in
 * Java by Adam Drozdek.
 * The basic infrastructure has been extended by Annabelle McIver.
 */

package lectures;

// A node in an integer singly linked list class

//************************  SLLNode.java  *******************************

class SLLNode {
    public Object info; 	// This is the data
    public SLLNode next; 	// this is the address of the next node
    public SLLNode() {		// Here's how we construct an empty list.
        next = null;
    }
    public SLLNode(Object el) {
        info = el; next = null;
    }
    public SLLNode(Object el, SLLNode ptr) {
        info = el; next = ptr;
    }
}

/***********************  SLList.java  ***************************
 *       generic singly linked list class with head only
 */

class SLList {
    protected SLLNode head = null;
    public SLList() {
    }
    
    public void setToNull() {
        head = null;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public Object first() {
        return head.info;
    }
    public SLLNode head() {
        return head;
    }
    public void printAll() {
       for (SLLNode tmp = head; tmp != null; tmp = tmp.next)
            System.out.print(tmp.info.toString());
    }
    public void add(Object el) {
        head= new SLLNode(el,head);
    }
    public Object find(Object el) {
        SLLNode tmp = head;
        for ( ; tmp != null && !el.equals(tmp.info); tmp = tmp.next);
        if (tmp == null)
             return null;
        else return tmp.info;
    }
    public Object deleteHead() { // remove the head and return its info;
        Object el = head.info;
        head = head.next;
        return el;
    }
    public void delete(Object el) {    // find and remove el;  
        if (head != null)              // if non-empty list;
             if (el.equals(head.info)) // if head needs to be removed;
                  head = head.next;
             else {
                  SLLNode pred = head, tmp = head.next;
                  for ( ; tmp != null && !(tmp.info.equals(el));
                          pred = pred.next, tmp = tmp.next);
                  if (tmp != null)     // if found
                        pred.next = tmp.next;
             }
    }
    
    public void join (SLList l2) {
    	// Precondition: none
    	// Postcondition: Links l2 to the end of the current list
    	// The performance of this depends on the length of the current list, but not l2. How do we test this?
    	SLList temp= this;
    	if (temp.isEmpty()) { this.head= l2.head;} // Is the current list empty?
    	else { // If not, find the tail of the current list and join it to the top of l2.
    		SLLNode t= this.head; 
    		for ( ; t.next!=null; t= t.next){}// Invariant: t!=null
    		t.next= l2.head; 		
    	}
    }
    
    //My Implementation - Daniel Stirba
/*     public static SLList interleave (SLList l1, SLList l2){
        
        SLList temp1 = l1;
        SLList temp2 = l2;
        SLList l3 = new SLList();
        SLLNode tempN1 = temp1.head();
        SLLNode tempN2 = temp2.head();

        while( tempN1.next != null || tempN2.next != null){
            if(tempN1 != null){
                l3.add(tempN1.info);
                tempN1 = tempN1.next;
            }
            if(tempN2 != null){
                l3.add(tempN2.info);
                tempN2 = tempN2.next;
            }

        }
        l3.llreverse();
        return l3;
        
    } */

    //tutorial solution far more efficent
    public static SLList interleave (SLList list1, SLList list2){
        SLLNode ct = null;
        SLList c = new SLList();
        SLLNode ta = list1.head();
        SLLNode tb = list2.head();

        for(; ta != null && tb != null; ta = ta.next, tb = tb.next){
            if (ct == null){
                c.head = ct = new SLLNode(ta.info);
            }
            ct.next= new SLLNode(tb.info);
            ct = ct.next;
        }

        if(ta!=null)
            ct.next=ta;
        if(tb!=null)
            ct.next=tb;
        
        return c;
    }

    public void llreverse() {
    	// Precondition: none
    	// Postcondition: Reverses the items in the current list
    	if ((head != null)) {
    		 SLList x= new SLList(); 
    		 Object y= deleteHead(); // removes head from current list, stores the info in y
    		 x.add(y);
    		 llreverse(); // recursive call, but to a smaller instance.
    		 join(x);
    	}
    }
    
    public void iterativeReverse() { // This just uses a single pass through the list.
    	// Precondition: none
    	// Postcondition: Reverses the items in the current list
    	SLList y= new SLList(); 
    	Object n;
    	for (SLLNode x= head; x != null; x= x.next) {
    		n= deleteHead();
    		y.add(n);
    		}
    	head= y.head;
    }


	public void insert(Object p, Object q){
		// Precondition: p and q are not null
		// Postcondition: Insert object p after object q,
		//					or at the end of the current list if q does not appear in the list
		SLLNode y= new SLLNode(p);// make a new node to insert;
		SLLNode x= head;
		if (x == null) {head= y;}
		else{
			for (; x.next != null && !x.info.equals(q); x= x.next){}// First find q, if it's there.
			// On termination x points to the last node, or to a node which has info equal to q
			if (x.next == null) {x.next= y;}// just add y to the end.
			else
			{
			y.next= x.next;
			x.next= y;
			}		
		}
	}
	
	public boolean equals(SLList p) {
		// Precondition: p is not null
		// Postcondition: Returns true iff the contents of the current list are the same
		// and in the same order as the input list p
		SLLNode x= head;
		SLLNode y= p.head;
		for(; y!=null && x!=null; y= y.next, x=x.next) {
			if (!x.info.equals(y.info)){ return false;	}
		}
		if (y==null && x==null) return true;
		else { return false; }
	}	
}
	


