package data_structures;

import java.util.Iterator;




public class Hash<K,V> implements HashI<K,V>
{
	int size;
	LinkedList<HashNode<K,V>>[]array; 
	double maxLoadFactor;
	int numElements;
	
	public Hash(int s)			//CONSTRUCTOR
	{
		size = s;
		array = (LinkedList<HashNode<K,V>>[])new Object[size];
		numElements=0;
		maxLoadFactor=1.75;//check this load factor
		
		for( int i = 0; i < size; i++)
		{
			array[i] = new LinkedList<HashNode<K,V>>();
		}
	}

	public int size() {
		return this.size;
	}
	public boolean isEmpty() 
	{
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void resize(int newSize)
	{
		Hash<K,V> resized = new Hash<K,V>(newSize);  //  creates new Hash (resized) set to right size
		
		for ( int i = 0; i<resized.size; i++) {
			
				resized.array[i] = this.array[i]; //takes elements from old array, copies into new
			}
		}

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
	}
	
    public boolean remove(K key)
    {
        
        HashNode<K,V> obj = new HashNode<K,V>(key, null); //makes node with same key as obj to be removed
 
        if(loadFactor()> maxLoadFactor) {
            int newsize = size+2;
            resize (newsize);
        }
        int hashcode = key.hashCode();
        hashcode = hashcode & 0x7fffffff;
        hashcode = hashcode%size;
        array[hashcode].remove(obj);
        numElements--;
        return true;
    }
	
	public V getValue(K key) {
		
		int keys = (int) key;
		if(array[keys] == null || size == 0) {
			return null;
		}
		LinkedList<HashNode<K,V>> toReturn = array[keys];  // linklist w/ desired Val
		return toReturn.peekObj().value;
	}

	public Iterator<K> iterator() {
		return new KeyIteratorHelper<K>();
	}


	public class KeyIteratorHelper<T> implements Iterator<T> {
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
      
        @Override
        public boolean hasNext() {
            return currpos < keys.length;
        }
        @Override
        public T next() {
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
