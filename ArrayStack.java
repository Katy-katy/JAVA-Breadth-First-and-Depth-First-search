/** a stack class that uses a one-dimensional array */
public class ArrayStack implements Stack { 
    // Implementation of the Stack interface 
    // using an array. 
    // default capacity of the stack 
    public static final int CAPACITY = 1000; 
    // maximum capacity of the stack. 
    private int capacity; 
    // S holds the elements of the stack 
    private Object s[]; 
    // the top element of the stack. 
    private int top = -1; 
 
    public ArrayStack(int cap) { 
	    // Initialize the stack with given capacity 
	    capacity = cap; 
	    s = new Object[capacity]; 
	    } 
    
	public ArrayStack() { 
	// Initialize the stack with default capacity 
		this(CAPACITY); 
	    } 
	
	public boolean empty() { 
	// Return true iff the stack is empty 
		return (top < 0); 
	 	}
	
	public void push(Object theObject) throws StackFullException {
	 
	    // Push a new object on the stack 
		if (top == capacity){	
			System.out.println("I'm here");
			throw new StackFullException("Stack overflow."); 
			
		}	
		s[++top] = theObject; // ++top or top++ 
	} 
	
	public Object peek() throws StackEmptyException {	 
	    // Return the top stack element 
		if (empty()) 
			throw new StackEmptyException("Stack is empty."); 
		return s[top]; 
	}
	 
	public Object pop() throws StackEmptyException { 
		// Pop off the stack element 	 
		Object elem; 
		if (empty()) 
			throw new StackEmptyException("Stack is Empty"); 
		elem = s[top]; 
		// Dereference S[top] and decrement top 
		s[top--] = null; 
		return elem; 
	} 
}


