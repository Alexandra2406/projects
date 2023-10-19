package inner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person[] p = 
	   { new Person("Name1", "Region1", 2000, true),
		 new Person("Name1", "Region1", 2000, false),
		 new Person("Name1", "Region1", 2001, false),
		 new Person("Name1", "Region2", 2001, false),
	     new Person("Name2", "Region2", 2001, false),
	     new Person("Name3", "Region2", 2002, false)};
		Scanner in = new Scanner(System.in);
        System.out.print("Input : ");
        String s= in.nextLine();
        if(s.equals("1")) {
        	Arrays.sort(p, new ComparatorByRegion());
        	System.out.println(Arrays.toString(p));
        }else if(s.equals("2")) {
        	Arrays.sort(p, new ComparatorByBirthYear());
        	System.out.println(Arrays.toString(p));
        }else if(s.equals("3")) {
        	Arrays.sort(p, new ComparatorByIsMale());
        	System.out.println(Arrays.toString(p));
        }else {
        	Arrays.sort(p);
        	System.out.println(Arrays.toString(p));
        }
	}

}

class ComparatorByName implements Comparator{
	public int compare(Object o1, Object o2) {
		if(((Person)o1).name.compareTo(((Person)o2).name) != 0) {
		return ((Person)o1).name.compareTo(((Person)o2).name);
		}else {
			ComparatorByRegion cr = new ComparatorByRegion();
			return cr.compare(o1, o2); 
		}
	}
}
class ComparatorByRegion implements Comparator{
	public int compare(Object o1, Object o2) {		
		if(((Person)o1).region.compareTo(((Person)o2).region) != 0) {
			return ((Person)o1).region.compareTo(((Person)o2).region);
			}else {
				ComparatorByBirthYear cr = new ComparatorByBirthYear();
				return cr.compare(o1, o2); 
			}
	}
}
class ComparatorByBirthYear implements Comparator{
	public int compare(Object o1, Object o2) {
		
		if(((Person)o1).birthYear - ((Person)o2).birthYear != 0) {
			return ((Person)o1).birthYear - ((Person)o2).birthYear;
			}else {
				ComparatorByIsMale cr = new ComparatorByIsMale();
				return cr.compare(o1, o2); 
			}
	}
}
class ComparatorByIsMale implements Comparator{
	public int compare(Object o1, Object o2) {
		if(((Person)o1).isMale.compareTo(((Person)o2).isMale) != 0)
		return ((Person)o1).isMale.compareTo(((Person)o2).isMale);
		else {
			ComparatorByName cr = new ComparatorByName();
			return cr.compare(o1, o2);
		}
	}
}
