import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class SeparateChainingHashTable<AnyType> {

	private static final int DEFAULT_TABLE_SIZE = 11;
	private static int size = 0;
	private static int collisionCount = 0; 
	private List<AnyType> lists[] = null;
	private AnyType array[] = null;
	
	SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	SeparateChainingHashTable(int defaultTableSize) {
		lists = new LinkedList[Prime.nextPrime(defaultTableSize)];
/*The hungry type*/
		for(int i = 0; i < lists.length; i++) { //initialized every elements of lists
			lists[i] = new LinkedList<>(); //and use clear at the same time when necessary
		}
	}
	
	private int myHash(AnyType x) { //index actually
		int hashVal = x.hashCode();
		hashVal %= lists.length;
		if(hashVal < 0) {
			hashVal += lists.length;
		}
		return hashVal;
	}
/*Error: new table size -> myHash change -> index change*/
	/*
	@SuppressWarnings("unchecked")
	private void rehash() { //expand capacity
		List<AnyType> oldLists[] = lists;
		lists = new LinkedList[Prime.nextPrime(lists.length*2)];
		for(int i = 0; i < lists.length; i++) {
			lists[i] = new LinkedList<>();
		}
		for(int i = 0; i < oldLists.length; i++) {
			if(!oldLists[i].isEmpty()) {
				lists[i] = oldLists[i];
			}
		}
	}
	*/
/*Must reinsert*/
	@SuppressWarnings("unchecked")
	private void rehash() { //expand capacity
		List<AnyType> oldLists[] = lists;
		lists = new LinkedList[Prime.nextPrime(lists.length*2)];
		for(int i = 0; i < lists.length; i++) {
			lists[i] = new LinkedList<>();
		}
		size = 0;
		for(int i = 0; i < oldLists.length; i++) {
			for(AnyType item : oldLists[i]) {
				insert(item);
			}
		}
	}
	
	public boolean insert(AnyType x) {
		if(contains(x)) {
			return false;
		}
		List<AnyType> whichList = lists[myHash(x)];
		/*
		if(whichList == null) { //Never NULL, but Empty sometimes
			whichList = new LinkedList<>(); //cause use clear();
		}
		*/
		if(!whichList.isEmpty()) {
			collisionCount++;
		}
		whichList.add(x);
		if(++size > lists.length) {
			rehash();
		}
		return true;
	}
	
	public boolean contains(AnyType x) {
		List<AnyType> whichList = lists[myHash(x)];
		return whichList.contains(x);
	}
	
	public boolean remove(AnyType x) {
		List<AnyType> whichList = lists[myHash(x)];
		if(whichList.contains(x)) {
			size--;
			return whichList.remove(x);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public AnyType[] toArray() {
//		AnyType array[] = new AnyType[size]; //cannot create an AnyType array
		List<AnyType> array = new LinkedList<>();
		for(int i = 0; i < lists.length; i++) {
			for(AnyType item : lists[i]) {
				if(item != null) {
					array.add(item);
				}
			}
		}
		Arrays.sort(array.toArray());
		return (AnyType[])array.toArray();
	}
	
	public void makeEmpty() {
		for(int i = 0; i < lists.length; i++) {
//			lists[i]  = null;
			lists[i].clear(); //not NULL;
		}
		size = 0;
		collisionCount = 0;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		array = toArray();
		String s = "[";
		for(int i = 0; i < array.length; i++) {
			if(i == array.length-1) {
				s += array[i];
			} else {
				s += array[i] + ", ";
			}
		}
		s += "]";
		return s;
	}
	
	public Iterator<AnyType> iterator() {
		array = toArray(); //sorted
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<AnyType> {

		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < array.length;
		}

		@Override
		public AnyType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[index++];
		}
		
	}

	public int getCollisionCount() {
		return collisionCount;
	}
	
}
