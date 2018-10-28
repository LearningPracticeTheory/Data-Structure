import java.util.PriorityQueue;
import java.util.Random;

public class Test {

	Random r = new Random();
	
	int maxErrorCount = 0;
	int minErrorCount = 0;
	int loopTimes = 500;
	int randomRange = 100;
	
	public static void main(String args[]) {
		Test test = new Test();
		test.launch();
	}
	
	public void launch() {
//		binaryHeapTest();
//		leftistHeapTest();
//		binomialQueueTest();
//		priorityQueueTest();
//		binaryHeapFindLeavesTest();
//		binaryHeapChainTest();
//		min_maxHeapTest();
		/*
		for(int i = 0; i < 100; i++) {
			mutipleBuildHeapTest();
			if(maxErrorCount > 0 || minErrorCount > 0) {
				System.out.println("ERROR");
				break;
			}
		}
		*/
		/*
		for(int i = 0; i < 100; i++) {
			mergeOfMinMaxHeapTest();
			if(maxErrorCount > 0 || minErrorCount > 0) {
				System.out.println("ERROR");
				break;
			}
		}
		*/
//		leftistHeapInsertTest();
		leftistHeapBuildHeapTest();
	}
	
	public void leftistHeapBuildHeapTest() {
		/*
		Queue<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(1); //can repeat
		list.add(2);
		System.out.println(list.size());
		System.out.print(list.poll() + " ");
		System.out.print(list.poll() + " ");
		System.out.println(list.poll()); //from head to tail
		*/
		Integer array[] = new Integer[1000];
		for(int i = 0; i < array.length; i++) {
			if(i % 40 == 0) {
				System.out.println();
			}
			array[i] = new Integer(1 + r.nextInt(100));
			System.out.print(array[i] + " ");
		}
		System.out.println();
		LeftistHeap<Integer> heap = new LeftistHeap<>(array);
		heap.insert(0);
		int size = heap.size();
		System.out.println("\n-----------------------");
		System.out.println("heap.size == " + heap.size());
		System.out.println("-----------------------");
		for(int i = 0; i < size; i++) {
			if(i % 40 == 0) {
				System.out.println();
			}
			System.out.print(heap.deleteMin() + " ");
		}
	}
	
	/*
	 * use debug to check out
	 */
	public void leftistHeapInsertTest() {
		LeftistHeap<Integer> heap = new LeftistHeap<>();
		for(int i = 1; i <= 10; i++) {
//			heap.insert(i);
			heap.insert(10-i);
		}
	}
	
	public void minMaxHeapPrint(MinMaxHeap<Integer> heap) {
		Integer max[] = new Integer[heap.size()/2+1];
		Integer min[] = new Integer[heap.size()/2+1];
		int maxIndex = 0;
		int minIndex = 0;
		maxErrorCount = 0;
		minErrorCount = 0;
		
		int size = heap.size();
		for(int k = 0; k < size; k++) {
			if(k % 2 == 0) {
				max[maxIndex++] = heap.deleteMax();
				if(maxIndex > 2 && max[maxIndex-2] < max[maxIndex-1]) {
					maxErrorCount++;
					System.out.println("MAAAAAX ERROR");
				}
			} else {
				min[minIndex++] = heap.deleteMin();
				if(minIndex > 2 && min[minIndex-2] > min[minIndex-1]) {
					minErrorCount++;
					System.out.println("MIN ERROR");
				}
			}
		}
			
		System.out.print("Max sort: ");
		for(int i = 0; i < max.length; i++) {
			System.out.print(max[i] + " ");
		}
		System.out.println();
		
		System.out.print("Min sort: ");
		for(Integer m : min) {
			System.out.print(m + " ");
		}
		System.out.println();
		System.out.println( " Max error times: " + maxErrorCount);
		System.out.println( " Min error times: " + minErrorCount);
//		System.out.println(heap2.isEmpty());
	}
	
	public void mergeOfMinMaxHeapTest() {
		MinMaxHeap<Integer> heap1 = new MinMaxHeap<>();
		MinMaxHeap<Integer> heap2 = new MinMaxHeap<>();
		
		for(int i = 0; i < loopTimes; i++) {
			heap1.insert(1 + r.nextInt(randomRange));
			heap2.insert(r.nextInt(20) + r.nextInt(randomRange));
		}
		
		heap1.merge(heap2);
		
		minMaxHeapPrint(heap1);
	}
	

	public void mutipleBuildHeapTest() {
		MinMaxHeap<Integer> heap = new MinMaxHeap<>();
		
		for(int i = 0; i < loopTimes; i++) {
			heap.insert(1 + r.nextInt(randomRange));
		}
		
		minMaxHeapPrint(heap);
	}
	
