import java.util.Iterator;

public class MyLinkedStack<AnyType> implements Iterable<AnyType> {
	
	private int size;
	private Node head = new Node(null, null, null);
	private Node tail = new Node(null, null, null);

	MyLinkedStack() {
		clear();
	}
	
	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void push(AnyType at) {
		/*
		Node x = new Node(at, null, null);
		x.next = tail;
		x.prev = tail.prev;
		tail.prev = tail.prev.next = x;
		*/
		tail.prev = tail.prev.next //same as up 
				= new Node(at, tail.prev, tail);
		size++;
	}
	
	public AnyType peek() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException();
		}
		return tail.prev.data;
	}
	
	public AnyType pop() {
		AnyType at = peek();
		remove();
		return at;
	}
	
	public Node remove() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException();
		}
		Node t = tail.prev;
		t.prev.next = tail;
		tail.prev = t.prev;
		size--;
		return t;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedStackIterator();
	}

	private class Node {
		AnyType data;
		Node prev;
		Node next;
		Node(AnyType date, Node prev, Node next) {
			this.data = date;
			this.prev = prev;
			this.next = next;
		}
		public String toString() {
//			return (String)data;
			return String.valueOf(data);
		}
	}
	
	private class LinkedStackIterator implements Iterator<AnyType> {

		Node t = head.next;

		@Override
		public boolean hasNext() {
			return t != tail;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			AnyType at = t.data;
			t = t.next;
			return at;
		}

		@Override
		public void remove() {
			MyLinkedStack.this.remove();
		}
		
	}
}