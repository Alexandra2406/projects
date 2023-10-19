package CarPolymorphism;

public class LeftTopTriangle extends Triangle {
	LeftTopTriangle(int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
	}

	void createAndPaint() {
	    for (int i = 0; i < this.size; i++)  { // row  line
	        Print.Shift(shift);
	        Print.Symbol(this.size - i, this.symbol);
	      }    
	    }
	  }