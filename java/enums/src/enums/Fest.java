package enums;

import java.util.Comparator;
import java.time.*;  

public class Fest implements Comparable<Fest>{

	String name, hostCity;
	YearMonthh yearMonth;
	Fest(String name, String hostCity, YearMonthh yearMonth){
		this.name = name;
		this.hostCity = hostCity;
		this.yearMonth = yearMonth;
	}
	public String toString() {
		return this.name + " " + this.hostCity + " " + this.yearMonth.toString();
	}
	@Override
	public int compareTo(Fest o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}	
}
class HostCityCompare implements Comparator<Fest>{
	@Override
	public int compare(Fest o1, Fest o2) {
		// TODO Auto-generated method stub
		return o1.hostCity.compareTo(o2.hostCity);
	}	
}
class YearMonthCompare implements Comparator<Fest>{
	@Override
	public int compare(Fest o1, Fest o2) {
		// TODO Auto-generated method stub
		return o1.yearMonth.compareTo(o2.yearMonth);
	}	
}

