package CarMVC;

public class AutoserviceStation {

	static void repair(Driver dr)
	{
		System.out.println("replacement of spare parts");
		dr.car.status = true;
	}
}
