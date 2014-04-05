package src.thomas;

import src.Intersection;

public class PathEdge {
	Intersection begin;
	Intersection end;
	int timeOfPassage;
	VehicleState vehicle;
	PathEdge parent=null;
}
