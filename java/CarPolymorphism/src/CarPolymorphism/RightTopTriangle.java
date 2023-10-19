package CarPolymorphism;

public class RightTopTriangle extends Triangle {
    RightTopTriangle(int size, int shift, String symbol) {
		super(size, shift, symbol);
		// TODO Auto-generated constructor stub
	}

    void createAndPaint(){
    	for (int i = 0; i < this.size; i++)	{ 
		Print.Shift(this.shift + i);
		Print.Symbol(this.size - i, this.symbol);
    	}
    }
}
