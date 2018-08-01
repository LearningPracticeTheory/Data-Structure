import java.nio.BufferUnderflowException;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

	private TreeNode root = null;
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void clear() {
		root = null;
	}
	
/*return TreeNode & void insert*/
	public void insert(AnyType x) {
		root = this.insert(x, root); //update root for every time, especially initialized the root
	}								 
	
	private TreeNode insert(AnyType x, TreeNode t) {
		if(t == null) {
			return new TreeNode(x);
		}
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			t.right = insert(x, t.right); //update parent's childNode, which is the Node of return
		} else if(compareResult < 0) {
			t.left = insert(x, t.left);
		} //else compareResult == 0; and do nothing
		return t;
	}
	
/*return boolean insert*/
/*	
	public boolean insert(AnyType x) { 
		return this.insert(x, root);
	}
//overload for left && right Node
	private boolean insert(AnyType x, TreeNode t) { 
		if(t == null) {
			root = new TreeNode(x);
			return true;
		}
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			return this.insert(x, t.right);
		} else if(compareResult < 0) {
			return this.insert(x, t.left);
		} else { //x already exist
			return false;
		}
	}
*/
	public boolean contains(AnyType x) {
		return contains(x, root);
	}
/*递归实现*/	
	private boolean contains(AnyType x, TreeNode t) {
 		if(t == null) {
			return false;
		}
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			return this.contains(x, t.right);
		} else if(compareResult < 0) {
			return this.contains(x, t.left);
		} else {
			return true;
		}
	}
/*while 实现 contains*/
/*
	private boolean contains(AnyType x, TreeNode t) {
		while(t != null) {
			int compareResult = x.compareTo(t.data);
			if(compareResult > 0) {
				t = t.right;
			} else if(compareResult < 0) {
				t = t.left;
			} else {
				return true;
			}
		}
		return false;
	}
*/
	
	public AnyType findMin() {
		if(isEmpty()) {
			throw new BufferUnderflowException();
		}
		return findMin(root);
	}
	
	private AnyType findMin(TreeNode t) {
		if(t.left != null) {
			return findMin(t.left);
		}
		return t.data;
	}
	
	public AnyType findMax() {
		if(isEmpty()) {
			throw new BufferUnderflowException();
		}
		return findMax(root).data;
	}
	
	private TreeNode findMax(TreeNode t) {
		while(t.right != null) {
			t = t.right;
		}
		return t;
	}
	
 	public void remove(AnyType x) {
		root = this.remove(x, root); //remove the root, use its child to replace
	}								//and update root every time as well
	
	private TreeNode remove(AnyType x, TreeNode t) {
		if(t == null) {
			return t;
		}
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			t.right = remove(x, t.right);
		} else if(compareResult < 0) {
			t.left = remove(x, t.left);
		} else if(t.left != null && t.right != null) { 
//			t.data = findMin(t.right);
//			t.right = remove(t.data, t.right); 
			t.data = removeMin(t.right, t, 1);
		} else { //include 3 cases, default return left
			return t.right != null ? t.right : t.left;
		}
		return t;
	}

	private AnyType removeMin(TreeNode t, TreeNode p, int level) { //p is t's parent Node
		if(t.left != null) {
			return removeMin(t.left, t, level+1);
		} else if(level <= 1){ //t.left == null at the same time
			AnyType tmp = t.data;
			p.right = p.right.right; //KIA t
			return tmp;
		} else { //level > 1
			AnyType tmp = t.data;
			p.left = t.right;
			return tmp;
		}
	}
	
	public void preorderTraversal() {
		this.preorderTraversal(root);
		System.out.println();
	}
	
	private void preorderTraversal(TreeNode t) {
		System.out.print(t.data + " ");
		if(t.left != null) preorderTraversal(t.left);
		if(t.right != null) preorderTraversal(t.right);
	}
	
	public void inorderTraversal() {
		this.inorderTraversal(root);
		System.out.println();
	}
	
	private void inorderTraversal(TreeNode t) {
		if(t.left != null) inorderTraversal(t.left);
		System.out.print(t.data + " ");
		if(t.right != null) inorderTraversal(t.right);
	}
	
	public void postorderTraversal() {
		this.postorderTraversal(root);
		System.out.println();
	}
	
	private void postorderTraversal(TreeNode t) {
		if(t.left != null) postorderTraversal(t.left);
		if(t.right != null) postorderTraversal(t.right);
		System.out.print(t.data + " ");
	}
	
	private class TreeNode {
		 AnyType data;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(AnyType data) {
			 this.data = data;
		 }
	}
	
}
