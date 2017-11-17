package Lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of MyList with a linked list (a longer exercise would be to
 * implement the List interface as is done in the class java.util.LinkedList:
 * the source of the LinkedList class is available from Sun. Check it out).
 */

public class MyLinkedList<E> implements MyList<E> {

	// A private class to represent a Node in the linked list
	private class Node {
		public E item;

		public Node next;

		// a convenient constructor
		public Node(E o) {
			this.item = o;
			this.next = null;
		}
	}

	// The start of the linked list
	private Node head;

	// The last Node in the linked list
	private Node tail;

	// Number of elements in the list
	private int size;

	/**
	 * Creates an empty list (this constructor is not necessary)
	 */
	public MyLinkedList() 
	{
		
	}

	/**
	 * Returns the number of elements in this list.
	 */
	public int size() 
	{
//		int count = 0;
//		for(Node p = head ; p!=null; p=p.next)
//		{
//			count++;
//		}
//		return count;
		return size;
		
	}

	/**
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() 
	{
		return size==0;
	}

	/**
	 * Appends the specified element to the end of this list
	 * this is bigO(1)
	 */
	public boolean add(E o) {
		
		Node n = new Node(o);
		
		
		if(this.isEmpty())
		{
			// If this is the first element in the list
			this.head=this.tail = n;
		}
		else
		{
			this.tail.next =n;// If the list is not empty, use tail
			this.tail = n;
		}
		this.size++;// update size
		return true;

		
	}

	/**
	 * Empties this List
	 */
	public void clear()
	{
		size = 0 ; 
		head=tail=null;
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index) 
	{
		if(index<0||index>=this.size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node p = head;
		for(int i = 1 ; i<=index;i++)
		{
			p = p.next;
		}
		return p.item;
		// Find it
	}

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 */
	public int indexOf(Object o) {
		Node p  =head;
		
		for(int i = 0 ; i< this.size; i++)
		{
		
			// If o is null
			if (o == null) // look for a null element in the list
			{
				if(p.item ==null)
				{
					return i; 
				}
				
			} else // o is an object (use equals)
			{
				
				if(o.equals(p.item))return i ; 
			}
			
			p = p.next;
			
		}
		return -1; 
		// if we get here, o is not in the list
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(Object o) 
	{
		return indexOf(o)==-1?false:true;
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(int index) {

		if(index<0||index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		if(index ==0 )
		{
			this.head = this.head.next;
			this.size--;
			return true;
		}
		
		Node p = this.head;
		for(int i = 1 ; i<index; i++,p=p.next);
		p.next = p.next.next;
		if(index == size -1 )this.tail = p; 
		this.size--;
		return true; 
	
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(Object o) {
		// easy with indexOf and remove (but expensive)
		
		int index = indexOf(o);
		
		if(index != -1)
		{
			return remove(index);
		}
		else
		{
			return false; 
		}
		
	}

	/**
	 * Adds the specified object at the specified location
	 */
	public boolean add(int index, E o) {

		if(index<0 || index> this.size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0 )
		{
			this.head = this.tail = new Node(o);
			this.size++;
			return true;
			
		}
		
		Node pre = null; Node p = this.head;
		
		for(int i = 1 ; i<=index; i++)
		{
			pre = p;
			p = p.next;
		}
		// Add a node between prev and p
		Node n = new Node(o);
		n.next = p;
		if(pre!=null)
		{
			pre.next = n;
		}
		
		if(index == size)
		{tail = n;}
		size++; return true;
		
		// Find the corresponding node

		
		// Special case (first node)

		// Update tail if necessary

		// Update size
	}

	/**
	 * Is this List equal to the specified object?
	 */
public boolean equals(Object o)
    {
        if (o instanceof MyLinkedList) 
        {
            // o is an ArrayList
        	MyLinkedList<E> list = (MyLinkedList<E>)o;
        	
            // if the number of elements is not the same, this and o are not the
			// same
        	if(list.size!= this.size)return false;

            // Check the elements one by one
        	
        	Node p = this.head ; Node q = list.head;
        	
        	for(; p.item==null&q.item==null; p =p.next, q= q.next)
        	{
        		if(p.item==null||q.item==null)
        		{
        			if((q.item!=null&&p ==null)||(p.item!=null&& q.item == null))return false;
        		}
        		else
        		{
        			if(!q.item.equals(p.item))return false;
        		}
        		
        		
        	}
        	return true;

            // At this point, the lists are equal

        }
		else {
			return false;
	    }
    }
	/**
	 * An inner class to define the iterator
	 */
	private class MyIterator implements Iterator<E> {
		
		private Node visiting = MyLinkedList.this.head;
		private Node visited= MyLinkedList.this.head;
		private Node beforevisited= MyLinkedList.this.head;
		
		

		//private MyLinkedList<E> list;

		private int lastVisitedIndex=0; // index of the most recently

		// visited node

		/**
		 * Create an iterator for a MyLinkedList
		 */
		
		public MyIterator()
		{
			
		}
		

		/**
		 * Any element left in the list?
		 */
		public boolean hasNext() 
		{
			// if the linkedlist is empty, the visiting node will be null;
			if(MyLinkedList.this.isEmpty())return false; 
			// actually who in the right mind will do this? A empty list should not return iterator at all! 
			
			return this.visiting.next!=null;
		}

		/**
		 * Return the current element in the list and move to the next element
		 */
		public E next() 
		{
			if(!this.hasNext())
			{
				throw new NoSuchElementException();
			}
			E item = this.visiting.item;
			this.beforevisited = this.visited;
			this.visited = this.visiting;
			this.visiting=this.visited.next;
			return item;
		}

		/**
		 * Remove the object currently pointed at by the iterator
		 */
		public void remove() 
		{	
			// the visited element and visiting element share 
			// the same address. 
			if(this.visited==this.visiting)
			{
				throw new IllegalStateException();
			}
			
			// skip the visited element.
			this.beforevisited.next= this.beforevisited.next.next;
			this.visited = this.visiting;
		}
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	
	
}
