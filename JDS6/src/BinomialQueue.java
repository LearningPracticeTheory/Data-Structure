import java.util.NoSuchElementException;

public class BinomialQueue<AnyType extends Comparable<AnyType>> {
	
	private static final int DEFAULT_SIZE = 1;
	private int size = 0;
	private HeapNode<AnyType> theTrees[] = null; //tree's roots
	
	@SuppressWarnings("unchecked")
	BinomialQueue() {
		theTrees = new HeapNode[DEFAULT_SIZE];
		clear();
	}
	
	@SuppressWarnings("unchecked")
	BinomialQueue(AnyType x) {
		theTrees = new HeapNode[1];
		theTrees[0] = new HeapNode<AnyType>(x);
		size = 1;
	}
	
	/*
	@SuppressWarnings("unchecked")
	private void expandTrees(int theNumOfTrees) {
		HeapNode<AnyType> oldTrees[] = theTrees;  
		theTrees = new HeapNode[theNumOfTrees];
		for(int i = 0; i < oldTrees.length; i++) { 
			theTrees[i] = oldTrees[i];
		}
		for(int i = oldTrees.length; i < theTrees.length; i++) { 
			theTrees[i] = null;
		}
	}
	*/
	
	@SuppressWarnings("unchecked")
	private void expandTrees(int newNumOfTrees) { //even if newNumOfTrees == 0;
		HeapNode<AnyType> oldTrees[] = theTrees;
		int oldNumOfTrees = oldTrees.length;
		theTrees = new HeapNode[newNumOfTrees];
		for(int i = 0; i < Math.min(oldNumOfTrees, newNumOfTrees); i++) {
			theTrees[i] = oldTrees[i];
		}
		for(int i = oldNumOfTrees; i < newNumOfTrees; i++) {
			theTrees[i] = null; //execute when oldNumOfTrees < newNumOfTrees 
		}
	}
	
	private int capacity() { //max capacity
		return (1 << theTrees.length) - 1; //1, 3, 7... which is the full value of binary number
										//first number is operate, second is the step of moving   
	}
	
	public void merge(BinomialQueue<AnyType> bq) {
		if(this == bq) {
			return;
		}
		
		size += bq.size;
		
		if(size > capacity()) {
			int maxCapacity = Math.max(this.theTrees.length, bq.theTrees.length);
			expandTrees(maxCapacity+1);
		}
		
		HeapNode<AnyType> carry = null;
		for(int i = 0, currentSize = 1; currentSize <= size; i++, currentSize *= 2) {
			HeapNode<AnyType> hn1 = theTrees[i];
			HeapNode<AnyType> hn2 = i < bq.theTrees.length ? bq.theTrees[i] : null; //not size
			
			int switchCase = 0;
			switchCase += hn1 == null ? 0 : 1;
			switchCase += hn2 == null ? 0 : 2;
			switchCase += carry == null ? 0 : 4;
			
			switch(switchCase) {
			case 0: //None
			case 1: //only this
				break;
			case 2: //only hn2, next loop on case 1
				theTrees[i] = hn2;
				bq.theTrees[i] = null;
				break;
			case 4: //only carry
				theTrees[i] = carry; //after combine trees -> carry, which will move to next position
				carry = null; //after move position, the node should be delete;
				break;
			case 3: //hn1 & hn2
				carry = combineTrees(hn1, hn2); //combine nodes should be KIA too
				theTrees[i] = bq.theTrees[i] = null;
				break;
			case 5: //hn1 & carry
				carry = combineTrees(hn1, carry);
				theTrees[i] = null;
				break;
			case 6: //hn2 & carry
				carry = combineTrees(hn2, carry);
				bq.theTrees[i] = null;
				break;
			case 7: //All three, which with same height;
				theTrees[i] = carry; //last time carryNode replace hn1's position
				carry = combineTrees(hn1, hn2); //combine hn1 & hn2 -> next position
				bq.theTrees[i] = null;
				break;
			}
		}
		
		for(int i = 0; i < bq.theTrees.length; i++) {
			bq.theTrees[i] = null;
		}
		bq.size = 0;
		
	}
	
	private HeapNode<AnyType> combineTrees(HeapNode<AnyType> hn1, HeapNode<AnyType> hn2) {
		if(hn1.element.compareTo(hn2.element) > 0) { //hn1 > hn2;
			return combineTrees(hn2, hn1);
		}
		hn2.nextSibling = hn1.leftChild;
		hn1.leftChild = hn2;
		return hn1;
	}
	
	public void insert(AnyType x) {
		merge(new BinomialQueue<>(x));
	}
	
	public AnyType deleteMin() {
		int minIndex = findMinIndex();
		AnyType minElement = theTrees[minIndex].element;
		HeapNode<AnyType> deleteTree = theTrees[minIndex].leftChild;
		BinomialQueue<AnyType> bq = new BinomialQueue<>();
		bq.expandTrees(minIndex); //include special case like minElement at theTrees[0];
		bq.size = (1 << minIndex) - 1;
		for(int i = minIndex-1; i >= 0; i--) {
			bq.theTrees[i] = deleteTree;
			deleteTree = bq.theTrees[i].nextSibling;
			bq.theTrees[i].nextSibling = null;
		}
		this.theTrees[minIndex] = null;
		size -= bq.size + 1;
		merge(bq); //size += bq.size;
		return minElement;
	}
	
	public AnyType findMin() {
		int minIndex = findMinIndex();
		return theTrees[minIndex].element;
	}
	
	/*
	private int findMinIndex() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		AnyType minElement = null;
		int minIndex = 0;
		for(; theTrees[minIndex] == null; minIndex++) { //loop until !NULL
			;
		}
		minElement = theTrees[minIndex].element;
		for(int i = minIndex; i < theTrees.length; i++) {
			if(theTrees[i] != null && theTrees[i].element.compareTo(minElement) < 0) {
				minIndex = i;
				minElement = theTrees[minIndex].element;
			}
		}
		return minIndex;
	}
	*/
	
	/*
	 * Optimize without use minElement
	 */
	private int findMinIndex() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int i = 0;
		int minIndex;
		for(; theTrees[i] == null; i++) { //loop until !NULL
			;
		}
		for(minIndex = i; i < theTrees.length; i++) {
			if(theTrees[i] != null && 
					theTrees[i].element.compareTo(theTrees[minIndex].element) < 0) {
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for(int i = 0; i < theTrees.length; i++) {
			theTrees[i] = null;
		}
		size = 0;
	}
	
	private static class HeapNode<AnyType> {
		AnyType element;
		HeapNode<AnyType> leftChild;
		HeapNode<AnyType> nextSibling;
		
		HeapNode(AnyType element) {
			this(element, null, null);
		}
		
		HeapNode(AnyType element, HeapNode<AnyType> leftChild, HeapNode<AnyType> nextSibling) {
			this.element = element;
			this.leftChild = leftChild;
			this.nextSibling = nextSibling;
		}
	}
	
}
