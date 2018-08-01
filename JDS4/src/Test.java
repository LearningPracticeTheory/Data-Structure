import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		new Test();
	}
	
	Test() {
		int a[] = {1};
		test(a);
//		System.out.println(a[0]);
//		binaryTreeTest();
//		avlTreeTest();
//		treeSetTest();
//		multipleMappingsTest();
		multipleMappingsPlusTest();
	}
	
	public void multipleMappingsPlusTest() {
		String inPath = "doc/words.txt";
		String outPath = "doc/output.txt";
		WordsExtractionFromFile weff = new WordsExtractionFromFile();
		MultipleMappings mm = new MultipleMappings();
		List<String> list = new ArrayList<>();
		BufferedReader br = null;
		String s = null;
		
		weff.wordsExtraction(inPath, outPath);
		try {
			br = new BufferedReader(new FileReader(new File(outPath)));
			while((s = br.readLine()) != null) {
				list.add(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		mm.printHighChangeable(MultipleMappings.computeAdjacentWords(list), 1);
		mm.printHighChangeable(MultipleMappings.computeAdjacentWordsPlus(list), 1);
		mm.printHighChangeable(MultipleMappings.computeAdjacentWordsPlusPlus(list), 1);
		
	}
	
	public void multipleMappingsTest() {
		MultipleMappings mm = new MultipleMappings();
		List<String> list = new ArrayList<>();
		
		list.add("wine");
		list.add("line");
		list.add("mine");
		list.add("nine");
		list.add("pine");
		
		list.add("wide");
		list.add("wife");
		list.add("wipe");
		list.add("wire");
		
		list.add("wing");
		list.add("wink");
		list.add("wins");
		
		list.add("map");
		list.add("set");
		
		mm.printHighChangeable(MultipleMappings.computeAdjacentWordsPlusPlus(list), 1);
	}
	
	public void treeMapTest() {
//		Map<String, List<String>> map = new TreeMap<>();
//		MySet<List<String>> set = new MyTreeSet<List<String>>(); //AnyType cannot accept List
	}
	
	public void treeSetTest() {
//		Set<Integer> set = new TreeSet<>();
		MySet<Integer> set = new MyTreeSet<>();
		set.add(3);
		set.add(2);
		set.add(1);
		set.add(5);
		set.add(4);
		System.out.println(set.add(3));
		
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		
		System.out.println("\nsize = " + set.size());
		System.out.println(set.contains(1));
		System.out.println(set.isEmpty());
		System.out.println(set.remove(1));
		System.out.println(set.remove(10));
		
	}
	
	public void test(int a[]) {
		a[0] = 100; //point to the same object, a[0] is a local field in heap
		a = null; //original a didn't change at all
	}

	public void avlTreeTest() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(15);
		tree.insert(14);
		tree.insert(13);
		tree.insert(12);
		tree.insert(11);
		tree.insert(10);
	}
	
	public void binaryTreeTest() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
/*insert contains remove test*/
		tree.insert(6);
		tree.insert(4);
		tree.insert(3);
		tree.insert(5);
		tree.insert(1);
		System.out.println(tree.contains(1));
		tree.remove(5); //0 child
		System.out.println(tree.contains(5));
		tree.remove(3); //1 child
		tree.insert(5);
		tree.remove(4); //2 children
		tree.remove(6); //root
		tree.remove(100);
//		tree.inorderTraversal();
		tree.clear();
/*removeMin test*/
		tree.insert(6);
		tree.insert(2);
		tree.insert(8);
		tree.inorderTraversal();
		tree.remove(8); //case 1
		tree.inorderTraversal();
		tree.insert(8);
		tree.insert(10);
		tree.inorderTraversal();
		tree.remove(8); //case 2
		tree.inorderTraversal();
		tree.insert(8);
		tree.insert(12);
		tree.inorderTraversal();
		tree.remove(8); //case 3
		tree.inorderTraversal();
		tree.insert(8);
		tree.insert(9);
		tree.inorderTraversal();
		tree.remove(8); //case 4
		tree.inorderTraversal();
/*traversal test*/
		tree.insert(6);
		tree.insert(4);
		tree.insert(3);
		tree.insert(5);
		tree.insert(2);
		tree.insert(1);
		tree.preorderTraversal();
		tree.inorderTraversal();
		tree.postorderTraversal();
		
	}
	
}
