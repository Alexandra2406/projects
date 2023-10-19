package CarPolymorphism;

import java.util.List;

public class ComplexShape {
	List<Shape> shapes;
	ComplexShape(List<Shape> shapes){
		this.shapes = shapes;
	}
	void createAndPaint() {
		// TODO Auto-generated method stub
		for(Shape shape : shapes){
			shape.createAndPaint();
		}
	}
	
}
