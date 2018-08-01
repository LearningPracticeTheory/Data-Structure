import java.io.File;

public class FilesDirsStructure {
	
	public static void main(String args[]) {
		File f = new File("E:/java_code/JDS4"); //不能在文件属性 copy 路径???
		System.out.println(f.getName());
		recursive(f, 1);
	}
	
	public static void recursive(File f, int level) {
		File children[] = f.listFiles();
		String tab = "";
		for(int i = 0; i < level; i++) {
			tab += "    ";
		}
		for(int i = 0; i < children.length; i++) {
			System.out.println(tab + children[i].getName());
			if(children[i].isDirectory()) {
				recursive(children[i], level+1);
			}
		}
	}
	
}
