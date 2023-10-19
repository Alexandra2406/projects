package hashMap;

public class Line {
	double K;
	double B;
	Line(double K, double B) {
		this.K = K;
		this.B = B;
	}
	Boolean belongs(Point p) {
		if(this.K * p.x + this.B == p.y) {
			return true;
		}else {
			return false;
		}
	}
	public boolean equals(Object o) {
		return this.K == ((Line)o).K && this.B == ((Line)o).B;
	}
	public int hashCode() {
		return (int) (17 * this.K + 19 * this.B);  
	}
}
