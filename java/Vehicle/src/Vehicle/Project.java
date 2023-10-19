package Vehicle;

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
interface vehicle{
	void move();
}
interface passengerVehicle extends vehicle{
	void deliverPassengers();
}
interface cargoVehicle extends vehicle{
	void deliverCargo();
}
interface militaryVehicle extends vehicle{
	void destroy();
}
abstract class Plane implements vehicle{

	String model;
	int speed, weight;
	Plane(String model, int speed, int weight){
		this.model = model;
		this.speed = speed;
		this.weight = weight;
	}
	@Override
	public abstract void move();
}
 class PassengerPlane extends Plane implements passengerVehicle{

	 PassengerPlane(String model, int speed, int weight, int numberOfSeats) {
		super(model, speed, weight);
		// TODO Auto-generated constructor stub
		this.numberOfSeats = numberOfSeats;
	}
	int numberOfSeats;
	@Override
	public void deliverPassengers() {
		// TODO Auto-generated method stub
		System.out.println("Maximum number of seats = " + this.numberOfSeats);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("Can fly with speed = " + this.speed);
	}
}
 class CargoPlane extends Plane implements cargoVehicle{

	 CargoPlane(String model, int speed, int weight, double weightOfCargo) {
		super(model, speed, weight);
		// TODO Auto-generated constructor stub
		this.weightOfCargo = weightOfCargo;
	}
	double weightOfCargo;
	@Override
	public void deliverCargo() {
		// TODO Auto-generated method stub
		System.out.println("Maximum weight of cargo = " + this.weightOfCargo);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("Can fly with speed = " + this.speed);
	}
}
 class MilitaryPlane extends Plane implements militaryVehicle{

	 MilitaryPlane(String model, int speed, int weight, int rockets) {
		super(model, speed, weight);
		// TODO Auto-generated constructor stub
		this.rockets = rockets;
	}
	int rockets;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Is armed with " + this.rockets + "rockets"); 
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("Can fly with speed = " + this.speed);
	}
}
       abstract class Car implements vehicle{

		String model;
		int speed, weight;
		Car(String model, int speed, int weight){
			this.model = model;
			this.speed = speed;
			this.weight = weight;
		}
		@Override
		public abstract void move(); 
     }
	  class PassengerCar extends Car implements passengerVehicle{

		 PassengerCar(String model, int speed, int weight, int numberOfSeats) {
			super(model, speed, weight);
			// TODO Auto-generated constructor stub
			this.numberOfSeats = numberOfSeats;
		}
		int numberOfSeats;
		@Override
		public void deliverPassengers() {
			// TODO Auto-generated method stub
			System.out.println("Maximum number of seats = " + this.numberOfSeats);
		}
		@Override
		public void move() {
			// TODO Auto-generated method stub
			System.out.println("Can ride with speed = " + this.speed);
		}
	}
	 class Truck extends Car implements cargoVehicle{

		 Truck(String model, int speed, int weight, double weightOfCargo) {
			super(model, speed, weight);
			// TODO Auto-generated constructor stub
			this.weightOfCargo = weightOfCargo;
		}
		double weightOfCargo;
		@Override
		public void deliverCargo() {
			// TODO Auto-generated method stub
			System.out.println("Maximum weight of cargo = " + this.weightOfCargo);
		}
		@Override
		public void move() {
			// TODO Auto-generated method stub
			System.out.println("Can ride with speed = " + this.speed);
		}
	}
	 class Tank extends Car implements militaryVehicle{

		 Tank(String model, int speed, int weight, int caliber) {
			super(model, speed, caliber);
			// TODO Auto-generated constructor stub
			this.caliber = caliber;
		}
		int caliber;
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void move() {
			// TODO Auto-generated method stub
			System.out.println("Can ride with speed = " + this.speed);
		}
	 } 
	 abstract class Ship implements vehicle{

			String model;
			int speed, weight;
			Ship(String model, int speed, int weight){
				this.model = model;
				this.speed = speed;
				this.weight = weight;
			}
			@Override
			public abstract void move(); 
	     }
		  class PassengerLiner extends Ship implements passengerVehicle{

			 PassengerLiner(String model, int speed, int weight, int numberOfSeats) {
				super(model, speed, weight);
				// TODO Auto-generated constructor stub
				this.numberOfSeats = numberOfSeats;
			}
			int numberOfSeats;
			@Override
			public void deliverPassengers() {
				// TODO Auto-generated method stub
				System.out.println("Maximum number of seats = " + this.numberOfSeats);
			}
			@Override
			public void move() {
				// TODO Auto-generated method stub
				System.out.println("Can sail with speed = " + this.speed);
			}
		}
		 class CargoShip extends Ship implements cargoVehicle{

			 CargoShip(String model, int speed, int weight, double weightOfCargo) {
				super(model, speed, weight);
				// TODO Auto-generated constructor stub
				this.weightOfCargo = weightOfCargo;
			}
			double weightOfCargo;
			@Override
			public void deliverCargo() {
				// TODO Auto-generated method stub
				System.out.println("Maximum weight of cargo = " + this.weightOfCargo);
			}
			@Override
			public void move() {
				// TODO Auto-generated method stub
				System.out.println("Can sail with speed = " + this.speed);
			}
		}
		 class MissileCarrier extends Ship implements militaryVehicle{

			 MissileCarrier(String model, int speed, int weight, int rockets) {
					super(model, speed, weight);
					// TODO Auto-generated constructor stub
					this.rockets = rockets;
				}
				int rockets;
			@Override
			public void destroy() {
				// TODO Auto-generated method stub
				System.out.println("Is armed with " + this.rockets + "rockets"); 
			}
			@Override
			public void move() {
				// TODO Auto-generated method stub
				System.out.println("Can sail with speed = " + this.speed);
			}
		}