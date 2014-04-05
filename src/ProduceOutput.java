package src;

import java.util.List;

public class ProduceOutput {
	
	List<VehicleState> vstates; 
	
	public ProduceOutput(List<VehicleState> vs) {
		vstates = vs; 
	}
	
	public String printStates() {
		StringBuffer tmp = new StringBuffer(); 
		
		tmp.append(vstates.size() + "\n"); 
		
		for(VehicleState vs : vstates) {
			List<Intersection> allInRoad = vs.getRoad(); 
			tmp.append(allInRoad.size() + "\n"); 
			for(Intersection intersection : allInRoad) {
				tmp.append(intersection.getId() + "\n"); 
			}
		}
		
		return tmp.toString(); 
	}	
}
