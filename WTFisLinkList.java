package Lab6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is cool I know, compuer science 101 stuff...
 * it just shouldn't be hard, right? 
 * 
 * 
 * @author Dashie Is THE BEST Waifu
 */

public class WTFisLinkList<E> implements MyList<E>, Iterable<E> {
	
	
	public static void main(String[] args)
	{
		WTFisLinkList<Integer> l = new WTFisLinkList<Integer>();
		for(int i = 0 ; i<10; i++)
		{
			l.add(i);
			System.out.println(l.get(i));
		}
		for(Integer i : l)
		{
			System.out.println(i);
		}
		
		System.out.println(l.equals(l));
		WTFisLinkList<Integer> newl= new WTFisLinkList<Integer>();
		for(Integer i : l)newl.add(i);
		
		newl.remove(2);
		newl.add(3, 500);
		System.out.println("The copy has been modified");
		System.out.println("This is the equal result: "+ l.equals(newl));
		System.out.println("newl.equals(l): "+newl.equals(l));
	}

	// It should be public, for the sake of liberty.
	public class Node {
		private E item;

		private Node next;

		
		public Node(E o) {
			this.item = o;
			this.next = null;
		}
		
		
		public Node getNext()
		{
			return this.next;
		}
		
		/**
		 * This will break link.
		 * @param n
		 */
		public void linkTheNodeto_(Node n)
		{
			this.next = n;
		}
		
		/**
		 * 
		 * This action will actually break the link.
		 * 
		 * @exception 
		 * nullpointer.
		 */
		public void skipNext()
		{
			if(this.next==null)return;// there is nothing to skip. cool.
			this.next = this.next.next;
		}
		
		public Node jumpOver(int i)
		{
			if(i<0)
			throw new IllegalArgumentException("What the heck? are you bucking serious? ");
			
			Node pointingat= this;
			while(i!=0)
			{
				pointingat =pointingat.getNext();
				i--;
			}
			return pointingat;
		}
	}

	
	private Node head;

	// The last Node in the linked list
	private Node tail;

	// Number of elements in the list
	private int size;

	
	public WTFisLinkList() 
	{
		
	}

	public int size() 
	{
		return size;
		
	}
	public boolean isEmpty() 
	{
		return size==0;
	}
	public boolean add(E o) 
	{
		Node n = new Node(o);
		if(this.isEmpty())
		{
			this.head=this.tail = n;
		}
		else
		{
			this.tail.linkTheNodeto_(n);
			this.tail = n;
		}
		this.size++;
		return true;
	}

	
	public void clear()
	{
		size = 0 ; 
		head=tail=null;
	}

	
	public E get(int index) 
	{
		if(index<0||index>=this.size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		Node n = this.head.jumpOver(index);
		E something = n.item;
		System.out.println(something);
		return something;
	}

	
	public int indexOf(Object o) 
	{
		
		Node pointingat=head;
		int counting=0;
		while(pointingat!=null&&pointingat.getNext()!=null)
		{
			if(pointingat.item!=o)
			{
				
				if(o!=null&&o.equals(pointingat.item))return counting;
				if(o==null && pointingat.item ==null)return counting;
			}
			
			pointingat = pointingat.getNext();
			counting++;
		}
		
		return -1; 
		
	}

	
	public boolean contains(Object o) 
	{
		return indexOf(o)==-1?false:true;
	}

	
	public boolean remove(int index) {

		if(index<0||index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(index ==0 )
		{
			this.head.skipNext();
			this.size--;
			return true;
		}
		if(index == size-1)
		{
			this.tail = this.head.jumpOver(index-1);
			this.tail.skipNext();
			this.size--;
			return true;
		}
		Node temp = this.head.jumpOver(index-1);
		
		temp.skipNext();
		this.size--;
		return true; 
	
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(Object o) {
		// easy with indexOf and remove (but expensive)
		Node pointingat = this.head;
		Node visited = null;
		while(pointingat.getNext()!=null)
		{
			if(pointingat.item!=null&&o!=null)
			{
				if(o.equals(pointingat.item))
				{
					visited.skipNext();
					this.size--;
					return true;
				}
			}
			else
			{
				if(o==null&&pointingat.item==null)
				{
					visited.skipNext();
					this.size--;
					return true;
				}
			}
			visited = pointingat;
			pointingat = pointingat.getNext();
		}
		return false;
		
	}

	
	public boolean add(int index, E o) {

		if(index<0||index> this.size)
		{
			throw new IndexOutOfBoundsException();
		}
		if(this.isEmpty())
		{
			this.head = this.tail=new Node(o);
			this.size++;
			return true;
		}
		if(index == 0 )
		{
			Node n = new Node(o);
			n.linkTheNodeto_(this.head);
			this.head = n;
			this.size++;
			return true;
		}
		Node temp =new Node(o);
		
		Node leftneighber =this.head.jumpOver(index-1);
		Node rightneigber = this.head.jumpOver(index);
		temp.linkTheNodeto_(rightneigber);
		leftneighber.linkTheNodeto_(temp);
		this.size++;
		 return true;
	}

	
	public boolean equals(Object o)
    {
		if(! (o instanceof WTFisLinkList))
		{
			return false;
		}
		WTFisLinkList something=this; 
		return equals(something,(WTFisLinkList)o);
    }
	public static boolean equals(WTFisLinkList a, WTFisLinkList b)
	{
		
		
		if(a.size()!=b.size())return false;
		
		Iterator itr = a.iterator(); Iterator itr2= b.iterator();
		
		while(itr.hasNext())
		{
			Object i= itr.next(), ii =itr2.next();
			if(i!=ii)return false;
			else
			{
				if(i==null){if(ii!=null)return false;}
				else
				{
					
					if(!i.equals(ii))return false;
				}
			}
		}
		return true;
	}
	
	private class MyIterator implements Iterator<E> {
		
		private Node visiting = WTFisLinkList.this.head;
		private Node visited= WTFisLinkList.this.head;
		private Node beforevisited= WTFisLinkList.this.head;
		
		

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
			
			if(WTFisLinkList.this.isEmpty())return false;
			return this.visiting!=null;
		}

		
		public E next() 
		{
			if(!this.hasNext())
			{
				throw new NoSuchElementException();
			}
			
			//1. extract visiting element
			Node result = this.visiting;
			this.beforevisited = this.visited;
			this.visited = this.visiting; 
			this.visiting = this.visiting.getNext();
			return result.item;
		}

		
		public void remove() 
		{	
			// the visited element and visiting element share 
			// the same address. 
			if(this.visited==this.visiting)
			{
				throw new IllegalStateException();
			}
			
			// skip the visited element.
			this.visited = this.visiting;
			this.beforevisited.skipNext();;
			
		}
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() {
		return this.new MyIterator();
	}
	
	
}
