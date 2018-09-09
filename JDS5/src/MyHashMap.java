import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyHashMap<K, V> {

	private static final int DEFAULT_SIZE = 0;
	private List<Entry<K, V>> lists[] = null;
	private List<K> keys = null; //keys' size == map size
	private Collection<V> values = null; //values' size == lists Size
	
	MyHashMap() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	MyHashMap(int initializedSize) {
		lists = new LinkedList[initializedSize]; //Warnings: The expression of type LinkedList[] 
			//needs unchecked conversion to conform to List<MyHashMap.Entry<K,V>>[]
		
		keys = new LinkedList<>();
		values = new LinkedList<>();
	}
	
	private Entry<K, V> getEntryByKey(K key) {
		if(containsKey(key)) {
			for(int i = 0; i < lists.length; i++) {
				if(lists[i] == null) {
					continue;
				}
				for(Entry<K, V> entry : lists[i]) {
					if(entry.key.equals(key)) {
						return entry;
					}
				}
			}
		}
		return null;
	}
	
	private Entry<K, V> getEntryByValue(V value) { //cannot Overload, cause K, V same type
		if(containsValue(value)) {
			for(int i = 0; i < lists.length; i++) {
				if(lists[i] == null) {
					continue;
				}
				for(Entry<K, V> entry : lists[i]) {
					if(entry.value.equals(value)) {
						return entry;
					}
				}
			}
		}
		return null;
	}
	
	private List<Entry<K, V>> getListByValue(V value) {
		Entry<K, V> entry = getEntryByValue(value);
		for(int i = 0; i < lists.length; i++) {
			if(lists[i] == null) {
				continue;
			}
			for(Entry<K, V> item : lists[i]) {
				if(item.equals(entry)) {
					return lists[i];
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void capacity(int newSize) {
		if(newSize == 0) {
			lists = new LinkedList[1];
			return;
		}
		List<Entry<K, V>> oldLists[] = lists;
		lists = new LinkedList[newSize];
		int index = 0;
		for(int i = 0; i < oldLists.length; i++) {
			if(oldLists[i] != null && !oldLists[i].isEmpty()) {
				lists[index++] = oldLists[i]; 
			}
		}
	}
	
	public V put(K key, V value) {
		if(containsKey(key)) { //replace
			Entry<K, V> entry = getEntryByKey(key);
			V oldValue = entry.value;
			List<Entry<K, V>> whichList = getListByValue(oldValue);
			if(whichList != null && whichList.size() == 1) {
				values.remove(oldValue);
			}
			keys.remove(key);
			whichList.remove(entry);
			put(key, value); //put on other List which is the new one
			return oldValue;
		} else if(containsValue(value)) { //contains value (!containsKey) -> Entry exists -> List exists
			List<Entry<K, V>> whichList = getListByValue(value); //which means No need Null processing
			whichList.add(new Entry<K, V>(key, value)); //same value at same List
			keys.add(key);
		} else { //!containsKey && !containsValue
			if(values.size() == lists.length) { //expand capacity
				capacity(lists.length*2+1);
			} //CANNOT use else here
			for(int i = 0; i < lists.length; i++) {
				if(lists[i] == null || lists[i].isEmpty()) { //insert first empty place in lists[]
					lists[i] = new LinkedList<>();
					lists[i].add(new Entry<K, V>(key, value));
					keys.add(key);
					values.add(value);
					break;
				}
			}
		}
		return value;
	}
	
	public V get(K key) {
		if(containsKey(key)) {
			return getEntryByKey(key).value;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		return keys.contains(key);
	}
	
	public boolean containsValue(V value) {
		return values.contains(value);
	}
	
	public V remove(K key) {
		if(containsKey(key)) {
			Entry<K, V> entry = getEntryByKey(key);
			List<Entry<K, V>> whichList = getListByValue(entry.value);
			V value = entry.value;
			
			if(whichList.size() == 1) {
				values.remove(entry.value);
			}
			
			keys.remove(key);
			whichList.remove(entry);
			
			if(values.size() == lists.length/4) {
				capacity(lists.length/2);
			}
			
			return value;
		}
		return null;
	}

	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		for(int i = 0; i < keys.size(); i++) {
			set.add(keys.get(i));
		}
		return set;
	}
	
	public Collection<V> values() { //No sort
		Collection<V> c = values;
		return c;
	}
	
	public V replace(K key, V value) {
		if(containsKey(key)) {
			return put(key, value);
		}
		return null;
	}
	
	public int size() {
		return keys.size();
	}
	
	public boolean isEmpty() {
		return keys.size() == 0;
	}
	
	public void clear() {
		keys.clear();
		values.clear();
		capacity(0);
	}
	
	@Override
	public String toString() {
		Set<K> set = keySet();
		int setIndex = 0;
		String s = "{";
		
		for(K key : set) {
			Entry<K, V> entry = getEntryByKey(key);
			if(setIndex++ == set.size()-1) {
				s += entry.key + "=" + entry.value;
			} else {
				s += entry.key + "=" + entry.value + ", ";
			}
		}
		
		s += "}";
		return s;
	}
	
	private static class Entry<K, V> {
		K key;
		V value;
		
		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
}
