import java.util.Random;

public class Test {
	
	Random r = new Random();
	Integer orignalArray[] = new Integer[20];
	Integer array[] = null;
	
	public static void main(String args[]) {
		new Test().launch();
	}

	public void launch() {
		initArray();
		
		insertionSort();
	}
	
	public void insertionSort() {
		array = orignalArray;
		InsertionSort.sort0(array);
		printArray("InsertionSort");
		array = orignalArray;
		InsertionSort.sort1(array);
		printArray("InsertionSort");
		array = orignalArray;
		InsertionSort.sort2(array);
		printArray("InsertionSort");
	}
	
	public void initArray() {
		System.out.print("Original array: ");
		for(int i = 0; i < orignalArray.length; i++) {
			orignalArray[i] = new Integer(r.nextInt(20));
			System.out.print(orignalArray[i] + " ");
		}
		System.out.println();
	}
	
	public void printArray(String title) {
		System.out.print(title + ": ");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
}
