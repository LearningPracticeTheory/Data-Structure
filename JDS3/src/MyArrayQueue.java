import java.util.Iterator;

public class MyArrayQueue<AnyType> implements Iterable<AnyType> {
	private int size = 0;
	private int addIdx = 0, pollIdx = 0;
	private AnyType array[];
	
	MyArrayQueue() {
		initCapacity();
	}
	
	@SuppressWarnings("unchecked")
	private void initCapacity() {
		array = (AnyType[]) new Object[1];
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) { //The size of array always >= 1
		AnyType tmp[] = (AnyType[]) new Object[capacity];
		for(int i = 0; i < array.length; i++) { //cannot use i < size();
			if(array[i] != null) {
				tmp[i] = array[i];
			}
		}
		array = tmp;
		pollIdx = 0; 
		if(size == 0) {
			addIdx = 0;
		} else {
			addIdx = size-1;
		}
	}
	
	public boolean add(AnyType at) {
		if(size == 0) { //case like: at the beginning of initialized && poll back to 0
			ensureCapacity(1); //size == 0; length == 1; 
		} else if(size == array.length) { 
			ensureCapacity(size*2); //full
		} else if(addIdx == array.length-1) { //!full but addIdx at bound
			ensureCapacity(size); //size default > 0
		}
		if(size == 0) {
			array[addIdx] = at; //both: size == 0 || one element --> addIdx == 0
		} else { //size > 0
			array[++addIdx] = at; 
		}
		size++;
		return true;
	}
	
	public AnyType poll() { //bounds problem
		if(size <= array.length/4) { //case include size == 0, but will throw Exception
			ensureCapacity(array.length/2);
		}
		if(pollIdx <= addIdx && size != 0) {
			AnyType tmp = array[pollIdx++]; //poll && move
			array[pollIdx-1] = null;
			size--;
			return tmp;
		} else { //size == 0 || opllIdx > addIdx
			throw new IndexOutOfBoundsException(); //NoSuchElementException();
		}
	}
	
	public AnyType peek(int idx) { //peek for iterator
		if(idx >= pollIdx && idx <= addIdx && size != 0) { //use '||' much more better
			return array[idx];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public AnyType remove() { //poll head
		return poll();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() { //KIA all element
		size = 0;
//		array = null; //ensureCapacity need array.length;
		initCapacity();
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<AnyType> {

		public int idx = 0;
		
		@Override
		public boolean hasNext() {
			return idx < size;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			} else {
				return array[pollIdx+idx++];
//				return peek(pollIdx+idx++);
			}
		}

		@Override
		public void remove() {
			MyArrayQueue.this.remove();
		}
		
	}

}
