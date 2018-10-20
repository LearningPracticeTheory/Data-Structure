/**
 * #### BAD CODE ####
 */

import java.util.NoSuchElementException;

public class BinaryHeapChain<AnyType extends Comparable<AnyType>> {
	
	private int size = 0;
	HeapNode<AnyType> root = null;
	
	private HeapNode<AnyType> findIndex(int index) {
		if(size == 0 || index <= 0 || index > size) {
			return null;
		} else if(index == 1) { //index start from 1 
			return root;
		}
		int level = level(index); //level > 1
		boolean bLevels[] = new boolean[level-1];
		int levelIndex = bLevels.length - 1; //reverse cause percolateUp
		while(index > 1) {
			if(index % 2 != 0) { //odd number == right == true
				bLevels[levelIndex] = true;
			}
			index /= 2;
			levelIndex--;
		}
		HeapNode<AnyType> node = root; //To find the indexNode which start from root & percolateDown
		for(int i = 0; i < bLevels.length; i++) {
			if(bLevels[i]) {
				node = node.right;
			} else { //default even == left == false;
				node = node.left;
			}
		}
		return node;
	}
	
	public AnyType findIndexElement(int index) {
		return findIndex(index).element;
	}
	
	private int level(int index) {
		int level = 1;
		while(((1 << level) - 1) < index) {
			level++;
		}
		return level;
	}
	
	public void insert(AnyType x) {
		insert(x, new HeapNode<AnyType>(x));
	}
	
	/*
	 * switchNodes
	 */
	/*	
	private void insert(AnyType x, HeapNode<AnyType> node) {
		if(size == 0) {
			size++;
			root = node;
		} else { //size > 0
			int nodeIndex = ++size; //hole > 1
			HeapNode<AnyType> parent = findIndex(nodeIndex/2);
			if(nodeIndex % 2 == 0) {
//				parent.left = new HeapNode<AnyType>(null);
				parent.left = node;
			} else {
//				parent.right = new HeapNode<AnyType>(null);
				parent.right = node;
			}
			while(nodeIndex > 1) {
				parent = findIndex(nodeIndex/2);
				if(parent == null) {
					break;
				}
				
				if(x.compareTo(parent.element) < 0) {
					if(nodeIndex % 2 == 0) { //left
//						moveNode(parent, parent.left);
						switchNodes(parent, parent.left);
					} else {
//						moveNode(parent, parent.right);
						switchNodes(parent, parent.right);						
					}
				} else {
					 break;
				}
				nodeIndex /= 2;
			}
			if(hole == 1) {
				root.element = x;
			} else {
				findIndex(hole).element = x;
			}
		}
		
	}
	
	 */

	private void switchNodes(HeapNode<AnyType> hn1, HeapNode<AnyType> hn2) {
		AnyType tmp = hn1.element;
		hn1.element = hn2.element;
		hn2.element = tmp;
	}
	
	/*
	 * one-way move
	 */
	private void insert(AnyType x, HeapNode<AnyType> node) {
		if(size == 0) {
			size++;
			root = node;
		} else { //size > 0
			int nodeIndex = ++size; //hole > 1
			int oldIndex = nodeIndex;
			HeapNode<AnyType> parent = null;
			while(nodeIndex > 1) {
				parent = findIndex(nodeIndex/2);	
				if(x.compareTo(parent.element) < 0) {
					if(nodeIndex % 2 == 0) { //left
						moveNode(parent, false);
					} else {
						moveNode(parent, true);
					}
				} else {
					 break;
				}
				nodeIndex /= 2;
			}
			if(nodeIndex == 1) { //case 1: move to root
				root.element = x;
			} else if(oldIndex == nodeIndex) { //case 2: normal insert which without move Node 
				if(nodeIndex % 2 == 0) { //even
					parent.left = node;
				} else {
					parent.right = node;
				}
			} else { //case 3: move to half, which not the case 1 
				if(nodeIndex % 2 == 0) {
					parent.left.element = x;
				} else {
					parent.right.element = x;
				}
			}
		}
		
	}
	
	/*
	 * hn1 move to left or right;
	 * one-way move
	 */
	private void moveNode(HeapNode<AnyType> parent, boolean flag) {
		if(flag) { //true == right;
			if(parent.right == null) {
				parent.right = new HeapNode<AnyType>(parent.element);
			} else {
				parent.right.element = parent.element;
			}
		} else { //default == false == left;
			if(parent.left == null) {
				parent.left = new HeapNode<AnyType>(parent.element);
			} else {
				parent.left.element = parent.element;
			}
		}
	}
	
	public AnyType deleteMin() {
		return deleteMin(size);
	}
	
	private AnyType deleteMin(int index) {
		if(size == 0) { //root == null
			throw new NoSuchElementException();
		} else if(size == 1) {
			AnyType min = root.element;
			clear();
			return min;
		}
		AnyType min = root.element;
		root.element = findIndex(index).element; //for percolate down
		HeapNode<AnyType> switchNode = root; //change to the first position
		HeapNode<AnyType> parentNode = findIndex(index/2); //KIA the last position Node;
		if(index % 2 == 0) { //even == left
			parentNode.left = null;
		} else {
			parentNode.right = null;
		}
		size--;
		while(switchNode.left != null) { //method to packet it 
			if(switchNode.right != null) {
				if(switchNode.left.element.compareTo(switchNode.right.element) > 0) { //left > right
					if(switchNode.element.compareTo(switchNode.right.element) > 0) { //parent > child
						switchNodes(switchNode, switchNode.right);
						switchNode = switchNode.right;
					} else {
						break;
					}
				} else { //left <= right
					if(switchNode.element.compareTo(switchNode.left.element) > 0) { //parent > child
						switchNodes(switchNode, switchNode.left);
						switchNode = switchNode.left;
					} else {
						break;
					}
				}
			} else { //right == null, which mean only left child
				if(switchNode.element.compareTo(switchNode.left.element) > 0) { //parent > child
					switchNodes(switchNode, switchNode.left);
					switchNode = switchNode.left;
				} else {
					break;
				}
			}
		}
		return min;
	}
	
	public void merge(BinaryHeapChain<AnyType> otherHeap) {
		for(int i = 1; i <= otherHeap.size; i++) {
			merge(otherHeap.findIndex(i).element);
		}
		otherHeap.size = 0;
		otherHeap.root = null;
	}

	private void merge(AnyType theElement) {
		this.insert(theElement);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0; //same;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}

	private static class HeapNode<AnyType> {
		AnyType element;
		HeapNode<AnyType> left;
		HeapNode<AnyType> right;
		
		public HeapNode(AnyType element) {
			this(element, null, null);
		}
		
		private HeapNode(AnyType element, HeapNode<AnyType> left, HeapNode<AnyType> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
		
	}

}
