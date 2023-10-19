package theme13;

import java.util.ArrayList;

public class C {
	public static boolean contains1(ArrayList<Car> car, Car c)
	{
		for (int i = 0; i < car.size(); i++) {
		      if(c.owner.equals(car.get(i).owner))
		      {
		    	  return true;
		      }
		    }
		return false;
		
	}
}
