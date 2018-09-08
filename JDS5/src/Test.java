
public class Test {

	public static void main(String args[]) {
		new Test().execute();
	}
	
	public void execute() {
		
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
			hashVal *= 37 + key.charAt(i);
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
