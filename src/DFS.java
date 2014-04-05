package src;

public class DFS {

	/**
	 * @param args
	 */
	Graph g;
	boolean[] discovered;
	boolean[] processed;
	
	public DFS() {
		g= new Graph();
		discovered = new boolean[200000];
		for (int i=0;i<discovered.length;i++) {
			discovered[i]=false;
			processed[i]=false;
		}
	}
	
	public void dfs(int v) {
		Intersection vertex;
		Intersection y;
		discovered[v]=true;
		vertex = g.allIntersections.get(v);
		
		for (Street edge : vertex.getStreetsFrom()) {
			y = edge.getEnd();
			if ()
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
