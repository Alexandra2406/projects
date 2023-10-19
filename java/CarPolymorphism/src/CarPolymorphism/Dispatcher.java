package CarPolymorphism;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println();
		
		//Tree
		Shape tz1 = new Trapezoid(0, 5, 15, "*");
		Shape tz2 = new Trapezoid(0, 10, 10, "*");
		Shape tz3 = new Trapezoid(0, 15, 5, "*");
		Shape r1 = new Rectangle(6,5,17,"*");
		List<Shape> l1 = new ArrayList<>();
		l1.add(tz1);
		l1.add(tz2);
		l1.add(tz3);
		l1.add(r1);
		ComplexShape cs1 = new ComplexShape(l1);
		cs1.createAndPaint();
		
		System.out.println();
		System.out.println();
		
		//rocket
		Shape tz4 = new Trapezoid(0, 5, 20, "*");
		Shape r2 = new Rectangle(10,5,20,"*");	
		Shape tz5 = new Trapezoid(10, 5, 15, "*");
		Shape r3 = new Rectangle(10,10,20,"*");	
		Shape tz6 = new Trapezoid(10, 10, 10, "*");
		List<Shape> l2 = new ArrayList<>();
		l2.add(tz4);
		l2.add(r2);
		l2.add(tz5);
		l2.add(r3);
		l2.add(tz6);
		ComplexShape cs2 = new ComplexShape(l2);
		cs2.createAndPaint();
	}
}
