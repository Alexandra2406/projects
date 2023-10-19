package CarMVC;

public class View {

	static void print(Driver dr)
	{
		if(dr.car.status){
			System.out.println("Car is repared");
		} else {
			System.out.println("Car is broken");
		}
		System.out.println("Driver phone number : " + dr.phoneNumber +
				"\nCar model : " + dr.car.model +
				"\nCar color : " + dr.car.color +
				"\nCar number : " + dr.car.number +
				"\nCar number of wheels : " + Car.numberOfWheels + "\n");	
		
	}
}
