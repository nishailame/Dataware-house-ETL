package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * 
 * @author Nisha Ilame
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size = 0;

	/** Create a new empty LinkedList */
	@SuppressWarnings("unused")
	public MyLinkedList() {

		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;

		// TODO: Implement this method
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		if (element==null){
			throw new NullPointerException();
		}
		LLNode<E> node = new LLNode<E>(element);
		if (head.next == tail) {
			head.next = node;
			node.next = tail;
			tail.prev = node;
			node.prev = head;
		} else {
			node.next = tail;
			node.prev = tail.prev;
			node.prev.next = node;
			tail.prev = node;
		}

		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
            
			LLNode<E> current = head.next;
			for (int i = 0; i < index ; i++) {
				current = current.next;
				//System.out.println(current.data);
			}
			
			return current.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method

		LLNode<E> node = new LLNode<E>(element);
		if (element==null){
			throw new NullPointerException();
		}
		
		if (index < 0 || (index >= size && size != 0)) {
			throw new IndexOutOfBoundsException();
		} else if (index == size() && size == 0) {
			add(element);
		} else if (index == 0) {
			
			
			node.prev=head;
			node.next=head.next;
			node.next.prev=node;
			head.next=node;
			size++;
		}
		else {
			
			LLNode<E> current = head.next;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			node.next=current;
			node.prev=current.prev;
			node.prev.next=node;
			current.prev=node;
			
			size++;		
		}
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"Index out of bounds. Can't remove a node. No node exists at the specified index");
		}

//		if (index == size() - 1) {
//			E val=tail.prev.data;
//			tail.prev=tail.prev.prev;
//			tail.prev.prev.next=tail;
//			size--;
//			return val;
//
//		} else if (index == 0) {
//			E val = head.next.data;
//		    head.next=head.next.next;
//		    head.next.next.prev=head;
//			size--;
//			return val;
	//	}
	else {

			LLNode<E> current;
			// starting next one to our head
			current = head.next;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			current.prev.next = current.next;
			current.next.prev = current.prev;
			size--;
			return current.data;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (element == null) {
			throw new NullPointerException();
		} else {
			LLNode<E> current = head.next;
			for (int i = 0; i < index; i++) {

				current = current.next;
			}
			E val = current.data;
			current.data = element;
			return val;
		}

	}
	
	public void print(){
		LLNode<E> temp2= head;
		while(head.next!=tail){
			LLNode<E> temp= head.next;
			System.out.println(temp.data);
			head=head.next;
		}
		head=temp2;
		
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	// public LLNode(E e, LLNode<E> node) {
	// this.data = e;
	// this.prev = node.prev;
	// this.next = node;
	// node.prev = this;
	// }
	//
	// public LLNode(E e, LLNode<E> n, LLNode<E> p) {
	// data = e;
	// next = n;
	// prev = p;
	// }
	//
	// public String toString() {
	// return data.toString();
	// }

}
