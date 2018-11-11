
public class QuickSort {

	private static final int CUT_OFF = 10;

	public static<AnyType extends Comparable<AnyType>>
	void sort(AnyType array[]) {
		sort(array, 0, array.length-1);
	}

	private static<AnyType extends Comparable<AnyType>>
	void sort(AnyType[] array, int left, int right) {
		if(left + CUT_OFF <= right) {
			AnyType pivot = madian3(array, left, right);
			
			/*
			 * use pivot to divide the smaller & larger
			 */
			int i = left, j = right-1;
			for( ; ; ) {
				while(array[++i].compareTo(pivot) < 0) { } //++ until array[i] >= pivot
				while(array[--j].compareTo(pivot) > 0) { } //-- until array[j] <= pivot
				if(i < j) {
					swap(array, i, j); //swap smaller to left & larger to right without sort
				} else {
					break;
				}
			}
			
			/*
			 * restore pivot back to i where divides left(smaller) & right(larger)
			 * smaller -> [left, i) | larger -> [i, r] except [r-1] | pivot -> [r-1] smaller than [i]
			 */
			swap(array, i, right-1);
			
			/*
			 * recursive sort left & right without pivot which is already on correct position
			 */
			sort(array, left, i-1);
			sort(array, i+1, right);
		} else { //sub-arrays.length which is shorter than CUT_OFF
			/*
			 * insertionSort sub-arrays which are divide by quickSort
			 */
			insertionSort(array, left, right); 
		}
	}
	
	/*
	 * sort [left-most, center, right-most]
	 * place pivot to right-1 where is not in the range of sort, like a safe place
	 * pivot is already sorted, put it into safe place which avoid secondary sorted 
	 */
	private static<AnyType extends Comparable<AnyType>>
	AnyType madian3(AnyType[] array, int left, int right) {
		int center = (left+right)/2;
		
		if(array[center].compareTo(array[left]) < 0) {
			swap(array, left, center);
		}
		if(array[right].compareTo(array[left]) < 0) {
			swap(array, left, right);
		}
		if(array[right].compareTo(array[center]) < 0) {
			swap(array, center, right);
		}
		
		/*
		 * place pivot at position right-1
		 * actually center is the pivot -> madian3 (middle of three) 
		 */
		swap(array, center, right-1);
		
		return array[right-1];
	}

	/*
	 * actual sorted for sub-arrays
	 */
	private static<AnyType extends Comparable<AnyType>>
	void insertionSort(AnyType[] array, int left, int right) {
		
		/*
		for(int i = left+1; i <= right; i++) {
			for(int j = i; j > left; j--) {
				if(array[j-1].compareTo(array[j]) > 0) {
					swap(array, j-1, j); //swap both
				} else {
					break;
				}
			}
		}
		*/
		
		for(int i = left+1; i <= right; i++) {
			AnyType tmp = array[i];
			int j;
			for(j = i; j > left && array[j-1].compareTo(tmp) > 0; j--) {
				array[j] = array[j-1]; //single-way assignment
			}
			array[j] = tmp;
		}
		
	}

	private static<AnyType extends Comparable<AnyType>>
	void swap(AnyType array[], int left, int right) {
		AnyType tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
	
}
