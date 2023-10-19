package enums;

import java.util.Arrays;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Fest[]  fests = new Fest[] {
				new Fest("Fest5", "hS5", YearMonthh.May),
				new Fest("Fest4", "hS4", YearMonthh.April),
				new Fest("Fest3", "hS3", YearMonthh.March),
				new Fest("Fest2", "hS2", YearMonthh.February),
				new Fest("Fest1", "hS1", YearMonthh.January)		
		};
		Arrays.sort(fests);
		System.out.println(Arrays.toString(fests));
	}

}
