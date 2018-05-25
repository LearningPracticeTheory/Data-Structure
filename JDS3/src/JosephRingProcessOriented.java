
public class JosephRingProcessOriented {
	
	public static final int QUIT_COUNT = 3;
	public static final int AMOUNT = 500;
	
	public static void main(String args[]) {
		booleanArray(AMOUNT);
		arraySimulationList(AMOUNT);
	}
	
	public static void booleanArray(int amount) {
		boolean array[] = new boolean[amount];

		for(int i = 0; i < array.length; i++) {
			array[i] = true;
		}
		
		int countNum = 0;
		int leftNum = array.length;
		int index = 0;
		
		while(leftNum > 1) {
			if(array[index] == true) {
				countNum++;
				if(countNum == QUIT_COUNT) {
					countNum = 0;
					leftNum--;
					array[index] = false;
				}
			}
			index++;
			if(index == array.length) {
				index = 0;
			}
		}
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == true) {
				System.out.println(i);
				break;
			}
		}
		
	}
	
	public static void arraySimulationList(int amount) {
		int a[] = new int[amount];
		
		a[a.length-1] = 0;
		for(int i = 0; i < a.length-1; i++) {
			a[i] = i+1;
		}
		
		int index = 0;
		int countNum = 0;
		
		while(a[index] != index) {
			countNum++;
			if(countNum == QUIT_COUNT-1) {
				countNum = 0;
				a[index] = a[a[index]];
			}
			index = a[index];
		}
		
		System.out.println(index); 
		
	}
	
}
