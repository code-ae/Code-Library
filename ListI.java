package data_structures;

import java.util.Iterator;

public interface ListI<E> extends Iterable<E> 

{
	public void addFirst(E obj);
	public void addLast(E obj);
	public E removeFirst();
	public E removeLast();
	public E peekFirst();
	public E peekLast();
	public void makeEmpty();
	public boolean isEmpty();
	public boolean isFull();
	public int size();
	public boolean contains(E obj);
	public void reverse();
	public Iterator<E> iterator();
}
