package data_structures;
import java.util.NoSuchElementException;


public class BalancedSearchTree<E> {
	
	
	private Node<E> root = new Node<E>(null);
	int currentSize;
	Node<E>[] array; // used for isFull method

	public Node<E> rootNode() {
		return this.root;
	}
	
	public E findNext(E obj) {
		Iterator<E> iterator = new Iterator<E>(null);
		Node<E> nextOne = new Node<E>();
		nextOne = iterator.next();
		return nextOne.data;
	}
	/**
	 * this method creates an ordered array with an Iterator,
	 * populated with all tree elements
	 * It then searches the array for the matching element, 
	 * and then returns the previous index
	 * @param obj
	 * @return
	 */
	public E findPrevious(E obj) {
		Iterator<E> iterator = new Iterator<E>(root);
		Object[] objects = new Object[currentSize];
		
		objects[0] = this.root;
		for(int i = 1; i<currentSize;i++)
			objects[i] = iterator.next();  //ARRAY POPULATED WITH ALL ELEMENTS
		
		
		Node<E> toFind = new Node<E>(obj);
		int y = 0;
		while(objects[y] != toFind)	//iterate through array until object is found
				if(objects[y] == null)
					return null;
				else
					y++;
		y--;
		return (E) objects[y];	
	}
	/**
	 * This method sets the root to the value of the parameter
	 * @param node
	 */
	public void setRoot(Node<E> node){
		this.root= node;
	}
	
	/**
	 * This method returns the value of the tree root
	 * @return
	 */
	public E getRootData() {
	return this.root.data;
	}
	
	/**
	 * Constructor
	 */
	public BalancedSearchTree() {
		this.root = null;
		this.currentSize = 0;
		//		this.array = null;
	}
/**
 * Node class definition, with data slot type E and 
 * pointers to a leftChild and rightChild
 * @author a
 *
 * @param <E>
 */
	 public class Node<E> {
		public E data;

		public int balance;

		Node<E> leftChild;    //pointers
		Node<E> rightChild;
		Node<E>	parent;

		public Node(E info) {
			this.data = info;																
			leftChild = rightChild = parent = null;
		}
		
		public Node() {
			this.data=null;
			leftChild = rightChild = parent = null;
		}

	}
	 
	/**
	 * This method adds a Node by navigating to the correct
	 * spot using BST rules. (Bigger on right, small on Left)
	 * it then sets the pointers of th sorrounding Nodes accordingly
	 * @param obj
	 * @return
	 */
	public boolean add(E obj) {
		if(root == null)
		{
			Node<E> newRoot = new Node<E>(obj);
			setRoot(newRoot);
		}
		else
		{
			insert(obj,root,null,false);
		}
		currentSize++;
		
		
		return true;
	}
	/**
	 * recursive Insert method, returns a value to add(E obj) to be used to complete addition
	 *
	 * @param obj
	 * @param n
	 * @param parent
	 * @param wasLeft
	 */
	private void insert(E obj, Node<E> n, Node<E> parent, boolean wasLeft) {
		if(n==null) {
			if(wasLeft) parent.leftChild = new Node<E>(obj);
			else parent.rightChild = new Node<E>(obj);
		}
		else if(((Comparable<E>)obj).compareTo((E)n.data) < 0) 
			insert(obj,n.leftChild,n,true);
		else
			insert(obj,n.rightChild,n,false);
	}
	
