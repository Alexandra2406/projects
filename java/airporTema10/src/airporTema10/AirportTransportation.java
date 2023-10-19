package airporTema10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Family {
    String name;
    String driveTo;
    int count;

    public Family(String name, String driveTo, int count) {
        this.name = name;
        this.driveTo = driveTo;
        this.count = count;
    }
}


class Plane {
    List<Family> families;
    int id;

    public Plane(List<Family> families, int id) {
        this.families = families;
        this.id = id;
    }
}

class Bus {
    int passengersCount;
    String driveTo;

    public Bus(int passengersCount, String driveTo) {
        this.passengersCount = passengersCount;
        this.driveTo = driveTo;
    }
}

public class AirportTransportation {
	 private static Lock planeLock = new ReentrantLock();
	    private static Lock busLock = new ReentrantLock();

    public static void main(String[] args) {
        List<Plane> planes = new ArrayList<>();
        planes.add(new Plane(createFamilies(), 1));
        planes.add(new Plane(createFamilies(), 2));
        planes.add(new Plane(createFamilies(), 3));

        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus(6, "Kalush"));
        buses.add(new Bus(7, "Kosiv"));
        buses.add(new Bus(8, "Galych"));
        buses.add(new Bus(8, "Kolomiya"));

        Thread planeThread = new Thread(() -> transportPassengersByPlanes(planes));
        Thread busThread = new Thread(() -> transportPassengersByBuses(buses, planes.get(0).families));
        Thread familyThread = new Thread(() -> processFamilies(planes));

        planeThread.start();
        busThread.start();
        familyThread.start();

        try {
            planeThread.join();
            busThread.join();
            familyThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All passengers have been transported.");
    }

    private static List<Family> createFamilies() {
        List<Family> families = new ArrayList<>();
        for (char c1 = 'a'; c1 <= 'c'; c1++) {
            for (char c2 = 'a'; c2 <= 'b'; c2++) {
                String name = String.valueOf(c1) + String.valueOf(c2);
                String travelTo = getRandomCity();
                int count = (int) (Math.random() * 4) + 1;
                families.add(new Family(name, travelTo, count));
            }
        }
        return families;
    }

    private static String getRandomCity() {
        String[] cities = {"Kalush", "Kosiv", "Galych", "Kolomiya"};
        int index = (int) (Math.random() * cities.length);
        return cities[index];
    }

    private static void transportPassengersByPlanes(List<Plane> planes) {
        for (Plane plane : planes) {
            System.out.println("Transporting passengers by Plane " + plane.id);
            try {
                Thread.sleep(1000); // Симуляція часу перевезення
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Passengers from Plane " + plane.id + " have been transported.");
        }
    }

    private static void transportPassengersByBuses(List<Bus> buses, List<Family> families) {
        for (Bus bus : buses) {
            System.out.println("Transporting passengers by Bus to " + bus.driveTo);

            busLock.lock(); // Захоплення блокування для автобуса

            try {
                // Вибираємо сім'ї, які їдуть до поточного міста
                List<Family> familiesToTransport = getFamiliesToTransport(families, bus.driveTo, bus.passengersCount);

                // Забираємо сім'ї автобусом
                for (Family family : familiesToTransport) {
                    System.out.println("Transporting Family " + family.name + " to " + bus.driveTo);
                    try {
                        Thread.sleep(500); // Симуляція часу перевезення
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Family " + family.name + " has been transported to " + bus.driveTo);
                }

                System.out.println("Passengers from Bus to " + bus.driveTo + " have been transported.");
            } finally {
                busLock.unlock(); // Звільнення блокування для автобуса
            }
        }
    }

    private static List<Family> getFamiliesToTransport(List<Family> families, String destination, int capacity) {
    	List<Family> familiesToTransport = new ArrayList<>();
    	for (Family family : families) {
    		if (family.driveTo.equals(destination) && familiesToTransport.size() < capacity) {
    			familiesToTransport.add(family);
    		}
    	}
    	return familiesToTransport;
    }
    

    private static void processFamilies(List<Plane> planes) {
        synchronized (planes) {
            for (Plane plane : planes) {
                for (Family family : plane.families) {
                    System.out.println("Processing Family " + family.name + " from Plane " + plane.id);
                    try {
                        Thread.sleep(500); // Simulating processing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Family " + family.name + " from Plane " + plane.id + " has been processed.");
                }
            }
        }
    }
}