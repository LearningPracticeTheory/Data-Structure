
//public class InsertionSort<AnyType extends Comparable<? super AnyType>> {
public class InsertionSort {
	
	public static<AnyType extends Comparable<AnyType>> void sort0(AnyType array[]) {
		
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) { //reverse
				if(array[j-1].compareTo(array[j]) > 0) {
					AnyType tmp = array[j];
					array[j] = array[j-1];
					array[j-1] = tmp;
				}
			}
		}
		
	}
	
	/*
	 * Algorithm optimization, which use one-way assignment
	 */
	public static<AnyType extends Comparable<AnyType>> void sort1(AnyType array[]) {
		int j;
		
		for(int i = 1; i < array.length; i++) {
			AnyType tmp = array[i];
			for(j = i; j > 0; j--) {
				if(tmp.compareTo(array[j-1]) < 0) {
					array[j] = array[j-1];
				} else {
					break;
				}
			}
			array[j] = tmp;
		}
		
	}
	
	/*
	 * Code optimization, which just simplify the code
	 */
	public static<AnyType extends Comparable<AnyType>> void sort2(AnyType array[]) {
		int j;
		
		for(int i = 1; i < array.length; i++) {
			AnyType tmp = array[i];
			for(j = i; j > 0 && array[j-1].compareTo(tmp) > 0; j--) {
				array[j] = array[j-1];
			}
			array[j] = tmp;
		}
		
	}

}
