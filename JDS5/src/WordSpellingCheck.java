import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordSpellingCheck {
	
	private static int lines[] = null;
	private BufferedReader br = null;
	private QuadraticProbingHashTable<String> qpht = null;
	private List<String> list = null;
	private Iterator<String> iterator = null;
	
	public WordSpellingCheck() {
		br = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("words/words.txt"))
				);
		list = new ArrayList<>();
		lines = new int[readLinesOfFile()];
		initializedLines();
		qpht = new QuadraticProbingHashTable<>();
		initializedQuadraticProbingHashTable();
	}
	
	private void initializedQuadraticProbingHashTable() {
		String str = null;
		int line = 0;
		iterator = list.iterator();
		while(iterator.hasNext()) {
			str = iterator.next();
			qpht.insert(str);
			if(qpht.sizeOfHashEntry() > lines.length) {
				expandLines(qpht.sizeOfHashEntry());
			}
			lines[qpht.findPos(str)] = line++; //After qpth.expand, 
				//its size bigger than lines.size, so lines[] should expand before assignment
		}
	}
	
	private void initializedLines() {
		for(int i = 0; i < lines.length; i++) {
			lines[i] = -1;
		}
	}
	
	private void expandLines(int newSize) {
		int array[] = lines;
		lines = new int[newSize];
		initializedLines();
		for(int i = 0; i < array.length; i++) {
			if(array[i] != -1) {
				lines[i] = array[i];
			}
		}
	}
	
	private int readLinesOfFile() { //default use BufferedReader's Input File
		int lines = 0;
		String str = null;
		try {
			while((str = br.readLine()) != null) {
				list.add(str);
				lines++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public boolean findWord(String target) {
		if(qpht.contains(target)) {
			return true;
		}
		return false;
	}
	
	public int findLineOfWord(String target) {
		if(findWord(target)) {
			return lines[qpht.findPos(target)]+1;
		}
		return -1;
	}
	
	public void deleteOneChar(String target) {
		int len = target.length();
		String tmp = target;
		System.out.print("deleteOneChar:\n");
		for(int i = 0; i < len; i++) {
			tmp = target.substring(0, i) + target.substring(i+1);
			if(qpht.contains(tmp)) {
				System.out.print(tmp + " ");
			}
		}
		System.out.println();
	}
	
	public void switchAdjacentChars(String target) {
		int len = target.length();
		String tmp = target;
		char charArray[] = null;
		System.out.print("switchAdjacentChars:\n");
		for(int i = 0; i < len-1; i++) {
			char c1 = target.charAt(i);
			char c2 = target.charAt(i+1);
			charArray = target.toCharArray();
			charArray[i] = c2;
			charArray[i+1] = c1;
			tmp = String.valueOf(charArray);
			if(qpht.contains(tmp)) {
				System.out.print(tmp + " ");
			}
		}
		System.out.println();
	}
	
	public void addOneCharAtAnyPlace(String target) {
		int len = target.length();
		String tmp = target;
		System.out.print("addOneCharAtAnyPlace:\n");
		char charArray[] = null;
		int count = 0;
		for(int n = 0; n < len; n++) {
			for(int i = 'a'; i <= 'z'; i++) {
				charArray = tmp.toCharArray();
				char newCharArray[] = new char[len+1];
				newCharArray[n] = (char)i;
				int index = 0;
				for(int j = 0; j < newCharArray.length; j++) {
					if(newCharArray[j] >= 'a' && newCharArray[j] <= 'z') {
						continue;
					}
					newCharArray[j] = charArray[index++];
				}
				tmp = String.valueOf(newCharArray);
				if(qpht.contains(tmp)) {
					System.out.print(tmp + " ");
					count++;
					if(count == 18) {
						count = 0;
						System.out.println();
					}
				}
				tmp = target;
			}
		}
		System.out.println();
	}
	
}
