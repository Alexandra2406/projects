package hashMap;

import java.io.File;
import java.util.LinkedList;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File lines = new File("src\\LINES");
		LinkedList<Point> points = new LinkedList<>();
		points.add(new Point(1, 1));
		points.add(new Point(1, 2));
		points.add(new Point(1, 3));
		points.add(new Point(2, 1));
		points.add(new Point(3, 1));
		points.add(new Point(5, 2));
		points.add(new Point(9, 3));
		points.add(new Point(2, 5));
		Controller.crossing(points, lines);
	}

}
