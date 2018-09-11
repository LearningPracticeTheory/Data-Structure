import java.util.Random;

public class StringHashFamily implements HashFamily<String> {

	private final int MULTIPLIERS[]; //final array which means cannot change length
	private Random r = new Random(); //but still can change elements
	private final int LIMIT_ARRAY_RANGE = 100;
	
	StringHashFamily(int numHashFunctions) { 
		MULTIPLIERS = new int[numHashFunctions];
		generateNewFunctions();
	}

	@Override
	public int hash(String x, int which) {
		final int multiplier = MULTIPLIERS[which];
		int hashVal = 0;
		for(int i = 0; i < x.length(); i++) {
//			hashVal *= multiplier + x.charAt(i); //ERROR: multiple then assignment, NO add!
			hashVal = multiplier * hashVal + x.charAt(i);
		}
		return hashVal;
	}

	@Override
	public int getNumberOfFunctions() { //
		return MULTIPLIERS.length;
	}

	@Override
	public void generateNewFunctions() { //initialized or reinitialized multipliers
		for(int i = 0; i < MULTIPLIERS.length; i++) {
			MULTIPLIERS[i] = r.nextInt(LIMIT_ARRAY_RANGE);
		}
	}
	
}
