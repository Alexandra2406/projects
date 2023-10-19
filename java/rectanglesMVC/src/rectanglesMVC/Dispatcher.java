package rectanglesMVC;

import java.util.ArrayList;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r1 = new Rectangle(1, 1, 5, 4);
		Rectangle r2 = new Rectangle(4, 2, 10, 5);
		Rectangle r3 = new Rectangle(3, 3, 6, 7);
		Rectangle r4 = new Rectangle(1, 1, 10, 8);
		Rectangle r5 = new Rectangle(10, 13, 12, 14);
		Rectangle r6 = new Rectangle(6, 8, 10, 13);
		ArrayList<Rectangle> rl = new ArrayList<Rectangle>();
		rl.add(r1); 	
		rl.add(r2);
		rl.add(r3);
		rl.add(r4);
		rl.add(r5);
		rl.add(r6);
		View.show(Controller.intersection(rl));
	}

}
