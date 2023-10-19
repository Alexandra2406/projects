package theme13;

import java.util.ArrayList;

public class Car {
	String model;
	String owner;
	int price; 
	int produceYear;
	Car(String model, String owner, int price, int produceYear)
	{
		this.model = model;
		this.owner = owner;
		this.price = price;
		this.produceYear = produceYear;
	}
	public boolean contains(Object o)
	{
		return this.owner == ((Car)o).owner;
	}
	
}
