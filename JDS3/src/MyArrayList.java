import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<AnyType> implements Iterable<AnyType>{
	
	private static final int DEFAULT_SIZE = 10;
	private AnyType a[];
	private int size = 0; 
	
	int deletedCount = 0;
	boolean deletedArray[] = null;
		
	MyArrayList() {
		ensureCapacity(DEFAULT_SIZE);
	}
/*指定大小扩容缩容*/
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		if(newCapacity <= size) {
			return;
		}
		AnyType t[] = (AnyType[]) new Object[newCapacity]; //Unchecked from Object to AnyType
		boolean b[] = new boolean[newCapacity];
		for(int i = 0; i < size; i++) {
			t[i] = a[i];
			b[i] = deletedArray[i];
		}
		a = t;
		deletedArray = b;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
//must add <AnyTpye> on the title of class while using in the method as argument
	public boolean add(AnyType at) { 
//		a[size++] = at;//OutOfBounds > 10, that's why need calling overload method
		add(size(), at); //size == index+1;
		return true;
	}
	
	public void add(int idx, AnyType at) { //more like insert
		if(a.length == size()) {
			ensureCapacity(size() * 2);
		}
		if(idx > size || idx < 0) { //idx == size, default add at last one
			throw new IndexOutOfBoundsException();
		}
		for(int i = size; i > idx; i--) { //move to next position
			a[i] = a[i-1];
		}
		a[idx] = at;
		size++;
	}
	
	public boolean addAll(Collection<? extends AnyType> c) {
		Iterator<? extends AnyType> it = c.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
		return c.size() != 0;
	}
	
	public AnyType set(int idx, AnyType at) { //change the value of idx
		if(idx >= size || idx < 0) {
			throw new ArrayIndexOutOfBoundsException(); //new means reference
		} //cannot throw method()/construct();
		AnyType tmp = a[idx];
		a[idx] = at;
		return tmp;
	}
	
	public AnyType get(int idx) {
		if(idx >= size || idx < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return a[idx];
	}
	
	public void remove() { //remove call remove(index); like add calling add(index);
		remove(size-1);
	}

	public AnyType remove(int idx) { //will create null elements
		if(size == 0) {
			return null;
		} else if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		} else if(size <= a.length/4) {
			ensureCapacity(a.length/2);
		}
		AnyType t = a[idx];
		for(int i = idx; i < size - 1; i++) {
			a[i] = a[i+1];
		}
		size--;
		return t;
	}

	public boolean lazyDeletion(int idx) {
		if(idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		deletedArray[idx] = true;
		deletedCount++;
		if(deletedCount == size/2) {
			lazyRemove();
			deletedCount = 0;
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void lazyRemove() {
		AnyType tmp[] = (AnyType[]) new Object[size];
		int count = 0;
		for(int i = 0; i < size; i++) {
			if(deletedArray[i] != true) {
				tmp[count++] = a[i];
			}
		}
		a = tmp;
		size -= deletedCount;
		if(size <= a.length/4) {
			ensureCapacity(a.length/2);
		}
	}
	
	public void clear() {
		size = 0;
	}
	
	public int size() {
		return size;
	}

	public Iterator<AnyType> iterator() {
		return new ArrayIterator();
	}
	
	public ListIterator<AnyType> listIterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayIterator implements Iterator<AnyType> {

		int index = 0;
		int lastPre = -1;
		
		public boolean hasNext() {
			return index < size;
		}

		public AnyType next() { 
			if(!hasNext()) { //like index out of bounds when not use while to loop
				throw new java.util.NoSuchElementException();
			} //use next() when out of bounds
			lastPre++;
			return a[index++]; //like push()
		}

		public void remove() { //Class.this point to itself as reference
			MyArrayList.this.remove(--index); //like pop();
		}
		
	}
	
	private class ArrayListIterator extends ArrayIterator 
					implements ListIterator<AnyType> {

		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public AnyType previous() {
			if(!hasPrevious()) { 
				throw new java.util.NoSuchElementException();
			}
			lastPre--;
			return a[--index]; //like pop();
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index-1;
		}

		@Override
		public void set(AnyType e) {
			MyArrayList.this.set(lastPre, e);
		}

		@Override
		public void add(AnyType e) {
			MyArrayList.this.add(index++, e); //like insert();
		}
		
	}
	
}
