import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType>{
	private int size;
	private int countDelete = 0;
	private Node head = new Node(null, null, null);
	private Node tail = new Node(null, null, null);
	
	MyLinkedList() {
		clear();
	}
	
	public void clear() {
		head.next = tail;
		tail.previous = head;
		size = 0;
	}
	
	public Node get(int idx) {
		Node t = head;
		for(int i = 0; i <= idx; i++) {
			t = t.next;
		}
		return t;
	}
/*优化 get(idx); 折半趋中*/	
	public Node getNode(int idx) {
		Node t;
		if(idx < size()/2) {
			t = head;
			for(int i = 0; i <= idx; i++) {
				t = t.next;
			}
			return t;
		} else {
			t = tail;
//			for(int i = size()-1; i >= idx; i--) {
			for(int i = size(); i > idx; i--) {
				t = t.previous;
			}
			return t;
		}
	}
	
	public Node getFirst() {
		return head.next;
	}
	
	public Node getLast() {
		return tail.previous;
	}
	
	public boolean add(AnyType at) {
		add(size(), at);
		return true;
	}
	
	public void add(int idx, AnyType at) {
		if(idx > size() || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node x = new Node(at, null, null);
		Node t = get(idx);
		x.data = at;
		x.next = t;
		x.previous = t.previous;
		t.previous = t.previous.next = x;
		size++;
	}
	
	public AnyType set(int idx, AnyType x) {
		Node t = get(idx);
		AnyType a = t.data;
		t.data = x;
		return a;
	}
	
	public void remove() {
		remove(size()-1);
	}
	
	public AnyType remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node t = get(idx);
		AnyType a = t.data;
		t.previous.next = t.next;
		t.next.previous = t.previous;
//		t.previous = t.next = null;
		t = null;
		size--;
		return a;
	}
	
	public boolean removeAll(Iterable<AnyType> iterable) {
		Iterator<AnyType> it = iterable.iterator();
		int currentSize = size;
		while(it.hasNext()) {
			AnyType tmp = it.next();
			for(int i = 0; i < size(); i++) {
				if(tmp.equals(this.get(i).data)) {
					this.remove(i);
				}
			}
		}
		return currentSize != size;
//		LinkedList.removeAll();
	}
	
	public boolean lazyDeletion(int idx) {
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		countDelete++;
		getNode(idx).bDelete = true;
		if(countDelete == size/2) {
			for(int i = 0; i < size; i++) {
				if(getNode(i).bDelete == true) {
					remove(i);
					i--; //back one step cause the next element replace the remove one
				}
			}
			countDelete = 0;
			return true;
		}
		return false;
	}
	
/*默认为右边交换，不作边界处理*/
	public void switchCurrentAndRight(int idx) {
		Node x = get(idx);
		Node y = x.next;
		x.previous.next = y;
		y.next.previous = x;
		x.next = y.next;
		y.previous = x.previous;
		x.previous = y;
		y.next = x;
	}
//相邻  Node 不兼容，默认不相邻
	public void switchXY(int xIdx, int yIdx) {
		Node x = get(xIdx);
		Node y = get(yIdx);
		x.previous.next = y;
		y.next.previous = x;
		x.next.previous = y;
		y.previous.next = x;
		Node y_pre = y.previous;
		Node x_pre = x.previous;
		x.previous = y_pre;
		y.previous = x_pre;
		Node x_next = x.next;
		Node y_next = y.next;
		x.next = y_next;
		y.next = x_next;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private class Node { //hiding <AnyType>, just need set it into the main class
		AnyType data;
		Node previous;
		Node next;
		boolean bDelete = false;
		
		Node(AnyType date, Node previous, Node next) {
			this.data = date;
			this.previous = previous;
			this.next = next;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<AnyType> {

		Node t = head.next;
		
		public boolean hasNext() {
			return t != tail;
		}

		public AnyType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			AnyType at = t.data;
			t = t.next;
			return at;
		}
		
		public void remove() {
			MyLinkedList.this.remove(size()-1);
		}
		
	}
	
}
