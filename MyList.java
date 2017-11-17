package Lab6;

import java.util.Iterator;

/**
 * An interface similar to but not as complete as java.util.List.
 */
						// the E specify the type of the elements. 
public interface MyList<E> extends Iterable<E> {

	/**
	 * Returns the number of elements in this list.
	 */
	public int size();

	/**
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty();

	/**
	 * Appends the specified element to the end of this list
	 */
	public boolean add(E o);

	/**
	 * Empty this List
	 */
	public void clear();

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index);

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 */
	public int indexOf(Object o);

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(Object o);

	/**
	 * Remove the element in the List at position index
	 */
	public boolean remove(int index);

	/**
	 * Remove the element in the List at position index
	 */
	public boolean remove(Object o);

	/**
	 * Add the specified object at the specified location
	 */
	public boolean add(int index, E o);

	/**
	 * Is this List equal to the specified object?
	 */
	public boolean equals(Object o);

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator();
}