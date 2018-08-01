import java.util.Iterator;

public class MyTreeSet<AnyType extends Comparable<AnyType>> implements MySet<AnyType> {

	private AVLTree<AnyType> tree = new AVLTree<>(); //AVLTree need Comparable
	private static int size = 0;
	
	@Override
	public boolean contains(AnyType x) {
		return tree.contains(x);
	}

	@Override
	public boolean add(AnyType x) {
		if(tree.contains(x)) {
			return false;
		} else {
			tree.insert(x);
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(AnyType x) {
		if(tree.contains(x)) {
			tree.remove(x);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		tree.clear();
	}

	@Override
	public Iterator<AnyType> iterator() {
		return tree.iterator();
	}
	
}
