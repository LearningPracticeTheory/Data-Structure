//臃肿的代码
public class InfixExpression {
	
	private MyLinkedStack<String> operator = new MyLinkedStack<>();
	private StringBuffer result = new StringBuffer();
	private String strs[];
	private int count = 0;
	private int countNum = 0, countOper = 0;
	
/*多次测试正确实现 infix -> postfix*/
	public String infixToPostfixBasic(String str) {
		strs = str.split("\\s");
		StringBuffer result = new StringBuffer();
		for(; count < strs.length; count++) {
			String tmp = strs[count];
			if(tmp.equals("+") || tmp.equals("-")) {
				if(operator.size() != 0) {
					result.append(operator.pop() + " ");
				}
				operator.push(tmp);
			} else if(tmp.equals("*") || tmp.equals("/")) {
				operator.push(tmp);
				result.append(strs[++count] + " ");//default has next number, should check out
				if(hasNext()) {
					String oper = strs[++count]; //next operator
					if(oper.equals("+") || oper.equals("-")) {
						result.append(operator.pop() + " ");
						if(operator.size() != 0) //use for solving double Level2 at the beginning 
							result.append(operator.pop() + " ");
					} else if(oper.equals("*") || oper.equals("/")) {
						result.append(operator.pop() + " ");
					}
					count--; //go back one step before the current operator, which is a number
				} else { //!hasNext == at the end of length
					result.append(operator.pop() + " ");
				}
			} else { //numbers
				result.append(tmp + " ");
			}
		}
		if(operator.size() != 0) { //no need use while, cause at last the operator.size() <= 1;
			result.append(operator.pop() + " ");
		}
		return result.toString().trim();
	}
/*引入括号 多测正确*/
	public String infixToPostfixAddParenthesis(String str) {
		strs = str.split("\\s+"); //split all space+
		for(; count < strs.length; count++) {
			String tmp = strs[count];
			if(tmp.equals("+") || tmp.equals("-")) {
				if(operator.size() != 0) {
					String s = operator.pop();
					if(!s.equals("(")) {
						result.append(s + " ");
					} else {
						operator.push(s); //put '(' back
					}
				}
				operator.push(tmp);
			} else if(tmp.equals("*") || tmp.equals("/")) {
				operator.push(tmp);
				String operOrNum = strs[++count];
				if(operOrNum.equals("(")) {
					operator.push(operOrNum);
					continue;
				} else { //number after + or -
					result.append(operOrNum + " ");//default has next number, should check out
				}
				if(hasNext()) {
					String oper = strs[++count]; //next operator
					if(oper.equals("+") || oper.equals("-")) {
						result.append(operator.pop() + " ");
						count--; //go back one step before the current operator, which is a number
						continue; //让给 level1 去处理
					} else if(oper.equals("*") || oper.equals("/")) {
						result.append(operator.pop() + " ");
						count--;
					} else if(oper.equals(")")) {
						methodForRightParenthesis();
					}
				} else { //!hasNext == at the end of length
					result.append(operator.pop() + " ");
				}
			} else if(tmp.equals("(")) {
				operator.push(tmp);
			} else if(tmp.equals(")")) {
				methodForRightParenthesis();
			} else { //numbers
				result.append(tmp + " ");
			}
		}
		if(operator.size() != 0) { //no need use while, cause at last the operator.size() <= 1;
			result.append(operator.pop() + " ");
		}
		return result.toString().trim();
	}
/*数量匹配处理*/
	public String infixToPostfixAddParenthesisAndMountMatch(String str) {
		strs = str.split("\\s+");
		for(; count < strs.length; count++) {
			String tmp = strs[count];
			if(tmp.equals("+") || tmp.equals("-")) {
				if(operator.size() != 0) {
					String oper = operator.pop();
					if(!oper.equals("(")) {
						result.append(oper + " ");
					} else {
						operator.push(oper);
					}
				}
				operator.push(tmp);
				countOper++;
			} else if(tmp.equals("*") || tmp.equals("/")) {
				operator.push(tmp);
				countOper++;
				String parenthesisOrNum = null;
				if(++count < strs.length) { 
					parenthesisOrNum = strs[count];
				} else { //at last
					break;
				}
				if(parenthesisOrNum.equals("(")) {
					operator.push(parenthesisOrNum);
					continue;
				} else if(parenthesisOrNum.matches("[0-9]+")) { //number after * or /
					result.append(parenthesisOrNum + " ");
					countNum++;
				} else { //parenthesisOrNum == + - * / or other symbols Error
					System.out.println("Operator error");
					return null;
				}
				if(hasNext()) {
					String oper = strs[++count]; 
					if(oper.matches("[0-9]+")) {
						System.out.println("Number error");
						return null;
					} else if(isOper(oper)){
						; //case like: 5 * 6 )
					} else { //!isOper(); return false
						return null;
					}
					if(oper.equals("+") || oper.equals("-")) {
						result.append(operator.pop() + " ");
						countOper--; //next time loop will deal with the + or - again
						count--; //go back one step before the current operator, which is a number
						continue;
					} else if(oper.equals("*") || oper.equals("/")) {
						result.append(operator.pop() + " ");
						countOper--;//same as up which for going back and checking again.
						count--;
					} else if(oper.equals(")")) {
						methodForRightParenthesis();
					}
				} else { //!hasNext == at the end of length
					result.append(operator.pop() + " ");
				}
			} else if(tmp.equals("(")) {
				operator.push(tmp);
			} else if(tmp.equals(")")) {
				methodForRightParenthesis();
			} else if(tmp.matches("[0-9]+")){ //numbers
				result.append(tmp + " ");
				countNum++;
			} else {
				System.out.println("Operator error");
				return null;
			}
		}
		if(operator.size() != 0) { //no need use while, cause at last the operator.size() <= 1;
			result.append(operator.pop() + " ");
		}
		if(countNum > countOper+1) { //case like: more numbers on head or tail
			System.out.println("Number error");
			return null;
		} else if(countNum <= countOper) { //case like: last element is * or /
			System.out.println("Operator error");
			return null;
		}
		return result.toString().trim();
	}
	
