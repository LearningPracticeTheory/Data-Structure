
public class BalanceSymbols {
	
	public static void main(String args[]) {
		new BalanceSymbols();
	}
	
	BalanceSymbols() {
		String str = "[()]{1+2*[2*3/(5/5)+11]-4}[{}]";
		System.out.println(isBalanceSymbols(str));
	}
	
	public boolean isBalanceSymbols(String str) { //{[()]}
		MyLinkedStack<Character> s = new MyLinkedStack<>();
		int countPush = 0;
		int countPop = 0;
		int cbpp = 0; //countBetweenPushPop
		for(int i = 0; i < str.length(); i++) {
			Character t = str.charAt(i);
			if(t.equals('{') || t.equals('[') || t.equals('(')) {
				s.push(t);
				countPush++;
				cbpp++;
			} else if(t.equals('}') || t.equals(']') || t.equals(')')) {
				countPop++;
				if(cbpp != 0) {
					Character tmp = s.pop();
					cbpp--;
					if(t.equals('}') && !tmp.equals('{')) {
						return false;
					} else if(t.equals(']') && !tmp.equals('[')) {
						return false;
					} else if(t.equals(')') && !tmp.equals('(')) {
						return false;
					}
				}
			}
		}
		if(countPush != countPop) {
			return false;
		}
		return true;
	}
	
}
