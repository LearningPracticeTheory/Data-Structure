import java.util.Iterator;

public class MyHashSet<AnyType> {

	private SeparateChainingHashTable<AnyType> scht = null;
	
	MyHashSet() {
		scht = new SeparateChainingHashTable<>();
	}

	public boolean add(AnyType x) {
		return scht.insert(x);
	}
	
	public boolean contains(AnyType x) {
		return scht.contains(x);
	}
	
	public boolean remove(AnyType x) {
		return scht.remove(x);
	}
	
	public AnyType[] toArray() {
		return scht.toArray();
	}
	
	public Iterator<AnyType> iterator() {
		return scht.iterator();
	}
	
	@Override
	public String toString() {
		return scht.toString();
	}
	
	public int size() {
		return scht.size();
	}
	
	public void clear() {
		scht.makeEmpty();
	}
	
}
