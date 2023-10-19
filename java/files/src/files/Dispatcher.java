package files;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileM original = new FileM("original");
		original.copy(new File("C:/Users/Admin/eclipse-workspace/files/InputFiles/file1.txt"), true);
		original.copy(new File("C:/Users/Admin/eclipse-workspace/files/InputFiles/file2.txt"), true);
		original.copy(new File("C:/Users/Admin/eclipse-workspace/files/InputFiles/file3.txt"), true);
		FileM f1 = new FileM("file1");
		FileM f2 = new FileM("file2");
		FileM f3 = new FileM("file3");
		Controller.sort(original, f1, f2, f3);
		List<Bottle> l1 = f1.convertToObject();		
		Bottle[] bottles = new Bottle[] {};
		bottles = l1.toArray(bottles);
		Arrays.sort(bottles);
		f1.convertToFile(Arrays.asList(bottles));
		List<Bottle> l2 = f2.convertToObject();
		Collections.sort(l2, new bottleCompare().thenComparing(new volumeCompare().thenComparing(new materialCompare())));
		f2.convertToFile(l2);
		List<Bottle> l3 = f3.convertToObject();
		Collections.sort(l3, new materialCompare().thenComparing(new volumeCompare().thenComparing(new bottleCompare())));
		f3.convertToFile(l3);
	}

}
