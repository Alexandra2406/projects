package garbageCollector;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Car c1 = new Car("model1");
		Car c2 = new Car("model2");
		Car c3 = new Car("model3");
		c1 = null;
		c2 = null;
		c3 = null;
		System.gc();
	}

}