	public void min_maxHeapTest() {
		MinMaxHeap<Integer> heap = new MinMaxHeap<>();
		
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		heap.insert(5);
		
		for(int i = 0; i <= 10; i++) {
			heap.insert(2 + r.nextInt(20));
		}
		
		/*
		 * findMin & Max Test
		 */
		System.out.println("min = " + heap.findMin());
		System.out.println("max = " + heap.findMax());
		
		/*
		 * deleteMin Test
		 */
		System.out.print("deleteMin = ");
		int size = heap.size();
		for(int i = 0; i < size - 1; i++) {
			System.out.print(heap.deleteMin() + " ");
		}
		System.out.println();
		System.out.println("findMax = " + heap.findMax());
		System.out.println("heap.size == " + heap.size());
		heap.clear();
		
		/*
		 * deleteMax Test
		 */
		for(int i = 0; i < 10; i++) {
			heap.insert(r.nextInt(20));
		}
		
		System.out.print("deleteMax = ");
		size = heap.size();
		for(int i = 0; i < size - 1; i++) {
			System.out.print(heap.deleteMax() + " ");
		}
		
		System.out.println();
		System.out.println("findMax = " + heap.findMax() + " == " + heap.findMin() + " = findMin");
		System.out.println("heap.size == " + heap.size());
		heap.clear();
		
		/*
		 * deleteMax&Min at the same time
		 */
		for(int i = 0; i < 129; i++) {
			heap.insert(1 + r.nextInt(40));
		}
		
		minMaxHeapPrint(heap);
		
		/*
		 * special case test
		 * allLeft < allRight;
		 */
		/*
		System.out.println("-------------------------");
		
		heap.clear();
		heap.insert(3);
		heap.insert(8);
		heap.insert(20);
		heap.insert(4);
		heap.insert(5);
		heap.insert(10);
		heap.insert(12);
		System.out.println(heap.deleteMin());
		 */
		
		/*
		 * buildHeap Test
		 */
		Integer array[] = new Integer[1290];
		for(int i = 0; i < array.length; i++) {
			array[i] = new Integer(1+r.nextInt(100));
		}
		heap = new MinMaxHeap<>(array);
		
		minMaxHeapPrint(heap);
	}
	
