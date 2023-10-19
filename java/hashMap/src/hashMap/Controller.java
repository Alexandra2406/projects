package hashMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Controller {
	static void crossing(LinkedList<Point> points, File lines) {
		HashMap<List<Point>, Integer> hm1 = new HashMap<>();
		HashMap<Line, Integer> hm2 = new HashMap<>();
		Iterator <Point> pIter = points.iterator(), pTempI = points.iterator(), tempP = points.iterator();
		//LinkedList<Point> tempPL = new LinkedList<>();;
		int countL, countP, tempC;
		Point tPoint1, tPoint2, p;
		Line tLine;
		List<Point> key = new ArrayList<>();
		
		while(pIter.hasNext()) {
			countL = 0;
			tPoint1 = pIter.next();
			while(pTempI.hasNext()) {
				tPoint2 = pTempI.next();
				tLine = tPoint1.line(tPoint2);
				countP = 0; tempC = 0;
				while(tempP.hasNext()) {
					p = tempP.next();
					if(tLine.belongs(p)) {
						key.add(p);
						countP++;
					}
				}
				tempP = points.iterator();
				hm2.put(tLine, countP);
				if(tempC < countP)
					countL++;
				tempC = countP;
			}
			hm1.put(key, countL - 1);
			key.clear();
			pTempI = points.iterator();
			tempP = points.iterator();
		}
        BufferedWriter bf = null;  
        try {
            bf = new BufferedWriter(new FileWriter(lines));
            for(Map.Entry<List<Point>, Integer> entry : hm1.entrySet()){
                
                //put key and value separated by a colon
                bf.write( entry.getKey() + ":" + entry.getValue() );
                
                //new line
                bf.newLine();
            }
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }
}
