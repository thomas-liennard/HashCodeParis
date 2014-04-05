package src.thomas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.GraphData;
import src.Intersection;
import src.Street;
import src.thomas.VehicleState;

public class GraphState {
	GraphData graph;
	HashMap<Street,StreetInfo> visitedStreetsInfo = new HashMap<Street,StreetInfo>(); 
	List<VehicleState> vehiclesState=new ArrayList<VehicleState>();

	
	void init()
	{
		List<Intersection> vertices=graph.getAllIntersections();
		Intersection start=vertices.get(graph.getStartingPoint());
		
		visitedStreetsInfo = new HashMap<Street,StreetInfo>(); 
		vehiclesState=new ArrayList<VehicleState>();
		for(VehicleState v:vehiclesState)
		{
			v.currentPosition=start;
			v.currentTime=0;
			v.path=new ArrayList<PathEdge>();
		}
		
	}
	boolean canMoveVehicle(	VehicleState v,Street s)
	{
		//not possible if not enough time
		if(v.currentTime+s.getCost()>graph.getNbOfSeconds()) return false;
		return true;
	}
	//returns false if not enough time
	boolean moveVehicle(VehicleState v,Street s)
	{
		//not possible if not enough time
		if(v.currentTime+s.getCost()>graph.getNbOfSeconds()) return false;
		//update vehicle position and path
		PathEdge edge=new PathEdge();
		edge.begin=v.currentPosition;
		if(s.getBegin()==v.currentPosition)
		{			
			v.currentPosition=s.getEnd();
			
		} else if(s.getEnd()==v.currentPosition && !s.isOneWay())
		{
			v.currentPosition=s.getBegin();
		} else
		{
			//should not happen
			assert(false);
		}
		edge.end=v.currentPosition;
		edge.timeOfPassage=v.currentTime;
		edge.vehicle=v;
		if(v.path.size()>0) edge.parent=v.path.get(v.path.size()-1);
		v.path.add(edge);
	
		//update vehicle time		
		v.currentTime+=s.getCost();
		
		//update visited streets information
		StreetInfo sinfo=null;
		if(visitedStreetsInfo.containsKey(s))
		{
			sinfo=visitedStreetsInfo.get(s);
		} else
		{
			sinfo = new StreetInfo();
			sinfo.street=s;
			visitedStreetsInfo.put(s, sinfo);
		}
		
		return false;
	}


}
