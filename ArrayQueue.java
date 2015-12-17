/** a queue class that uses a one-dimensional array */
public class ArrayQueue implements Queue { 
	private List first; 
	private List last; 
	private class List { Object item; List next; } 	

	public boolean isEmpty() { return (first == null); }
	
	public void put(Object theObject) { 
		List x = new List(); 
		x.item = theObject; 
		x.next = null; 
		if (isEmpty()) { first = x; last = x; } 
		else { last.next = x; last = x; } 
	}
	
	public Object remove() { 
		Object val = first.item; 
		first = first.next; 
		return val; 
	}
	public Object getFrontElement(){
		return first;		
	}
	
	public Object getRearElement(){
		return last;
	}
}

