package parsing;

import java.io.*;
import java.util.regex.*;

public class Controller {
	static Boolean vowel(String ch) {
		String[] vovels = new String[]{"e", "y", "u", "i", "o", "a", "j"};
		for(String v : vovels) {
			if(ch.compareToIgnoreCase(v) == 0)
				return true;
		}
		return false;
	}
	static void deleteAllWords(File source, int length, File  result) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		Pattern p = Pattern.compile("\\(?\\b[a-zA-Z]*\\ ?\\,?\\.?\\)?\\??");
		Matcher m;
		String[] word;
		try {
			br = new BufferedReader(new FileReader(source));
			bw = new BufferedWriter(new FileWriter(result, true));
			String temp = "";
			while((temp = br.readLine()) != null) {
				m = p.matcher(temp);
				while(m.find()) {
					word = m.group().split("");
					if(m.group().length()-1 != length || vowel(word[0])) {
						bw.write(m.group());
					}
					else {									
						bw.write(word[word.length - 1]);
					}
				}
				bw.write("\n");
			}		
			System.out.println("Successfully wrote to the file.");
		}catch(FileNotFoundException fnfe) {
			System.out.println(fnfe);
		} catch (IOException e) {
			System.out.println(e);
		}finally {
			try {
				br.close();
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
