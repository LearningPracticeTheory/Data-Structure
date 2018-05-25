import java.util.Iterator;

public class MyArrayStack<AnyType> implements Iterable<AnyType>{

	private static final int initCapacity = 10;
	int size = 0;
	AnyType at[];
	
	MyArrayStack() {
		ensureCapacity(initCapacity);
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		AnyType t[] = (AnyType[]) new Object[newCapacity];
		for(int i = 0; i < size(); i++) {
			t[i] = at[i];
		}
		at = t;
	}
	
	public void push(AnyType x) {
		if(at.length == size()) {
			ensureCapacity(size()*2);
		}
		at[size++] = x;
	}
	
	public AnyType peek() {
		if(size() <= 0) {
			throw new IndexOutOfBoundsException();
		}
		return at[size-1];
	}
	
	public AnyType pop() {
		if(size() < at.length/4) {
			ensureCapacity(size()/2);
		}
		return remove();
	}
	
	public AnyType remove() {
		AnyType t = peek(); //peek size-1;
		at[--size] = null;
		return t;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		return new ArrayStackIterator();
	}
	
	private class ArrayStackIterator implements Iterator<AnyType> {

		int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			return at[index++];
		}

		@Override
		public void remove() {
			MyArrayStack.this.remove();
		}

	}
	
}
