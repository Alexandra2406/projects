package CarMVC;

public class Driver {
	Car car;
	String phoneNumber;
	Driver(Car car, String phoneNumber)
	{
		this.car = car;
		this.phoneNumber = phoneNumber;
	}
	void repair()
	{
		this.car.status = true;
	}
}
