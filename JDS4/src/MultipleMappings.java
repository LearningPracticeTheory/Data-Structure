import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MultipleMappings {
	
	public void printHighChangeable(Map<String, List<String>> map, int minWords) {
		for(Map.Entry<String, List<String>> entry : map.entrySet()) {
			List<String> words = entry.getValue();
			if(words.size() >= minWords) {
				System.out.print(entry.getKey() + " (" + words.size() + "):");
				for(String w : words) {
					System.out.print(" " + w);
				}
				System.out.println();
			}
		}
	}
	
	private static boolean oneCharOff(String word1, String word2) {
		if(word1.length() != word2.length()) {
			return false;
		}
		int diff = 0;
		for(int i = 0; i < word1.length(); i++) {
			if(word1.charAt(i) != word2.charAt(i)) {
				if(++diff > 1) {
					return false;
				}
			}
		}
		return diff == 1; //same words return false
	}
	
	private static <KeyType> void update(Map<KeyType, List<String>> map, KeyType key, String value) {
		List<String> list = map.get(key);
		if(list == null) {
			list = new ArrayList<>();
			map.put(key, list);
		}
		list.add(value);
	}
	
	public static Map<String, List<String>> computeAdjacentWords(List<String> theWords) {
		Map<String, List<String>> adjWords = new TreeMap<>();
		String words[] = new String[theWords.size()];
		theWords.toArray(words);
		for(int i = 0; i < words.length; i++) {
			for(int j = i+1; j < words.length; j++) {
				if(oneCharOff(words[i], words[j])) {
					update(adjWords, words[i], words[j]);
					update(adjWords, words[j], words[i]);
				}
			}
		}
		return adjWords;
	}
	
	public static Map<String, List<String>> computeAdjacentWordsPlus(List<String> theWords) {
		Map<String, List<String>> adjWords = new TreeMap<>();
		Map<Integer, List<String>> lenWords = new TreeMap<>();
		
		for(String w : theWords) {
			update(lenWords, w.length(), w); //split by word's size
		}
		
		for(List<String> groupWords : lenWords.values()) { //loop different word size
			String words[] = new String[groupWords.size()];
			groupWords.toArray(words);
			for(int i = 0; i < words.length; i++) {
				for(int j = i+1; j < words.length; j++) {
					if(oneCharOff(words[i], words[j])) {
						update(adjWords, words[i], words[j]);
						update(adjWords, words[j], words[i]);
					}
				}
			}
		}
		
		return adjWords;
	}
/*P157 ????*/
	public static Map<String, List<String>> computeAdjacentWordsPlusPlus(List<String> theWords) {
		Map<String, List<String>> adjWords = new TreeMap<>();
		Map<Integer, List<String>> wordsByLength = new TreeMap<>();
		
		for(String w : theWords) {
			update(wordsByLength, w.length(), w);
		}
		
		for(Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {
			List<String> groupsWords = entry.getValue();
			Integer groupNum = entry.getKey();
			
			for(int i = 0; i < groupNum; i++) {
				Map<String, List<String>> repToWords = new TreeMap<>();
				
				for(String str : groupsWords) {
					String rep = str.substring(0, i) + str.substring(i+1);
					update(repToWords, rep, str);
				}
				
				for(List<String> wordClique : repToWords.values()) {
					if(wordClique.size() > 1) {
						for(String str1 : wordClique) {
							for(String str2 : wordClique) {
								if(str1 != str2) {
									update(adjWords, str1, str2);
								}
							}
						}
					}
				}
				
			}
			
		}
		return adjWords;
	}
	
}
