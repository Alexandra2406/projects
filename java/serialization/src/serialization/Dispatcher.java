package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Dispatcher {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		File planesF = new File("C:/Users/Admin/eclipse-workspace/serialization/PLANES.txt");
		File shipsF = new File("C:/Users/Admin/eclipse-workspace/serialization/SHIPS.txt");
	    Engine shipEngine1 = new Engine("Reciprocating", 12000);
        Boat boat1 = new Boat(1389, "Metal");
        List<Plane> planes = new ArrayList<>(Arrays.asList(
        		new Plane("model1", 31000, new Chassis(new Wheel(50, 10), 4), 800, 2022, new Engine("type", 11)),
				new Plane("model1", 31000, new Chassis(new Wheel(50, 10), 4), 800, 2021, new Engine("type", 11)),
				new Plane("model1", 31000, new Chassis(new Wheel(50, 10), 4), 800, 2020, new Engine("type", 11)),
				new Plane("model1", 31000, new Chassis(new Wheel(50, 10), 4), 800, 2019, new Engine("type", 11)),
				new Plane("model1", 31000, new Chassis(new Wheel(50, 10), 4), 800, 2018, new Engine("type", 11))		
	));
        List<Ship> ships = new ArrayList<>(Arrays.asList(
                new Ship(134, 1945, boat1, 5, 123, shipEngine1),
                new Ship(126, 1956, boat1, 8, 146, shipEngine1),
                new Ship(197, 1978, boat1, 4, 123, shipEngine1),
                new Ship(158, 1963, boat1, 12, 256, shipEngine1),
                new Ship(80, 1896, boat1, 7, 94, shipEngine1),
                new Ship(143, 1993, boat1, 8, 110, shipEngine1),
                new Ship(247, 2002, boat1, 5, 74, shipEngine1)
        ));
        Collections.sort(planes);
        Collections.sort(ships);
        System.out.println(planes);
        System.out.println(ships);

        ObjectOutputStream oosPlanes = new ObjectOutputStream(new FileOutputStream(planesF));
        ObjectOutputStream oosShips = new ObjectOutputStream(new FileOutputStream(shipsF));
        oosPlanes.writeObject(planes);
        oosShips.writeObject(ships);
        oosPlanes.flush(); oosPlanes.close();
        oosShips.flush(); oosShips.close(); 
       
        ObjectInputStream oisPlanes = new ObjectInputStream( new FileInputStream(planesF));
        List<Plane> outPlanes = (ArrayList)oisPlanes.readObject();        
        outPlanes.stream().forEach(x -> System.out.println(x));
        
        ObjectInputStream oisShips = new ObjectInputStream( new FileInputStream(shipsF));
        List<Ship> outShips = (ArrayList)oisShips.readObject();
        outShips.stream().forEach(x -> System.out.println(x));
	}
}
	class Vehicle implements Comparable<Vehicle>{
		double speed;
		int yearOfProduction;
		Engine engine;
		Vehicle(){}
		Vehicle(double speed, int yearOfProduction, Engine engine){
			this.speed = speed;
			this.yearOfProduction = yearOfProduction;
			this.engine = engine;
		}
		public String toString() {
		return this.speed + " " + this.yearOfProduction + " " + this.engine.toString(); 
		}
		@Override
		public int compareTo(Vehicle o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.yearOfProduction, o.yearOfProduction);
		}
	}
	class Plane extends Vehicle implements  Serializable{
		Plane(){}
		Plane(String model, double flightRange, Chassis chassis, double speed, int yearOfProduction, Engine engine) {
			super(speed, yearOfProduction, engine);
			// TODO Auto-generated constructor stub
			this.model = model;
			this.flightRange = flightRange;
			this.chassis = chassis;
		}
		String model;
		double flightRange;
		transient Chassis chassis;
		public String toString() {
			return super.toString()+ " " + this.model + " " + this.flightRange + " " + this.chassis.toString() + "\n";
		}
		private void writeObject(ObjectOutputStream os) {
			try {
				os.defaultWriteObject();
				os.writeDouble(speed);
		        os.writeInt(yearOfProduction);
		        os.writeObject(engine.type);
		        os.writeDouble(engine.power);
				os.writeDouble(chassis.wheel.load);
				os.writeDouble(chassis.wheel.diameter);
				os.writeInt(chassis.numberOfWheels);
			}catch(IOException ioe) {}
		}
		private void readObject(ObjectInputStream is) {
			try {
				is.defaultReadObject();
				speed = is.readDouble();
		        yearOfProduction = is.readInt();
		        engine = new Engine((String)is.readObject(), is.readDouble());
				chassis = new Chassis( new Wheel((double)is.readDouble(), (double)is.readDouble()), (int)is.readInt());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class Ship extends Vehicle implements Externalizable{
		Ship(){}
		Ship(double waterTonnage, double length, Boat boat, double speed, int yearOfProduction, Engine engine) {
			super(speed, yearOfProduction, engine);
			// TODO Auto-generated constructor stub
			this.waterTonnage = waterTonnage;
			this.length = length;
			this.boat = boat;
		}	   
		double waterTonnage;
		double length;
		transient Boat boat;
		public String toString() {
			return super.toString()+ " " + this.waterTonnage + " " + this.length + " " + this.boat.toString() + "\n";
		}
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			// TODO Auto-generated method stub
			out.writeDouble(waterTonnage);
			out.writeDouble(length);
			out.writeInt(boat.numberOfPassengers);
			out.writeObject(boat.material);
			out.writeDouble(speed);
			out.writeInt(yearOfProduction);
			out.writeObject(engine.type);
			out.writeDouble(engine.power);
		}
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			// TODO Auto-generated method stub
			waterTonnage = (double)in.readDouble();
			length = (double)in.readDouble();
			boat = new Boat((int)in.readInt(), (String)in.readObject());
			speed = (double)in.readDouble();
			yearOfProduction = (int)in.readInt();
			engine = new Engine((String)in.readObject(), (double)in.readDouble());
		}	
	}
	class Engine{
		String type;
		double power;
		Engine(){}
		Engine(String type,	double power){
			this.type = type;
			this.power = power;
		}
		public String toString() {
			return this.type + " " + this.power;
		}
	}
	class Chassis{
		Wheel wheel;
		int numberOfWheels;
		Chassis(){}
		Chassis(Wheel wheel, int numberOfWheels){
			this.wheel = wheel;
			this.numberOfWheels = numberOfWheels;
		}
		public String toString() {
			return this.wheel.toString() + " " + this.numberOfWheels;
		}
	}
	class Boat{
		int numberOfPassengers;
		String material;
		Boat(){}
		Boat(int numberOfPassengers, String material){
			this.numberOfPassengers = numberOfPassengers;
			this.material = material;
		}
		public String toString() {
			return this.numberOfPassengers + " " + this.material;
		}
	}
	class Wheel{
		double load;
		double diameter;
		Wheel(){}
		Wheel(double load, double diameter){
			this.load = load;
			this.diameter = diameter;
		}
		public String toString() {
			return this.load + " " + this.diameter;
		}
	}