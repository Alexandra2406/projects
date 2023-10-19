package loops;

public class Loops1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		highRight();
		
	}
	public static void task11A(int randomNumber) 
	{
		for(int i = 1; i < 10; i++)
		{ 
			int number = Integer.parseInt(i+"35"+i);
			if(number % randomNumber == 0)
	        {
	    	    System.out.print(number + " ");
	        }
	    }  
	}
	public static void task11B(int randomNumber)
	{
		
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = 1; j < 10; j++)
			{
				int number = Integer.parseInt(i+"35"+j);
				if(number % randomNumber == 0)
				{
					System.out.print(number + " ");
				}
			} 
			System.out.println();
	    }  
	}
	public static void task12()
	{
		int number;
		for (int a = 0; a < 10; a++)
		{ 
			for (int b = 0; b < 10; b++)
			{
				for (int c = 0; c < 10; c++)
				{
					number = Integer.parseInt("92" + a + b + c);
					if (number % 874 == 0)
					{
						System.out.print(number / 874 + " ");
					}
				}
			}
		}
	}
	public static void task13()
	{		
		int number, counter = 0;
		for (int a = 0; a < 10; a++)
		{ 
			for (int b = 0; b < 10; b++)
			{
				number = a * a + b * b;
				if(number > 200)
				{
					break;
				}
				else if(number % 49 == 0)
				{
					counter++;				
				}
			}
		}
		System.out.print(counter);
	}
	public static void task14()
	{
		double number, max = 0;
		for (int a = 0; a < 10; a++)
		{ 
			for (int b = 0; b < 10; b++)
			{
				for (int c = 0; c < 10; c++)
				{
					number = a*100 + b*10 + c - Math.pow(a, 3) - Math.pow(b, 3) - Math.pow(c, 3);
					if (max < number)
					{
						max = number;						
					}
				}
			}
		}
		System.out.print(max);
	}
	public static void task15()
	{
		int number = 0;
		for (int a = 1; a < 10; a++)
		{ 
			for (int b = 0; b < 10; b++)
			{
				for (int c = 1; c < 10; c++)
				{
					number++;
				}
			}
		}
		System.out.print(number);
	}
	
	public static void task2Triangle()
	{
		highLeft();
		System.out.println();
		downLeft();
		System.out.println();
		downRight();
		System.out.println();
		highRight();
		System.out.println();
		rectangle();
		System.out.println();
		
	}
	
	public static void highLeft()
	{
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = i; j < 10; j++)
			{
			 System.out.print(". ");
			}
			System.out.println();
		}
	}
	public static void downLeft()
	{
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = 1; j < i + 1; j++)
			{
			 System.out.print(". ");
			}
			System.out.println();
		}
	}
	public static void downRight()
	{
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = 1; j < 10; j++)
			{
				if(i >= 10-j) 
				{
					System.out.print(". ");
				} 
				else
				{
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
	public static void highRight()
	{
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = 1; j < 10; j++)
			{
				if (j >= i ) 
				{
                    System.out.print(". ");
                } 
				else 
				{
                    System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
	public static void rectangle()
	{
		for (int i = 1; i < 10; i++)
		{ 
			for (int j = 1; j < 10; j++)
			{
				System.out.print(". ");                
			}
			System.out.println();
		}
	}

	public static void task3()
	{
		downLeft();
		highLeft();
		System.out.println();
		downLeft();
		highRight();
		System.out.println();
		downRight();
		highRight();
		System.out.println();
		downRight();
		highLeft();
		System.out.println();
		downRight();
		rectangle();
		highRight();
		System.out.println();
		downRight();
		rectangle();
		highLeft();
		System.out.println();
	}
	public static void task4()
	{
		horizontalTriangle(10,5,".");
		System.out.println();
		trapezoid(10);
	}
	public static void horizontalTriangle(int m, int shift, String symbol) {
        for (int i = 1; i < m; i++) 
        {
            for (int s = 0; s < shift; s++) 
            {
                System.out.print("  ");
            }
            for (int j = 1; j < m; j++) 
            {
                if (i >= m - j) 
                {
                    System.out.print(symbol + " ");
                }
                else 
                {
                    System.out.print("  ");
                }
            }
            for (int j = 1; j < i + 1; j++)
			{
   			 System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
	public static void trapezoid(int m) {
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                if (i > m - j) {
                    System.out.print(". ");
                } else {
                    System.out.print("  ");
                }
            }
            for (int j = 1; j < m; j++) {
                System.out.print(". ");
            }
            for (int j = 1; j < i+1; j++) 
            {
                 System.out.print(". ");                
            }
            System.out.println();
        }
    }
	public static void task5Tree(int high, int width, int shift, String symbol)
	{
		for(int i = 0; i < high; i++)
		{
			horizontalTriangle(width / 2, shift, symbol);
		}
	}
}


