package garbageCollector;

public class Car {
	String model;
	Car(String model){
		this.model = model;
	}
	protected void finalize(){
		System.out.println(this.model);
	}
}