	/**
	 * This method takes the value to be deleted and traverses the tree
	 * until it finds it. When it does, it handles it according to wether it has no children, one
	 * right child, one left child, or both.
	 * If tree is empty, it will return false
	 * @param obj
	 * @return
	 */
	public boolean delete(E obj) {
		if(currentSize == 0)
			return false;
		Node<E> thisNode = root;
		Node<E> toDelete = new Node<E>(obj);
		
		while(thisNode != null) { 
			
			if(((Comparable<E>)thisNode.data).compareTo(toDelete.data) > 0) {
				thisNode = thisNode.leftChild;}
			else if(((Comparable<E>)thisNode.data).compareTo(toDelete.data) < 0) {
				thisNode = thisNode.rightChild;}
			else{
				
				  currentSize--; //DECREMENT
				  //thisNode has no child, just remove
				  if(thisNode.leftChild==null && thisNode.rightChild == null) {
					  
					  if(((Comparable<E>)thisNode.parent.data).compareTo(thisNode.data) < 0) 
						  thisNode.parent.rightChild = null;
					  									
					  else
						  	thisNode.parent.leftChild = null;
					  break;					
				  }
				  						//should be good
				  else if(thisNode.leftChild == null) { // only has right node
					  thisNode.parent.leftChild = null ; // move right node up to current
					  						//thisNode.rightChild
				  }
				  						//ALL GOOD CASE
				  else if(thisNode.rightChild == null){ // only has left node
					  thisNode.parent.rightChild = thisNode.leftChild; // move leftchild up to current
					  
				  }
				  //current node has two children
				  else { 
					  
					  Node<E> bigger = new Node<E>();
					  bigger = thisNode.rightChild;
					  Node<E> current = new Node<E>(thisNode.data);
					  current = thisNode;
					 
					  							//replace current with SUCCESSOR
					  while(bigger!=null){
						  current = bigger;
					  	  bigger = bigger.leftChild;
				      }
					  thisNode.data = current.data;
					  current.parent.leftChild.data = null;
					  
				  
				  }
			}
		}
		return true;
	}
	
	/**
	 * THis method checks the balance factor of a node by taking
	 * the difference between the heights of the trees rooted at the nodes rightChild
	 * and leftChild. If any imbalance, is found, the Node is balanced, along with its parents
	 * @param node
	 */
	public void checkBalance(Node<E> node) {   //recursive method
		if(node == null)
			return;						    // corresponding stopping condition
		if((heightBelow(node.leftChild) - heightBelow(node.rightChild) > 1 ) || (heightBelow(node.leftChild) - heightBelow(node.rightChild) < -1 )) 
		{
			balanceAll(node);
		}

		checkBalance(node.parent);
	}

/**
 * This method is used to balance the parameter node,
 * along with every one of its ancestors
 * @param start
 */
	public void balanceAll(Node<E> start) 
	{
		int balance = heightBelow(start.leftChild) - heightBelow(start.rightChild);

		if(balance < -1) 
		{
			if(heightBelow(start.leftChild.leftChild) >= heightBelow(start.leftChild.rightChild)) {
				start = rotateRight(start);
			} else 
			{
				start = leftRightRotate(start);
			}
		}     else if (balance > 1) 
		{
			if(heightBelow(start.rightChild.rightChild) >= heightBelow(start.rightChild.leftChild))
				start = rotateLeft(start);
		}   else {
			
				start = rightLeftRotate(start);
		}

	}

	/**
	 * Rotates the node by rearranging its
	 * children accordingly
	 * @param n
	 * @return
	 */ 
	private Node<E> rotateLeft(Node<E> n) {
		Node<E> newTop = n.rightChild;
		n.leftChild = newTop.rightChild;
		newTop.rightChild = n;
		return newTop;
	}
/**
 * Rotates the node by rearranging its
 * children accordingly
 * @param n
 * @return
 */ 
	private Node<E> leftRightRotate(Node<E> n) {
		n.leftChild = rotateLeft(n.leftChild);
		return rotateRight(n);
	}
/**
 * Rotates the node by rearranging its
 * children accordingly
 * @param n
 * @return
*/ 
	private Node<E> rightLeftRotate(Node<E> n) {
		n.rightChild = rotateRight(n.rightChild);
		return rotateLeft(n);
	}

	private Node<E> rotateRight(Node<E> n) {
		Node<E> newTop = n.leftChild;
		n.leftChild = newTop.rightChild;
		newTop.rightChild = n;
		return newTop;
	}



