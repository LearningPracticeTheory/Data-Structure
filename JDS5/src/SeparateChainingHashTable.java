import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {

	private static final int DEFAULT_TABLE_SIZE = 101;
	private static int size = 0;
	private List<AnyType> lists[] = null;
	
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
		whichList.add(x);
		if(++size > lists.length) {
			rehash();
		}
		return true;
	}
	
	public boolean contains(AnyType x) {
		List<AnyType> whichList = lists[myHash(x)];
		/*
		if(whichList == null) {
			return false;
		}
		*/
		return whichList.contains(x);
	}
	
	public boolean remove(AnyType x) {
		List<AnyType> whichList = lists[myHash(x)];
		/*
		if(whichList == null) { 
			return false;
		}
		*/
		if(whichList.contains(x)) {
			size--;
			return whichList.remove(x);
		}
		return false;
	}
	
	public void makeEmpty() {
		for(int i = 0; i < lists.length; i++) {
//			lists[i]  = null;
			lists[i].clear(); //not NULL;
		}
		size = 0;
	}
	
}
