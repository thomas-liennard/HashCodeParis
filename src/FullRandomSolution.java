package src;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FullRandomSolution {
	GraphData graph;
	
	void random()
	{
		List<Intersection> vertices=graph.getAllIntersections();
		Intersection start=vertices.get(graph.getStartingPoint());
		List<List<Intersection>> solution=new ArrayList<List<Intersection>>();
		Random rndgen = new Random();
		PrintWriter writer=null;
		try {
			writer = new PrintWriter("foo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.println(8);
		for(int v=0;v<8;v++)
		{
			Intersection currentPosition=start;
			List<Intersection> path=new ArrayList<Intersection>();
			path.add(currentPosition);
			int time=0;
			while(true)
			{
				List<Street> streetsFrom=currentPosition.getStreetsFrom();
				Street foo=streetsFrom.get(rndgen.nextInt(streetsFrom.size()));
				time+=foo.getCost();
				if(time>graph.getNbOfSeconds()) break;
				if(currentPosition==foo.getBegin())
				{
					currentPosition=foo.getEnd();
				} else
				{
					currentPosition=foo.getBegin();
				}
				path.add(currentPosition);
			}
			solution.add(path);
			writer.println(path.size());
			for(Intersection vv : path)
			{
				writer.println(vv.getId());
			}
		}
		writer.close();
	}

}
