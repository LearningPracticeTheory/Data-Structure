import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LeftistHeap<AnyType extends Comparable<AnyType>> {

	private HeapNode<AnyType> root = null;
	private int size = 0;
	
	LeftistHeap() {
		
	}
	
	LeftistHeap(AnyType x) { //Build single node LeftistHeap
		root = new HeapNode<>(x);
		size = 1;
	}
	
	LeftistHeap(AnyType array[]) { //build multiple nodes Heap
		buildHeap(array);
	}
	
	public void merge(LeftistHeap<AnyType> lh) { //Merge lh into the priority queue
		if(this == lh) { //lh must be different from this
			return; 
		}
		root = merge(root, lh.root);
		this.size += lh.size;
		lh.root = null; //KIA the LeftistHeap which has been merge
		lh.size = 0;
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
		size++;
	}
	
	private void insert(HeapNode<AnyType> heapNode) {
		root = merge(heapNode, root);
	}

	public AnyType deleteMin() {
		AnyType min = findMin(); //out of bound check
		root = merge(root.left, root.right);
		size--;
		return min;
	}
	
	public AnyType findMin() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return root.element;
	}
	
	private void buildHeap(AnyType array[]) {
		Queue<LeftistHeap<AnyType>> queue = new LinkedList<>();
		for(int i = 0; i < array.length; i++) {
			switch(queue.size()) {
			case 0:
				queue.add(new LeftistHeap<AnyType>(array[i]));
				break;
			case 1:
				queue.add(new LeftistHeap<AnyType>(array[i]));
				queueMerge(queue);
				break;
//			case 2:
//				break;
			}
		}
		
		if(!queue.isEmpty()) {
			this.root = queue.poll().root;
		}
		
		size = array.length;
	}
	
	private void queueMerge(Queue<LeftistHeap<AnyType>> queue) {
		LeftistHeap<AnyType> heap1 = queue.poll();
		LeftistHeap<AnyType> heap2 = queue.poll();
		heap1.merge(heap2);
		queue.add(heap1);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
//		return root == null;
		return size == 0;
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
