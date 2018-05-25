import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyLinkedStackWithSingleTrack<AnyType> implements Iterable<AnyType>{
	
	private int size = 0;
	private int modifyCount = 0;
	private Node last = null;
	
	public boolean push(AnyType at) {
		Node tmp = new Node(at);
		if(size == 0) {
			last = tmp;
		} else {
			tmp.prev = last;
			last = tmp;
		}
		size++;
		modifyCount++;
		return true;
	}
	
	public AnyType pop() {
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			AnyType data = last.data;
			last = last.prev;
			size--;
			modifyCount++;
			return data;
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
		last = null;
		modifyCount++;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedStackIterator();
	}
	
	private class LinkedStackIterator implements Iterator<AnyType> {

		int index = size-1;
		int exceptModifyCount = modifyCount;
		Node tmp = last;
		
		@Override
		public boolean hasNext() {
			return index >= 0;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			} else if(exceptModifyCount != modifyCount) {
				throw new ConcurrentModificationException();
			}
			AnyType data = tmp.data;
			tmp = tmp.prev;
			index--;
			return data;
		}
		
	}
	
	private class Node {
		Node prev;
		AnyType data;
		Node(AnyType data) {
			this.data = data;
		}
	}
	
}
