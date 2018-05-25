import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyLinkedQueue<AnyType> implements Iterable<AnyType>{

	private int size = 0;
	private int modifyCount = 0;
	private Node first;
	private Node last;
	
	public boolean add(AnyType at) {
		Node tmp = new Node(at);
		if(size == 0) {
			first = last = tmp;
		} else {
			last.next = tmp;
			last = tmp;
		}
		size++;
		modifyCount++;
		return true;
	}

	public AnyType poll() {
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Node tmp = first;
			first = first.next;
			size--;
			modifyCount++;
			return tmp.data;
		}
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		first = last = null;
		modifyCount++;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedQueueIterator();
	}
	
	private class LinkedQueueIterator implements Iterator<AnyType>{

		int index = 0;
		int exceptModifyCount = modifyCount;
		Node tmp = first;
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			} else if(exceptModifyCount != modifyCount) {
				throw new ConcurrentModificationException();
			}
			AnyType data = tmp.data;
			tmp = tmp.next;
			index++;
			return data;
		}
		
	}
	
	private class Node {
		Node next;
		AnyType data;
		Node(AnyType data) {
			this.data = data;
		}
	}

}
