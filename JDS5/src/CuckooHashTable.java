import java.util.Random;

public class CuckooHashTable<AnyType> {

	private static final int ALLOWED_REHASH = 1;
	private static final double MAX_LOAD = 0.4;
	private static final int DEFAULT_SIZE = 101;
	
	private int size = 0;
	private AnyType array[] = null;
	
	
	private HashFamily<? super AnyType> hashFunctions = null;
	private int numHashFunctions = 0;
	
	CuckooHashTable(HashFamily<? super AnyType> hf) {
		this(hf, DEFAULT_SIZE);
	}
	
	CuckooHashTable(HashFamily<? super AnyType> hf, int defaultSize) {
		allocateArray(defaultSize);
		clear();
		hashFunctions = hf;
		numHashFunctions = hf.getNumberOfFunctions();
	}
	
	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		array = (AnyType[]) new Object[Prime.nextPrime(arraySize)];
	}
	
	public void clear() {
		size = 0;
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	private int myHash(AnyType x, int which) { //int which decide use which hash Function
		int hashVal = hashFunctions.hash(x, which);
		hashVal %= array.length;
		if(hashVal < 0) {
			hashVal += array.length;
		}
		return hashVal;
	}
	
	private int findPos(AnyType x) {
		for(int i = 0; i < numHashFunctions; i++) { //use different hashFunctions to find position
			int pos = myHash(x, i);
			if(array[pos] != null && array[pos].equals(x)) {
				return pos;
			}
		}
		return -1; //Not found
	}
	
	public boolean contains(AnyType x) {
		return findPos(x) != -1;
	}
	
	public boolean remove(AnyType x) {
		int pos = findPos(x);
		if(pos != -1) {
			array[pos] = null;
			size--;
		}
		return pos != -1;
	}
	
	public boolean insert(AnyType x) {
		if(contains(x)) {
			return false;
		}
		if(size >= (int)(array.length*MAX_LOAD)) {
			expand();
		}
		return insertHelper(x);
	}

	private Random r = new Random();
	private static final int COUNT_LIMIT = 100; // ??? replace by the size of table ???
	
	private boolean insertHelper(AnyType x) { //always return true;
		
		int rehashTimes = 0;
		
		while(true) {
			
			for(int i = 0; i < COUNT_LIMIT; i++) {	
				int lastPos = -1;
				int pos = 0;
				
				for(int j = 0; j < numHashFunctions; j++) { //the number of hashFuctions
					pos = myHash(x, j); //for using different hash functions to find a null place 
					if(array[pos] == null) {
						array[pos] = x;
						size++;
						return true;
					}
				}
				
				/*None of the spots are available. Evict out a random one*/
				int t = 0;
				do { //if all hash functions could not found a null place, which means insert fail
					pos = myHash(x, r.nextInt(numHashFunctions)); //then random find a position
				} while(pos == lastPos && ++t < 5); //kick it out, replace by x
				
				AnyType tmp = array[lastPos = pos];
				array[pos] = x;
				x = tmp; //then reinsert the element which is been kick out on next loop
			}
			
			if(++rehashTimes > ALLOWED_REHASH) { //rehash more than once，then expand
				expand();
				rehashTimes = 0;
			} else {
				rehash(); //same table size, but use new hash functions
			}
			
		}
	}

	private void rehash() {
		hashFunctions.generateNewFunctions(); //???
		rehash(array.length);
	}
	
	private void rehash(int arraySize) {
		AnyType oldArray[] = array;
		allocateArray(Prime.nextPrime(arraySize)); 
		size = 0; //for reinsert
		for(int i = 0; i < oldArray.length; i++) {
			if(oldArray[i] != null) {
				insert(oldArray[i]);
			}
		}
	}

	private void expand() {
		rehash((int)(array.length/MAX_LOAD)); //expand to array.length/0.4
	}
	
}
