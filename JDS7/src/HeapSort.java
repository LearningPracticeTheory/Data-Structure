
public class HeapSort<AnyType extends Comparable<AnyType>> {

	public static<AnyType extends Comparable<AnyType>> void sort0(AnyType array[]) {
		currentIndex = array.length-1;
		buildHeap(array);
		for(int i = 0; i < array.length-1; i++) { //last one is the minimum
			deleteMax(array);
		}
	}
	
	/*
	 * maxHeap, start from 0 which means without sentry on array[0];
	 */
	private static<AnyType extends Comparable<AnyType>> 
	void buildHeap(AnyType array[]) {
		for(int i = (currentIndex)/2; i >= 0; i--) {
			percolateDown(i, array);
		}
	}
	
	private static<AnyType extends Comparable<AnyType>> 
	void percolateDown(int index, AnyType array[]) {
		AnyType tmp = array[index];
		
		while(leftChild(index) <= currentIndex) {
			if(leftChild(index) < currentIndex) { //hasRight
				int greaterIndex = array[leftChild(index)].compareTo(array[leftChild(index)+1]) > 0 ? 
						leftChild(index) : leftChild(index)+1;
				if(tmp.compareTo(array[greaterIndex]) < 0) {
					array[index] = array[greaterIndex];
					index = greaterIndex;
				} else {
					break;
				}
			} else if(tmp.compareTo(array[leftChild(index)]) < 0) {
				array[index] = array[leftChild(index)];
				index = leftChild(index);
			} else {
				break;
			}
		}
		
		array[index] = tmp;
		
	}
	
	private static int currentIndex;
	
	private static<AnyType extends Comparable<AnyType>> 
	void deleteMax(AnyType array[]) {
		AnyType tmp = array[0];
		array[0] = array[currentIndex--];
		percolateDown(0, array);
		array[currentIndex+1] = tmp;
	}
	
	/*
	 * find left child from the index 
	 * element start from array[0];
	 */
	private static int leftChild(int index) {
		return index*2+1;
	}
	
/************************************************/
	
	public static<AnyType extends Comparable<AnyType>> 
	void sort1(AnyType array[]) {
		for(int i = array.length/2-1; i >= 0; i--) { //buildHeap
			percDown(array, i, array.length);
		}
		/*
		 * deleteMax optimization, which only swap & percDown N-1 times [array.length-1, 1]
		 * after that, the last element is already the minimum one
		 */
		for(int i = array.length-1; i > 0; i--) {
			swap(array, 0, i); //swap the max to the current size index
			percDown(array, 0, i);
		}
	}
	
	/*
	 * percolate optimization
	 * start always from 0, similar to BinaryHeap.percolateDown(hole);
	 */
	/*
	private static<AnyType extends Comparable<AnyType>>
	void percDown(AnyType array[], int start, int end) {
		int child = 0;
		for(; leftChild(start) < end; start = child) {
			child = leftChild(start);
			if(child + 1 < end && 
					array[child].compareTo(array[child+1]) < 0) { //left < right
				child++;
			}
			if(array[start].compareTo(array[child]) < 0) {
				swap(array, start, child);
			} else {
				break;
			}
		}
	}
	*/
	
	/*
	 * one-way assignment 
	 */
	private static<AnyType extends Comparable<AnyType>>
	void percDown(AnyType array[], int start, int end) {
		int child = 0;
		AnyType tmp = array[start];
		for(; leftChild(start) < end; start = child) {
			child = leftChild(start);
			if(child + 1 < end && 
					array[child].compareTo(array[child+1]) < 0) { //left < right
				child++;
			}
			if(array[start].compareTo(array[child]) < 0) {
				swap(array, start, child);
			} else {
				break;
			}
		}
		array[start] = tmp;
	}
	
	private static<AnyType extends Comparable<AnyType>> 
	void swap(AnyType array[], int index1, int index2) {
		AnyType tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
	
}
