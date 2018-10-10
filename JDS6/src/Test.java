
public class Test {

	public static void main(String args[]) {
		Test test = new Test();
		test.launch();
	}
	
	public void launch() {
		binaryHeapTest();
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
