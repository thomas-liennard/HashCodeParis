package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DFS {

	/**
	 * @param args
	 */
	public static int TIMELIMIT = 54000;
	
	static int length = 0;
	static int cost = 0;
	static int numbers =1;
	static boolean finished = false;
	GraphData g;
	FileWriter fw;
	BufferedWriter bw;
	
	public void closefile() {
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DFS() {
		g= new GraphData();
		try {
			fw = new FileWriter("output.txt");
			bw = new BufferedWriter(fw);
			bw.write("1");
			bw.newLine();
			bw.write("1");
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			g.loadGraphData("paris_54000.txt");
			System.out.println("nbIntersections = "
					+ g.getAllIntersections().size() + " ==? "
					+ g.getNbIntersections());
			
			List<Street> streets = g.getAllStreets(); 
			System.out.println("nbStreets=" + streets.size() + " ==? " + g.getNbOfStreets());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process_edge(Street edge) {
		cost += edge.getCost();
		numbers ++;
		if (!edge.visited) {
			length += edge.getLength();
			edge.visited = true;
		}
		try {
			bw.write(edge.getBegin().getId()+"");
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Street select_edge(Intersection vertex) {
		if (vertex.getStreetsFrom().size() != 0) {
			
		}
	}
	
	public void dfs(Intersection vertex) {
		Intersection y;
		vertex.discovered=true;
		
		Street edge = select_edge(vertex);
		
		for (Street edge : vertex.getStreetsFrom()) {
			if (finished) break;
			y = edge.getEnd();
			if (y.discovered == false) {
				y.parent = vertex;
				
				process_edge(edge);
				
				if (cost < TIMELIMIT)
					dfs(y);
				else {
					length -= edge.getLength();
					finished = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DFS d = new DFS();
		Intersection start = d.g.getAllIntersections().get(d.g.getStartingPoint());
		
		System.out.println(d.g.getStartingPoint());
		System.out.println("Streets from: ");
		for (Street s : start.getStreetsFrom()) {
			System.out.println(s.getEnd().getId());
		}
		
		System.out.println("Streets to:");
		for (Street s : start.getStreetsTo() ) 
			System.out.println(s.getBegin().getId());
		
		d.dfs(start);
		d.closefile();
		System.out.println("Totol time is : " + cost);
		System.out.println("Totol length is : " + length);
		System.out.println("Total number is : " + numbers);
	}

}
