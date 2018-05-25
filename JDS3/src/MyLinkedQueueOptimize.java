import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedQueueOptimize<AnyType> implements Iterable<AnyType> {
	private int size = 0;
	private Node addNode = null;
	private Node pollNode = null;
	
	public boolean add(AnyType at) {
		if(size == 0) {
			addNode = pollNode = new Node(at);
			addNode.next = pollNode;
			pollNode.next = addNode;
		} else if(addNode.next.data == null) { 
			addNode.next.data = at;
			addNode = addNode.next;
		} else {
			Node tmp = addNode.next;
			addNode = addNode.next = new Node(at); 
			addNode.next = tmp;
		}
		size++;
		return true;
	}
	
	public AnyType poll() {
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		AnyType tmp = pollNode.data;
		pollNode.data = null;
		pollNode = pollNode.next;
		size--;
		return tmp;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		addNode = pollNode = null;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<AnyType> {

		int currentSize = size;
		Node p = pollNode;
		
		@Override
		public boolean hasNext() {
			return currentSize != 0;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			AnyType tmp = p.data;
			p = p.next;
			currentSize--;
			return tmp;
		}
		
	}
	
	private class Node {
		AnyType data;
		Node next;
		Node(AnyType data) {
			this.data = data;
		}
	}
	
}
