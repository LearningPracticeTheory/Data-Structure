
interface MyMap<K, V> {
	
	public V put(K key, V value);
	public V get(K key);
	
	public boolean containsKey(K key);
	public boolean containsValue(V value);
	
	public V remove(K key);
	V remove(K key, V value);
	
	V replace(K key, V value);
	boolean replace(K key, V oldValue, V newValue);

	public MySet<MyMap.Entry<K, V>> entrySet();
	public MySet<K> keySet();
	
	public void clear();
	public boolean isEmpty();
	public int size();
	
	public static interface Entry<K, V> { //???
		
	}
	
}
