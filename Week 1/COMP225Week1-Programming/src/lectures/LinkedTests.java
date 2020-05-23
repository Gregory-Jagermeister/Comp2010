package lectures;

import org.junit.Test;

/**
 * Project:   BasicLinkedLists
 * Methods to test the basic infrastructure of the linked list class
 */

import static org.junit.Assert.*;

/*
 * In this file there's a bunch of JUnit tests to check some of the
 * properties of the methods in the linked list class. This test suite
 * is by no means a complete set of tests.
 */

public class LinkedTests {	
	/*
	 * Test methods
	 */
	@Test
	public void defaultConstructorBuildsEmptyList() { // Tests the constructor makes an empty list
		SLList vList = new SLList();  // Makes a default list 
	    assertTrue(vList.isEmpty());
	}
	
	@Test
	public void testFind() {
		SLList vList = new SLList();
		SLList x= new SLList();
		x.add(1);
		assertNull(vList.find(x));
		
		vList.add(2);
		//assertNull(vList.find(null));
		
		vList.add(3);
		assertNull(vList.find(x));
		
		vList.add(1);
		assertEquals(1,vList.find(x));
	}
	
	@Test
	public void testDeleteHead() {
		// Prepare some data for the test
	}
	
	@Test
	public void listFromSingleObjectContainsOneElement() { 
		SLList vList = new SLList();
		assertTrue(vList.isEmpty());
		vList.add(1);
		assertTrue(!vList.isEmpty());
		assertEquals("1", vList.deleteHead().toString());
		assertTrue(vList.isEmpty());
		assertTrue("1"=="1");
	}

	@Test
	public void testEquals() {  // Tests equals 
		// set up three lists
		SLList htl= new SLList();
		SLList htl2= new SLList();
		htl.add(1);
	    htl.add(2);
	    htl.add(3);
	       
	    htl2.add(1);
	    htl2.add(2);
	    htl2.add(3);
	        
	    int i= 3;
	       for(SLLNode p= htl.head; p!=null; p= p.next) {
	        	assertEquals(i, p.info);
	        	i--;
	        }	       
	    assertTrue(htl.equals(htl2));
	}
	
	@Test
	public void testJoin() { // Tests join
		// set up three lists, one empty
		// join a-b, join empty-a join a-empty
		SLList vList0= new SLList();
		SLList vList1= new SLList();
		SLList vList2= new SLList();
		vList1.add(1); vList1.add(2);

		vList0.join(vList1);
		assertTrue(vList1.equals(vList0)); // Join empty to the front, to make no difference

		vList1.join(vList2);		
		assertTrue(vList1.equals(vList0));	// Join empty to the back, to make no difference
		
		vList0= new SLList();
		vList2= new SLList();
		vList2.add(3); vList2.add(4);	
		
		vList1.join(vList2); // Join two non-empty lists
		
		vList0.add(3);  // Set up the list that the join should equal
		vList0.add(4); 
		vList0.add(1); 
		vList0.add(2);
		
		assert(vList1.equals(vList0)); // Test that the join equals vList0
	}
	
	@Test
	public void testInsert() {
		// set up three lists, one empty
		// join a- b, join empty-a join a-empty
		SLList vList0= new SLList();
		SLList vList1= new SLList();
		SLList vList2= new SLList();
		vList1.add(1); vList1.add(2);vList1.add(3);
		vList2.add(1); vList2.add(3);
		vList2.insert(2, 3);
		assertTrue(vList1.equals(vList2));
		
		vList0.add(2); vList0.add(3);
		vList0.insert(1, 4); // 1 should be added to the end.
		assertTrue(vList1.equals(vList0));
	}
	
	
	@Test
	public void getFirstReturnsFirstElementOfAList() {
		SLList vList = new SLList();
		vList.add(1);
		assertEquals("1",vList.first().toString());
	}
	
	@Test
	public void testReverse() {
		SLList vList= new SLList();
		vList.add(1); vList.add(2); // Make two lists, one the reverse of the other
		SLList rList= new SLList();
		rList.add(2); rList.add(1);
		
		vList.llreverse(); // Call reverse
		assertTrue(rList.equals(vList));	 // And check that it equals its reversed list. 
		// Note: Are there any other special cases that should be checked?
	}
	
}
