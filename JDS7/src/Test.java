import java.util.Random;

public class Test {
	
	public static final int RANDOM_AMOUNT = 20;
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
		insertionSort();
	}
	
	public void randomTest() {
		Random r0 = new Random();
		Random r1 = new Random(1);
		Random r2 = new Random(1);
		Random r3 = new Random(2);
		int a0[] = new int[10];
		int a1[] = new int[10];
		int a2[] = new int[10];
		int a3[] = new int[10];
		
		for(int i = 0; i < 10; i++) {
			a0[i] = r0.nextInt(100);
			a1[i] = r1.nextInt(100);
			a2[i] = r2.nextInt(100);
			a3[i] = r3.nextInt(100);
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
		System.out.print(title + ": ");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
}
