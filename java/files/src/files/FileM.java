package files;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileM {
	File file;
	static File dir = new File("OutputFiles");
	FileM(String name){
		try {
			dir.mkdir();
			this.file = new File(dir, name + ".txt");		
			if (this.file.createNewFile()) {
				System.out.println("File created: " + this.file.getName());
			} else {
	    	  System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public String toString() {
		String temp = "";
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(this.file));
			
			while((temp = br.readLine()) != null) {
				temp += "/n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return temp;
		
	}
	void copy(File file, Boolean b) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(file));
			bw = new BufferedWriter(new FileWriter(this.file, b));
			String temp = "";
			while((temp = br.readLine()) != null) {
				bw.write(temp + "\n");
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
	void writeln(String str, Boolean b) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(this.file, b));
			bw.write(str + "\n");			
			System.out.println("Successfully wrote to the file.");
		}catch (IOException e) {
			System.out.println(e);
		}finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	void delete() {
		if (this.file.delete()) { 
		      System.out.println("Deleted the file: " + this.file.getName());
		    } else {
		      System.out.println("Failed to delete the file.");
		    } 
	}
	List<Bottle> convertToObject(){
		BufferedReader br = null;
		List<Bottle> bottles = new ArrayList<>();
			try {
				br = new BufferedReader(new FileReader(file));
				String temp = "";
				while((temp = br.readLine()) != null) {
					String[] bottleDetail = temp.split("\t");
					bottles.add(new Bottle(Integer.parseInt(bottleDetail[0]), bottleDetail[1], Double.parseDouble(bottleDetail[2]), bottleDetail[3]));
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return bottles;		
	}
	void convertToFile(List<Bottle> bottles) {
		this.writeln("", false);
		for(Bottle b : bottles) {
			this.writeln(b.toString(), true);
		}
		
	}
	
}
