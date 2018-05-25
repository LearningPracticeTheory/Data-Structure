import java.util.ConcurrentModificationException;
import java.util.Iterator;

/*减少扩容缩容的次数，重复利用空间*/
public class MyArrayQueueOptimize<AnyType> implements Iterable<AnyType>{
	
	private int size = 0;
	private int addIdx = 0, pollIdx = 0;
	private static final int INIT_CAPACITY = 10;
	private static int modCount = 0; //data modify count
	private AnyType array[] = null;
	
	MyArrayQueueOptimize() {
		initCapacity();
	}
	
	@SuppressWarnings("unchecked")
	private void initCapacity() {
		array = (AnyType[]) new Object[INIT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		AnyType tmp[] = (AnyType[]) new Object[capacity];
		int count = 0;
		for(int i = 0; i < size; i++) { //resort
			if(pollIdx != size) { //size, not length; when shrink capacity
				tmp[i] = array[pollIdx++]; //include case like: pollIdx == 0 || addIdx == size-1
			} else { //from 0 to addIdx
				tmp[i] = array[count++];
			}
		}
		pollIdx = 0;
		addIdx = size; //at the next add index which value is null
		array = tmp;
	}
	
	public boolean add(AnyType at) { //add then move to next index
		if(size < array.length) {
			if(addIdx == array.length) { //index out of bound
				addIdx = 0;
			}
		} else {
			ensureCapacity(size*2);
		}
		array[addIdx++] = at;
		size++;
		modCount++;
		return true;
	}
	
	public AnyType poll() { //poll and move to next index
		if(size == INIT_CAPACITY/2 && array.length != INIT_CAPACITY) { //half init_capacity
			ensureCapacity(INIT_CAPACITY);
		} else if(size != 0) {
			if(pollIdx == array.length) {
				pollIdx = 0;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
		size--;
		modCount++;
		return array[pollIdx++];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		initCapacity();
		modCount++;
	}
	
	public AnyType remove() {
		modCount++;
		return poll();
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new QueueOptmizedIterator();
	}
	
	private class QueueOptmizedIterator implements Iterator<AnyType> {

		int count = 0;
		int index = pollIdx;
		int expectModCount = modCount ;
		
		@Override
		public boolean hasNext() {
			if(expectModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return count != size;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			} else if(expectModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(index == size) {
				index = 0;
			}
			count++;
			return array[index++];
		}

		@Override
		public void remove() { //remove will influence the iterator when traversal
			MyArrayQueueOptimize.this.remove();
		}
		
	}
	
}