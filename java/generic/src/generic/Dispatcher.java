package generic;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Dog> d = new ArrayList<>();
		d.add(new Dog("d1", 1));
		d.add(new Dog("d2", 2));
		d.add(new Dog("d2", 2));
		d.add(new Dog("d2", 2));
		d.add(new Dog("d3", 3));
		Dog[] dogs = new Dog[] {
				new Dog("d1", 1),
				new Dog("d1", 1),
				new Dog("d2", 2),
				new Dog("d4", 4)
		};		
		Map<Dog, Integer> map = repeats(d, dogs);
		System.out.println(map);
		print(missing(d, dogs));
	}
	public static <T> void print(Collection<T> t) {
		for(T t1 : t) {
			System.out.print(t1 + " ");
		}
		System.out.println();
	}
	public static <T extends Animal> Map<T, Integer> repeats(ArrayList<T> al, T[] t){
		HashMap<T, Integer> map = new HashMap<>();
		Integer counter; Boolean b = false;
		for(T a : al) {
			counter = 0;
			for(T a1 : al) {
				if(a.equals(a1)) {
					counter++;
				}
			}
			for(T t1 : t) {
				if(a.equals(t1)) {
					counter++;
					b = true;
				}
			}
			if(b) {
				map.put((T) a, counter);
			}
		}		
		return map;		
	}
	public static <T extends Animal> ArrayList<T> missing(ArrayList<T> al, T[] t){
		Boolean b;
		for(T t1 : t) {
			b = false;
			for(T a : al) {
				if(t1.equals(a)) {
					b = true;
				}
			}
			if(!b) {
				al.add(t1);
			}
		}		
		return al;		
	}
}
class Animal {
	String name;
	Animal(String name){
		this.name = name;
	}
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((Animal)o).name);
	}
	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public int hashCode() {
		return this.name.hashCode() ;
	}
}
class Dog extends Animal {
	Double speed;
	Dog(String name, double speed) {
		super(name);
		// TODO Auto-generated constructor stub
		this.speed = speed;
	}
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	@Override
	public int hashCode() {
		return super.hashCode() + 19 *  this.speed.intValue();
	}
	@Override
	public String toString() {
		return this.name + " " + this.speed;
	}
}
class Poodle extends Dog{

	Poodle(String name, double speed) {
		super(name, speed);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object o) {
		return super.equals(o) && this.speed.equals(((Dog)o).speed);
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public String toString() {
		return this.name + " " + this.speed;
	}
}