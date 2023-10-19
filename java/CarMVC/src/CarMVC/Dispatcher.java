package CarMVC;

public class Dispatcher {

	  public static void main(String[] args) {
	    // TODO Auto-generated method stub
		  Car car = new Car("Range Rover Sport", "White", "AA 9652 KA", false);
		    Driver driver1 = new Driver(car, "06875175471");
		    driver1.repair();
		    View.print(driver1);
		    driver1.car.status = false;
		    View.print(driver1);
		    AutoserviceStation.repair(driver1);
		    View.print(driver1);
	  }
}
	
