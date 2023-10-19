package Final;

import java.util.ArrayList;

public class Student {
	String name;
	ArrayList<Integer> marks;
	Student(String name, ArrayList<Integer> marks){
		this.name = name;
		this.marks = marks;
	}
	void print() {
		System.out.print(this.name + " ");
		for(int mark : this.marks) {
			System.out.print(mark + " ");
		}
		System.out.println();
	}
}
