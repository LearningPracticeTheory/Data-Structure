import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileSize {

	public static long fileLength(File file) {
		if(file.exists() && file.isFile()) {
			return file.length();
		}
		return -1; //!exists | idDir
	}

	private static FileInputStream fis = null;
	
	public static int fileAvailable(File file) {
		if(file.exists() && file.isFile()) {
			try {
				fis = new FileInputStream(file);
				return fis.available();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	private static FileChannel fc = null;
	
	public static long fileSize(File file) {
		if(file.exists() && file.isFile()) {
			try {
				fis = new FileInputStream(file);
				fc =  fis.getChannel();
				return fc.size();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return -1;
	}
	
}
