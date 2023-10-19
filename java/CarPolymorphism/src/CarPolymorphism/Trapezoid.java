package CarPolymorphism;

public class Trapezoid extends Shape {
	Trapezoid(int width, int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
		this.width = width;
	}
	int width;
	@Override
	void createAndPaint() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.size ; i++) {
        	Print.Shift(shift + this.size - 1 - i);
  	        Print.Symbol(i + 1 + this.width + i + 1, this.symbol);	
  	    }
	}	
}
