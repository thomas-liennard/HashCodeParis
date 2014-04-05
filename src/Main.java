package src;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		GraphData data = new GraphData();
		try {
			data.loadGraphData("paris_54000.txt");
			System.out.println("nbIntersections = "
					+ data.getAllIntersections().size() + " ==? "
					+ data.getNbIntersections());
			
			List<Street> streets = data.getAllStreets(); 
			System.out.println("nbStreets=" + streets.size() + " ==? " + data.getNbOfStreets());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
