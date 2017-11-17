package Lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test the implementation of a List
 */

public class TestMyList {

	/** Tests the two list implementations */
	public static void main(String[] args) {
		// Create an arrayList
		System.out.println("Test the ArrayList implementation");
		MyList<Integer> l = new MyArrayList<Integer>();
		test(l);
		System.out.println();
		l = new MyLinkedList<Integer>();
		System.out.println("\nTest the LinkedList implementation");
		test(l);
		l = new WTFisLinkList<Integer>();
		System.out.println("Testing the : "+ l.getClass());
		test(l);
	}

	/** A series of list operations to test the implementations of MyList */
	private static void test(MyList<Integer> l) 
	{
		System.out.println("__________________TEST INITIATED__________________");
		// Add 9 integers
		System.out.println("Add 1,...,9");
		for (int i = 1; i <= 9; i++)
			l.add(new Integer(i));

		// Print l
		printList(l);

		// Make a copy of l
		System.out.println("Make a copy of the list");
		MyList<Integer> copy = createMyList(l.getClass());
		for (int i = 1; i <= 9; i++)
			copy.add(new Integer(i));
		System.out.print("copy=");
		printList(copy);
		System.out.println("the list and the copy are the same: "
				+ l.equals(copy));
		System.out
				.println("Change the copy (remove element at position 4 and add 18)");
		copy.remove(4);
		copy.add(new Integer(18));
		System.out.print("copy=");
		printList(copy);
		System.out.println("the list and the copy are the same: "
				+ l.equals(copy));

		// Remove and add an elemen
		System.out.println("Could remove 10=" + l.remove(new Integer(10)));

		System.out.println("add 21 at position 5");
		l.add(5, new Integer(21));
		System.out.println("print the list (using get)");
		printListWithGet(l);

		System.out.println("5 has index " + l.indexOf(new Integer(5)));
		System.out.println("34 has index " + l.indexOf(new Integer(34)));

		// Clear the list
		System.out.println("\nClear the list");
		l.clear();
		System.out.println("the list is empty=" + l.isEmpty());
		printList(l);

		System.out.println("the list contains 3=" + l.contains(new Integer(3)));

		// Add some integers to the list and null
		System.out.println("\nAdd to the list: 1,null,2,3");
		for (int i = 1; i <= 3; i++)
			l.add(new Integer(i));
		l.add(1, null);
		printList(l);

		System.out.println("The index of null=" + l.indexOf(null));
		System.out.println("The index of 1=" + l.indexOf(new Integer(1)));

		// Remove the front element
		System.out
				.println("\nRemove the element at position 0 until the list is empty");
		while (!l.isEmpty()) {
			l.remove(0);
			printList(l);
		}

		// Create a new list
		l.clear();
		for (int i = 0; i <= 10; i++)
			l.add(l.size(), new Integer(i));
		System.out.println("\nA new list: 0,1,...,10");
		printList(l);

		// Remove the last element
		System.out.println("Remove the last element");
		l.remove(l.size() - 1);
		printList(l);

		// Add another element
		System.out.println("Add 100");
		l.add(new Integer(100));
		printList(l);

		// clear the list with an iterator
		System.out
				.println("clear the list one element at a time with an iterator");
		Iterator<Integer> it = l.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
			printList(l);
		}

		// A new list
		System.out.print("A new list: l = ");
		for (int i = 0; i < 5; i++) {
			l.add(new Integer(i));
		}
		printList(l);

		// Should throw an exception
		try {
			it = l.iterator();
			while (it.hasNext()) {
				it.remove();
			}
			// should not get here
			throw new RuntimeException("The test of the iterator has failed.");
		} catch (IllegalStateException e) {
			System.out.println("Exception thrown as expected");
		}

	}

	/**
	 * Prints the list
	 */
	private static <E> void printList(MyList<E> l) {
		String s = "{";
		Iterator<E> it = l.iterator();
		if (it.hasNext()) // first element (no comma before it)
			s += it.next();
		while (it.hasNext())
			s += "," + it.next();
		s += "}";
		System.out.println(l.size() + " element" + ((l.size() > 1) ? "s" : "")
				+ ": " + s);
	}

	/**
	 * Prints the list (with get)
	 */
	private static <E> void printListWithGet(MyList<E> l) {
		String s = "{";
		if (l.size() > 0) // first element (no comma before it)
			s += l.get(0);
		for (int i = 1; i < l.size(); i++)
			s += "," + l.get(i);
		s += "}";
		System.out.println(l.size() + " element" + ((l.size() > 1) ? "s" : "")
				+ ": " + s);
	}

	/**
	 * Returns a list as an instance of the given type
	 */
	private static <E> MyList<E> createMyList(Class c) {
		if (c == MyArrayList.class) {
			return new MyArrayList<E>();
		} else if (c == MyLinkedList.class) {
			return new MyLinkedList<E>();
		} else if(c == WTFisLinkList.class)
		{
			return new WTFisLinkList<E>();
		}
		else
		{
			
			return null;
		}
	}
	
	public static void timingAndturturetext()
	{
		
	}

}