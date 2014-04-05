package src;

import java.util.List;

public class VehicleState {
	
	long totalCost; 
	List<Intersection> roadOfIntersections;
	
	
	public long getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(long totalCost) {
		this.totalCost = totalCost;
	}



	public List<Intersection> getRoad() {
		return roadOfIntersections;
	}



	public void setRoad(List<Intersection> road) {
		this.roadOfIntersections = road;
	}


	@Override
	public String toString() {
		return "VehicleState [totalCost=" + totalCost + ", road=" + roadOfIntersections + "]";
	} 
	
	
}
