import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class tmp {
	public static void main(String args[]) {
/*增强 for 循环 && Iterator 遍历时删除元素的影响*/
		/*
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		foreachRemove(a);
		iteratorRemove(a);
		*/
/*ArrayList test*/
		/*
		List<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(0, 0);//insert
//		al.add(-1, -1); //IndexOutOfBoundsException
//		al.add(3, 3); //same up
		Iterator<Integer> i = al.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
//		al.remove(2); //same up
		*/
/*LinedList test*/
		/*
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(1);
		ll.add(0, 0);
		ll.add(2);
		ll.add(3, 3); //same as add(3) at last position
//		ll.add(5, 5);//IndexOutOfBoundsException
//		ll.get(100); //same up
		ll.set(1, 100);
		Iterator<Integer> i = ll.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println("\n" + ll.pop() + " " + ll.remove());
		System.out.println(ll.size());
		ll.remove();
//		ll.remove();//NoSuchElementException
		*/
/*Stack test*/
		/*
		Stack<Integer> s = new Stack<>();
		s.add(0);
		s.push(1);
		s.add(2, 2);
		Iterator<Integer> i = s.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println("\n" + s.pop() + " " + s.remove(s.size()-1));
		System.out.println("size = " + s.size());
		s.remove(s.size()-1);
//		s.remove(s.size()-1); //ArrayIndexOutOfBoundsException;
		*/
/*MyArrayStack && MyLinkedStack test*/
		/*
		MyArrayStack<Integer> s = new MyArrayStack<>();
//		MyLinkedStack<Integer> s = new MyLinkedStack<>();
		s.push(0);
		s.push(1);
		s.push(2);
		Iterator<Integer> i = s.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println("\n" + s.pop() + " " + s.remove());
//		s.remove();
//		s.pop();
		s.clear();
		System.out.println("size = " + s.size());
		*/
/*postfix expression test*/
		/*
		PostfixExpression postfix = new PostfixExpression();
//		count(); //6 5 2 3 + 8 * + 3 + *
		String str = "6 5 2 3 + 8 * + 3 + *";
		str = "2 3 4 * + 5 6 * + ";
		str = "6 5 2 3 + 8 * + 3 + * +"; //more operator error test
		str = "100 6 5 2 3 + 8 * + 3 + *"; //more numbers error test
		postfix.count(str);
		*/
/*infix expression -> postfix expression test*/
/*		
		InfixExpression infix = new InfixExpression();
		PostfixExpression postfix = new PostfixExpression();
*/
/*test for infixToPostfix();*/
/*
		String str = "1 + 2 + 3 * 4 + 5 * 6";
		str = "3 * 4"; //单个运算符的操作，最简式		
		str = "1 + 2 + 3 * 4";
		str = "1 + 2 - 3 * 4"; //+-同级顺序计算 Level1，check pop 是否得当
		str = "1 - 2 + 3 * 4 / 5 + 6 * 7"; //同级顺序计算  + - * /
		str = "1 * 2 / 3 - 4 + 5 * 6 * 7"; //error 前导全是 * / Level2
		str = "1 * 2 / 3 * 4 / 5 * 6 * 7"; //error 前导全是 * / Level2
System.out.println("infix:\t" + str);
		String result = infix.infixToPostfixBasic(str);
System.out.println("postfix:" + result);
		postfix.count(result);
*/
/*test for add parenthesis*/
/*
	//No parenthesis //原先的算法没有受到影响
		String str = "3 * 4"; //单个运算符的操作，最简式		
		str = "1 + 2 - 3 * 4"; //+-同级顺序计算 Level1，check pop 是否得当
		str = "1 - 2 + 3 * 4 / 5 + 6 * 7"; //同级顺序计算  + - * /
		str = "1 * 2 / 3 - 4 + 5 * 6 / 7"; //error 前导全是 * / Level2
	//Single parenthesis
		str = "1 - ( 2 * 3 + 4 ) * 5";
		str = "1 - ( 2 * 3 * 4 + 5 * 6 ) + 7";
		str = "1 + 2 / ( 3 + 4 * 5 ) * 6";
		str = "1 + 2 * 3 / ( 4 * 5 + 6 ) * 7";
	//Double parenthesis
		str = "1 + 2 - ( 3 + 4 ) - ( 5 + 6 )";//同级+括号独立分开
		str = "1 + 2 * ( 3 * 4 ) / ( 5 + 6 )";//混合+括号独立分开
		str = "1 + 2 * ( 3 * ( 4 - 5 ) / 6 )";//混合+括号镶嵌分开
		str = "1 + 2 * ( ( 3 * 4 - 5 ) / 6 )";//混合+括号镶嵌前导临边 
		str = "1 + 2 / ( 3   *  ( 4  - 5 / 6 ) )";//混合+括号镶嵌尾端临边 + space+
	//More parenthesis
		str = "1 + 2 / ( ( ( 3 - 4 ) * 5 ) * 6 )"; //处理')'只删除一个对应的'('
		str = "1 + 2 / ( 3 * ( 4 - ( 5 + 6 ) ) )";
System.out.println("infix:\t" + str);
		String result = infix.infixToPostfixAddParenthesis(str);
System.out.println("postfix:" + result);
		postfix.count(result);
*/
/*test for MountMatch*/
	//Original test correct
		/*
		String str = "1";
		str = "1 - ( 2 * 3 + 4 ) * 5";
		str = "1 - ( 2 * 3 * 4 + 5 * 6 ) + 7";
		str = "1 + 2 / ( 3 + 4 * 5 ) * 6";
		str = "1 + 2 * 3 / ( 4 * 5 + 6 ) * 7";
		
		str = "1 + 2 * ( 3 + 4 ) / ( 5 + 6 )";
		str = "1 + 2 * ( 3 * ( 4 - 5 ) / 6 )";
		str = "1 + 2 * ( ( 3 * 4 - 5 ) / 6 )";
		str = "1 + 2 / ( 3   *  ( 4  - 5 / 6 ) )";
		
		str = "1 + 2 / ( ( ( 3 - 4 ) * 5 ) / 6 )";
		str = "1 + 2 / ( 3 * ( 4 - ( 5 + 6 ) ) )";
		*/
/*
	//Number error test
		String str = "1 + 2 + 3 * 4 / 5 + 6 6";
		str = "1 1 + 2 + 3 * 4 / 5 + 6";
		str = "1 + 2 + 3 * 100 4 / 5 + 6";
		str = "1 + 2 + 3 * 4 / 100 5 + 6";
		str = "1 + 2 ( + 3 * 100 4 / ) 5 + 6 /";
	//Operator error test
		str = "+";
		str = "*";
		str = "1 + ( 2 + 3 / 4 +  5 ) * 6 * + /"; //last + is not number
		str = "1 + ( 2 + 3 / 4 +  5 ) * / 6";
		str = "1 + ( 2 + 3 / 4 + 5 / ) * / 6";
		str = "* + 1 + ( 2 + 3 / 4 +  5 ) * 6 ";
		str = "1 + ( 2 + 9 / 4 + & 5 ) = * 6"; //other symbols
	//error
		str = "1 + ( 2 + 3 / ( 4 + ) 5 ) * 6"; //镶嵌括号格式错误未报错
System.out.println("infix:\t" + str);
		String result = infix.infixToPostfixAddParenthesisAndMountMatch(str);
System.out.println("postfix:" + result);
		postfix.count(result);
*/
/*Queue test*/
/*		
		MyArrayQueue<Integer> queue = new MyArrayQueue<>();
		queue.add(1);
		System.out.println(queue.size() + " " + queue.poll() + " " + queue.size());
		queue.add(0);queue.add(1);
		System.out.println("peek = " + queue.peek(1));
		System.out.println("size = " + queue.size());
		
		Iterator<Integer> i = queue.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		
		queue.clear();
		System.out.println("\nsize = " + queue.size());
//		queue.poll(); || queue.remove(); //will throw Exception catch by self	
		queue.add(100);
		System.out.println("poll after clear && add one element = " + queue.poll());
		queue.add(0);queue.add(1);queue.add(2);queue.add(3);queue.add(4);
		
		i = queue.iterator();
		int count = 0;
		while(i.hasNext()) {
			if(count == 2) {
				queue.remove();queue.remove();
//				queue.remove(); //hasNext at index == 2, remove 2 and 3, left 4 
			}
			System.out.print(i.next() + " "); 
			count++;
		} 
*/	
/*MyArrayQueueOptimize test */
/*		
		MyArrayQueueOptimize<Integer> queueOpt = new MyArrayQueueOptimize<>();
		queueOpt.add(0);queueOpt.add(1);queueOpt.add(2);queueOpt.add(3);queueOpt.add(4);
		queueOpt.add(5);queueOpt.add(6);queueOpt.add(7);queueOpt.add(8);queueOpt.add(9);
System.out.println("size = " + queueOpt.size());
		queueOpt.add(100);

		Iterator<Integer> it = queueOpt.iterator();
		while(it.hasNext()) {
//			queueOpt.remove(); //Both will throw ConcurentModificationException();
//			it.remove(); //iterator cannot modify element when traversal 
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		int size = queueOpt.size();
		for(int i = 0; i < size; i++) {
			if(i == 2) { //can edit elements when traversal, not quit true
				queueOpt.remove();
				size--;
			}
			System.out.print(queueOpt.poll() + " ");
		}
		
//		queueOpt.poll(); //throw Exception test
		queueOpt.add(101); //add again while size is 0
		queueOpt.clear();
		System.out.println("\nsize = " + queueOpt.size());
*/
/*MyLinkedQueue test*/
/*		
		MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();
		queue.add(0);queue.add(1);
		System.out.println(queue.poll() + " " + queue.poll());
		queue.add(0);queue.add(1);
		System.out.println(queue.poll() + " " + queue.poll());
//		queue.poll(); //throw Exception test
		queue.add(0);queue.add(1);queue.add(2);queue.add(3);queue.add(4);
		Iterator<Integer> i = queue.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println("\nsize = " + queue.size());
		queue.clear();
*/
/*MyLinedStackWithSingleTrack test*/
/*		
		MyLinkedStackWithSingleTrack<Integer> stack = new MyLinkedStackWithSingleTrack<>();
		stack.push(0);stack.push(1);
		System.out.println(stack.pop() + " " + stack.pop());
		stack.push(0);stack.push(1);stack.push(2);stack.push(3);stack.push(4);
		Iterator<Integer> i = stack.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println("\nsize = " + stack.size());
		stack.clear();
*/		
/*MyArrayList test*/
/*		
		MyArrayList<Integer> mal = new MyArrayList<>();
		System.out.println(mal.isEmpty());
		mal.add(1);
		mal.add(0, 0); //insert
		mal.set(1, 11); //change the value
		mal.add(3);mal.add(4);mal.add(5);mal.add(6);mal.add(7);
		mal.add(8);mal.add(9);mal.add(10);mal.add(11);mal.add(12);
		Iterator<Integer> i = mal.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		mal.remove();mal.remove();mal.remove();mal.remove();mal.remove();
		mal.remove();mal.remove();mal.remove();mal.remove();mal.remove();
		mal.remove();
		mal.set(0, 1);
		i = mal.iterator();
		while(i.hasNext()) { //remove() without last one
			System.out.print("\nelement " + i.next() + " ");
		}
//		i.next(); //out of bounds, no such element
		mal.clear();
*/
/*MyArrayList addAll test*/
/*		
		MyArrayList<Integer> al = new MyArrayList<>();
		al.add(0);al.add(1);
		List<Integer> list = new ArrayList<>();
		list.add(100);list.add(101);
		al.addAll(list);
		Iterator<Integer> it = al.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
//		ArrayList.addAll(); //track the source code
*/		
/*MyLinkedList test*/
/*
 		MyLinkedList<Integer> mll = new MyLinkedList<>();
		mll.add(0);
		mll.add(1);
		mll.add(1, 2);//insert
//		mll.add(100, 100); //insert
		System.out.print(mll.get(0) + " " + mll.get(1) + " ");
		System.out.println(mll.get(2) + " " + mll.get(3)); //idx3 == tail
//		System.out.println(mll.get(4));//idx4 == tail.next;
		System.out.println(mll.set(0, 100) + " " + mll.get(0));
		System.out.println(mll.remove(0));
		
		for(int i = 0; i < mll.size(); i++) {
			System.out.print(mll.get(i) + " ");
		}
		System.out.println();
		
		mll.remove(); mll.remove();
		mll.add(0); mll.add(1); mll.add(2);
		Iterator<Integer> i = mll.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		mll.clear();
		System.out.println();

		mll.add(0);mll.add(1);mll.add(2);mll.add(3);mll.add(4);
		i = mll.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println();
*/
/*调换 Node 的位置*/ 
/*
		mll.switchCurrentAndRight(0);
		i = mll.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println();
*/
/*调换 XY 位置*/
/*
//		mll.switchXY(4, 0);
		mll.switchXY(0, 4); //前后顺序无关
		i = mll.iterator();
		while(i.hasNext()) {
			System.out.print(i.next() + " ");
		}
		System.out.println();
*/
/*MyLinedList removeAll test*/
/*
		MyLinkedList<Integer> link = new MyLinkedList<>();
		link.add(0);link.add(1);link.add(2);link.add(3);link.add(4);link.add(2);
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);list.add(2);
		link.removeAll(list);
		Iterator<Integer> it = link.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
*/
/*MyArrayList ListIterator test*/
/*		
		MyArrayList<Integer> array = new MyArrayList<>();
//		ArrayList<Integer> array = new ArrayList<>();
		array.add(0);array.add(1);array.add(2);array.add(3);array.add(4);
		ListIterator<Integer> li = array.listIterator();
		int tmp = 0;
		System.out.print(li.previousIndex() + " "); //-1
		System.out.println(li.nextIndex()); //0
		while(li.hasNext()) {
			tmp++;
			if(tmp == 4) {
//				li.remove(); //4 3 1 0
//				li.remove(); //IllegalStateException
//				li.add(100); //4 3 100 2 1 0
//				li.add(888); //4 3 888 100 2 1 0
//				li.set(100); //4 3 100 1 0 
//				li.set(888); //4 3 888 1 0
			}
			System.out.print(li.next() + " ");
		}
		System.out.println(); //4 3 2 1 0
		System.out.print(li.previousIndex() + " "); //4
		System.out.println(li.nextIndex()); //5
		while(li.hasPrevious()) { //next previous use common index
			System.out.print(li.previous() + " ");
		}
		System.out.println();
		System.out.print(li.previousIndex() + " "); //-1
		System.out.println(li.nextIndex()); //0
*/
/*LazyDeletion test*/
/*		
//		MyArrayList<Integer> array = new MyArrayList<>();
		MyLinkedList<Integer> array = new MyLinkedList<>();
		array.add(0);array.add(1);array.add(2);array.add(3);array.add(4);
		array.add(5);array.add(6);array.add(7);array.add(8);array.add(9);
		array.lazyDeletion(0);array.lazyDeletion(1);
		array.lazyDeletion(4);array.lazyDeletion(6);
		System.out.println(array.lazyDeletion(8));
		Iterator<Integer> it = array.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
*/		
/*RingListImplementQueue test*/
		
		MyLinkedQueueOptimize<Integer> queue = new MyLinkedQueueOptimize<>();
		queue.add(0);queue.add(1);queue.add(2);queue.add(3);queue.add(4);
		System.out.println("poll = " + queue.poll());
		queue.add(100);
		queue.add(999);
		
		Iterator<Integer> it = queue.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		queue.poll();queue.poll();queue.poll();queue.poll();queue.poll();queue.poll();
//		queue.poll(); //Exception test
		queue.add(0);queue.add(1);queue.add(2);queue.add(3);queue.add(4); //KIA All, add again
		
		it = queue.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		queue.clear();
		System.out.println("size = " + queue.size());

	}
	
	public static void foreachRemove(List<Integer> list) {
		for(Integer t : list) { //Exception, cannot remove when for each
			if(t.equals(3)) {
				list.remove(t);
			}
			System.out.println(t);
		}
	}
	
	public static void iteratorRemove(List<Integer> list) {
		Iterator<Integer> i = list.iterator();
		while(i.hasNext()) {
			Integer t = i.next();
			if(t.equals(2)) {
				i.remove();
			}
			System.out.print(t + " ");
		}
		System.out.println();
		for(Integer t : list) {
			System.out.print(t + " ");
		}
	}
}
