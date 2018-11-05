import java.util.Random;

public class Test {
	
	private static Random r = new Random();
	public static final int RANDOM_AMOUNT = r.nextInt(50); //random test
//	public static final int RANDOM_AMOUNT = 7; //debug test
//	public static final int RANDOM_AMOUNT = 1; //special test
	public static final int RANDOM_RANGE = 20;
	public static final int RANDOM_SEED = 1;
	
	final Integer array[] = new Integer[RANDOM_AMOUNT];
	
	public static void main(String args[]) {
		new Test().launch();
	}

	public void launch() {
		initArray();
		
		System.out.print("Original array: ");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
//		randomTest();
		
//		insertionSort();
		
		/*
		System.out.println(lengthOfBinaryNum(0));
		System.out.println(lengthOfBinaryNum(1));
		System.out.println(lengthOfBinaryNum(2));
		System.out.println(lengthOfBinaryNum(5));
		System.out.println(lengthOfBinaryNum(10));
		*/
		
//		shellSort();
		
//		heapSort();
		
		mergeSort();
	}
	
	public void mergeSort() {
		
		initArray();
		MergeSort.sort0(array);
		printArray("MergeSort");
		
		initArray();
		MergeSort.sort1(array);
		printArray("MergeSort");
		
		initArray();
		MergeSort.sort2(array);
		printArray("MergeSort");
		
	}

	public void heapSort() {
		initArray();
		HeapSort.sort0(array);
		printArray("HeapSort");
		
		initArray();
		HeapSort.sort0(array);
		printArray("HeapSort");
		
	}
	
	public void shellSort() {
		
		initArray();
		ShellSort.sort0(array);
		printArray("ShellSort");
		
		initArray();
		ShellSort.sort1(array);
		printArray("ShellSort");
		
	}
	
	public int lengthOfBinaryNum(int n) {
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
	
	
	public void randomTest() {
		Random r0 = new Random(); //NO seed
		Random r1 = new Random(1);
		Random r2 = new Random(1); //same seed, same range
		Random r3 = new Random(1); //same seed, different range
		Random r4 = new Random(1); //same seed, different amount
		Random r5 = new Random(2); //different seed
		int a0[] = new int[10];
		int a1[] = new int[10];
		int a2[] = new int[10];
		int a3[] = new int[10];
		int a4[] = new int[20];
		int a5[] = new int[10];
		
		for(int i = 0; i < 10; i++) {
			a0[i] = r0.nextInt(100);
			a1[i] = r1.nextInt(100);
			a2[i] = r2.nextInt(100);
			a3[i] = r3.nextInt(50);
			a5[i] = r5.nextInt(100);
		}
		
		for(int i = 0; i < a4.length; i++) {
			a4[i] = r4.nextInt(100);
		}
		
		System.out.print("R0: ");
		for(int i = 0; i < 10; i++) {
			System.out.print(a0[i] + " ");
		}
		System.out.println();
		
		System.out.print("R1: ");
		for(int i = 0; i < 10; i++) {
			System.out.print(a1[i] + " ");
		}
		System.out.println();
		
		System.out.print("R2: ");
		for(int i = 0; i < 10; i++) {
			System.out.print(a2[i] + " ");
		}
		System.out.println();
		
		System.out.print("R3: ");
		for(int i = 0; i < 10; i++) {
			System.out.print(a3[i] + " ");
		}
		System.out.println();
		
		System.out.print("R4: ");
		for(int i = 0; i < 20; i++) {
			System.out.print(a4[i] + " ");
		}
		System.out.println();
		
		System.out.print("R5: ");
		for(int i = 0; i < 10; i++) {
			System.out.print(a5[i] + " ");
		}
		System.out.println();
	}
	
	public void insertionSort() {
		initArray();
		InsertionSort.sort0(array);
		printArray("InsertionSort");
		
		initArray();
		InsertionSort.sort1(array);
		printArray("InsertionSort");
		
		initArray();
		InsertionSort.sort2(array);
		printArray("InsertionSort");
	}
	
	public void initArray() {
		Random random = new Random(1); //setSeed(); each time will create same random numbers;
		for(int i = 0; i < array.length; i++) {
			array[i] = new Integer(random.nextInt(RANDOM_RANGE));
		}
	}
	
	public void printArray(String title) {
		if(array.length == 0) {
			System.out.println("Array is NULL");
			return;
		}
		int errorCount = 0;
		Integer previous = array[0];
		System.out.print(title + ": ");
		for(int i = 0; i < array.length; i++) {
			if(array[i] < previous) {
				errorCount++;
				System.err.println("ERROR");
			}
			System.out.print(array[i] + " ");
			previous = array[i];
		}
		if(errorCount != 0) {
			System.err.println("\nError times = " + errorCount);
		} else {
			System.out.println("\nCORRECT!");
		}
	}
	
}
