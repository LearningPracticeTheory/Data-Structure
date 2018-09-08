
public class Prime {

	public static boolean isPrime(int n) {
		boolean flag = true;
		int m = (int)Math.sqrt((double)n);
		for(int i = 2; i < m; i++) {
			if(n % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static int nextPrime(int n) {
		boolean flag = isPrime(n);
		while(!flag) {
			flag = isPrime(++n);
		}
		return n;
	}
	
}
