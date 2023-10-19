package t2;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.io.*;
import java.time.*;

public class Test {
	public static void main(String[] args) throws IOException {
		
        List<Integer> n = new ArrayList<>();
        BiFunction<Integer, Integer, Integer> productFunction = (x, y) -> x * y;
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, 12);
        c.set(Calendar.DAY_OF_MONTH, 8);
        System.out.print(c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.MONTH)+ " "+ c.get(Calendar.YEAR));
        LocalDateTime dt = LocalDateTime.of(2017, 11, 26, 15, 38);
        Period p = Period.ofYears(1).ofMonths(2).ofDays(3);
        dt = dt.minus(p);
        System.out.println();
        System.out.print(dt);
        System.out.println();
        
        String t = "abc";
        byte[] data = t.getBytes();
        OutputStream o = new FileOutputStream("data.txt");
        for(int i=0; i<3; i++) {
        	o.write(data);
        }
        o.close();
        
        int sum = Arrays.stream(new int[] {1,2,3,4})
        		.limit(3).filter(i -> i>2).map(i -> i*2).sum();
        System.out.println(sum);
        
        List<List<String>> table = new ArrayList<>();
        table.add(Arrays.asList("1", "2", "3"));
        table.add(Arrays.asList("4", "5"));
        table.stream().flatMap(Collection::stream).skip(1).forEach(System.out::print);
        System.out.println();
        Stream.of(2,3,0,1).map(num -> IntStream.range(0, num)).forEach(stream -> stream.forEach(System.out :: print));
        System.out.println();
        DoubleStream.of((int)0, 1)
        .boxed().map(Object::getClass)
        .forEach(  System.out::print);
        System.out.println();
        Stream.concat(DoubleStream.of(1).boxed(),IntStream.of(2).boxed()).map(Object::getClass).forEach(  System.out::print);
        System.out.println();
        Stream.of(Stream.of(1,2,3,4,5).sorted(Comparator.reverseOrder()).limit(4).reduce(0,(acc,x) -> acc + x), 0).forEach(  System.out::print);
        System.out.println();
        System.out.println(
        		Stream.of(3,2,1).peek(x ->   System.out.print(x)).reduce((acc,  x) -> acc+x).orElse(0));
        
        Random r = new Random();
        System.out.println(
        		Stream.generate(()-> r.nextInt(7)).limit(4).max(Comparator.naturalOrder()).orElse(6));
	}
}
