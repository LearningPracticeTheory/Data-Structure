import java.util.NoSuchElementException;

/*
 * L: Local
 * P: Parent
 * GP: GrandParent
 */
public class MinMaxHeap<AnyType extends Comparable<AnyType>> {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private AnyType array[] = null;

	MinMaxHeap() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public MinMaxHeap(int defaultSize) {
		array = (AnyType[]) new Comparable[defaultSize];
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public MinMaxHeap(AnyType array[]) {
		this.array = (AnyType[]) new Comparable[(array.length+2)*11/10];
		for(int i = 0; i < array.length; i++) {
			this.array[i+1] = array[i]; //array[0] is not an actual element
		}
		size = array.length;
		buildHeap();
	}
	
	public void insert(AnyType x) {
		if(++size == array.length-1) {
			ensureCapacity(array.length*2+1);
		}
		int hole = size;
		array[0] = x; //sentry
		percolateUp(array[0], hole);
	}
	
	/**
	 * percolate up method for dealing with even depth case
	 * @param x the element which for percolate up
	 * @param hole the hole position of array OR the position where x belong
	 */
	private void evenDepthPercolateUp(AnyType x, int hole) {
		/*
		 * true == greater
		 * false == smaller
		 */
		boolean flag = false;
		
		/*
		 * greater or smaller than P
		 */
		if(hole / 2 > 0) { 
			if(x.compareTo(array[hole/2]) > 0) { //greater than P
				array[hole] = array[hole/2];
				hole /= 2; //hole jumps to P position, which is on odd level
				flag = true;
			} else {
				flag = false; //stay at L position
			}
		}
		
		/*
		 * compare with GP & move it when necessary
		 */
		while(hole / 4 > 0) {
			if(flag) { //odd level
				if(x.compareTo(array[hole/4]) > 0) { //greater than GP
					array[hole] = array[hole/4];
				} else {
					break;
				}
			} else { //even level
				if(x.compareTo(array[hole/4]) < 0) {
					array[hole] = array[hole/4];
				} else {
					break;
				}
			}
			hole /= 4; //move to GP position
		}
		
		array[hole] = x; //the element x assign to the hole position finally
	}

	/*
	 * similar to the evenDepthPercolateUp
	 */
	private void oddDepthPercolateUp(AnyType x, int hole) {
		boolean flag = false;
		
		if(hole / 2 > 0) { 
			if(x.compareTo(array[hole/2]) < 0) { //only compare symbol is different from even's
				array[hole] = array[hole/2];
				hole /= 2; //jump to even level
				flag = true;
			} else { 
				flag = false; 
			}
		}
		
		while(hole / 4 > 0) {
			if(flag) { //even level
				if(x.compareTo(array[hole/4]) < 0) { //same as up
					array[hole] = array[hole/4];
				} else {
					break;
				}
			} else { //odd level
				if(x.compareTo(array[hole/4]) > 0) { //same as up
					array[hole] = array[hole/4];
				} else {
					break;
				}
			}
			hole /= 4;
		}
		
		array[hole] = x;
	}

	private void percolateUp(AnyType x, int hole) {
		int depth = depth(hole);
		if(depth % 2 == 0) {
			evenDepthPercolateUp(x, hole);
		} else {
			oddDepthPercolateUp(x, hole);
		}
	}
	
	public AnyType deleteMin() {
		if(size <= array.length/4) {
			ensureCapacity(array.length/2);
		}
		int minIndex = findMinIndex(1);
		AnyType min = array[minIndex];
		array[1] = array[size--];
		minPercolateDown(1);
		return min;
	}
	
	/**
	 * percolate down the element on index
	 * @param index percolate down from where it start
	 */
	private void minPercolateDown(int index) {
		AnyType theElement = array[index];
		int leftMinIndex = 0, rightMinIndex = 0;
		
		while(index * 2 <= size) { //has child
			leftMinIndex = findMinIndex(index*2);
			
			if(index * 2 < size) { //has right child
				rightMinIndex = findMinIndex(index*2+1);
			} else {
				rightMinIndex = 0;
			}
			
			if(rightMinIndex == 0) { //only left child
				if(theElement.compareTo(array[leftMinIndex]) > 0) { //kid smaller than theElement
					array[index] = array[leftMinIndex];
					index = leftMinIndex;
				} else {
					break;
				}
			} else { //both left & right
				int nextIndex = array[leftMinIndex].compareTo(array[rightMinIndex]) < 0 ? 
						leftMinIndex : rightMinIndex; //default right
				if(theElement.compareTo(array[nextIndex]) > 0) { //kid smaller than theElement
					array[index] = array[nextIndex];
					index = nextIndex;
				} else {
					break;
				}
			}
		}
		
		array[index] = theElement;
		
		/*
		 * special case: allLeft < allRight
		 * deleteMin -> minPercolateDown will destroy the balance of MinMaxHeap
		 * that's why we need percolateUp Test for theElement
		 */
		percolateUp(theElement, index);
		
	}

	public AnyType findMin() {
		return array[findMinIndex(1)];
	}
	
	/**
	 * find minimum index from start, which start from the heap or subheap's root
	 * @param start find minimum index where it start
	 * @return int the index of minimum element
	 */
	private int findMinIndex(int start) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		if(start*2 <= size) { //has kid
			if(array[start].compareTo(array[start*2]) < 0) { /*for minHeap case*/
				/*
				 * if (startNode < leftNode) --so that--> (startNode < rightNode) (if exist)
				 * which means left child as representative
				 */
				return start; //return the root's index == 1;
			} else if(start*2 < size) { /*for maxHeap case*/ 
				/*
				 * has right child | find minimum from maxHeap,  
				 */
				return array[start*2].compareTo(array[start*2+1]) < 0 ? 
						start*2 : start*2+1; 
			} else { 
				/*
				 * only left child
				 */
				return start*2;
			}
		}
		return start; //NO kids: only root & node without kids
	}
	
