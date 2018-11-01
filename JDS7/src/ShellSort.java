
public class ShellSort {

	public static<AnyType extends Comparable<AnyType>> void sort0(AnyType array[]) {
		int arrayLength = array.length;
		int hLength = lengthOfBinaryNum(arrayLength);
		int h[] = new int[hLength];
		
		for(int i = 0; i < hLength; i++) {
			h[i] = arrayLength / 2;
			arrayLength /= 2;
		}
		
		for(int k = 0; k < h.length; k++) {
			for(int i = h[k]; i < array.length; i++) {
				int j = i;
				AnyType tmp = array[j];
				for(; j-h[k] >= 0; j -= h[k]) {
					if(array[j-h[k]].compareTo(tmp) > 0) {
						array[j] = array[j-h[k]];
					} else {
						break;
					}
				}
				array[j] = tmp;
			}
		}
		
	}
	
	private static int lengthOfBinaryNum(int n) {
		int count = 0;
		while(true) {
			if(n == 0 || n == 1) {
				break;
			}
			n /= 2;
			count++;
		}
		return count;
	}
	
	public static<AnyType extends Comparable<AnyType>> void sort1(AnyType array[]) {
		int j;
		
		for(int h = array.length/2; h > 0; h /= 2) { //diminishing increment sequence
			for(int i = h; i < array.length; i++) { 
				AnyType tmp = array[i]; //InsertionSort.sort2();
				for(j = i; j >= h && array[j-h].compareTo(tmp) > 0; j -= h) {
					array[j] = array[j-h];
				}
				array[j] = tmp;
			}
		}
	}
	
}
