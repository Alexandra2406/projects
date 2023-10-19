package rectangles;

import java.io.IOException;

public class handler {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	int xa1 = 1, ya1 = 5, xb1 = 10, yb1 = 10, xa2 = 8, ya2 = 3, xb2 = 11, yb2 = 5; 
	
	if((yb1 < ya2 && xb1 < xa2)|| ya2 < yb1 || ya1 > yb2 || (yb1 < ya2 && xb2 < xa1))
		System.out.println("no intersection");
	else 
		System.out.println("intersection");
	
	if(ya2 == yb1)
		if(xb1 == xa2 ||  xa1 == xb2)
			System.out.println("dot intersection");
		else 
			System.out.println("no dot intersection");
	else if(ya1 == yb2)
		if(xb2 == xa1 ||  xb1 == xa2)
			System.out.println("dot intersection");
		else 
			System.out.println("no dot intersection");
	else 
		System.out.println("no dot intersection");
	
	if((xb1 == xa2 && ya2 < yb1) || (xb2 == xa1 && yb2 > ya1)) 
		System.out.println("vertical line intersection");
	else 
		System.out.println("no vertical line intersection");
	
	if((ya1 == yb2 && xa2 < xb1) ||(ya2 == yb1 && xa1 > xb2)) 
		System.out.println("horizontal line intersection");
	else 
		System.out.println("no horizontal line intersection");
	
	if(xa2 < xb1 & ya2 < yb1 && ya1 < yb2 && xa1 < xb2)
		System.out.println("rectangle intersection");
	else 
		System.out.println("no rectangle intersection");
	}
}
