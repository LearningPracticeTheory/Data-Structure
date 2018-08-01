import java.util.Iterator;

public interface MySet<AnyType> {
	
	public boolean contains(AnyType x);
	public boolean add(AnyType x);
	public boolean remove(AnyType x);
	public boolean isEmpty();
	public int size();
	public void clear();
	public Iterator<AnyType> iterator();
	
}