	public void binaryHeapChainTest() {
		BinaryHeapChain<Integer> heap = new BinaryHeapChain<>();
		
		heap.insert(2);
		heap.insert(1);
		heap.insert(3);
		heap.insert(4);
		heap.insert(0);
		heap.insert(2);
		
		System.out.print(heap.deleteMin() + " ");
		System.out.print(heap.deleteMin() + " ");
		System.out.print(heap.deleteMin() + " ");
		System.out.print(heap.deleteMin() + " ");
		System.out.println(heap.deleteMin());
		
		for(int i = 0; i < 10; i++) {
			heap.insert(10 + r.nextInt(30));
		}
		
		for(int i = 0; i < 10; i++) { //levelOrderTraversal
			System.out.print(heap.findIndexElement(i+1) + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < 10; i++) {
			System.out.print(heap.deleteMin() + " ");
		}

		heap.clear();
		System.out.println();
		
		/*
		 * mergeTest		
		 */		
		heap.insert(2);
		heap.insert(1);
		heap.insert(8);
		heap.insert(6);
		heap.insert(0);
		
		System.out.print("heap1: ");
		for(int i = 0; i < heap.size(); i++) { //levelOrderTraversal
			System.out.print(heap.findIndexElement(i+1) + " ");
		}
		System.out.println();
		
		BinaryHeapChain<Integer> heap2 = new BinaryHeapChain<>();
		
		for(int i = 0; i < 3 + r.nextInt(8); i++) {
			heap2.insert(r.nextInt(3) + r.nextInt(15));
		}
		
		System.out.print("heap2: ");
		for(int i = 0; i < heap2.size(); i++) { //levelOrderTraversal
			System.out.print(heap2.findIndexElement(i+1) + " ");
		}
		System.out.println();
		
		heap.merge(heap2);
		System.out.print("1 merge 2: ");
		for(int i = 0; i < heap.size(); i++) { //levelOrderTraversal
			System.out.print(heap.findIndexElement(i+1) + " ");
		}
		System.out.println(" heap2 == null ? " + heap2.isEmpty());
		
		heap2.merge(heap);
		System.out.print("2 merge 1: ");
		for(int i = 0; i < heap2.size(); i++) { //levelOrderTraversal
			System.out.print(heap2.findIndexElement(i+1) + " ");
		}
		System.out.println(" heap1 == null ? " + heap.isEmpty());
		
		System.out.print("sort:      ");
		int heapSize = heap2.size();
		for(int i = 0; i < heapSize; i++) {
			System.out.print(heap2.deleteMin() + " ");
		}
		
	}

	public void binaryHeapFindLeavesTest() {
		BinaryHeap<Integer> bh = new BinaryHeap<>();
//		Integer leaves[] = null; //Comparable --X--> Integer
		Comparable<Integer> leaves[] = null;
		for(int i = 1; i <= 10; i++) {
			bh.insert(i);
			leaves = bh.findLeaves();
			for(Comparable<Integer> leaf : leaves) {
				System.out.print(leaf + " ");
			}
			System.out.println();
		}
		bh.clear();
		System.out.println(bh.findLeaves());
	}

	/*
	 * The priority queue of java standard library
	 */
	public void priorityQueueTest() { //sort
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		System.out.print(pq.add(1) + " ");
		System.out.print(pq.add(3) + " ");
		System.out.println(pq.add(2));
		System.out.println(pq.contains(1));
		System.out.println(pq.remove());
		System.out.println(pq.peek());
		System.out.println(pq.poll());
		System.out.println(pq.size());
		pq.clear();
		System.out.println(pq.isEmpty());
	}
	
	public void binomialQueueTest() {
		BinomialQueue<Integer> bq = new BinomialQueue<>();
					//case:
		bq.insert(7); // 2
		bq.insert(6); // 3 -> 4 /*expandTrees(2)*/ 
		bq.insert(5); // 2 -> 1
		bq.insert(4); // 3 -> 5 -> 4 /*expandTrees(3)*/
		bq.insert(3); // 2 -> 0 -> 1
		bq.insert(2); // 3 -> 4 -> 1
		bq.insert(1); // 2 -> 1 -> 1
		/*
		 * sort from small -> large
		 */
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.print(bq.deleteMin() + " ");
		System.out.println(bq.isEmpty());
//		System.out.println(bq.deleteMin()); //Exception test
		
		for(int i = 0; i < 10; i++) {
			bq.insert(r.nextInt(20));
		}
		for(int i = 0; i < 10; i++) {
			System.out.print(bq.deleteMin() + " ");
		}
		
		bq.clear();
		System.out.println(bq.isEmpty());
		
		bq.insert(7);
		bq.insert(6);
		bq.insert(5);
		bq.insert(1);
		bq.insert(4);
		bq.insert(3);
		bq.insert(2);
		
		System.out.println(bq.deleteMin());
		
	}
	
	public void leftistHeapTest() {
		LeftistHeap<Integer> lh1 = new LeftistHeap<>();
		LeftistHeap<Integer> lh2 = new LeftistHeap<>();
		
		/*
		 * insert merge Test
		 */
		for(int i = 0; i < 10; i++) {
			lh1.insert(5 + r.nextInt(20));
		}
		for(int i = 0; i < 8; i++) {
			lh2.insert(r.nextInt(15));
		}
		System.out.print("lh1.min = " + lh1.findMin() + "; ");
		System.out.println("lh2.min = " + lh2.findMin());
		lh1.merge(lh2);
		System.out.println(lh1.deleteMin());
		System.out.println(lh1.isEmpty() + " " + lh2.isEmpty());
		
		/*
		 * deleteMin Test
		 */
		int size = lh1.size();
		for(int i = 0; i < size-1; i++) {
			System.out.print(lh1.deleteMin() + " ");
		}
		System.out.println("\n" + "heap.size == " + lh1.size());
		
		lh1.clear();
		System.out.println(lh1.isEmpty());
	}
	
	public void binaryHeapTest() {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(0);
		heap.insert(5);
		heap.insert(2);
		heap.insert(6);
		heap.insert(7);
		heap.insert(4);
		heap.insert(3);
/*
 * minHeap sort from smallest to biggest -> 
 * deleteMin of all
 * <- maxHeap sort from biggest to smallest
 */
		/*
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		System.out.print(heap.findMin() + " ");
		System.out.println(heap.deleteMinImplByMyself());
		*/
		System.out.println(heap.deleteMin());
		System.out.println(heap.deleteMin());
		System.out.println(heap.deleteMin());
		System.out.println(heap.deleteMin());
		System.out.println(heap.deleteMin());
		System.out.println(heap.deleteMin());
//		System.out.println(heap.deleteMin());
//		System.out.println(heap.deleteMin()); //size == 0; deleteMin throw Exception

		heap.clear();
		System.out.println(heap.isEmpty());
		
	}
	
}
