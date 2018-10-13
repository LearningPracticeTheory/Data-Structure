import java.util.Random;

public class Test {

	Random r = new Random();
	
	public static void main(String args[]) {
		Test test = new Test();
		test.launch();
	}
	
	public void launch() {
//		binaryHeapTest();
		leftistHeapTest();
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
