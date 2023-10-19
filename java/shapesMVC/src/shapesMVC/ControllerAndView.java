package shapesMVC;

public class ControllerAndView {

	static void print()
	{
		System.out.print("  ");
	}
	static void print(int n)
	{
		for(int i = 0; i < n; i++)
		{
			System.out.print("  ");
		}
	}
	static void print(int n, String s)
	{
		for(int i = 0; i < n; i++)
		{
			System.out.print(s + " ");
		}
		System.out.println();
	}
	static void heightLeftT(Model t, int shift)
	{		
		int n = 0;
		for (int i = 0; i < t.t.size; i++)
		{ 
			print(shift);
			for (int j = i; j < t.t.size; j++)
			{
			 n++;
			}
			print(n, t.t.symbol);
			n = 0;
		}
	}
	static void downLeftT(Model t, int shift)
	{
		int n = 0;
		for (int i = 0; i < t.t.size; i++)
		{ 
			print(shift);
			for (int j = 0; j < i + 1; j++)
			{
				 n++;
			}
			print(n, t.t.symbol);
			n = 0;
		}
	}
	static void downRightT(Model t, int shift)
	{
		int n = 0;
		for (int i = 0; i < t.t.size; i++)
		{ 
			print(shift);
			for (int j = 0; j < t.t.size; j++)
			{
				if(i >= t.t.size - 1 - j) 
				{
					n++;
				}   
				else
				{
					print();
				}
			}
			print(n, t.t.symbol);
			n = 0;
		}
	}
	static void heightRightT(Model t, int shift)
	{
		int n = 0;
		for (int i = 0; i < t.t.size; i++)
		{ 
			print(shift);
			for (int j = 0; j < t.t.size; j++)
			{
				if (j >= i) 
				{                    
					n++;
				} 
				else
				{
					print();
				}
			}
			print(n, t.t.symbol);
			n = 0;
		}
	}
	static void rectangle(Model r, int shift, int increaze)
	{
		int n = 0;
		for (int i = 0; i < r.r.height + increaze; i++)
		{ 
			print(shift);
			for (int j = 0; j < r.r.width; j++)
			{
				n++;                
			}
			print(n, r.r.symbol);
			n = 0;
		}
	}
	static void trapezoid(Model tz, int shift, int increaseHeight, int increaseWidth) 
	{ 
		int n = 0;
        for (int i = 0; i < tz.tz.height + increaseHeight; i++) {
        	print(shift);
            for (int j = 0; j < tz.tz.height + 1 + increaseHeight; j++) {
                if (i >= tz.tz.height + increaseHeight - j) {
                   n++;
                } else {
                    print();
                }                
            }
            for (int j = 0; j < tz.tz.width + increaseWidth; j++) {
                n++;
            }
            for (int j = 0; j < i+1; j++) 
            {
                 n++;                
            }
            print(n, tz.tz.symbol);
			n = 0;
        }
    }
	static void configuration1(Model m, int shift)
	{
		downLeftT(m, shift);
		rectangle(m, shift, 0);
		heightLeftT(m, shift);
		System.out.println();
	}
	static void configuration2(Model m, int shift)
	{
		downLeftT(m, shift);
		rectangle(m, shift, 0);
		heightRightT(m, shift);
		System.out.println();
	}
	static void configuration3(Model m, int shift)
	{
		downRightT(m, shift);
		rectangle(m, shift, 0);
		heightRightT(m, shift);
		System.out.println();
	}
	static void configuration4(Model m, int shift)
	{
		downRightT(m, shift);
		rectangle(m, shift, 0);
		heightLeftT(m, shift);
		System.out.println();
	}
	static void tree(Model m, int shift, int increase)
	{
		trapezoid(m, shift + 2 * increase, 0, - m.tz.width);
		trapezoid(m, shift + increase , increase, - m.tz.width);
		trapezoid(m, shift , 2 * increase, - m.tz.width);
		rectangle(m, shift + m.tz.height + 2 * increase - m.r.width/2+1, 0);
		System.out.println();
	}
	static void rocket(Model m, int shift, int increase)
	{
		trapezoid(m, shift + 2 * increase, 0, - m.tz.width);
		rectangle(m, shift + 2 * increase + 1, 0);
		trapezoid(m, shift + increase  - m.tz.width / 2 , increase, 0);
		rectangle(m, shift + 2 * increase + 1, m.r.height);
		trapezoid(m, shift - increase + 1, 2 * increase, 0);
		System.out.println();
	}
}
