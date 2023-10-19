package files;

import java.io.*;
import java.util.Scanner;

public class Controller {

	static void sort(FileM original, FileM file1, FileM file2, FileM file3) {
		try {
			Scanner in = new Scanner(original.file);
			while(in.hasNext()) {
				String input = in.nextLine();
				String[] words = input.split("\t");
				double volume = Double.parseDouble(words[2]);
				if(volume <= 0.5) {
					file1.writeln(input, true);
				}else if(volume <= 0.99) {
					file2.writeln(input, true);
				}else {
					file3.writeln(input, true);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
