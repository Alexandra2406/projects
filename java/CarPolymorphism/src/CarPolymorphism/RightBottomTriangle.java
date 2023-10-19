package CarPolymorphism;

public class RightBottomTriangle extends Triangle {

    RightBottomTriangle(int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
	}

      void  createAndPaint() {
	    for (int i = 0; i < this.size; i++) { 
	      Print.Shift(shift + this.size - 1 - i);
	      Print.Symbol(i + 1, this.symbol);	      
	    } 
	  }
	}