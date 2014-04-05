package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Graph {
	private int nbIntersections; 
	private int nbOfStreets; 
	private int nbOfCars; 
	private int nbOfSeconds; 
	
	List<Intersection> allIntersections = new LinkedList<Intersection>(); 
	List<Street> allStreets = new LinkedList<Street>(); 
	
	private  void loadGraphData(String path) throws IOException{
	FileInputStream istream = null;

	// read the other file
	istream = new FileInputStream(path);
	DataInputStream in = new DataInputStream(istream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));

	String strLine;

	strLine = br.readLine(); // first line
	String[] initialData = strLine.split(" "); 
	nbIntersections = Integer.valueOf(initialData[0]); 
	nbOfStreets = Integer.valueOf(initialData[1]); 
	nbOfCars = Integer.valueOf(initialData[2]); 
	nbOfSeconds = Integer.valueOf(initialData[3]); 

	System.out.println("nbIntersections= " + nbIntersections + " , nbOfStreets = " + nbOfStreets + ", nbOfCars=" + nbOfCars + ", nbOfSeconds=" + nbOfSeconds);


	//int id = 1;
	try {
		while ((strLine = br.readLine()) != null) {
			if (strLine.trim().length() == 2) {
				String[] intersectionData = strLine.split(" "); 
				Intersection i = new Intersection(Integer.valueOf(intersectionData[2]), Integer.valueOf(intersectionData[0]), Integer.valueOf(intersectionData[1])); 
				allIntersections.add(i); 
			}else{
				if (strLine.trim().length() == 5) {
					String[] streetData = strLine.split(" "); 
					int idxIntersectionBegin = Integer.valueOf(streetData[0]); 
					int idxIntersectionEnd = Integer.valueOf(streetData[1]); 
					int waysVal = Integer.valueOf(streetData[2]);
					boolean oneway = waysVal ==1?true:false;
					Street s = new Street(allIntersections.get(idxIntersectionBegin), allIntersections.get(idxIntersectionEnd), oneway, 
							Integer.valueOf(streetData[3]), Integer.valueOf(streetData[4])); 
					allStreets.add(s); 
				}
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	br.close();
	}
	
	//fast test
	public static void main(String[] args) {
		Graph data = new Graph(); 
		try {
			data.loadGraphData("/home/mari/Desktop/HASHLOFT/paris_54000.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
