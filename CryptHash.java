package data_structures;

import java.util.Iterator;




public class CryptHash<K,V> implements HashI<K,V>
{
	int size;
	LinkedList<HashNode<K,V>>[]array; //creates array of linkedlist (dictionary)
	double maxLoadFactor;
	int numElements;
		
	public CryptHash(int hashLength)
	{	//CONSTRUCTOR
		this.size = hashLength;
		this.array = (LinkedList<HashNode<K,V>>[])new Object[size];
		this.numElements=0;
		this.maxLoadFactor=1.75;//check this load factor
		
		for( int i = 0; i < size; i++) {
			 array[i] = new LinkedList<HashNode<K,V>>();
		}
	}
	
    private int getIndex(K key){
        return (key.hashCode() & 0x7FFFFFFF) % size; }
    

	/**
	 * This method returns the size of the Hash
	*/
	public int size() {
		return this.size;
	}
	
	/**
	 * If Hash is empty(size=0), method returns false
	 */
	public boolean isEmpty() 
	{
		if(size == 0)
			return true;
		else 
			return false;
	}
	
	/**
	 * This method iterates through the list counting the NumElements
	 * then it divides the numElements by the total size of the Hash
	 * to return the Max Load Factor
	 * 
	 */
	public double loadFactor() {
		double occupied = 0;
				for( int i = 0; i <size; i++) 
				{
				        LinkedList<HashNode<K,V>> check = new LinkedList<HashNode<K,V>>()
					if( check.head != null  ) {
						occupied++;
					}
				}
				return (double) numElements/size;
		}
	/**
	 * This method creates a new copy of the hashtable and copies
	 * the old contents into the new, stopping at whatever array was shortest
	 */
	public void resize(int newSize){
		CryptHash<K,V> resized = new CryptHash<K,V>(newSize);  //  creates new Hash (resized) set to right size
		for ( int i = 0; i<resized.size; i++) {
				resized.array[i] = this.array[i]; //takes elements from old array, copies into new
		}
	}
	/**
	 * This method creates a new HashNode that is given a Key and Value
	 * If loadfactor is too high or key is occupied, method returns false
	 * otherwise it adds the new HashNode into the bucket
	 */
	public boolean add(K key, V value) 
	{
		int keys =(int) key;
		HashNode<K,V> toadd = new HashNode<K,V>(key,value);
		
		if( loadFactor() > maxLoadFactor)
		{  //resizes if Hash cant fit more
			int newsize = size+2;
			this.resize(newsize);
		}
		array[keys].addFirst(toadd);   //inserts HashNode at Key
		numElements++;
		return true;
		//take the key; hashcode it; value is "index"; set array[index] to VALue
	}
	/**
	 * This method removes the Value at the designated Key in the HashTable
	 * It also decrements the numElements variable for accuracy;
	 * returns false if element couldnt be added and true otherwise
	 */
    public boolean remove(K key) {
        HashNode<K,V> obj = new HashNode<K,V>(key, null); //makes node with same key as obj to be removed
        if(loadFactor()> maxLoadFactor) {
            int newsize = size+2;
            resize (newsize);
        }
        long hashcode = key.hashCode();
        hashcode = hashcode * 10111; 	   //Multiplying the default hash by 10111
        hashcode = hashcode%size;          // increases the encryption strength, 
        array[(int)hashcode].remove(obj);  // attributed to the prime factor.
        numElements--;
        return true;
    }
	 /**
	 * This method goes to the parameter Key and counts the elements in that bucket
	 * It then reduces the numElements by that amount,
	 * then returning "bucket" to empty
	 **/
	public V getValue(K key) 
	{
		int keys = (int) key;
		if(array[keys] == null || size == 0) {
			return null;
		}
		LinkedList<HashNode<K,V>> toReturn = array[keys];  // linklist w/ desired Val
		return toReturn.peekObj().value;
	}
	/**
	 * Returns an Iterator object to iterate through Key values.
	 **/
	public Iterator<K> iterator() {
		return new KeyIteratorHelper<K>();}
	/**
 	*  Key Iterator that iterates through and returns keys
 	**/
	class KeyIteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int currpos;
      
        public KeyIteratorHelper(){
            keys = (T[]) new Object[numElements];
            int count = 0;
            for (int i = 0; i < size; i++) 
            {
                LinkedList<HashNode<K, V>> list = array[i];    
                for (HashNode<K, V> nodez : list) {
                    keys[count++]   =   (T) nodez.key;
                }
            }
            currpos = 0;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
     
        public boolean hasNext() {
            return currpos < keys.length;
        }
        @Override
        public T next() 
        {
            if (!hasNext())
                return null;
            return (T) keys[currpos++];
        }
	}

	public Iterator<K> keys(){
		return new KeyIteratorHelper<K>();
	}
	
	public Iterator<V> values(){
		return new ValueIteratorHelper<V>();
	}
	
	 /**
	 * Value Iterator that iterates through Nodes and returns the values	
	 **/
	class ValueIteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int current;
        int x = 0;
        
        public ValueIteratorHelper(){
            keys = (T[]) new Object[numElements];
                    
            for (int i = 0; i < size; i++) 
            {
                LinkedList<HashNode<K, V>> list = array[i];
                for (HashNode<K, V> nodez : list) 
                {
                    keys[x++] = (T) nodez.value;
                }
            }
            current = 0;
        }
       
        public void remove(){
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasNext() {
            return current < keys.length;
        }
        @Override
        public T next() {
            if (!hasNext())
                return null;
            return(T) keys[current++];
                }
    }
	 /**
	 * This Object is a HashNode that is held in a Dictionary
	 * It contains a Key and Value, which are its location in the Dictionary and its meaning, resp.
	 * It has a pointer to the next Node, if any
	 * Setter and Getter methods make data manipulation easy.
	 **/
	public class HashNode<K,V> implements Comparable<HashNode<K,V>> 
	{
		K key;
		V value;
		
		public HashNode(K keys, V values) 
		{
			this.key = keys;
			this.value=values;
		}

		public int compareTo(HashNode<K,V> node)
		{
			return ((Comparable<K>)key).compareTo((K)node.key);
		}
		
		public V getData() {
			return  this.value;
		}
	
	}
}
