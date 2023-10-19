package test;

import java.time.LocalDate;
import java.util.function.Predicate;

interface Moveable {
	 int move(int distance);
	}
	class Person {
	 static int MIN_DISTANCE = 5;
	 int age;
	 float height;
	 boolean result;
	 String name;
	}
	public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(1);
		synchronized(args){
			System.out.print(2);
			try {
				args.wait();
			}catch(InterruptedException e) {}
		}
		System.out.print(3);
		int num[] = {10, 15, 2, 17};
		int sum = 0;
		for (int number : num) {
			if (number % 2 == 0)
				 continue;
		 sum += number;
		}
		System.out.print(sum);
	}

}