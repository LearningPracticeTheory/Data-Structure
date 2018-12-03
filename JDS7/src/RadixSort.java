import java.util.ArrayList;

public class RadixSort {

	private static final int BUCKETS = 256; //ASCII
	
	@SuppressWarnings("unchecked")
	public static void radixSortSameLength(String array[], int stringLength) {
		
		ArrayList<String> lists[] = new ArrayList[BUCKETS];
		
		for(int i = 0; i < lists.length; i++) {
			lists[i] = new ArrayList<>();
		}
		
		/*
		 * use strings.charAt(index) to sort, which start from tail to head
		 */
		for(int i = stringLength-1; i >= 0; i--) {
			
			/*
			 * put the same chars into same zone
			 * array change every time when loop
			 */
			for(String tmp : array) {
				lists[tmp.charAt(i)].add(tmp);
			}
			
			int index = 0;
			
			/*
			 * lists include all strings
			 */
			for(ArrayList<String> list : lists) {
				for(String tmp : list) {
					array[index++] = tmp; //sorted by charAt(i);
				}
				list.clear();
			}
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void radixSort(String array[], int maxLen) {
		ArrayList<String> stringsByLength[] = new ArrayList[maxLen+1]; //+1? length != 0
		ArrayList<String> buckets[] = new ArrayList[BUCKETS];
		
		for(int i = 0; i < stringsByLength.length; i++) {
			stringsByLength[i] = new ArrayList<>();
		}
		 
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<>();
		}
		
		/*
		 * Group by String.length
		 */
		for(String s : array) {
			stringsByLength[s.length()].add(s);
		}
		
		int idx = 0;
		for(ArrayList<String> list : stringsByLength) {
			for(String s : list) {
				array[idx++] = s;
			}
		}
		
		/*
		 * startingIndex record the index of first element in groups
		 */
		int startingIndex = array.length;
		for(int pos = maxLen-1; pos >= 0; pos--) {
//			int startingIndex = array.length - stringsByLength[pos+1].size(); //ERROR
			startingIndex -= stringsByLength[pos+1].size(); //expand the range each time
			for(int i = startingIndex; i < array.length; i++) {
				buckets[array[i].charAt(pos)].add(array[i]);
			}
			/*
			 * only change the elements that are sorted
			 */
			int index = startingIndex;
			for(ArrayList<String> bucket : buckets) {
				for(String s : bucket) {
					array[index++] = s;
				}
				bucket.clear();
			}
		}
		
	}
	
	public static void countingRadixSort(String array[], int stringLength) {
		
		int N = array.length;
		
		String buffer[] = new String[N];
		
		for(int pos = stringLength-1; pos >= 0; pos--) {
			int count[] = new int[BUCKETS+1];
			
			/*
			 * count[k+1] is the number of k which is ASCII
			 */
			for(int i = 0; i < N; i++) {
				count[array[i].charAt(pos)+1]++;
			}
			
			/*
			 * the number of chars which <= count[k]
			 * which means how many chars before char[k] (include k) 
			 */
			for(int i = 1; i <= BUCKETS; i++) {
				count[i] += count[i-1]; 
			}
			
			/*
			 * buffer is always be the last sorted array
			 * count++ solve the conflict problem of the same characters
			 */
			for(int i = 0; i < N; i++) {
				buffer[count[array[i].charAt(pos)]++] = array[i];
			}
			
			String tmp[] = array;
			array = buffer;
			buffer = tmp;
		}
		
		/*
		 * odd times swap, buffer -> newArray; array -> oldArray
		 */
		if(stringLength % 2 == 1) {
			for(int i = 0; i < N; i++) {
				buffer[i] = array[i]; //so copy back
			}
		}
		
	}
	
}
