
public class QuadraticProbingHashTable<AnyType> {

	private static final int DEFAULT_TABLE_SIZE = 11;
	private static int size = 0;
	private static int collisionCount = 0;
	private HashEntry<AnyType> entrys[] = null;
	
	QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}
	
/*The lazy type*/	
	@SuppressWarnings("unchecked")
	QuadraticProbingHashTable(int defaultTableSize) {
		entrys = new HashEntry[Prime.nextPrime(defaultTableSize)];
		makeEmpty();
	}
	
	private int myHash(AnyType x) { //same as SeparateChainingHashTable.myHash(); 
		int hashVal = x.hashCode();
		hashVal %= entrys.length;
		if(hashVal < 0) {
			hashVal += entrys.length;
		}
		return hashVal;
	}
	
	private int findPos(AnyType x) { //Finding the exactly position
		int offSet = 1; //1, 3, 5...
		int index = myHash(x);
		while(entrys[index] != null && !entrys[index].data.equals(x)) {
			index += offSet;
			offSet += 2;
<<<<<<< HEAD
			if(offSet > size) { //offSet too bigger, index more than twice the size, pull back
				offSet = 1;
			}
=======
>>>>>>> 6e43e60f896cc2ee6f0dffa0181ce40509bef8e7
			collisionCount++;
			if(index >= entrys.length) { //must >= !!! debug
				index -= entrys.length; //pull back to the range of array
			}
		}
		return index;
	}
	
	private boolean isActive(int currentPos) {
		return entrys[currentPos] != null && entrys[currentPos].isActive;
	}
	
	@SuppressWarnings("unchecked")
<<<<<<< HEAD
	private void rehash(int capacity) { //expand capacity
=======
	private void rehash() { //expand capacity
>>>>>>> 6e43e60f896cc2ee6f0dffa0181ce40509bef8e7
		HashEntry<AnyType> oldEntrys[] = entrys;
		entrys = new HashEntry[Prime.nextPrime(capacity)];
		size = 0; //insert size++; new Entry with new size
		for(int i = 0; i < oldEntrys.length; i++) {
			if(oldEntrys[i] != null && oldEntrys[i].isActive) {
				insert(oldEntrys[i].data);
			}
		}
	}
	
	public boolean insert(AnyType x) {
		int index = findPos(x);
		if(isActive(index)) { //existed
			return false;
		}
		entrys[index] = new HashEntry<>(x);
		if(++size > entrys.length/2) {
			rehash(entrys.length*2);
		}
		return true;
	}
	
	public boolean contains(AnyType x) {
		int index = findPos(x);
		return isActive(index);
	}
	
	public boolean remove(AnyType x) {
		if(contains(x)) { //findPos + isActive
			entrys[findPos(x)].isActive = false;
			if(--size <= entrys.length/4) {
				rehash(entrys.length/2);
			}
			return true;
		}
		return false;
	}
	
	public void makeEmpty() {
		size = 0;
		collisionCount = 0;
		for(int i = 0; i < entrys.length; i++) {
			entrys[i] = null;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

	private static class HashEntry<AnyType> { //like Node
		boolean isActive;
		AnyType data;
		
		HashEntry(AnyType data) {
			this(data, true);
		}

		HashEntry(AnyType data, boolean isActive) {
			this.data = data;
			this.isActive = isActive;
		}
	}

	public int getCollisionCount() {
		return collisionCount;
	}
	
}