	/*
	 * similar to the deleteMin
	 */
	public AnyType deleteMax() {
		if(size <= array.length/4) {
			ensureCapacity(array.length/2);
		}
		int maxIndex = findMaxIndex(1); //max maybe the last element
		AnyType max = array[maxIndex];
		array[maxIndex] = array[size--];
		maxPercolateDown(maxIndex);
		return max;
	}
	
	/*
	 * similar to the minPercolateDown
	 */
	private void maxPercolateDown(int index) {
		AnyType theElement = array[index];
		int leftMaxIndex = 0, rightMaxIndex = 0;
		
		while(index * 2 <= size) {
			leftMaxIndex = findMaxIndex(index*2);
			
			if(index * 2 < size) { 
				rightMaxIndex = findMaxIndex(index*2+1);
			} else {
				rightMaxIndex = 0;
			}
			
			if(rightMaxIndex == 0) { 
				if(theElement.compareTo(array[leftMaxIndex]) < 0) { 
					array[index] = array[leftMaxIndex];
					index = leftMaxIndex;
				} else {
					break;
				}
			} else { 
				int nextIndex = array[leftMaxIndex].compareTo(array[rightMaxIndex]) > 0 ? 
						leftMaxIndex : rightMaxIndex;
				if(theElement.compareTo(array[nextIndex]) < 0) {
					array[index] = array[nextIndex];
					index = nextIndex;
				} else {
					break;
				}
			}
			
		}
		
		array[index] = theElement;
		percolateUp(theElement, index);
	}

	/*
	 * similar to the findMinIndex
	 */
	private int findMaxIndex(int start) {
		if(isEmpty()) { 
			throw new NoSuchElementException();
		}
		if(start*2 <= size) {
			if(array[start].compareTo(array[start*2]) > 0) { //maxHeap
				return start;
			} else if(start*2 < size) { //has right child | minHeap
				return array[start*2].compareTo(array[start*2+1]) > 0 ? 
						start*2 : start*2+1; 
			} else { 
				return start*2;
			}
		}
		return start;
	}
	
	public AnyType findMax() {
		if(isEmpty()) { 
			throw new NoSuchElementException();
		} else if(size <= 2) {
			return array[size];
		} else {
			return array[2].compareTo(array[3]) > 0 ? array[2] : array[3];
		}
	}
	
	/*
	 * percolateUp from top to tail
	 * percolateDown ERROR
	 */
 	private void buildHeap() {
		for(int i = 1; i <= size; i++) {
			percolateUp(array[i], i);
		}
	}
	
	public void merge(MinMaxHeap<AnyType> otherHeap) {
		if(this.size == 0) {
			array = otherHeap.array;
			return;
		} else if(otherHeap.size == 0) {
			return;
		}
		AnyType otherArray[] = otherHeap.array;
		int mergeSize = this.size + otherHeap.size;
		ensureCapacity((mergeSize+2)*11/10);
		for(int i = 0; i < otherHeap.size; i++) {
			array[++size] = otherArray[i+1];
		}
		buildHeap();
		otherHeap.clear();
	}
	
	/**
	 * 
	 * @param index the position of array
	 * @return int the index belongs to which depth
	 */
	private int depth(int index) {
		int depth = 0;
		while(((1 << depth) - 1) < index) {
			depth++;
		}
		return depth-1;
	}	
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		AnyType old[] = array;
		array = (AnyType[]) new Comparable[capacity];
		for(int i = 0; i <= size; i++) { //delete, but NO set null on array, just size--;
			array[i] = old[i];
		}
	}
	
	public void clear() {
		size = 0;
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
}
