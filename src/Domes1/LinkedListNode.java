package Domes1;

public class LinkedListNode {
	private Object element;
	private LinkedListNode next; 
	
	public LinkedListNode(Object it, LinkedListNode nextval) 
	{ 
		element = it; 
		next = nextval; 
	}
	
	LinkedListNode(LinkedListNode nextval) 
	{ 
		next = nextval; 
	}
	
	LinkedListNode next() 
	{ 
		return next; 
	}
	
	LinkedListNode setNext(LinkedListNode nextval) 
	{ 
		return next = nextval; 
	}
	
	Object element() { return element; }
	
	Object setElement(Object it) { return element = it; }
}
