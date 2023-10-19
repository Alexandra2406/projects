package rectanglesMVC;

import java.util.ArrayList;

public class Controller {
	//  1 - no intersection
	//  2 - point
	//  3 - vertical line
	//  4 - horizontal line
	//  5 - rectangle

	static String intersectionType(Rectangle r1, Rectangle r2)
	{
		if(r1.xb < r2.xa || r1.yb < r2.ya || r2.yb < r1.ya || r2.xb < r1.xa){
			return "no intersection";
	    }
		else if((r1.xb == r2.xa || r1.xa == r2.xb) && (r1.yb == r2.ya || r1.ya == r2.yb)){
			return "point";
	    }
	    else if((r1.xb == r2.xa || r1.xa == r2.xb) && r2.ya < r1.yb && r2.yb > r1.ya){
	    	return "vertical line";
	    }
	    else if((r1.yb == r2.ya || r1.ya == r2.yb) && r2.xa < r1.xb && r2.xb > r1.xa){
	    	return "horizontal line";
	    }
	    else{
	    	return "rectangle";
	    }
	}
	static String[][] intersection(ArrayList<Rectangle> rl)
	{
		String[][] type = new String[rl.size()][rl.size()];
		for(int i = 0; i < rl.size(); i++) {
			for(int j = i + 1; j < rl.size(); j++) {
				type[i][j] = intersectionType(rl.get(i), rl.get(j));
			}
		}
		return type;
	}
}
