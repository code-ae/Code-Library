package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

//generic ordered linked list implementation
//order ascending 
public class OrderedList<T>  implements Iterable<T>{

    //just need head pointer

    private Node<T> head = null;
    //tail is useless
    private int currentSize;
    private Node<T> tail;

    @SuppressWarnings("unchecked")
    public void insert(T obj) {
        //this is the only insert
        //as oppsed to unorderedlist
        //which has first and last

        Node<T> nextNode = new Node<T>(obj);
        Node<T> previous = null, current = head;

        while (current != null && ((Comparable<T>) obj).compareTo(current.data) >= 0) {
            previous = current;
            current = current.next;
        }

        if (isEmpty()) {
            head = nextNode;
        }
        else if (previous == null) {
            nextNode.next = head;
            head = nextNode;
        }
        else if (current == null) {
            previous.next = nextNode;
        }
        else {
            previous.next = nextNode;
            nextNode.next = current;
        }

        currentSize++;
    }

    public T removeFirst() //removeMin
    {
        return removeMin();
    }

    @SuppressWarnings("unchecked")
    public T remove(T obj) //remove arbitrary object in list
    {
        //traverse list 'till we find obj
        Node<T> previous = null, current = head;

        while (current != null && ((Comparable<T>) obj).compareTo(current.data) != 0) {
            previous = current;
            current = current.next;
        }

        if (current == null)
            return null;

        if (head == tail)//there's only 1 item in list (or empty null==null)
        {
            head = tail = null;
        }
        else if (previous == null) {
            head = head.next;
        }
        else if (current == tail) {
            previous.next = null;
            tail = previous;
        }
        else // 2 or more nodes and it's in middle
        {
            previous.next = current.next;

        }

        currentSize--;
        return current.data;
    }
    public T peekMin() {
        return this.head.data;
    }
    
    public T removeMin() {

        if (isEmpty()) {
            return null;
        }

        T temp = head.data;
        head = head.next;
        currentSize--;
        return temp;

    }

//    public T find(T obj) {
//        //find an object in list based on obj's available attributes
//
//        throw new NotImplementedException();
//    }

    public Iterator<T> iterator() {
        return new ListIterator();

    }

    public boolean isEmpty() {
        return this.getCurrentSize() < 1;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    //can use this for ordered list too
    public class ListIterator implements Iterator<T> {

        Node<T> iterPtr;

        public ListIterator() {
            iterPtr = head;
        }

        @Override
        public boolean hasNext() {
            return iterPtr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = iterPtr.data;
            iterPtr = iterPtr.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }

    public void clear() {
        head=null;
        currentSize=0;
        
    }
    
    public class Node<T> {

        public Node(T obj) {
            this.data=obj;
        }
        public Node<T> next;
        public Node<T> previous;
        public T data;

    }
}