	private boolean hasNext() {
		return count+1 < strs.length;
	}
	
	private void methodForRightParenthesis() {
		boolean flag = true;
		String s = null;
		
		while(flag) { //must use while, cause in () has 1 or 2 operators
			if(operator.size() != 0) {
				s = operator.pop();
			} else {
				break;
			}
			if(!s.equals("(")) {
				result.append(s + " ");
			} else { //KIA '('
				flag = false;
			}
		}
		
		if(operator.size() != 0) { //may be the first operator is '(', the size will be 0
			String oper = operator.pop(); //the operator before '('
			if(oper.equals("+") || oper.equals("-")) {
				operator.push(oper); ///return back, keep it on its location
			} else if(oper.equals("*") || oper.equals("/")){ //solving double '(' side by side
				result.append(oper + " ");
			} else { //oper.equals("("); //一次处理  ')' 只删除一个对应的 '('
				operator.push(oper); //more '(' push it back
			}
		} //pop one more time, which get the operator before '('
		
	}
	
	private boolean isOper(String str) { //use once
		if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
			countOper++;
			return true;
		} else if(str.equals("(") || str.equals(")")) {
			return true;
		} else { //!operator == numbers/other symbols
			System.out.println("Operator error");
			return false;
		}
	}
	
/*基本实现 infix -> postfix*/ //bug
/*	
	public String infixToPostfix(String str) {
		String strs[] = str.split("\\s");
		StringBuffer result = new StringBuffer();
		for(int i = 0; i < strs.length; i++) {
			String tmp = strs[i];
			if(tmp.equals("*") || tmp.equals("/")) {
				result.append(strs[++i] + " "); //next number after tmp
				result.append(tmp + " ");
			} else if(tmp.equals("+") || tmp.equals("-")) {
				operator.push(tmp);
				result.append(strs[++i] + " ");
				if(++i < strs.length) { //has next operator
					String oper = strs[i]; //get next operator
					if(oper.equals("+") || oper.equals("-")) {
						result.append(operator.pop() + " ");
						i--; //position back one step, which is under data
					} else if(oper.equals("*") || oper.equals("/")) {
						result.append(strs[++i] + " "); //next number after oper
						result.append(oper + " ");
						result.append(operator.pop() + " ");
					} 
				} else { //at the end of expression
					result.append(operator.pop());
				}
			} else { //numbers
				result.append(tmp + " ");
			}
		}
//System.out.println("postfix:" + result);
		return result.toString().trim();
	}
*/	
	
}
