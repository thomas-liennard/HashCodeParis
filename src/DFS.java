package src;

public class DFS {

	/**
	 * @param args
	 */
	public static int TIMELIMIT = 54000;
	
	static int length = 0;
	static int cost = 0;
	static boolean finished = false;
	GraphData g;
	
	public DFS() {
		g= new GraphData();
	}
	
	public void dfs(Intersection vertex) {
		Intersection y;
		vertex.discovered=true;
		
		for (Street edge : vertex.getStreetsFrom()) {
			if (finished) break;
			y = edge.getEnd();
			if (y.discovered == false) {
				y.parent = vertex;
				cost += edge.getCost();
				length += edge.getLength();
				
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

	}

}
