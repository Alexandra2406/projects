package rectanglesMVC;

public class View {

    //  1 - no intersection
	//  2 - point
	//  3 - vertical line
	//  4 - horizontal line
	//  5 - rectangle
	static void show(String[][] type)
	{
		for(int i = 0; i < type.length; i++) {
			for(int j = i + 1; j < type.length; j++) {
				System.out.print("Rectangles  " + (i+1) + "  " + (j+1) + "  : " + type[i][j] + "\n");
				}
			}
		}
	}
