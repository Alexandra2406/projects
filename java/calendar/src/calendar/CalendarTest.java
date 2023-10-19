package calendar;

import java.text.*;
import java.time.*;
import java.util.Date;
import java.util.Scanner;

public class CalendarTest
{
	public static void main(String[] args)
	{
		System.out.println("Enter date 'YYYY-MM'");
		Scanner sc = new Scanner(System.in);
		String[] lD = sc.nextLine().split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(lD[0]), Integer.parseInt(lD[1]), 1);
		int month = date.getMonthValue();
		int today = date.getDayOfMonth();
		date = date.minusDays(today - 1);
		DayOfWeek weekday = date.getDayOfWeek();
		int value = weekday.getValue();
		System.out.println("1 - Eng \n2 - Ukr");
		int e = sc.nextInt();
		sc.close();
		if(e == 1)
			System.out.println("Mon Tue Wed Thu Fri Sat Sun");
		else if(e == 2)
			System.out.println(" Пн  Вт  Ср  Чт  Пт  Сб  Нд");
		for (int i = 1; i < value; i++)
			System.out.print("    ");
		while (date.getMonthValue() == month)
		{
			System.out.printf("%3d", date.getDayOfMonth());
			if (date.getDayOfMonth() == LocalDateTime.now().getDayOfMonth() && date.getMonth() == LocalDateTime.now().getMonth())
				System.out.print("*");
			else
				System.out.print(" ");
			date = date.plusDays(1);
			if (date.getDayOfWeek ().getValue() == 1)
				System.out.println();
		}
			if (date.getDayOfWeek().getValue() != 1)
				System.out.println();
	}
}
