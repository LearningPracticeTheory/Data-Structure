
public class PatternString {

	private String str = null;
	private String strings[] = null;
	private StringEntry hashStrings[] = null;
	
	PatternString(String str) {
		this.str = str;
	}
	
	private int myHash(String s) {
		int hashVal = s.hashCode();
		hashVal %= hashStrings.length;
		if(hashVal < 0) {
			hashVal += hashStrings.length;
		}
		return hashVal;
	}
	
	private int splitStrByTargetLen(String target, int length) {
		int size = str.length()-length+1;
		strings = new String[size];
		for(int i = 0; i < size; i++) {
			strings[i] = str.substring(i, i+length); //[i, i+length)
//System.out.print(strings[i] + " ");
		}
		return size;
	}
	
	private int findPos(String s) { //collision -> simply linear detection
		int index = myHash(s);
		while(hashStrings[index] != null && !hashStrings[index].data.equals(s)) {
			index++;
			if(index >= hashStrings.length) {
				index -= hashStrings.length;
			}
		}
		return index; //return the element which is null of index || s.index
	}
	
	public boolean equals(String s, int index) { //hashStrings index, like isActive
		if(hashStrings[index] != null) {
			return s.equals(hashStrings[index].data);
		}
		return false;
	}
	
	private void insert(String s, int index) {
		if(contains(s)) {
			return;
		}
		int pos = findPos(s);
		hashStrings[pos] = new StringEntry(s, index); 
	}
	
	private boolean contains(String s) {
		int pos = findPos(s);
		return equals(s, pos);
	}
	
	private void initializedStringEntry(int size) { //insert All
		hashStrings = new StringEntry[size];
		for(int i = 0; i < size; i++) {
			insert(strings[i], i);
		}
	}
	
	public int indexOf(String target) {
		int length = target.length();
		int size = splitStrByTargetLen(target, length); //initialized strings
		initializedStringEntry(size);
		int hashTarget = findPos(target);
		if(equals(target, hashTarget)) {
			return hashStrings[hashTarget].index;
		}
		return -1;
	}
	
	private class StringEntry {
		String data;
		int index;
		StringEntry(String data, int index) {
			this.data = data;
			this.index = index;
		}
	}
	
}
