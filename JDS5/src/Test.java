import java.util.Iterator;

public class Test {

	public static void main(String args[]) {
		new Test().execute();
	}
	
	public void execute() {
//		hashSetTest();
//		hashMapTest();
//		cuckooHashTableTest();
//		priorityTest();
	}
	
	public void priorityTest() {
		int num = 0;
		for(int i = 0; i < 5; i++) {
			num *= 2 + 1; //multiple then assignment, NO add!
			System.out.print(num + " ");
		}
	}
	
/*测试应该将插入移除过程中值得踢出&替换显示较为合适*/
	public void cuckooHashTableTest() {
		CuckooHashTable<String> cht = new CuckooHashTable<>(new StringHashFamily(3));
		System.out.println(cht.insert("A"));
		System.out.println(cht.insert("B"));
		System.out.println(cht.insert("C"));
		System.out.println(cht.insert("D"));
		System.out.println(cht.insert("A"));
		System.out.println(cht.contains("A"));
		System.out.println(cht.remove("A"));
		System.out.println(cht.contains("A"));
		cht.clear();
		System.out.println(cht.contains("D"));
	}
	
	/*
	public final class String { //final class which cannot be extends 
		
		private int hashVal = 0;
		
		@Override
		public int hashCode() {
			if(hashVal != 0) { //Caching the hash code
				return hashVal; 
			} 
			for(int i = 0; i < length(); i++) {
//				hashVal *= 31 + (int) charAt(i); //error
				hashVal = 31*hashVal + (int) charAt(i);
			}
			return hashVal;
		}
		
	}
	*/
	
	public void hashMapTest() {
//		HashMap<Integer, String> hm = new HashMap<>();
		MyHashMap<Integer, String> hm = new MyHashMap<>();
		hm.put(1, "one");
		hm.put(3, "one"); //keys -> value
		hm.put(2, "two");
		hm.put(1, "1"); //Cover
		System.out.println(hm); //toString
		System.out.println(hm.containsKey(1));
		System.out.println(hm.containsValue("one"));
		System.out.println(hm.put(3, "three")); //return old Value, which cover by the new one
		System.out.println(hm.get(3)); //get key return value
		System.out.println(hm.keySet()); //return all Keys, return Set<>
		System.out.println(hm.values()); //return values
		System.out.println(hm.remove(3)); //remove key return value
		System.out.println("size=" + hm.size()); 
		System.out.println(hm.replace(2, "2")); //replace by new Value, return old one
		hm.clear();
		System.out.println(hm); //{} == NULL
		System.out.println(hm.isEmpty());
		
//		HashMap<List<Integer>, List<String>> hashMap = new HashMap<>(); //K, V same
		hm.put(1, "one");
		hm.put(2, "two");
		hm.put(3, "three");
		System.out.println(hm);
		System.out.println(hm.remove(3));
		System.out.println(hm);
		System.out.println(hm.remove(10));
		System.out.println(hm.remove(2));
		System.out.println(hm.remove(1));
		System.out.println(hm);
	}

	public void hashSetTest() { //add contains remove toArray toString
//		HashSet<Integer> hs = new HashSet<>();
		MyHashSet<Integer> hs = new MyHashSet<>();
		hs.add(1);
		hs.add(2);
		hs.add(3);
		hs.add(10);
		hs.add(4); //sort?? toArray -> Arrays.sort() -> iterator
		hs.add(4); //No repeat
		Iterator<Integer> it = hs.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		Object array[] = hs.toArray(); //sorted
		
		for(Object i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println(hs); //toString()
		
		System.out.println(hs.remove(1));
		System.out.println(hs.add(1));
		System.out.println(hs);
		
		hs.remove(1);		
		hs.remove(2);
		
		
		hs.remove(3);
		hs.remove(4);
		System.out.println(hs);
		System.out.println("size=" + hs.size());

		hs.clear();
		
		System.out.println(hs);
	}
	
/*ASCII add all*/
	public static int hash1(String key, int tableSize) {
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++) {
			hashVal += key.charAt(i); //return char, int actually 
		}
		return hashVal %= tableSize;
	}

/*k0*27^0 + k1*27^1 + k2*27^2*/ //27 == alphabets + space
	public static int hash2(String key, int tableSize) {
		return (key.charAt(0) + 27*key.charAt(1) + 
				27*27*key.charAt(2)) % tableSize; 
	}
	
/*k0*37^0 + k1*37^1 + k2*37^2 + ... + ki*37^i*/ //37 == alphabets + space + numbers
	public static int hash3(String key, int tableSize) {
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++) {
//			hashVal *= 37 + key.charAt(i); //error
			hashVal = 31*hashVal + (int) key.charAt(i);
		}
		hashVal %= tableSize;
		if(hashVal < 0) {
			hashVal += tableSize;
		}
		return hashVal;
	}
	
/*借助 hashCode()*/
	public static int hash4(String key, int tableSize) { //hash3 simplify
		int hashVal = key.hashCode();
		hashVal %= tableSize;
		if(hashVal < 0) {
			hashVal += tableSize;
		}
		return hashVal;
	}
	
}
