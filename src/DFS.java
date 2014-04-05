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
	static int numbers = 1;
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
		g = new GraphData();
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
			System.out.println("nbStreets=" + streets.size() + " ==? "
					+ g.getNbOfStreets());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean process_edge(Street edge) {
		if (cost + edge.getCost() > TIMELIMIT)
			return false;
		else {
			cost += edge.getCost();
			numbers++;
			if (!edge.visited) {
				length += edge.getLength();
				edge.visited = true;
			}
			try {
				System.out.println(edge.getBegin().getId());
				System.out.println("Cost now is : " + cost);
				if (cost == 20642) {
					System.out.println("debug here");
				}
				bw.write(edge.getBegin().getId() + "");
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}

	int select_edge(Intersection vertex) {
		float maxSpeed = -1;
		int result = -1;
		int minTime = 54000;
		int timeResult = -1;
		for (int i = 0; i < vertex.getStreetsFrom().size(); i++) {
			Street street = vertex.getStreetsFrom().get(i);
			if (!street.visited) {
				if (street.speed > maxSpeed) {
					maxSpeed = street.speed;
					result = i;
				}
			} else {
				if (street.getCost() < minTime) {
					minTime = street.getCost();
					timeResult = i;
				}
			}
		}
		if (result == -1) {
			return timeResult;
		} else {
			return result;
		}

	}

	public void dfs(Intersection vertex) {
		Intersection y;
		vertex.discovered = true;

		int x=select_edge(vertex);
		System.out.println("x=" + x);
		Street edge = vertex.getStreetsFrom().get(x);
		y = edge.getEnd();
		
		if (process_edge(edge))
			dfs(y);
		else
			return;
		/*
		if (y.discovered == false) {
			y.parent = vertex;

			process_edge(edge);

			if (cost < TIMELIMIT)
				dfs(y);
			else {
				length -= edge.getLength();
				finished = true;
			}
		}*/

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DFS d = new DFS();
		Intersection start = d.g.getAllIntersections().get(
				d.g.getStartingPoint());

		Intersection view = d.g.getAllIntersections().get(2420);
		System.out.println("Streets from: ");
		for (Street s : view.getStreetsFrom()) {
			System.out.println(s.getEnd().getId());
		}

		System.out.println("Streets to:");
		for (Street s : view.getStreetsTo())
			System.out.println(s.getBegin().getId());

		d.dfs(start);
		d.closefile();
		System.out.println("Totol time is : " + cost);
		System.out.println("Totol length is : " + length);
		System.out.println("Total number is : " + numbers);
	}

}
