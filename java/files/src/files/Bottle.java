package files;

import java.util.Comparator;
import java.lang.Double;

public class Bottle implements Comparable {
	int n;
	String bottle;
	double volume;
	String material;
	Bottle(int n, String bottle, double volume, String material){
		this.n = n;
		this.bottle = bottle;
		this.volume = volume;
		this.material = material;
	}
	public String toString() {
		return n + " " + bottle + " " + volume + " " + material;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int result = Double.compare(this.volume, ((Bottle)o).volume);
		if(result != 0) {
			return result;
		}else {
			result = this.material.compareTo(((Bottle)o).material);
			if(result != 0) {
				return result;
			}else {
				return this.bottle.compareTo(((Bottle)o).bottle);
			}
		}
	}
}
class bottleCompare implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return ((Bottle)o1).bottle.compareTo(((Bottle)o2).bottle);
	}	
}
class volumeCompare implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return Double.compare(((Bottle)o1).volume, ((Bottle)o2).volume);
	}	
}
class materialCompare implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return ((Bottle)o1).material.compareTo(((Bottle)o2).material);
	}	
}


