import java.util.NoSuchElementException;

public class BinaryHeap<AnyType extends Comparable<AnyType>> {

	private static final int DEFAULT_SIZE = 10; 
	private int size = 0;
	private AnyType array[] = null;
	
	BinaryHeap() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	BinaryHeap(int capacity) {
//		array = (AnyType[]) new Object[capacity]; //ClassCastException: 
												//Object cannot be cast to Comparable;
		array = (AnyType[]) new Comparable[capacity]; 
	}
	
	/*
	BinaryHeap(AnyType array[]) { //total O(N)
		this.array = array;
		buildHeapImplByMyself();
	}
	
	public void buildHeapImplByMyself() { //slow
		for(int i = 0; i < array.length; i++) { O(N) * O(logN) -> O(N);
			insert(array[i]);
		}
	}
	*/
	
	@SuppressWarnings("unchecked") //Run in linear time -> O(N*logN);
	BinaryHeap(AnyType items[]) { //better than array = items; but need expand the capacity
		this.array = (AnyType[]) new Comparable[(items.length+2)*11/10];
		size = items.length;
		int i = 0;
		for(AnyType item : items) {
			array[++i] = item; //start from array[1];
		}
		buildHeap();
	}
	
	public void insert(AnyType x) { //array[0] as sentry, which like a tmpElement
		if(size == array.length-1) {
			ensureCapacity(array.length*2+1);
		}
		
		int hole = ++size; //compare < 0, avoid index always be 0 if use <= 0
		for(array[0] = x; x.compareTo(array[hole/2]) < 0; hole /= 2) {
			array[hole] = array[hole/2];
		}
		
		array[hole] = x;
	}
	
	public AnyType deleteMinImplByMyself() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		AnyType min = array[1];
		int hole = 1;
		for(int i = hole*2; i <= size; i *= 2) { //default dump to left Node/position
			if(i+1 <= size) { //has right element
				if(array[i].compareTo(array[i+1]) > 0) { //left > right
					i++; //use right element to move up, which fill the empty Node/position (array[hole])
				}
			}
			array[hole] = array[i]; //smallest between left & right;
			hole = i; //holeElement move down to next level which switch with moving up element
		}
		array[hole] = array[size]; //last hole fill by last element
		size--;
		return min;
	}
	
/*--------------------------------------*/
	
	public AnyType deleteMin() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		AnyType min = array[1];
		array[1] = array[size--];
		percolateDown(1);
		return min;
	}
	
	private void percolateDown(int hole) { 
		int child;
		AnyType tmp  = array[hole]; //backup array[1], which is the last element of Heap
		for(; hole*2 <= size; hole = child) {
			child = hole*2; //default left child
			if(child != size && array[child].compareTo(array[child+1]) > 0) { //right side
				child++;
			}
			if(array[child].compareTo(tmp) < 0) {
				array[hole] = array[child];
			} else { //case like childNode >= parentNode
				break;
			}
		}
		array[hole] = tmp;
	}
	
/*--------------------------------------*/
	
	public void buildHeap() { //O(logN);
		for(int i = size/2; i > 0; i--) { //percolate from last to first;
			percolateDown(i); //which are not leafNodes, leaves are been percolated
		}
	}
	
	public AnyType findMin() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[1];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for(int i = 1; i <= size; i++) {
			array[i] = null;
		}
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		AnyType tmpArray[] = array;
		array = (AnyType[]) new Object[capacity];
		int index = 0;
		for(int i = 0; i < tmpArray.length; i++) {
			if(tmpArray[i] != null) {
				array[++index] = tmpArray[i];
			}
		}
	}
	
}
