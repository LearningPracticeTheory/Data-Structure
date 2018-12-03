import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {

	private static Random r = new Random();
	public static final int RANDOM_AMOUNT = r.nextInt(50); //random test
//	public static final int RANDOM_AMOUNT = 7; //debug test
//	public static final int RANDOM_AMOUNT = 1; //special test
//	public static final int RANDOM_AMOUNT = 15; //normal test
	public static final int RANDOM_RANGE = 20;
	public static final int RANDOM_SEED = 1;
	
	public final static Integer array[] = new Integer[RANDOM_AMOUNT];
	
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
		
//		mergeSort();
		
//		badQuickSort();
		
//		quickSort();
		
		radixSortTest();
		
//		createStringFile();
	}
	
	private static final int STRING_NUMS = 20;
	private static final int STRING_RANGE = 10;
	
	public void createStringFile() {
		File f = new File("text.txt");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < STRING_NUMS; i++) {
			int length = 1 + r.nextInt(STRING_RANGE);
			char chars[] = new char[length];
			for(int k = 0; k < length; k++) {
				chars[k] = (char)('a' + r.nextInt(26));
			}
			String s = new String(chars);
System.out.print(s + " ");
			try {
				bw.write(s);
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\nfile already created");
	}
	
	public void radixSortTest() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
//				this.getClass().getClassLoader().getResourceAsStream("words.txt"))); //in bin/
//					new FileInputStream("words.txt"))); //same length strings
					new FileInputStream("text.txt"))); //random length strings
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<>();
		String s;
		try {
			while((s = br.readLine()) != null) {
				list.add(s);
			}
//			String array[] = new String[list.size()/2];
			String array[] = new String[list.size()];
			int maxLen = 0;
			for(int i = 0; i < array.length; i++) {
				array[i] = list.get(i);
				if(array[i].length() > maxLen) {
					maxLen = array[i].length();
				}
			}
			printStrings(array, "Original");
			
			//same length test
//			RadixSort.radixSortSameLength(array, array[0].length());
//			RadixSort.countingRadixSort(array, array[0].length());
			
			//random length test
			RadixSort.radixSort(array, maxLen);
			
			printStrings(array, "Sorted  ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void printStrings(String array[], String title) {
		System.out.print(title + ": ");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	/*
	 * just show how it work
	 */
	public static<AnyType extends Comparable<AnyType>>
	void badQuickSort() {
		List<Integer> list = new LinkedList<>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		list = badQuickSort(list);
		
		for(int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		
		printArray("bad quick sort");
	}
	
	public static<AnyType extends Comparable<AnyType>>
	List<AnyType> badQuickSort(List<AnyType> items) {
		if(items.size() > 1) {
			List<AnyType> smaller = new LinkedList<>();
			List<AnyType> same = new LinkedList<>();
			List<AnyType> greater = new LinkedList<>();
			AnyType center = items.get(items.size()/2); 
			
			for(AnyType t : items) {
				if(t.compareTo(center) < 0) {
					smaller.add(t);
				} else if(t.compareTo(center) > 0) {
					greater.add(t);
				} else {
					same.add(t);
				}
			}
			
			badQuickSort(smaller);
			badQuickSort(greater);
			
			items.clear();
			items.addAll(smaller);
			items.addAll(same);
			items.addAll(greater);
			
			return items;
		}
		return null;
	}
	
	public void quickSort() {
		initArray();
		QuickSort.sort(array);
		printArray("QuickSort");
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
	
	public static void printArray(String title) {
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
