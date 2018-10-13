import java.util.NoSuchElementException;

public class LeftistHeap<AnyType extends Comparable<AnyType>> {

	private HeapNode<AnyType> root = null;
	
	public void merge(LeftistHeap<AnyType> lh) { //Merge lh into the priority queue
		if(this == lh) { //lh must be different from this
			return; 
		}
		root = merge(root, lh.root);
		lh.root = null; //KIA the LeftistHeap which has been merge
	}
	
	private HeapNode<AnyType> merge(HeapNode<AnyType> hn1, HeapNode<AnyType> hn2) {
		if(hn1 == null) {
			return hn2;
		}
		if(hn2 == null) {
			return hn1;
		}
		if(hn1.element.compareTo(hn2.element) < 0) {
			return actualMerge(hn1, hn2); //small always be the first argument
		} else {
			return actualMerge(hn2, hn1); //small one as topic element
		}
	}
	
	private HeapNode<AnyType> actualMerge(HeapNode<AnyType> hn1, HeapNode<AnyType> hn2) {
		if(hn1.left == null) { //special case
			hn1.left = hn2; //hn1 already accurate
		} else {
			hn1.right = merge(hn1.right, hn2);
			if(hn1.left.npl < hn1.right.npl) { //leftHeap < rightHeap
				swapChildren(hn1);
			}
			hn1.npl = hn1.right.npl + 1;
		}
		return hn1; //change upLevel's hn1.right, include topLevel root
	}
	
	private void swapChildren(HeapNode<AnyType> heapNode) {
		HeapNode<AnyType> tmpNode = heapNode.left;
		heapNode.left = heapNode.right;
		heapNode.right = tmpNode;
	}

	public void insert(AnyType x) {
		insert(new HeapNode<AnyType>(x));
	}
	
	private void insert(HeapNode<AnyType> heapNode) {
		root = merge(heapNode, root);
	}

	public AnyType deleteMin() {
		AnyType min = findMin(); //out of bound check
		root = merge(root.left, root.right);
		return min;
	}
	
	public AnyType findMin() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return root.element;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void clear() {
		root = null;
	}
	
	private static class HeapNode<AnyType> {
		
		AnyType element = null;
		int npl = 0; //Null path length
		HeapNode<AnyType> left;
		HeapNode<AnyType> right;
		
		HeapNode(AnyType element) {
			this(element, null, null);
		}
		
		HeapNode(AnyType element, HeapNode<AnyType> left, HeapNode<AnyType> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
		
	}
	
}
