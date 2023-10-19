package theme13;

import java.util.ArrayList;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car car1 = new Car("m1","Serg", 1, 2020);
		Car car2 = new Car("m2","O2", 1, 2020);
		Car car3 = new Car("m2","O3", 1, 2020);
		Car car4 = new Car("m2","O2", 1, 2020);
		ArrayList<Car> cars = new ArrayList<Car>();
		cars.add(car1);
		cars.add(car2);
		System.out.println(cars.contains(car3));
		System.out.println(C.contains1(cars, car4));
	}

}
