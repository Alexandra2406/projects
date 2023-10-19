package inner;

import java.util.Comparator;

public class Person implements Comparable {
	String name;
	String region;
	int birthYear;
	Boolean isMale;
	Person(String name, String region, int birthYear, boolean isMale){
		this.name = name;
		this.region = region;
		this.birthYear = birthYear;
		this.isMale = isMale;
	}
	public String toString() {
		return this.name + " " + this.region + " " + this.birthYear + " " + this.isMale;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int result = this.name.compareTo(((Person)o).name);
		if(result != 0) {
			return result;
		}else {
			result = this.region.compareTo(((Person)o).region);
			if(result != 0) {
				return result;
			}else {
				result = this.birthYear - ((Person)o).birthYear;
				if(result != 0) {
					return result;
				}else {
					return this.isMale.compareTo(((Person)o).isMale);
				}
			}
		}
	}
}
