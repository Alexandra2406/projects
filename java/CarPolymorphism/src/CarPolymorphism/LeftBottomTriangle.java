package CarPolymorphism;

public class LeftBottomTriangle extends Triangle {

	LeftBottomTriangle(int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
	}

	void createAndPaint()  {
	    for (int i = 0; i < this.size; i++)  { 
	        Print.Shift(shift);
	        Print.Symbol(i + 1, this.symbol);
	      } 
	    }
	  }
