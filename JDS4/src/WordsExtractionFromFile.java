import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordsExtractionFromFile {
	
	public void wordsExtraction(String inPath, String outPath) {
	
		File file = new File(inPath);
		File outputFile = new File(outPath);
		/*
		System.out.println(file);
		System.out.println(outputFile);
		
		System.out.println("hello".matches("[a-z].*"));
		*/
		if(!outputFile.exists()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			bw = new BufferedWriter(new FileWriter(outputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String line = null;
		
		try {
			while((line = br.readLine()) != null) {
				if(line.matches("[a-z].+")) {
					line = line.substring(0, line.indexOf(" "));
					line.trim();
					bw.write(line);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
