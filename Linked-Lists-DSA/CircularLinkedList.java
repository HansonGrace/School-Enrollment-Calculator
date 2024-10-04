

public class CircularLinkedList<T>{
	Node last;
	//node class
	class Node{
		T data;
		Node next;
		Node(T val){
			data = val;
			next = null;
		}
	}
	CircularLinkedList(){
		last = null;
	}
	//inserts at beginning of list
	public void insertAtBeginning(T val){
		Node newNode = new Node(val);
		if(last==null) {
			newNode.next = newNode;
			last = newNode;
		}
		else {
			newNode.next = last.next;
			last.next = newNode;
		}
	
	}
	//insert at end of list
	public void insertAtEnd(T val){
		Node newNode = new Node(val);
		if(last==null) {
			newNode.next = newNode;
			last = newNode;
		}
		else {
			newNode.next = last.next;
			last.next = newNode;
			last=newNode;
		}
	
	}
	//display list
	public void display() {
		if(last==null)
			return;
		Node temp = last.next;
		do{
			System.out.print(temp.data + " ");
			temp = temp.next;
		}while(temp!=last.next);		
	}
	
	public T deleteAtBeginning() {
		if(last==null) 
			throw new IndexOutOfBoundsException("Deletion attempted on empty list ");
		T temp = last.next.data;
		if(last.next==last)
			last = null;
		else
			last.next = last.next.next;
		
		return temp;
	}
	
	public void deleteAtEnd() {
		if(last==null) 
			throw new IndexOutOfBoundsException("Deletion attempted on empty list ");
		if(last.next==last)
			last = null;
		else {
			Node temp = last.next;
			while(temp.next!=last) {
				temp = temp.next;
			}
			temp.next = last.next;
			last = temp;
		}
		
	}
}
