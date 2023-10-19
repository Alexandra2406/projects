package CarPolymorphism;

public abstract class Shape {
	int size, shift;
	String symbol;
	Shape(int size, int shift, String symbol){
		this.size = size;
		this.shift = shift;
		this.symbol = symbol;
	}
	abstract void createAndPaint();	
}