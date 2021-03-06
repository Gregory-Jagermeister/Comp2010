package lectures;

/************************  IntBST.java  **************************
 *                 binary search tree of integers
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class IntBST {
    protected IntBSTNode root;
    int number= 0;
    protected void visit(IntBSTNode p) { // Visit just print out the key
        System.out.print(p.key + " ");
    }
    public IntBST() {
        root = null;
    }
    public IntBSTNode search(int el) {
        return search(root,el);
    }
    protected IntBSTNode search(IntBSTNode p, int el) {
        while (p != null)
            if (el == p.key)
                 return p;
            else if (el < p.key)
                 p = p.left;
            else p = p.right;
        return null;
    }
    
    public int traverseforMax(IntBSTNode p) {
        
        int max = p.key;

        if (p.left != null || p.right != null) {
            if (p.left.key >= max) {
                int maxL = traverseforMax(p.left);
                if (maxL > max)
                    max = maxL;
                return max = traverseforMax(p.left);
            } else {
                max = p.right.key;
                return max = traverseforMax(p.right);
            }
        } else {
            return max;
        }
    }


    public int addAll(IntBSTNode p){
        if (p == null) {
            return 0;
        }else{
           return p.key + addAll(p.left) + addAll(p.right);
        }
    }

    public void breadthFirst() {
        IntBSTNode p = root;
        Queue queue = new Queue();
        if (p != null) {
             queue.enqueue(p);
             while (!queue.isEmpty()) {
                 p = (IntBSTNode) queue.dequeue();
                 visit(p);
                 if (p.left != null)
                      queue.enqueue(p.left);
                 if (p.right != null)
                      queue.enqueue(p.right);
             }
        }
    }
    public String preorder() {
       return preorder(root);
    }
    protected String preorder(IntBSTNode p) {
        if (p != null) {
             return p.key + preorder(p.left) + preorder(p.right);
        }
        else return "";
    }
    public String inorder() {
       return  inorder(root);
    }
    protected String inorder(IntBSTNode p) {
        if (p != null) {
            return inorder(p.left) + p.key + inorder(p.right);
        }
        else return "";
    }
    public String postorder() {
        return postorder(root);
    }
    protected String postorder(IntBSTNode p) {
        if (p != null) {
           return  postorder(p.left) + postorder(p.right) + p.key;
        }
        else return "";
    }
    public void iterativePreorder() {
        IntBSTNode p = root;
        Stack travStack = new Stack();
        if (p != null) {
             travStack.push(p);
             while (!travStack.isEmpty()) {
                 p = (IntBSTNode) travStack.pop();
                 visit(p);
                 if (p.right != null)
                      travStack.push(p.right);
                 if (p.left  != null)        // left child pushed after right
                      travStack.push(p.left);// to be on the top of the stack;
             }
        }
    }
    public void iterativeInorder() {
        IntBSTNode p = root;
        Stack travStack = new Stack();
        while (p != null) {
            while(p != null) {               // stack the right child (if any) (1)
                 if (p.right != null)        // and the node itself when going
                	travStack.push(p.right); // to the left;
                 	travStack.push(p);
                 	p = p.left;
            }
            p = (IntBSTNode) travStack.pop();   // pop a node with no left child (2)
            while (!travStack.isEmpty() && p.right == null) { // visit it and all
                 visit(p);                   // nodes with no right child;
                 p = (IntBSTNode) travStack.pop();
            }
            visit(p);                        // visit also the first node with (3)
            if (!travStack.isEmpty())        // a right child (if any);
                 p = (IntBSTNode) travStack.pop();
            else p = null;
        }
    }
    public void iterativePostorder() {
        IntBSTNode p = root;
        Stack travStack = new Stack(), output = new Stack();
        if (p != null) {        // left-to-right postorder = right-to-left preorder
             travStack.push(p);
             while (!travStack.isEmpty()) {
                 p = (IntBSTNode) travStack.pop();
                 output.push(p);
                 if (p.left != null)
                      travStack.push(p.left);
                 if (p.right != null)
                      travStack.push(p.right);
             }
             while (!output.isEmpty()) {
                 p = (IntBSTNode) output.pop();
                 visit(p);
             }
        }
    }
   
    public void insert(int el) {
        IntBSTNode p = root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            if (p.key < el)
                 p = p.right;
            else p = p.left;
        }
        if (root == null)    // tree is empty;
             root = new IntBSTNode(el);
        else if (prev.key < el)
        { prev.right = new IntBSTNode(el); number++;}
        else { prev.left  = new IntBSTNode(el); number++;}
    }
    
    
    public void deleteByCopying(int el) {
        IntBSTNode node, p = root, prev = null;
        while (p != null && p.key != el) {       // find the node p
             prev = p;                            // with element el;
             if (p.key < el)
                  p = p.right;
             else p = p.left;
        }
        node = p;
        if (p != null && p.key == el) {
             if (node.right == null)              // node has no right child;
                  node = node.left;
             else if (node.left == null)          // no left child for node;
                  node = node.right;
             else {
                  IntBSTNode tmp = node.left;     // node has both children;
                  IntBSTNode previous = node;     // 1.
                  while (tmp.right != null) {     // 2. find the rightmost
                      previous = tmp;             //    position in the
                      tmp = tmp.right;            //    left subtree of node;
                  }
                  node.key = tmp.key;             // 3. overwrite the reference  
                  if (previous == node)           //    of the key being deleted;
                       previous.left  = tmp.left; // 4.
                  else previous.right = tmp.left; // 5.
             }
             if (p == root)
                  root = node;
             else if (prev.left == p)
                  prev.left = node;
             else prev.right = node;
           number--;
        }    
        else if (root != null)
             System.out.println("key " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }
  
    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public boolean isInTree(int el) {
        return search(root,el) != null;
    }
    
    public void balance(int data[], int first, int last) {
        if (first <= last) {
            int middle = (first + last)/2;
            System.out.print(data[middle]+" ");
            insert(data[middle]);
            balance(data,first,middle-1);
            balance(data,middle+1,last);
        }
    }
    
    public void sortedStack(IntBSTNode t, Stack s){

        if(t == null){
            return;
        }

        inStack(t, s);
        sortedStack(t.left, s);
        sortedStack(t.right, s);
    }

    public void inStack(IntBSTNode t, Stack st){
//PRE:  t is a binary search tree and st is a stack of items sorted from bottom to top so
//      that all the items of t are greater than the items of the stack
//POST: Adds the items of t to the stack
  if (t != null) {
    inStack(t.left, st); // A
      st.push(t.key); // This is correct becuause of the binary search tree structure
    inStack(t.right, st);  // All the elements of the stack, including the extra ones now have items less than in t.right
  }
}
    
   @Test
   public   void TestEmptyBST() {
    	IntBSTNode p=	search(1);
    	assertNull(p);  	
    }
    
    @Test 
    public  void TestInsert() {
    	insert(2);
    	insert(3);
    	insert(4);
    	IntBSTNode p=	search(2);
    	assertEquals(2, p.key);
    	p=	search(4);
    	assertEquals(4, p.key);
    	p=	search(5);
    	assertNull(p);
    }
    
    @Test
    public   void TestDelete() {   	
    	insert(5);
    	insert(6);
    	insert(7);
    	
    	IntBSTNode p=	search(7);
    	assertEquals(7, p.key);
    	deleteByCopying(7);
    	p=	search(7);
    	assertNull(p);   	
    }
    
    @Test
    public  void TestInorder() {
    	
    	root= null;
    	insert(2);
    	insert(3);
    	insert(4);
    	insert(1);
    	insert(6);
    	insert(5);
    	
    	String A= inorder();
        assertEquals("123456", A);   	
    	
     }
    
    @Test
    public  void TestPreorder() {
    	
    	root= null;
    	insert(2);
    	insert(3);
    	insert(4);
    	insert(1);
    	insert(6);
    	insert(5);    	
    	String A= preorder();
        assertEquals("213465", A);   	
    	
     }
    
    @Test 
    public void TestInStack() {
    	root= null;
    	insert(2);
    	insert(3);
    	insert(4);
    	insert(1);
    	insert(6);
    	insert(5); 
    	
    	Stack s= new Stack();
    	inStack(root, s);
    	assertEquals(6, s.pop());
    	assertEquals(5, s.pop());
    	assertEquals(4, s.pop());
    	assertEquals(3, s.pop());
    	assertEquals(2, s.pop());
    	assertEquals(1, s.pop());    	
    }
    
}
