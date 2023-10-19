package hashMap;

public class Point {
	int x;
	int y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	Line line(Point p) {
		double k = 1.0*(this.y - p.y)/(this.x - p.x);
			return new Line(k, 1.0* this.y - k * this.x);
	}
	public boolean equals(Object o) {
		return this.x == ((Point)o).x && this.y == ((Point)o).y;
	}
	public int hashCode() {
		return 17 * this.x + 19 * this.y;  
	}
	@Override
	public String toString() {
		return x + " " + y;
	}
}