	/**
	 * calls recursive find function
	 * @param obj
	 * @return
	 */
	public E get(E obj) {
		return find(obj,root);		//	/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	}								//	GET&FIND METHODS USE EACH OTHER 
	/**								//	\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	 * 
	 * @param obj
	 * @param n
	 * @return E (object searched for in AVL tree)
	 * this method takes a node paramter and root parameter
	 * to begin searching through the entire tree for a match
	 */
	private E find(E obj, Node<E> startnode) 
	{
		if( startnode == null)
			return null;

		if(((Comparable<E>)obj).compareTo(startnode.data) < 0 ) {
			return find(obj, startnode.leftChild); }
		if(((Comparable<E>)obj).compareTo(startnode.data) > 0 ) {
			return find(obj, startnode.rightChild);
		}
		return (E) startnode.data;
	}

/*
 * This method returns the value of the currentSize instance variable
 */
	public int size() {
		return this.currentSize;
	}

	/**
	 * Methods returns wether list is empty or not
	 * by checking null. if it is empty, root is null
	 * otherwise, list is not empty
	 * @return
	 */
	public boolean isEmpty() {  
		if(root == null)
			return true;
		else
			return false;
	}
	/**
	 * This method calulates the height below a certain node by incrementing the return
	 * value by 1 every time the Node steps down the tree one level, returning the value once
	 * the end is reached. 
	 * @param node
	 * @return
	 */
	public int heightBelow(Node<E> node) {
		if( node == null)
			return -1;
		return 1 + Math.max(heightBelow(node.leftChild), heightBelow(node.rightChild));
	}	//adds 1 for every call of the method, until child is null; then returns larger of both values

	public int height() {  //height() returns height from the top of the tree
		return heightBelow(root);
	}

	//recursive contains, takes parameter and starts search from root
	public boolean contains(E obj) {
		Node<E> newNode = new Node<E>(obj);					//
		return contains(newNode,root);					//		LINKED RECURSIVE FUNCTIONS (CONTAINS)
	}														//
	/**
	 * This method iterates through the tree comparing the parameter to the current Node
	 * If match is found, return trues;
	 * Otherwise, method recalls on the nodes left/right child,
	 * depending on the outcome of the compareTo()
	 * 
	 */
	public boolean contains(Node<E> toFind, Node<E> node){
		if(node == null)
			return false;
		if(((Comparable<E>)toFind.data).compareTo(node.data) == 0)   // if match found
			return true;
		if(((Comparable<E>)toFind.data).compareTo(node.data) < 0)   // search leftchild for Node toFind
			return contains(toFind,node.leftChild);
		// all conditions not found, should go to the right
		return contains(toFind,node.rightChild);
	}
	/**
	 * This is an Iterator, which iterates through the tree in order
	 * @author a
	 * @param <E>
	 */
	public class Iterator<E>
	{
		  private Node<E> nextInLine;

		  public Iterator(Node<E> node) {
		     nextInLine = node;
		     if(nextInLine == null)
		       return ;
		     while (nextInLine.leftChild != null)
		       nextInLine = nextInLine.leftChild;
		  }
		  /**
		   * This method returns wether there is a next element
		   * in the list
		   * @return
		   */
		  public boolean hasNext(){
		     return nextInLine != null;
		  }
		  /**
		   * This method returns the next value in the the tree
		   * based on the rules outlined below
		   * 
		   */
		  public Node<E> next(){
		     if(!hasNext()) 
		    	 throw new NoSuchElementException();
		     Node<E> r = nextInLine;
		     // if you can walk right, walk right, then fully left.
		     // otherwise, walk up until you come from left.
		     if(nextInLine.rightChild != null)
		     {
		       nextInLine = nextInLine.rightChild;
		       while (nextInLine.leftChild != null)
		         nextInLine = nextInLine.leftChild;
		       return r;
		     }  else while(true)
		     {
		       if(nextInLine.parent == null)
		       {
		         nextInLine = null;
		         return r;
		       }
		       if(nextInLine.parent.leftChild == nextInLine){
		         nextInLine = nextInLine.parent;
		         return r;
		       }
		       nextInLine = nextInLine.parent;
		     }
		   }
		 }

}
