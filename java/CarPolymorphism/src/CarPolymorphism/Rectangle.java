package CarPolymorphism;

public class Rectangle extends Shape {
	Rectangle(int width,int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
		this.width = width;
	}
	int width;
	@Override
	void createAndPaint() {
	    for (int i = 0; i < this.size; i++)  { 
	        Print.Shift(this.shift);
	        Print.Symbol(this.width, this.symbol);
	      }  
	    }
	  }