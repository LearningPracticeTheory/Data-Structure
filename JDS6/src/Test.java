import java.util.PriorityQueue;
import java.util.Random;

public class Test {

	Random r = new Random();
	
	public static void main(String args[]) {
		Test test = new Test();
		test.launch();
	}
	
	public void launch() {
//		binaryHeapTest();
//		leftistHeapTest();
//		binomialQueueTest();
//		priorityQueueTest();
		binaryHeapFindLeavesTest();
	}
	
	private void binaryHeapFindLeavesTest() {
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
