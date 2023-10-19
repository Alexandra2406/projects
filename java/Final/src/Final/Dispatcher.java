package Final;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> marks = new ArrayList<>();
		marks.add(5);
		marks.add(4);
		Student st1 = new Student("Name1", marks);
		Student st2 = new Student("Name2", marks);
		ArrayList<Student> students = new ArrayList<>();
		students.add(st1);
		students.add(st2);
		final Group gr1 = new Group(students);
		gr1.finished();
		gr1.print();
		try{
			gr1.students.add(st2);
		}
		catch(UnsupportedOperationException  e) {
			System.out.println(e);
		}
		
	}

}
