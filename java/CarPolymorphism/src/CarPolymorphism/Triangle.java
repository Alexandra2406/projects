package CarPolymorphism;

public abstract class Triangle extends Shape {
	Triangle(int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
	}
	abstract void createAndPaint();	
}
