import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<AnyType extends Comparable<AnyType>> {
	
	private static final int ALLOWED_IMBALANCE = 1;
	private TreeNode root = null;
	private ArrayList<AnyType> list = new ArrayList<>();
	
	public void insert(AnyType x) {
		root = insert(x, root);
	}
	
	private TreeNode insert(AnyType x, TreeNode t) {
		if(t == null) {
			return new TreeNode(x);
		}
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			t.right = insert(x, t.right);
		} else if(compareResult < 0) {
			t.left = insert(x, t.left);
		}
		return balance(t);
	}
	
	public boolean contains(AnyType x) {
		return contains(x, root);
	}
	
	private boolean contains(AnyType x, TreeNode t) {
		if(t == null) {
			return false;
		} 
		int compareResult = x.compareTo(t.data);
		if(compareResult > 0) {
			return contains(x, t.right);
		} else if(compareResult < 0) {
			return contains(x, t.left);
		} else {
			return true;
		}
	}
	
	public void remove(AnyType x) {
		root = remove(x, root);
	}
	
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
			t.data = findMin(t.right);
			t.right = remove(t.data, t.right);
		} else {
			return t.left != null ? t.left : t.right;
		}
//		return t;
		return balance(t);
	}

	private AnyType findMin(TreeNode t) {
		if(t.left != null) {
			return findMin(t.left);
		}
		return t.data;
	}
	
	public AnyType findMax() {
		return findMax(root);
	}
	
	private AnyType findMax(TreeNode t) {
		if(t == null) {
			throw new NullPointerException();
		}
		while(t.right != null) {
			t = t.right;
		}
		return t.data;
	}

	private TreeNode balance(TreeNode t) {
		if(t == null) { //when used?
			return t;
		}
		if(height(t.left)- height(t.right) > ALLOWED_IMBALANCE) { //NullPoint if use t.left.height
			if(height(t.left.right) <= height(t.left.left)) {
				return rotateWithLeftChild(t);
			} else {
				return doubleWithLeftChild(t);
			}
		} else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if(height(t.right.left) <= height(t.right.right)) {
				return rotateWithRightChild(t);
			} else { //B.height > C.height
				return doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	

	private TreeNode rotateWithRightChild(TreeNode t1) {
		TreeNode t2 = t1.right;
		t1.right = t2.left;
		t2.left = t1;
		t1.height = Math.max(height(t1.left), height(t1.right));
		t2.height = Math.max(height(t2.left), height(t2.right));
		return t2;
	}
	
	private TreeNode rotateWithLeftChild(TreeNode t1) {
		TreeNode t2 = t1.left;
		t1.left = t2.right;
		t2.right = t1;
		t1.height = Math.max(height(t1.left), height(t1.right));
		t2.height = Math.max(height(t2.left), height(t2.right));
		return t2;
	}
	
	private TreeNode doubleWithRightChild(TreeNode t1) {
		t1.right = rotateWithLeftChild(t1.right);
		return rotateWithRightChild(t1);
	}
	
	private TreeNode doubleWithLeftChild(TreeNode t1) {
		t1.left = rotateWithRightChild(t1.left);
		return rotateWithLeftChild(t1);
	}

	private int height(TreeNode t) {
		return t == null ? -1 : t.height;
	}
	
	public void clear() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
/*
	@SuppressWarnings("unchecked")
	public AnyType[] toArray() { //error
		if(root == null) {
			throw new NullPointerException();
		}
//		array = new AnyType[]; //cannot new AnyType[]; case interface
		inorderTraversal(root);
		return (AnyType[])list.toArray(); //return Object[] throw java.lang.ClassCastException: 
							///[Ljava.lang.Object; cannot be cast to [Ljava.lang.Comparable;
	}
*/
	private void inorderTraversal(TreeNode t) {
		if(t.left != null) { inorderTraversal(t.left); }
		list.add(t.data);
		if(t.right != null) { inorderTraversal(t.right); }
	}

	public Iterator<AnyType> iterator() {
		if(root == null) {
			throw new NullPointerException();
		}
		inorderTraversal(root);
		return list.iterator();
	}

	private class TreeNode {
		TreeNode left;
		TreeNode right;
		AnyType data;
		int height;
		TreeNode(AnyType data) {
			this.data = data;
			height = 0;
		}
	}
	
}
