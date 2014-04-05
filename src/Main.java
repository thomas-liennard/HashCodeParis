package src;

import java.io.IOException;
import java.util.List;

import src.thomas.Algo1;

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
		//LessDumbRandomSolution sol=new LessDumbRandomSolution();
		//sol.graph=data;
		//sol.random();
		Algo1 algo=new Algo1(data);
		algo.run();
	}

}
