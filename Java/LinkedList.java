package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<E> implements ListI<E> {     //ASSIGNMENT 1

	private boolean isNegative;					//ASSIGNMENT 1
	private int size;
	public Node<E> head;							//ASSIGNMENT 1

	/**
	 * This is a constructor which, w/ no args, initializes
	 * properties to a default value 
	 */ 
	public LinkedList() {

		isNegative = false;
		size = 0;
		head = null;
	}
	/**
	 * This constructor takes an object 
	 * as a parameter and sets it as the 
	 * data element
	 * @param obj
	 */
	public LinkedList(E obj){
		isNegative = false;
		size = 1;
		head.data = obj;
	}


	/**
	 * This method takes an obj param
	 * If the head is null, the obj is set as first element in the list
	 * otherwise, obj is set to head and old head is pushed to next
	 * size var is incremented 
	 * Complexity: O(n)
	 */
	public void addFirst(E obj){
		
		Node<E> newnode = new Node<E>(obj);
		size++;
		if( this.head == null){             // if list is not empty
		    this.head = newnode;
		    
		}
		else{
			Node<E> secondnode = this.head; //old head  to become second node
			this.head = newnode;   // newnode is set to head
			newnode.next=secondnode; // node after newnode is
			
		}
			
			
	
	}

	/**
	 *  Takes obj parameter 
	 *  If list is empty, obj is simpy inserted
	 *  size variabe is incremented
	 *  Complexity: O(n)
	 */
	public void addLast(E obj)
	{
		Node<E> currenthead = this.head;    		//save current head in listcurrent
		Node<E> listtemp = new Node<E>(obj);    	//node to be inserted is listtemp

		if( this.head == null ) { 			// if list is empty
			this.head = listtemp;	//just put it in
			size++;
		}
		else {

			while ( currenthead.getNext() != null ) {		//if list has contents
				currenthead = currenthead.getNext(); // traverse through list until there is no next 
			}

			currenthead.setNext(listtemp); //set last Node to inserted node
			size++;
		}
	}
	/**
	 * This method sets the second item as the head
	 * and returns the removed object
	 * size variable is decremented 
	 * Complexity: O(1)
	 */
	public E removeFirst() {
		if(this.head == null){
			return null;
		}
		else{ 
		     E tmp = head.data; 
		      head = head.next;
		      size--;
		      return tmp;
		}  
		   }
	
	public boolean remove(E obj) {
		Node<E> previous = null, current = head;
		
		while(current != null && ((Comparable<E>) obj).compareTo(current.data) != 0) {
			previous = current;
			current = current.next;
		}
		
		if( current == null)  // 
			return false;
		
		if( current == head)
			head = head.next;
		
	
		else if( current.next == null) {
			previous.next = null;
			
		}
		else 
			previous.next = current.next;
		
		
		return true;
	}

	
		

	/**
	 * This method returns null if empty,
	 * otherwise, traverses to the end of the last
	 * and returns the removed data
	 * size is decremented
	 * Complexity: O(n)
	 */
	public E removeLast() {
	   //data from last node stored here to be returned
		E deleted = null;
		Node<E> temp = head;
		Node<E> prev = new Node<E>();
		if(head==null)
		{
			return null;
		}
		else {			
			while(temp.getNext()!= null) {
				
				prev = temp;
				temp = temp.getNext();
			}
			deleted = temp.data;
			
			prev.setNext(null);
			size--;
		}
		return deleted;
			}

	/**
	 * This method returns an Object of type E that is
	 * the first value in the list; does NOT remove
	 * If empty, return null
	 * Complexity: O(1)
	 */
	public E peekFirst() {
		if( head == null) 
		{
			return null;
		}
		else {
			E data = head.data;
			return data;	
		}
	}
	/**
	 * This method returns the last value in the list by 
	 * traversing to the end of the list and returning/removing the last object
	 * size is decremented
	 * Complexity: O(n)
	 */
	public E peekLast() {    //returns str value of last digit in List

		Node<E> holder = null;
		if( head == null) 
		{
			return null;
		}
		else {
			Node<E> temp = this.head;
			while( temp.getNext() != null ) { //while there is a next node
				temp=temp.getNext();		 // go to the next node
			}
			holder = temp;  				//now that you're at the end
		}								// store the last node in holder
		E data = (E) holder.getData();
		E peek = data;
		return peek;
	}

	/**
	 * this method sets head to null and list size to zero
	 * essentially removing any traces of elements
	 * Complexity: O(1)
	 */
	public void makeEmpty() {
		size = 0;
		head = null;
	}
	/**
	 * Checks to see if size variable is zero
	 * if yes, list is empty, returns true
	 * otherwise, returns false
	 * Complexity: O(1)
	 */
	public boolean isEmpty() {

		if(this.size==0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * List checks to see if all Nodes in list contain some kind of data
	 * if any Node.data returns null, empty space found
	 * returns false4
	 * If no empty space found, return True
	 * Complexity: O(n)
	 */
	public boolean isFull() {
		boolean res = true;
		Node<E> current1= this.head;

		while(current1!=null) { 
			//while you are in the list
			if(current1.data == null) { 		//if there is data in the node
				res = false;
				return res; 				// if empty space found, return FALSE
			}
			else { 					  //otherwise, go to next Node
				current1=current1.getNext();
			}
			res = true;  //res is true
		}
		return res;
	}


	/**
	 * returns size of list
	 * Complexity: O(1)
	 */
	public int size() {
		return this.size;
	}



	/**
	 * This method takes an object parameter and compares it 
	 * to all values contained in the list
	 * If any match found, return true.
	 * Otherwise, return false
	 * Complexity: O(n)
	 */
	public boolean contains(E obj) {
		Node<E> current = head; //save head in current
		boolean result = false;

		while( current!= null && !result ) {
			if( obj.equals (current.getData())){
				result = true;
			}
			else {
				current = current.getNext();
			}
		}
		return result;
	}
	/**
	 * this method goes into list and reverses order
	 * of all contained elements
	 * list itself is altered
	 * Complexity: O(n)
	 */
	public void reverse(){
		LinkedList<E> revd = new LinkedList<E>();
		Node<E> temp = this.head;
		while(temp != null){
			E data = temp.getData();
			revd.addFirst((data));  //add data to front of list
			temp = temp.getNext();
		}
		this.head = revd.head;
	}	

/**
 * returns an instance of the iterator Object
 */
	public Iterator<E> iterator() {
		return (Iterator<E>) new IteratorHelper();	
	}

	class IteratorHelper implements Iterator<E> {
		int counter;
		int iteratorIndex;
		Node<E> index;
		
		public IteratorHelper() {
			index = head;
			counter = size;
		}
		
		
		public boolean hasNext() {
			if(index == null) { 
				return false;
		}
			else 
			{	 return true; 
			}
			}
		
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();}
			Node<E> tmp = index;
			index = index.next;
			counter--;
			return tmp.data;
		}
		}
	
		
				Node<E> current = new Node<E>();
				Node<E> previous = new Node<E>();
				
				
	}
	/**
	 * The Node class is made up of a data holder type E &
	 * a pointer to another Node, the next Node.
	 * 
	 * @author a
	 *
	 * @param <E>
	 */
	class Node<E> {
		Node<E> next;
		E data;

		public Node() {
			next = null;
			data=null;
		}
/**
 * this constructor sets the data to the passed argument
 * & instantiates a new Node
 * @param obj
 */
		public Node(E obj)  {    //default constructor
			next = null;
			data = obj;
		}
/**
 * 
 * @return data held by Node
 */
		public E getData() {
			return this.data; }
/**
 * set data to value of passed argument
 * @param obj
 */
		public void setData(E obj) {
			data=obj; }
/**
 * returns the next Node pointed to
 * @return
 */
		public Node<E> getNext() {
			return this.next; }
/**
 * sets the value of next Node to passed argument
 * @param nextval
 */
		public void setNext(Node<E> nextval) {
			next = nextval; }
	

}	
