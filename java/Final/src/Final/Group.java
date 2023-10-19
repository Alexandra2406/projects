package Final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
	List<Final.Student> students;
	Group(ArrayList<Student> students){
		this.students = students;
	}
	void finished() {
		this.students = Collections.unmodifiableList(this.students);		
	}
	void print() {
		for(Student student : this.students){
			student.print();
		}
	}		
}
