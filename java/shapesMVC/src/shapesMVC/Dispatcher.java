package shapesMVC;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//configuration1
		Triangle t1 = new Triangle(12, ".");
		Rectangle r1 = new Rectangle(12, 5, ".");		
		Model m1 = new Model(t1, r1);
		ControllerAndView.configuration1(m1, 10);	
		m1.r.height = 0;
		ControllerAndView.configuration1(m1, 10);
		//configuration2
		Triangle t2 = new Triangle(12, ".");
		Rectangle r2 = new Rectangle(12, 5, ".");		
		Model m2 = new Model(t2, r2);
		ControllerAndView.configuration2(m2, 10);
		m2.r.height = 0;
		ControllerAndView.configuration2(m2, 10);
		//configuration3
		Triangle t3 = new Triangle(12, ".");
		Rectangle r3 = new Rectangle(12, 5, ".");		
		Model m3 = new Model(t3, r3);
		ControllerAndView.configuration3(m3, 10);
		m3.r.height = 0;
		ControllerAndView.configuration3(m3, 10);
		//configuration4
		Triangle t4 = new Triangle(12, ".");
		Rectangle r4 = new Rectangle(12, 5, ".");		
		Model m4 = new Model(t4, r4);
		ControllerAndView.configuration4(m4, 10);	
		m4.r.height = 0;
		ControllerAndView.configuration4(m4, 10);
		//tree
		Rectangle rT = new Rectangle(12, 5, ".");
		Trapezoid tzT = new Trapezoid(10, 6, ".");
		Model mT = new Model(rT, tzT);
		ControllerAndView.tree(mT, 10, 6);		
		//rocket
		Rectangle rR = new Rectangle(12, 5, ".");
		Trapezoid tzR = new Trapezoid(10, 6, ".");
		Model mR = new Model(rR, tzR);
		ControllerAndView.rocket(mR, 10, 6);
	}

}
