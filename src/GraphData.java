package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;



/**
 * loading data from file
 * @author mari
 *
 */
public class GraphData {
	
	private int nbIntersections;
	private int nbOfStreets;
	private int nbOfCars;
	private int nbOfSeconds;
	private int startingPoint;

	private List<Intersection> allIntersections = new LinkedList<Intersection>();
	private List<Street> allStreets = new LinkedList<Street>();

	public int getNbIntersections() {
		return nbIntersections;
	}

	public void setNbIntersections(int nbIntersections) {
		this.nbIntersections = nbIntersections;
	}

	public int getNbOfStreets() {
		return nbOfStreets;
	}

	public void setNbOfStreets(int nbOfStreets) {
		this.nbOfStreets = nbOfStreets;
	}

	public int getNbOfCars() {
		return nbOfCars;
	}

	public void setNbOfCars(int nbOfCars) {
		this.nbOfCars = nbOfCars;
	}

	public int getNbOfSeconds() {
		return nbOfSeconds;
	}

	public void setNbOfSeconds(int nbOfSeconds) {
		this.nbOfSeconds = nbOfSeconds;
	}

	public int getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(int startingPoint) {
		this.startingPoint = startingPoint;
	}

	public List<Intersection> getAllIntersections() {
		return allIntersections;
	}

	public void setAllIntersections(List<Intersection> allIntersections) {
		this.allIntersections = allIntersections;
	}

	public List<Street> getAllStreets() {
		return allStreets;
	}

	public void setAllStreets(List<Street> allStreets) {
		this.allStreets = allStreets;
	}

	private void loadGraphData(String path) throws IOException {
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
		nbOfSeconds = Integer.valueOf(initialData[2]);
		nbOfCars = Integer.valueOf(initialData[3]);
		startingPoint = Integer.valueOf(initialData[4]);

		System.out.println("nbIntersections= " + nbIntersections
				+ " , nbOfStreets = " + nbOfStreets + ", nbOfCars=" + nbOfCars
				+ ", nbOfSeconds=" + nbOfSeconds + ", startingPoint="
				+ startingPoint);

		int id = 1;
		try {
			while ((strLine = br.readLine()) != null) {
				if (strLine.trim().length() != 0) {
					String[] lineData = strLine.split(" ");
					if (lineData.length == 2) {
						Intersection i = new Intersection(id,
								Double.valueOf(lineData[0]),
								Double.valueOf(lineData[1]));
						allIntersections.add(i);
						id++;
					} else {
						if (lineData.length == 5) {
							int idxIntersectionBegin = Integer
									.valueOf(lineData[0]);
							int idxIntersectionEnd = Integer
									.valueOf(lineData[1]);
							int waysVal = Integer.valueOf(lineData[2]);
							boolean oneway = waysVal == 1 ? true : false;
							
							Intersection ibegin = allIntersections.get(idxIntersectionBegin); 
							Intersection iend = allIntersections.get(idxIntersectionEnd); 
							
							Street s = new Street(ibegin,iend, 
									oneway, Integer.valueOf(lineData[3]),
									Integer.valueOf(lineData[4]));
							
							allStreets.add(s);
							
							ibegin.addToStreetsTo(s); 
							iend.addToStreetsFrom(s); 
						
							if(oneway==false) {
								iend.addToStreetsTo(s); 
								ibegin.addToStreetsFrom(s); 
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		br.close();
	}

	// fast test
	public static void main(String[] args) {
		GraphData data = new GraphData();
		try {
			data.loadGraphData("/home/mari/Desktop/HASHLOFT/paris_54000.txt");
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
