
public class ThreadBinaryTree<E> {
	
	private TreeNode preNode = null;
	private TreeNode root = null;
	
	public class TreeNode {
		E element;
		TreeNode left;
		TreeNode right;
		boolean isLeftThread;
		boolean isRightThread;
		TreeNode(E element) {
			this.element = element;
		}
	}
	
	public void createThreadTree(E elements[]) {
		root = this.createThreadTree(elements, 0);
	}
	
	private TreeNode createThreadTree(E elements[], int index) {
		
		TreeNode t = null;
		
		if(index < elements.length) {
			t = new TreeNode(elements[index]);
			t.left = createThreadTree(elements, index*2+1);
			t.right = createThreadTree(elements, index*2+2);
		}
		
		return t;
	}
	
	public void inOrderThreadTree() {
		this.inOrderThreadTree(root);
	}
	
	private void inOrderThreadTree(TreeNode t) {
		
		if(t.left != null) inOrderThreadTree(t.left);
		
		if(t.left == null) { //E.left -> B
			t.left = preNode;
			t.isLeftThread = true;
		}
		if(preNode != null && preNode.right == null) { //E.right -> A (E as preNode)
			preNode.right = t;
			preNode.isRightThread = true;
		}
		preNode = t;
		
		if(t.right != null) inOrderThreadTree(t.right);
		
	}
	
	public void inOrderTraversalBySuccessor() {
		this.inOrderTraversalBySuccessor(root);
		System.out.println();
	}
	
	private void inOrderTraversalBySuccessor(TreeNode t) {
		while(t.left != null && t.isLeftThread == false) { //start from left side
			t = t.left;
		}
		
		while(t != null) {
			System.out.print(t.element + " ");
			if(t.isRightThread) { //D -> B
				t = t.right;
			} else { //A -> F (reverse) && B -> E (normal)
				t = t.right;
				while(t != null && !t.isLeftThread) { //t != null; must be the first adjustment
					t = t.left;
				}
			}
		}
	}
	
	public void inOrderTraversalByPrecursor() {
		this.inOrderTraversalByPrecursor(root);
		System.out.println();
	}
	
	private void inOrderTraversalByPrecursor(TreeNode t) { //The reverse of Successor
		while(t.right != null && !t.isRightThread) {
			t = t.right;
		}
		
		while(t != null) {
			System.out.print(t.element + " ");
			if(t.isLeftThread) {
				t = t.left;
			} else {
				t = t.left;
				while(t != null && !t.isRightThread) {
					t = t.right;
				}
			}
		}
	}
	
}
