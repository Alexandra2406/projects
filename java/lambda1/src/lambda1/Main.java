package lambda1;

import java.util.function.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1
		Function<Integer[], Object[]> f1 = (tVal)-> {
			List<Integer> result1 = new ArrayList<>();
			List<Integer> result2 = new ArrayList<>();
			for(Integer temp : tVal) {
				if(temp > 0) {
					result1.add(temp);
				}else {
					result2.add(temp);
				}
			}
			return new Object[] {result1, result2};
		};
	
		Function<Integer[], Object[]> f2 = (tVal)-> {
			List<Integer> result1 = new ArrayList<>();
			List<Integer> result2 = new ArrayList<>();
			for(Integer temp : tVal) {
				if(temp % 2 == 0) {
					result1.add(temp);
				}else {
					result2.add(temp);
				}
			}
			return new Object[] {result1, result2};
		};

		BiFunction<int[], int[], List<Integer>> bf1 = (tVal, Uval)-> {
			List<Integer> result = new ArrayList<>();
			double a = Arrays.stream(tVal).sum()/tVal.length,
				   b = Arrays.stream(Uval).sum()/Uval.length;
			for(Integer temp : tVal) {
				if((temp >= a && temp <= b) || (temp <= a && temp >= b)) {
					result.add(temp);
				}
			}
			for(Integer temp : Uval) {
				if((temp >= a && temp <= b) || (temp <= a && temp >= b)) {
					result.add(temp);
				}
			}
			return result;
		};
		
		//2
		BiFunction<List<Car[]>, Integer, Object[]> bf2 = (tVal, Uval) -> {
			List<Car> c1 = new ArrayList<>(), c2 = new ArrayList<>();
			for(Car[] temp : tVal) {
				for(Car t : temp) {
					if(t.maxSpeed < Uval) {
						c1.add(t);
					}else {
						c2.add(t);
					}					
				}
			}
			return new Object[] {c1, c2};
		};		
	}
}
class Car{
	int maxSpeed;
	Car(int maxSpeed){
		this.maxSpeed = maxSpeed;
	}
}
