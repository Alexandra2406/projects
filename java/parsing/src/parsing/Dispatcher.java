package parsing;

import java.io.File;
import java.io.IOException;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file;
		File dir = new File("OutputFiles");
			try {
				dir.mkdir();
				file = new File(dir, "file1.txt");		
				if (file.createNewFile()) {
					System.out.println("File created: " + file.getName());
				} else {
		    	  System.out.println("File already exists.");
				}
				Controller.deleteAllWords( new File("C:/Users/Admin/eclipse-workspace/parsing/source.txt"), 8, file);
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	}

}
