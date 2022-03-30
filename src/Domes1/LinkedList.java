package Domes1;

public class LinkedList {
	private LinkedListNode Head = null;
	private LinkedListNode Tail = null;
	
	public void insertElement(int x,int y) {		
		Coordinate coord = new Coordinate();
		coord.x = x;
		coord.y = y;
		LinkedListNode tmp = new LinkedListNode(coord,null); 
		if(Head == null) {
			Head = tmp;
			Tail = tmp;
		}
		else {
			Tail.setNext(tmp);
			Tail = tmp;			
		}
	}
	
	public int searchElement(int x,int y) {
		int compareCounter = 0;
		LinkedListNode tmp = Head;
		if(tmp == null) {return -1;}
		while(tmp.next() != null) {
			compareCounter++;
			if(((Coordinate)tmp.element()).y == y && ((Coordinate)tmp.element()).x == x) 
			{
				return compareCounter;
			}
			tmp = tmp.next();
		}
		if(((Coordinate)tmp.element()).y == y && ((Coordinate)tmp.element()).x == x) 
		{
			return compareCounter;
		}
		else 
		{
			return -1;
		}
	}
}
