package src.thomas;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		vehiclesState=new ArrayList<VehicleState>(8);
		for(int i=0;i<8;i++)
		{
			VehicleState v=new VehicleState();
			v.currentPosition=start;
			v.currentTime=0;
			v.path=new ArrayList<PathEdge>();
			vehiclesState.add(v);
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
		
		return true;
	}
	
	//call djiksttra to help us get back fast to the unexplored wilderness
	Street djikstraWillSaveUs(Intersection start)
	{				
		Set<Intersection> exploredSet=new HashSet<Intersection>();
		Set<Intersection> openSet=new HashSet<Intersection>();
		class DjikstraLance
		{
			Set<Intersection> exploredSet=new HashSet<Intersection>();
			Set<DjikstraVertex> openSet=new HashSet<DjikstraVertex>();
			DjikstraVertex finalVertex=null;
			
			class DjikstraVertex
			{
				DjikstraVertex parent;
				Street backEdge;
				Intersection intersection;
				int cost;
			}
			
			
			void init(Intersection start)
			{
				DjikstraVertex v=new DjikstraVertex();
				v.parent=null;
				v.backEdge=null;
				v.intersection=start;
				v.cost=0;
				exploredSet.add(start);
				extendOpenSet(v);
				
			}
			
			void extendOpenSet(DjikstraVertex v)
			{
				exploredSet.add(v.intersection);
				openSet.remove(v);
				for(Street s:v.intersection.getStreetsFrom())
				{
					
					Intersection d=null;
					if(v.intersection==s.getBegin()){
						d=s.getEnd();
					} else
					{
						d=s.getBegin();
					}
					if(!exploredSet.contains(d))
					{
						DjikstraVertex vv=new DjikstraVertex();
						vv.parent=v;
						vv.intersection=d;
						vv.cost=v.cost+s.getCost();
						vv.backEdge=s;
						openSet.add(vv);
						//if we hit 
						if(!visitedStreetsInfo.containsKey(s))
						{
							finalVertex=vv;													
						}
					}					
				}				
			}
			
			Street saveUs()
			{
				//find the minimal cost vertex in the open set.
				while(finalVertex==null)
				{
					int minCost=10000;
					DjikstraVertex minVr=null;
					for(DjikstraVertex vx : openSet)
					{
						if(vx.cost<minCost)
						{
							minCost=vx.cost;
							minVr=vx;
						}
					}
					extendOpenSet(minVr);					
				}
				//backtrack
				DjikstraVertex vvv=finalVertex;
				Street backEdge=null;
				while(vvv.parent!=null)
				{
					backEdge=vvv.backEdge;
					vvv=vvv.parent;
				}
				return backEdge;
				
			}
		}
		
		DjikstraLance lance=new DjikstraLance();
		lance.init(start);
		return lance.saveUs();
		

		
		
		
	}
	void writeToFile(String fn)
	{
		PrintWriter writer=null;
		try {
			writer = new PrintWriter(fn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.println(vehiclesState.size());
		for(VehicleState vs : vehiclesState)
		{
			writer.println(vs.path.size()+1);
			for(PathEdge e:vs.path)
			{
				writer.println(e.begin.getId());
				
			}
			writer.println(vs.currentPosition.getId());
		}
		writer.close();
		
	}


}
