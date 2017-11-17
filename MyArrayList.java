package Lab6;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of MyList with an array (a longer exercise would be to
 * implement the List interface as is done in the class java.util.ArrayList: the
 * source of the ArrayList class is available from Sun. Check it out).
 * 
 * Gegneric is only used in the compiler level. 
 */

public class MyArrayList<E> implements MyList<E> {

	// Use an array for the implementation
	private E[] items; 

	// Default capacity of the array
	private static final int DEFAULT_CAPACITY = 10;

	// Number of elements in the array
	private int size;

	/**
	 * Constructs a MyArrayList with a specified capacity
	 * JVM doesn't know any thing about generic type. , 
	 * We need to cast it to please the constructor. Generic 
	 * never get passed down to the runtime evironment. 
	 * E can be an abstract class; or an interface. 
	 * 
	 */
	public MyArrayList(int initialCapacity)
	{
		this.items = (E[])new Object[initialCapacity];
		this.size= 0 ;
		
	}

	/**
	 * Constructs a MyArrayList with a default capacity
	 */
	public MyArrayList() 
	{
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the number of elements in this list.
	 */
	public int size() 
	{
		return this.size;
	}

	/**
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {return this.size==0;}

	/**
	 * Appends the specified element to the end of this list
	 * We will create a new array and put the elements already there 
	 * to the new array and make the array into the field. 
	 */
	public boolean add(E o) {
		// If there is no room in the array items
		// Make room for the new element
		// add the new element
		if(this.size==items.length)
		{
			E[] temp = (E[])new  Object[this.items.length+DEFAULT_CAPACITY];	
//			for (int i = 0; i< this.items.length; i++)
//			{
//				temp[i]= this.items[i];
//			}
			
			// or we can use java: 
			//this.items = Arrays.copyOf(this.items, this.items.length+DEFAULT_CAPACITY);
			System.arraycopy(this.items, 0, temp, 0, this.items.length);
			this.items= temp;
		}
		
		// now we have the room for the new element 
		this.items[this.size++]= o;
		return true;
		
		
	}

	/**
	 * Empties this List
	 */
	public void clear() 
	{
		this.size = 0 ;
		// Running gc is opitional 
		this.items = (E[]) new Object[DEFAULT_CAPACITY];
		
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index) 
	{
		if(index<0|| index >= this.size)
		{
			throw new IndexOutOfBoundsException("index = "+ index);
		}
		return this.items[index];
	}

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 */
	public int indexOf(Object o) {
		// If o is null (look for a null element in the array)
		if (o == null) 
		{
			for(int i =0; i<this.size; i++)
			{
				if(o == this.items[i])
				{
					return i ;
				}
			}
		}
		else // o is an object (use equals)
		{
			for(int i =0; i<this.size; i++)
			{
				if(o.equals( this.items[i]))
				{
					return i ;
				}
			}
		}

		return -1;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(Object o) {
		return this.indexOf(0)!= -1 ;
	}

	/**
	 * Removes the element in the List at position index
	 * 
	 */
	public boolean remove(int index) {
		
		if(index>this.size||index<0)
		{
			throw new IndexOutOfBoundsException();
		}
		for(int i =index; i<this.size; i++)
		{
			this.items[i]= this.items[i+1];
		}
		this.size--;
		// compact the array
		return true; 
		// let's gc do its work

	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(Object o) {
		// easy with indexOf and remove
		int index = this.indexOf(0);
		return index==-1? false:remove(index);
	}

	/**
	 * Adds the specified object at the specified location
	 * add to the end and then move it to the index; 
	 * throw unchecked exception if the index is our of bound. 
	 */
	public boolean add(int index, E o) 
	{
		if(index<0 || index>this.size){throw new IndexOutOfBoundsException(); }
		this.add(o);
		for(int i = this.size-1; i> index+1; i--)
		{
			E temp = items[i-1];
			items[i-1] = items[i];
			items[i] = temp;
		}
		return true; 
	}

	/**
	 * Is this List equal to the specified object?
	 * 
	 */
public boolean equals(Object o)
    {
        if (o instanceof MyArrayList) 
        {
           
        	MyArrayList<E> list = (MyArrayList<E> ) o ;
        	
        	if(list.size!= size)
        	{return false; }
        	for(int i = 0 ; i< this.size; i++)
        	{
        		if(items[i] == null)
        		{
        			if(list.items[i]!= null)
        			{
        				return false; 
        			}
        		}
        		else
        		{
        			if(!items[i].equals(list.items[i]))
        			{return false; }
        		}
        	}
           return true; 
        }
        
		else {
			return false;
	    }
	}
	/**
	 * An inner class to define the iterator
	 */
	private class MyIterator implements Iterator<E> {
		
		private int index = 0; // the next index for visting 

		private MyArrayList<E> list;

		private int lastIndex = -1; // index of the object most recently visited

		
		// by next

		/**
		 * Create an iterator for a MyArrayList
		 * we have access to the class directly. 
		 */
		public MyIterator() 
		{
			
		}

		/**
		 * Any element left in the list?
		 */
		public boolean hasNext() 
		{
			return index < size;
		}

		
		private boolean nextcall = false; 
		/**
		 * Returns the current element in the list and move to the next element
		 */
		public E next() 
		{
			this.nextcall= true; 
			
			if(!this.hasNext())
			{
				throw new NoSuchElementException(); 
			}
			else
			{
				return MyArrayList.this.items[this.index++];
				
			}
		}

		/**
		 * Removes the last object returned by next
		 * we can only remove the elements we are visiting. 
		 * 
		 */
		public void remove() 
		{
			if(!this.nextcall)
			{
				throw new IllegalStateException(); 
			}
			this.nextcall = false; 
			MyArrayList.this.remove(index - 1 ); // index is incremented by the 
			// next();
		}
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() 
	{
		return new MyIterator();
	}
}
