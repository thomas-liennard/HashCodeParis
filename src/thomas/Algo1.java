package src.thomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.GraphData;
import src.Street;

public class Algo1 {
	GraphState graphState;
	GraphData graph;
	Random rndgen = new Random();
	
	public Algo1(GraphData _g)
	{
		graph=_g;
		graphState=new GraphState();
		graphState.graph=graph;
		graphState.init();
	}
	
	public void run()
	{		
		while(iterate())
		{
		}
		graphState.writeToFile("algo1.txt");
	};
	
	boolean iterate()
	{
		int minTime=100000000;
		VehicleState minV=null;
		//select the vehicle with the least time
		for(VehicleState v:graphState.vehiclesState)
		{
			if(v.currentTime<minTime)
			{
				minTime=v.currentTime;
				minV=v;
			}
		}
		System.out.println("time:"+minTime);
		//build list of unused edges
		List<Street> streetsFrom=new ArrayList<Street>();
		for(Street s:minV.currentPosition.getStreetsFrom())
		{
			if(!graphState.visitedStreetsInfo.containsKey(s)) streetsFrom.add(s);
		}
		//take one street randomly if stuck
		if(streetsFrom.size()==0) 
		{
			streetsFrom=minV.currentPosition.getStreetsFrom();
			while(streetsFrom.size()>0)
			{
				Street foo=streetsFrom.get(rndgen.nextInt(streetsFrom.size()));
				//select one randomly, if not good with time remaining test another
				if(foo.getCost()+minV.currentTime<=graph.getNbOfSeconds())
				{
					graphState.moveVehicle(minV, foo);
					return true;
				}else
				{
					streetsFrom.remove(foo);
				}
			}
			return false;
		}
		Street minS=null;
		int minCosto=1000000;
		for(Street s:streetsFrom)
		{
			if(s.getCost()/s.getLength()<minCosto)
			{
				minCosto=s.getCost()/s.getLength();
				minS=s;
			}
		}
		System.out.println("foo");
		graphState.moveVehicle(minV,minS);
		return true;
		
	}
	

}
