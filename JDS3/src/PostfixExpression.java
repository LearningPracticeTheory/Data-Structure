import java.util.Scanner;

public class PostfixExpression {
	
	private Scanner in = new Scanner(System.in);
	private MyLinkedStack<Integer> s = new MyLinkedStack<>();
	private int count = 0;//数据-操作符相对量
/*scanner 进行输入，没有处理数量匹配问题*/
	public void count() {
		while(in.hasNext()) {
			String str = in.next();
			if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
				Integer num2 = s.pop();
				Integer num1 = s.pop();
				if(str.equals("+")) {
					s.push(num1 + num2);
				} else if(str.equals("-")) {
					s.push(num1 - num2);
				} else if(str.equals("*")) {
					s.push(num1 * num2);
				} else if(str.equals("/")) {
					s.push(num1 / num2);
				}
			} else {
				s.push(Integer.parseInt(str));
			}
		}
		System.out.println("sum = " + s.pop());
		in.close();
	}
/*使用空格对数据-操作符 进行隔开, 处理数量匹配*/
	public void count(String str) {
		
		if(str == null) {
			return;
		}
		
		String strs[]  = str.split("\\s+");
		Integer num1 = null, num2 = null;
		
		for(int i = 0; i < strs.length; i++) {
			String tmp = strs[i].trim();
			if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/")) {
				if(count >= 2) { //countOper < countNum
					num2 = s.pop();
					num1 = s.pop();
					count -= 2;
				} else { //countOper >= countNum && 前导是 operator
					System.out.println("Operation error");
					return;
				}
				if(tmp.equals("+")) {
					push(num1 + num2);
				} else if(tmp.equals("-")) {
					push(num1 - num2);
				} else if(tmp.equals("*")) {
					push(num1 * num2);
				} else if(tmp.equals("/")) {
					push(num1 / num2);
				}
			} else { //numbers
				push(Integer.parseInt(tmp));
			}
		}
		if(s.size() != 1) { //at last must left only one operator in Stack
			System.out.println("Numbers error"); //cause when i == length; break out 
		} else { //but still have one operator in Stack that doesn't have time to solve
			System.out.println("Sum = " + s.pop());//so pop it at last
		}
	}
	
	public void push(Integer i) {
		s.push(i);
		count++;
	}
	
}
